package com.frilsa.spotifyreminder.forms;

import com.frilsa.spotifyreminder.configurations.SQLConnector;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;

public class Paket extends javax.swing.JFrame {

    private SQLConnector database = SQLConnector.getInstance();

    private DefaultTableModel tabmode;

//    String kdPaket;

    public Paket() {
        initComponents();
        this.setLocationRelativeTo(this);

        initialize();
        autonumber();
//        aktif();
//        kosong();
    }

    protected void aktif() {
        tfKdPaket.requestFocus();
    }

    protected void kosong() {
        //tfId.setText("");
        tfNmPaket.setText("");
        tfDurasi.setText("");
        tfHarga.setText("");
    }

    protected final void initialize() {
        Object[] Baris = {"Kode Paket", "Paket", "Durasi (Bln)", "Harga (Rp)"};
        tabmode = new DefaultTableModel(null, Baris);
        tblPaket.setModel(tabmode);

        String cariItem = tfCari.getText();
        try {
            String sql = "SELECT * FROM paket WHERE "
                    + "kd_paket LIKE '%" + cariItem + "%' OR "
                    + "nama     LIKE '%" + cariItem + "%' OR "
                    + "durasi   LIKE '%" + cariItem + "%' OR "
                    + "harga   LIKE '%" + cariItem + "%' "
                    + "ORDER BY kd_paket ASC";
            java.sql.Statement stat = database.getConnection().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String kode = hasil.getString("kd_paket");
                String nama = hasil.getString("nama");
                String durasi = hasil.getString("durasi");
                String harga = hasil.getString("harga");

                String[] data = {kode, nama, durasi, harga};
                tabmode.addRow(data);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "[Code: 001] Terdapat kesalahan pada aplikasi, " + e.getMessage());
        }
    }

    protected void autonumber() {
        int id = 0;
        try {
            String sql = "SELECT MAX(ID) AS 'last_id' FROM paket";
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
        String kdPaket = "PK" + ur;
        tfKdPaket.setText(kdPaket);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bKeluar = new javax.swing.JButton();
        inputPelanggan = new javax.swing.JPanel();
        lbKdPaket = new javax.swing.JLabel();
        lbNamaPaket = new javax.swing.JLabel();
        lbDurasi = new javax.swing.JLabel();
        tfKdPaket = new javax.swing.JTextField();
        tfNmPaket = new javax.swing.JTextField();
        tfDurasi = new javax.swing.JTextField();
        bSimpan = new javax.swing.JButton();
        bBatal = new javax.swing.JButton();
        bUbah = new javax.swing.JButton();
        lbHarga = new javax.swing.JLabel();
        tfHarga = new javax.swing.JTextField();
        tabel = new javax.swing.JPanel();
        tfCari = new javax.swing.JTextField();
        bCari = new javax.swing.JButton();
        jScrollPaneTblPaket = new javax.swing.JScrollPane();
        tblPaket = new javax.swing.JTable();
        bHapus = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Data Paket");

        bKeluar.setText("Keluar");
        bKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bKeluarActionPerformed(evt);
            }
        });

        inputPelanggan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TAMBAH PAKET", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        lbKdPaket.setText("Kode Paket");

        lbNamaPaket.setText("Paket");

        lbDurasi.setText("Durasi (Bulan)");

        tfKdPaket.setEditable(false);
        tfKdPaket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfKdPaketKeyTyped(evt);
            }
        });

        tfNmPaket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNmPaketActionPerformed(evt);
            }
        });
        tfNmPaket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfNmPaketKeyTyped(evt);
            }
        });

        tfDurasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDurasiActionPerformed(evt);
            }
        });
        tfDurasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfDurasiKeyTyped(evt);
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

        lbHarga.setText("Harga");

        tfHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfHargaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inputPelangganLayout = new javax.swing.GroupLayout(inputPelanggan);
        inputPelanggan.setLayout(inputPelangganLayout);
        inputPelangganLayout.setHorizontalGroup(
            inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputPelangganLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputPelangganLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bSimpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bUbah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bBatal))
                    .addGroup(inputPelangganLayout.createSequentialGroup()
                        .addGroup(inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNamaPaket)
                            .addComponent(lbKdPaket)
                            .addComponent(lbDurasi)
                            .addComponent(lbHarga))
                        .addGap(18, 18, 18)
                        .addGroup(inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfKdPaket)
                            .addComponent(tfNmPaket)
                            .addComponent(tfDurasi, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(tfHarga))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        inputPelangganLayout.setVerticalGroup(
            inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputPelangganLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbKdPaket)
                    .addComponent(tfKdPaket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNamaPaket)
                    .addComponent(tfNmPaket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDurasi)
                    .addComponent(tfDurasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbHarga)
                    .addComponent(tfHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bBatal)
                    .addComponent(bUbah)
                    .addComponent(bSimpan))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        tabel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DAFTAR PAKET", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

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

        tblPaket.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPaket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPaketMouseClicked(evt);
            }
        });
        jScrollPaneTblPaket.setViewportView(tblPaket);

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
                    .addComponent(jScrollPaneTblPaket, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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
                .addComponent(jScrollPaneTblPaket, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inputPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanActionPerformed
        String sql = "INSERT INTO paket(kd_paket, nama, durasi, harga) VALUES (?,?,?,?)";

        try {
            PreparedStatement stat = database.getConnection().prepareStatement(sql);
            stat.setString(1, tfKdPaket.getText());
            stat.setString(2, tfNmPaket.getText());
            stat.setString(3, tfDurasi.getText());
            stat.setString(4, tfHarga.getText());
            stat.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Paket Baru Telah Ditambahkan");
            kosong();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Penambahan Data Paket Gagal! " + e.getMessage());
        }
        initialize();
        kosong();
        autonumber();
    }//GEN-LAST:event_bSimpanActionPerformed

    private void bHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            String sql = "DELETE FROM paket WHERE kd_paket = ?";
            try {
                PreparedStatement stat = database.getConnection().prepareStatement(sql);
                stat.setString(1, tfKdPaket.getText());
                stat.executeUpdate();
//                JOptionPane.showMessageDialog(null, "Data Paket Berhasil Dihapus");
                kosong();
                tfKdPaket.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Paket Gagal Dihapus!" + e);
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

    private void tblPaketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPaketMouseClicked
        int bar = tblPaket.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();

        tfKdPaket.setText(a);
        tfNmPaket.setText(b);
        tfDurasi.setText(c);
        tfHarga.setText(d);
    }//GEN-LAST:event_tblPaketMouseClicked

    private void bCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCariActionPerformed
        initialize();
    }//GEN-LAST:event_bCariActionPerformed

    private void tfCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            initialize();
        }
    }//GEN-LAST:event_tfCariKeyPressed

    private void tfKdPaketKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKdPaketKeyTyped
        char c = evt.getKeyChar();
        if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) || tfKdPaket.getText().length() == 6) {
            evt.consume();
        }
    }//GEN-LAST:event_tfKdPaketKeyTyped

    private void tfNmPaketKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNmPaketKeyTyped
        char c = evt.getKeyChar();
        if (tfNmPaket.getText().length() == 15) {
            evt.consume();
        }
    }//GEN-LAST:event_tfNmPaketKeyTyped

    private void tfDurasiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDurasiKeyTyped
        char c = evt.getKeyChar();
        if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) || tfDurasi.getText().length() == 2) {
            evt.consume();
        }
    }//GEN-LAST:event_tfDurasiKeyTyped

    private void tfHargaKeyTyped(java.awt.event.KeyEvent evt) {                                  
        char c = evt.getKeyChar();
        if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) || tfHarga.getText().length() == 50) {
            evt.consume();
        }
    } 

    private void bUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahActionPerformed
        String sql = "UPDATE paket SET nama = ?, durasi = ?, harga = ? WHERE kd_paket = ?";
        try {
            PreparedStatement stat = database.getConnection().prepareStatement(sql);
            stat.setString(1, tfNmPaket.getText());
            stat.setString(2, tfDurasi.getText());
            stat.setString(3, tfHarga.getText());
            stat.setString(4, tfKdPaket.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Paket Berhasil Diubah!");
            kosong();
            tfKdPaket.requestFocus();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Paket Gagal Diubah! " + e.getMessage());
        }
        initialize();
        autonumber();
    }//GEN-LAST:event_bUbahActionPerformed

    private void bKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bKeluarActionPerformed
        this.dispose();
    }//GEN-LAST:event_bKeluarActionPerformed

    private void tfDurasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDurasiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDurasiActionPerformed

    private void tfNmPaketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNmPaketActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNmPaketActionPerformed

    private void tfHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfHargaActionPerformed

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
            java.util.logging.Logger.getLogger(Paket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Paket().setVisible(true);
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
    private javax.swing.JScrollPane jScrollPaneTblPaket;
    private javax.swing.JLabel lbDurasi;
    private javax.swing.JLabel lbHarga;
    private javax.swing.JLabel lbKdPaket;
    private javax.swing.JLabel lbNamaPaket;
    private javax.swing.JPanel tabel;
    private javax.swing.JTable tblPaket;
    private javax.swing.JTextField tfCari;
    private javax.swing.JTextField tfDurasi;
    private javax.swing.JTextField tfHarga;
    private javax.swing.JTextField tfKdPaket;
    private javax.swing.JTextField tfNmPaket;
    // End of variables declaration//GEN-END:variables
}
