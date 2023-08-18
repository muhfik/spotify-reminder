package com.frilsa.spotifyreminder.forms;

import com.frilsa.spotifyreminder.configurations.SQLConnector;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;

public class Pelanggan extends javax.swing.JFrame {

    private SQLConnector database = SQLConnector.getInstance();
    
    private DefaultTableModel tabmode;

    public Pelanggan() {
        initComponents();
        this.setLocationRelativeTo(this);

        initialize();
        autonumber();
//        aktif();
//        kosong();
    }

    protected void aktif() {
        tfIdPelanggan.requestFocus();
    }

    protected void kosong() {
        //tfId.setText("");
        tfNmPelanggan.setText("");
        tfEmailPelanggan.setText("");
    }

    protected final void initialize() {
        Object[] Baris = {"ID", "Nama", "Email"};
        tabmode = new DefaultTableModel(null, Baris);
        tblPelanggan.setModel(tabmode);

        String cariItem = tfCari.getText();
        try {
            String sql = "SELECT * FROM pelanggan WHERE "
                    + "kd_pelanggan LIKE '%" + cariItem + "%' OR "
                    + "nama         LIKE '%" + cariItem + "%' OR "
                    + "email        LIKE '%" + cariItem + "%' "
                    + "ORDER BY kd_pelanggan ASC";
            java.sql.Statement stat = database.getConnection().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String id = hasil.getString("kd_pelanggan");
                String nama = hasil.getString("nama");
                String email = hasil.getString("email");

                String[] data = {id, nama, email};
                tabmode.addRow(data);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "[Code: 001] Terdapat kesalahan pada aplikasi, " + e.getMessage());
        }
    }

    protected void autonumber() {
        int id = 0;
        try {
            String sql = "SELECT MAX(id) AS 'last_id' FROM pelanggan";
            PreparedStatement stat = database.getConnection().prepareStatement(sql);
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt("last_id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "[Code: 001] Terdapat kesalahan pada aplikasi, " + e.getMessage());
        }

        int u = id + 1;
        String ur;
        if (u < 10) {
            ur = "000" + u;
        } else if (u < 100) {
            ur = "00" + u;
        } else if (u < 1000) {
            ur = "0" + u;
        } else {
            ur = "" + u;
        }
        String idPelanggan = "PL" + ur;
        tfIdPelanggan.setText(idPelanggan);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bKeluar = new javax.swing.JButton();
        inputPelanggan = new javax.swing.JPanel();
        lbId = new javax.swing.JLabel();
        lbNama = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        tfIdPelanggan = new javax.swing.JTextField();
        tfNmPelanggan = new javax.swing.JTextField();
        tfEmailPelanggan = new javax.swing.JTextField();
        bSimpan = new javax.swing.JButton();
        bBatal = new javax.swing.JButton();
        bUbah = new javax.swing.JButton();
        tabel = new javax.swing.JPanel();
        tfCari = new javax.swing.JTextField();
        bCari = new javax.swing.JButton();
        jScrollPaneTblPelanggan = new javax.swing.JScrollPane();
        tblPelanggan = new javax.swing.JTable();
        bHapus = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Data Pelanggan");

        bKeluar.setText("Keluar");
        bKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bKeluarActionPerformed(evt);
            }
        });

        inputPelanggan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TAMBAH PELANGGAN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        lbId.setText("ID");

        lbNama.setText("Nama");

        lbEmail.setText("e-Mail");

        tfIdPelanggan.setEditable(false);
        tfIdPelanggan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfIdPelangganKeyTyped(evt);
            }
        });

        tfNmPelanggan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfNmPelangganKeyTyped(evt);
            }
        });

        tfEmailPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfEmailPelangganActionPerformed(evt);
            }
        });
        tfEmailPelanggan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfEmailPelangganKeyTyped(evt);
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

        bUbah.setText("Ubah");
        bUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUbahActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inputPelangganLayout = new javax.swing.GroupLayout(inputPelanggan);
        inputPelanggan.setLayout(inputPelangganLayout);
        inputPelangganLayout.setHorizontalGroup(
            inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputPelangganLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bSimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bUbah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bBatal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(inputPelangganLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNama)
                    .addComponent(lbId)
                    .addComponent(lbEmail))
                .addGap(18, 18, 18)
                .addGroup(inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfIdPelanggan)
                    .addComponent(tfNmPelanggan)
                    .addComponent(tfEmailPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        inputPelangganLayout.setVerticalGroup(
            inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputPelangganLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbId)
                    .addComponent(tfIdPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNama)
                    .addComponent(tfNmPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmail)
                    .addComponent(tfEmailPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSimpan)
                    .addComponent(bBatal)
                    .addComponent(bUbah))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        tabel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DAFTAR PELANGGAN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        tfCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfCariKeyPressed(evt);
            }
        });

        bCari.setText("Cari");
        bCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCariActionPerformed(evt);
            }
        });

        tblPelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        tblPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPelangganMouseClicked(evt);
            }
        });
        jScrollPaneTblPelanggan.setViewportView(tblPelanggan);

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addComponent(bHapus))
                    .addComponent(jScrollPaneTblPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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
                .addComponent(jScrollPaneTblPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(inputPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bKeluar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bKeluar)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inputPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanActionPerformed
        String sql = "INSERT INTO pelanggan(kd_pelanggan, nama, email) VALUES (?,?,?)";

        try {
            PreparedStatement stat = database.getConnection().prepareStatement(sql);
            stat.setString(1, tfIdPelanggan.getText());
            stat.setString(2, tfNmPelanggan.getText());
            stat.setString(3, tfEmailPelanggan.getText());

            stat.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Pelanggan Baru Telah Ditambahkan");
            kosong();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Penambahan Pelanggan Gagal! " + e.getMessage());
        }
        initialize();
        kosong();
        autonumber();
    }//GEN-LAST:event_bSimpanActionPerformed

    private void bHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            String sql = "DELETE FROM pelanggan WHERE kd_pelanggan = '" + tfIdPelanggan.getText() + "'";
            try {
                PreparedStatement stat = database.getConnection().prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Pelanggan Berhasil Dihapus");
                kosong();
                tfIdPelanggan.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Pelanggan Gagal Dihapus!" + e);
            }
            initialize();
            autonumber();
        }
    }//GEN-LAST:event_bHapusActionPerformed

    private void bBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBatalActionPerformed
        kosong();
        initialize();
        autonumber();
    }//GEN-LAST:event_bBatalActionPerformed

    private void tblPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPelangganMouseClicked
        int bar = tblPelanggan.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();

        tfIdPelanggan.setText(a);
        tfNmPelanggan.setText(b);
        tfEmailPelanggan.setText(c);
    }//GEN-LAST:event_tblPelangganMouseClicked

    private void bCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCariActionPerformed
        initialize();
    }//GEN-LAST:event_bCariActionPerformed

    private void tfCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            initialize();
        }
    }//GEN-LAST:event_tfCariKeyPressed

    private void tfIdPelangganKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfIdPelangganKeyTyped
        char c = evt.getKeyChar();
        if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) || tfIdPelanggan.getText().length() == 6) {
            evt.consume();
        }
    }//GEN-LAST:event_tfIdPelangganKeyTyped

    private void tfNmPelangganKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNmPelangganKeyTyped
        char c = evt.getKeyChar();
        if (!((Character.isAlphabetic(c) || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) || tfNmPelanggan.getText().length() == 30) {
            evt.consume();
        }
    }//GEN-LAST:event_tfNmPelangganKeyTyped

    private void tfEmailPelangganKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfEmailPelangganKeyTyped
        char c = evt.getKeyChar();
        if (tfEmailPelanggan.getText().length() == 30) {
            evt.consume();
        }
    }//GEN-LAST:event_tfEmailPelangganKeyTyped

    private void bUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahActionPerformed
        String sql = "UPDATE pelanggan SET nama = ?, email = ? WHERE kd_pelanggan='" + tfIdPelanggan.getText() + "'";
        try {
            PreparedStatement stat = database.getConnection().prepareStatement(sql);
            stat.setString(1, tfNmPelanggan.getText());
            stat.setString(2, tfEmailPelanggan.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Pelanggan Berhasil Diubah!");
            kosong();
            tfIdPelanggan.requestFocus();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Pelanggan Gagal Diubah! " + e.getMessage());
        }
        initialize();
        autonumber();
    }//GEN-LAST:event_bUbahActionPerformed

    private void bKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bKeluarActionPerformed
        this.dispose();
    }//GEN-LAST:event_bKeluarActionPerformed

    private void tfEmailPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfEmailPelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEmailPelangganActionPerformed

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
            java.util.logging.Logger.getLogger(Pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pelanggan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bBatal;
    private javax.swing.JButton bCari;
    private javax.swing.JButton bHapus;
    private javax.swing.JButton bKeluar;
    private javax.swing.JButton bSimpan;
    private javax.swing.JButton bUbah;
    private javax.swing.JPanel inputPelanggan;
    private javax.swing.JScrollPane jScrollPaneTblPelanggan;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbNama;
    private javax.swing.JPanel tabel;
    private javax.swing.JTable tblPelanggan;
    private javax.swing.JTextField tfCari;
    private javax.swing.JTextField tfEmailPelanggan;
    private javax.swing.JTextField tfIdPelanggan;
    private javax.swing.JTextField tfNmPelanggan;
    // End of variables declaration//GEN-END:variables
}
