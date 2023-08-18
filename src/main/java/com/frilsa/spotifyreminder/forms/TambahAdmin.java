package com.frilsa.spotifyreminder.forms;

import com.frilsa.spotifyreminder.configurations.SQLConnector;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TambahAdmin extends javax.swing.JFrame {

    private SQLConnector database = SQLConnector.getInstance();

    String idAdmin;

    public TambahAdmin() {
        initComponents();
        this.setLocationRelativeTo(this);

        initialize();
//        aktif();
//        kosong();
//        datatable();
//        autonumber();
    }

    private DefaultTableModel tabmode;

    private void initialize() {
        Object[] baris = {"ID", "Username"};
        tabmode = new DefaultTableModel(null, baris);
        tblAdmin.setModel(tabmode);

        tfId.setText(this.autoGenerateCodeAdmin());
        tfUsername.requestFocus();
        searchData();
    }

    private void searchData() {
        String cariItem = tfCari.getText();
        try {
            String sql = "SELECT * FROM admin WHERE "
                    + "kd_admin LIKE '%" + cariItem + "%' OR "
                    + "username LIKE '%" + cariItem + "%' "
                    + "ORDER BY id ASC LIMIT 20";

            PreparedStatement ps = database.getConnection().prepareStatement(sql);
            ResultSet hasil = ps.executeQuery(sql);
            while (hasil.next()) {
                String id = hasil.getString("kd_admin");
                String username = hasil.getString("username");

                String[] data = {id, username};
                tabmode.addRow(data);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "[Code: 001] Terdapat kesalahan pada aplikasi, " + e.getMessage());
        }
    }

    private void clearInput() {
        tfId.setText("");
        tfUsername.setText("");
        pfPassword1.setText("");
        pfPassword2.setText("");
    }

    private String autoGenerateCodeAdmin() {
        int lastId = 0;
        try {
            String sql = "SELECT MAX(id) AS last_id FROM admin";
            PreparedStatement stat = database.getConnection().prepareStatement(sql);
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                lastId = rs.getInt("last_id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "[Code: 002] Terdapat kesalahan pada aplikasi, " + e.getMessage());
        }

//        if (id.length() < 1) {
//            id = "AD0000";
//        }
//        String ur = id.substring(2);
//        int u = Integer.parseInt(ur) + 1;
//        System.out.println(ur + "==" + u);
        String kode;
        if (lastId < 10) {
            kode = "000" + (lastId + 1);
        } else if (lastId < 100) {
            kode = "00" + (lastId + 1);
        } else if (lastId < 1000) {
            kode = "0" + (lastId + 1);
        } else {
            kode = "" + (lastId + 1);
        }

        return "AD" + kode;
    }

    private String getHashedPassword(char[] passwordChar) throws NoSuchAlgorithmException {
        String password = new String(passwordChar);

        MessageDigest md5Digest = MessageDigest.getInstance("MD5");
        byte[] passwordBytes = password.getBytes();
        byte[] hashBytes = md5Digest.digest(passwordBytes);

        StringBuilder hexBuilder = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = String.format("%02x", b);
            hexBuilder.append(hex);
        }

        java.util.Arrays.fill(passwordChar, '0');
        return hexBuilder.toString();
    }

    private void simpan() {
        String username = tfUsername.getText();
        String pass1 = new String(pfPassword1.getPassword());
        String pass2 = new String(pfPassword2.getPassword());

        try {
            if (username == null || username.length() <= 0) {
                tfUsername.requestFocus();
                JOptionPane.showMessageDialog(null, "Usename tidak boleh kosong!");
            } else if (pass1.length() <= 0) {
                pfPassword1.requestFocus();
                JOptionPane.showMessageDialog(null, "Password tidak boleh kosong!");
            } else if (pass2.length() <= 0) {
                pfPassword2.requestFocus();
                JOptionPane.showMessageDialog(null, "Verifikasi Password tidak boleh kosong!");
            } else if (!pass1.equals(pass2)) {
                pfPassword1.requestFocus();
                pfPassword1.setText("");
                pfPassword2.setText("");
                JOptionPane.showMessageDialog(null, "Password Tidak Sesuai!");
            } else {
                if (tfId.getText() != null && tfId.getText().length() > 0 && !tfId.getText().equals(autoGenerateCodeAdmin())) {
                    String sql = "UPDATE admin SET `username` = ?, `password` = ? WHERE `kd_admin` = ?";
                    PreparedStatement ps = database.getConnection().prepareStatement(sql);
                    ps.setString(1, tfUsername.getText());
                    ps.setString(2, this.getHashedPassword(pfPassword1.getPassword()));
                    ps.setString(3, tfId.getText());
                    ps.executeUpdate();
                } else {
                    String sql = "INSERT INTO admin (`kd_admin`, `username`, `password`) VALUES (?,?,?)";
                    PreparedStatement ps = database.getConnection().prepareStatement(sql);
                    ps.setString(1, autoGenerateCodeAdmin());
                    ps.setString(2, tfUsername.getText());
                    ps.setString(3, this.getHashedPassword(pfPassword1.getPassword()));
                    ps.executeUpdate();
                }
                clearInput();
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(null, "Penambahan Admin Gagal! " + e.getMessage());
        }
        initialize();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bKeluar = new javax.swing.JButton();
        inputAdmin = new javax.swing.JPanel();
        lbId = new javax.swing.JLabel();
        lbUsername = new javax.swing.JLabel();
        lbPassword = new javax.swing.JLabel();
        tfId = new javax.swing.JTextField();
        tfUsername = new javax.swing.JTextField();
        pfPassword1 = new javax.swing.JPasswordField();
        pfPassword2 = new javax.swing.JPasswordField();
        bSimpan = new javax.swing.JButton();
        bBatal = new javax.swing.JButton();
        tabel = new javax.swing.JPanel();
        bCari = new javax.swing.JButton();
        tfCari = new javax.swing.JTextField();
        jScrollPaneTblAdmin = new javax.swing.JScrollPane();
        tblAdmin = new javax.swing.JTable();
        bHapus = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Data Admin");

        bKeluar.setText("Keluar");
        bKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bKeluarActionPerformed(evt);
            }
        });

        inputAdmin.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TAMBAH ADMIN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        lbId.setText("ID");

        lbUsername.setText("Username");

        lbPassword.setText("Password");

        tfId.setEditable(false);
        tfId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfIdKeyTyped(evt);
            }
        });

        tfUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfUsernameKeyTyped(evt);
            }
        });

        pfPassword1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pfPassword1KeyTyped(evt);
            }
        });

        pfPassword2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pfPassword2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pfPassword2KeyTyped(evt);
            }
        });

        bSimpan.setText("Simpan");
        bSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSimpanActionPerformed(evt);
            }
        });

        bBatal.setText("Batal");
        bBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBatalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inputAdminLayout = new javax.swing.GroupLayout(inputAdmin);
        inputAdmin.setLayout(inputAdminLayout);
        inputAdminLayout.setHorizontalGroup(
            inputAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inputAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(inputAdminLayout.createSequentialGroup()
                        .addComponent(bSimpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bBatal))
                    .addGroup(inputAdminLayout.createSequentialGroup()
                        .addGroup(inputAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbUsername)
                            .addComponent(lbId)
                            .addComponent(lbPassword))
                        .addGap(18, 18, 18)
                        .addGroup(inputAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pfPassword2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfId)
                            .addComponent(tfUsername)
                            .addComponent(pfPassword1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        inputAdminLayout.setVerticalGroup(
            inputAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inputAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbId)
                    .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(inputAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbUsername)
                    .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(inputAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbPassword)
                    .addComponent(pfPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pfPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(inputAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSimpan)
                    .addComponent(bBatal))
                .addContainerGap())
        );

        tabel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DAFTAR ADMIN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        bCari.setText("Cari");
        bCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCariActionPerformed(evt);
            }
        });

        tfCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfCariKeyPressed(evt);
            }
        });

        tblAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        tblAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAdminMouseClicked(evt);
            }
        });
        jScrollPaneTblAdmin.setViewportView(tblAdmin);

        bHapus.setText("Hapus");
        bHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabelLayout = new javax.swing.GroupLayout(tabel);
        tabel.setLayout(tabelLayout);
        tabelLayout.setHorizontalGroup(
            tabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabelLayout.createSequentialGroup()
                        .addComponent(tfCari, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bCari)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(bHapus))
                    .addComponent(jScrollPaneTblAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        tabelLayout.setVerticalGroup(
            tabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCari)
                    .addComponent(bHapus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneTblAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bKeluar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(inputAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(11, 11, 11))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bKeluar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inputAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanActionPerformed
        simpan();
    }//GEN-LAST:event_bSimpanActionPerformed

    private void bHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusActionPerformed
        String kdAdmin = tfId.getText();
        if (kdAdmin == null || kdAdmin.length() <= 0) {
            JOptionPane.showMessageDialog(null, "Mohon untuk memilih data admin yang ingin dihapus terlebih dahulu!");
        } else {
            try {
                String sqlCheck = "SELECT COUNT(kd_admin) AS jumlah_admin FROM admin";
                PreparedStatement psCheck = database.getConnection().prepareStatement(sqlCheck);
                ResultSet rs = psCheck.executeQuery();
                int jumlahAdmin = 0;
                while (rs.next()) {
                    jumlahAdmin = rs.getInt("jumlah_admin");
                }

                if (jumlahAdmin < 2) {
                    JOptionPane.showMessageDialog(null, "Tidak dapat menghapus, pastikan minimal ada 1 admin dalam data!");
                } else {
                    int ok = JOptionPane.showConfirmDialog(null, "Hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
                    if (ok == 0) {
                        String sql = "DELETE FROM admin WHERE kd_admin = ?";
                        PreparedStatement ps = database.getConnection().prepareStatement(sql);
                        ps.setString(1, kdAdmin);
                        ps.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Admin Berhasil Dihapus");
                        clearInput();
                        initialize();
                        tfId.requestFocus();
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus! " + e.getMessage());
            }
        }
    }//GEN-LAST:event_bHapusActionPerformed

    private void bBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBatalActionPerformed
        clearInput();
//        datatable();
//        autonumber();
    }//GEN-LAST:event_bBatalActionPerformed

    private void tblAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAdminMouseClicked
        int bar = tblAdmin.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        //String c = tabmode.getValueAt(bar, 2).toString();

        tfId.setText(a);
        tfUsername.setText(b);
        //pfPassword1.setText(c);
        //pfPassword2.setText(c);
    }//GEN-LAST:event_tblAdminMouseClicked

    private void bCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCariActionPerformed
        initialize();
    }//GEN-LAST:event_bCariActionPerformed

    private void tfCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            initialize();
        }
    }//GEN-LAST:event_tfCariKeyPressed

    private void tfIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfIdKeyTyped
        char c = evt.getKeyChar();
        if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) || tfId.getText().length() == 12) {
            evt.consume();
        }
    }//GEN-LAST:event_tfIdKeyTyped

    private void tfUsernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfUsernameKeyTyped
        //char c = evt.getKeyChar();
        if (tfUsername.getText().length() == 30) {
            evt.consume();
        }
    }//GEN-LAST:event_tfUsernameKeyTyped

    private void pfPassword2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfPassword2KeyTyped
        if (pfPassword2.getText().length() == 8) {
            evt.consume();
        }
    }//GEN-LAST:event_pfPassword2KeyTyped

    private void pfPassword2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfPassword2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            simpan();
        }
    }//GEN-LAST:event_pfPassword2KeyPressed

    private void pfPassword1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfPassword1KeyTyped
        if (pfPassword1.getText().length() == 8) {
            evt.consume();
        }
    }//GEN-LAST:event_pfPassword1KeyTyped

    private void bKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_bKeluarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TambahAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TambahAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bBatal;
    private javax.swing.JButton bCari;
    private javax.swing.JButton bHapus;
    private javax.swing.JButton bKeluar;
    private javax.swing.JButton bSimpan;
    private javax.swing.JPanel inputAdmin;
    private javax.swing.JScrollPane jScrollPaneTblAdmin;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbPassword;
    private javax.swing.JLabel lbUsername;
    private javax.swing.JPasswordField pfPassword1;
    private javax.swing.JPasswordField pfPassword2;
    private javax.swing.JPanel tabel;
    private javax.swing.JTable tblAdmin;
    private javax.swing.JTextField tfCari;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfUsername;
    // End of variables declaration//GEN-END:variables
}
