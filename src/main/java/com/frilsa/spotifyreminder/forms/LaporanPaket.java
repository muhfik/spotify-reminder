package com.frilsa.spotifyreminder.forms;

//lib
import com.frilsa.spotifyreminder.configurations.SQLConnector;
import java.sql.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class LaporanPaket extends javax.swing.JFrame {

    private SQLConnector database = SQLConnector.getInstance();

    private DefaultTableModel tabmode;

    String kdPaket;

    public LaporanPaket() {
        initComponents();
        this.setLocationRelativeTo(this);

        datatable();
        Locale locale = new Locale ("id","ID");
        Locale.setDefault(locale);
    }

    protected void cleanTable() {
        int baris = tabmode.getRowCount();
        for (int a = 0; a < baris; a++) {
            tabmode.removeRow(0);
        }
    }

    protected void datatable() {
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
            System.out.println("Data Error: 001 " + e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabel = new javax.swing.JPanel();
        tfCari = new javax.swing.JTextField();
        bCari = new javax.swing.JButton();
        jScrollPaneTblPaket = new javax.swing.JScrollPane();
        tblPaket = new javax.swing.JTable();
        bHapus = new javax.swing.JButton();
        bKeluar = new javax.swing.JButton();
        bKeluar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Laporan Data Paket");
        setSize(new java.awt.Dimension(700, 500));
        setType(java.awt.Window.Type.UTILITY);

        tabel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LAPORAN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

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
        bHapus.setEnabled(false);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
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
                .addComponent(jScrollPaneTblPaket, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                .addContainerGap())
        );

        bKeluar.setText("Keluar");
        bKeluar.setPreferredSize(new java.awt.Dimension(65, 22));
        bKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bKeluarActionPerformed(evt);
            }
        });

        bKeluar1.setText("Cetak");
        bKeluar1.setPreferredSize(new java.awt.Dimension(65, 22));
        bKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bKeluar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bKeluar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bKeluar1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        tabel.getAccessibleContext().setAccessibleName("LAPORAN DATA PAKET");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            datatable();
        }
    }//GEN-LAST:event_tfCariKeyPressed

    private void bCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCariActionPerformed
        datatable();
    }//GEN-LAST:event_bCariActionPerformed

    private void tblPaketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPaketMouseClicked
        int bar = tblPaket.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();

        kdPaket = a;
    }//GEN-LAST:event_tblPaketMouseClicked

    private void bKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bKeluarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_bKeluarActionPerformed

    private void bHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            String sql = "DELETE FROM paket WHERE kd_paket = '" + kdPaket + "'";
            try {
                PreparedStatement stat = database.getConnection().prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Paket Berhasil Dihapus");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Paket Gagal Dihapus!" + e);
            }
            datatable();
        }
    }//GEN-LAST:event_bHapusActionPerformed

    private void bKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bKeluar1ActionPerformed
        try {
            // Letak Penyimpanan Report
            String path = getClass().getClassLoader().getResource("templates/LaporanPaket.jrxml").getPath();
            JasperReport reports = JasperCompileManager.compileReport(path);

            // 
            HashMap parameter = new HashMap();
            parameter.put("kdPaket", kdPaket);

            JasperPrint print = JasperFillManager.fillReport(reports, parameter, database.getConnection());
            JasperViewer.viewReport(print, false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Dokumen Tidak Ada " + ex);
        }
    }//GEN-LAST:event_bKeluar1ActionPerformed
                                    

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LaporanPaket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LaporanPaket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LaporanPaket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LaporanPaket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LaporanPaket().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCari;
    private javax.swing.JButton bHapus;
    private javax.swing.JButton bKeluar;
    private javax.swing.JButton bKeluar1;
    private javax.swing.JScrollPane jScrollPaneTblPaket;
    private javax.swing.JPanel tabel;
    private javax.swing.JTable tblPaket;
    private javax.swing.JTextField tfCari;
    // End of variables declaration//GEN-END:variables
}
