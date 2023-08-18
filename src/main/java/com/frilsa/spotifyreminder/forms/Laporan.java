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

public class Laporan extends javax.swing.JFrame {

    private SQLConnector database = SQLConnector.getInstance();

    private DefaultTableModel tabmode;

    String kdPesanan;

    public Laporan() {
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
        Object[] Baris = {"Kode Pesanan", "Nama", "Email", "Order By", "Durasi", "Tgl Aktif", "Tgl Exp", "Admin"};
        tabmode = new DefaultTableModel(null, Baris);
        tblPesanan.setModel(tabmode);

        String cariItem = tfCari.getText();
        try {
            String sql = "SELECT tr.kd_transaksi, pe.nama AS nama_pelanggan, pe.email AS email_pelanggan, "
                    + "so.nama AS order_by, pa.durasi, tr.tgl_aktif, tr.tgl_exp, tr.kd_admin "
                    + "FROM transaksi tr "
                    + "JOIN pelanggan pe ON tr.kd_pelanggan = pe.kd_pelanggan "
                    + "JOIN paket pa ON tr.kd_paket = pa.kd_paket "
                    + "JOIN sumber_order so ON tr.kd_sumber = so.kd_sumber "
                    + "WHERE "
                    + "tr.kd_transaksi    LIKE '%" + cariItem + "%' OR "
                    + "tr.kd_pelanggan    LIKE '%" + cariItem + "%' OR "
                    + "pe.nama            LIKE '%" + cariItem + "%' OR "
                    + "pe.email           LIKE '%" + cariItem + "%' OR "
                    + "tr.kd_admin        LIKE '%" + cariItem + "%' "
                    + "ORDER BY tr.kd_transaksi ASC";
            java.sql.Statement stat = database.getConnection().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String kode = hasil.getString("kd_transaksi");
                String nama = hasil.getString("nama_pelanggan");
                String email = hasil.getString("email_pelanggan");
                String order = hasil.getString("order_by");
                String durasi = hasil.getString("durasi");
                String tglAktif = hasil.getString("tgl_aktif");
                String tglExp = hasil.getString("tgl_exp");
                String admin = hasil.getString("kd_admin");

                String[] data = {kode, nama, email, order, durasi, tglAktif, tglExp, admin};
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
        tblPesanan = new javax.swing.JTable();
        bHapus = new javax.swing.JButton();
        bKeluar = new javax.swing.JButton();
        bKeluar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Laporan Data Pelanggan");
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

        tblPesanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        tblPesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPesananMouseClicked(evt);
            }
        });
        jScrollPaneTblPaket.setViewportView(tblPesanan);

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
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
                .addComponent(jScrollPaneTblPaket, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
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
        bKeluar1.setToolTipText("");
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bKeluar1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(bKeluar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

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

    private void tblPesananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPesananMouseClicked
        int bar = tblPesanan.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();

        kdPesanan = a;
    }//GEN-LAST:event_tblPesananMouseClicked

    private void bKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bKeluarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_bKeluarActionPerformed

    private void bHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            String sql = "DELETE FROM transaksi WHERE kd_pesanan = '" + kdPesanan + "'";
            try {
                PreparedStatement stat = database.getConnection().prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Trasaksi Berhasil Dihapus");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Transaksi Gagal Dihapus!" + e);
            }
            datatable();
        }
    }//GEN-LAST:event_bHapusActionPerformed

    private void bKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bKeluar1ActionPerformed
        try {
            // Letak Penyimpanan Report
            String path = getClass().getClassLoader().getResource("templates/Laporan.jrxml").getPath();
            JasperReport reports = JasperCompileManager.compileReport(path);

            // 
            HashMap parameter = new HashMap();
            parameter.put("kdPesanan", kdPesanan);

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
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Laporan().setVisible(true);
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
    private javax.swing.JTable tblPesanan;
    private javax.swing.JTextField tfCari;
    // End of variables declaration//GEN-END:variables
}
