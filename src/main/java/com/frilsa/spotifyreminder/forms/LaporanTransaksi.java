package com.frilsa.spotifyreminder.forms;

//lib
import com.frilsa.spotifyreminder.configurations.SQLConnector;
import java.sql.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.TreeMap;
import java.util.TreeSet;

public class LaporanTransaksi extends javax.swing.JFrame {

    private SQLConnector database = SQLConnector.getInstance();

    private DefaultTableModel tabmode;

    String kdTransaksi;

    public LaporanTransaksi() {
        initComponents();
        this.setLocationRelativeTo(this);

        datatable();
        setComboBox();
        Locale locale = new Locale ("id","ID");
        Locale.setDefault(locale);
    }

    protected void cleanTable() {
        int baris = tabmode.getRowCount();
        for (int a = 0; a < baris; a++) {
            tabmode.removeRow(0);
        }
    }
    
    protected void setComboBox() {
    String sql = "SELECT * FROM transaksi";

    try {
        java.sql.Statement stat = database.getConnection().createStatement();
        ResultSet rs = stat.executeQuery(sql);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Menggunakan TreeMap untuk menyimpan bulan dan tahun unik dengan urutan yang benar
        TreeMap<Integer, String> uniqueMonths = new TreeMap<>();
        TreeSet<Integer> uniqueYears = new TreeSet<>();

        while (rs.next()) {
            String dateStr = rs.getString("tgl_aktif");
            LocalDate date = LocalDate.parse(dateStr, formatter);

            int month = date.getMonthValue();
            int year = date.getYear();

            // Tambahkan bulan dan tahun jika belum ditambahkan
            uniqueMonths.put(month, date.getMonth().name());
            uniqueYears.add(year);
        }

        for (String monthName : uniqueMonths.values()) {
            cbBulan.addItem(monthName); // contoh: "JANUARY"
        }

        for (Integer year : uniqueYears) {
            cbTahun.addItem(String.valueOf(year)); // contoh: "2023"
        }

        } catch (SQLException e) {
            System.out.println("Data Error: 001 " + e);
        }
    }

    protected void datatable() {
        Object[] Baris = {"Nama", "Order By", "Tgl Aktif", "Tgl Exp", "Paket"};
        tabmode = new DefaultTableModel(null, Baris);
        tblTransaksi.setModel(tabmode);

        String cariItem = tfCari.getText();
        try {
            String sql = "SELECT pe.nama AS nama_pelanggan, "
                    + "so.nama AS order_by, pa.durasi, tr.tgl_aktif, tr.tgl_exp, tr.kd_admin "
                    + "FROM transaksi tr "
                    + "JOIN pelanggan pe ON tr.kd_pelanggan = pe.kd_pelanggan "
                    + "JOIN paket pa ON tr.kd_paket = pa.kd_paket "
                    + "JOIN sumber_order so ON tr.kd_sumber = so.kd_sumber "
                    + "WHERE "
                    + "tr.kd_pelanggan    LIKE '%" + cariItem + "%' OR "
                    + "pe.nama            LIKE '%" + cariItem + "%' "
                    + "ORDER BY tr.kd_transaksi ASC";
            java.sql.Statement stat = database.getConnection().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String nama = hasil.getString("nama_pelanggan");
                String order = hasil.getString("order_by");
                String tglAktif = hasil.getString("tgl_aktif");
                String tglExp = hasil.getString("tgl_exp");
                String durasi = hasil.getString("durasi");

                String[] data = {nama, order, tglAktif, tglExp, durasi};
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
        tblTransaksi = new javax.swing.JTable();
        bHapus = new javax.swing.JButton();
        bKeluar = new javax.swing.JButton();
        bKeluar1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbBulan = new javax.swing.JComboBox<>();
        cbTahun = new javax.swing.JComboBox<>();
        btnCetakBln = new javax.swing.JButton();

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

        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTransaksiMouseClicked(evt);
            }
        });
        jScrollPaneTblPaket.setViewportView(tblTransaksi);

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
                .addComponent(jScrollPaneTblPaket, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addContainerGap())
        );

        bKeluar.setText("Keluar");
        bKeluar.setPreferredSize(new java.awt.Dimension(65, 22));
        bKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bKeluarActionPerformed(evt);
            }
        });

        bKeluar1.setText("Cetak Semua Data");
        bKeluar1.setPreferredSize(new java.awt.Dimension(65, 22));
        bKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bKeluar1ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PER 1 BULAN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel2.setText("Bulan");

        jLabel3.setText("Tahun");

        cbBulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBulanActionPerformed(evt);
            }
        });

        cbTahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTahunActionPerformed(evt);
            }
        });

        btnCetakBln.setText("Cetak");
        btnCetakBln.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakBlnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCetakBln, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(cbTahun, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbBulan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCetakBln)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bKeluar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bKeluar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bKeluar1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabel.getAccessibleContext().setAccessibleName("LAPORAN DATA PAKET");

        getAccessibleContext().setAccessibleName("Laporan Data Transaksi");

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

    private void tblTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTransaksiMouseClicked
        int bar = tblTransaksi.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();

        kdTransaksi = a;
    }//GEN-LAST:event_tblTransaksiMouseClicked

    private void bKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bKeluarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_bKeluarActionPerformed

    private void bHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            String sql = "DELETE FROM paket WHERE kd_Transaksi = '" + kdTransaksi + "'";
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
            String path = getClass().getClassLoader().getResource("templates/LaporanTransaksi.jrxml").getPath();
            JasperReport reports = JasperCompileManager.compileReport(path);

            // 
            HashMap parameter = new HashMap();
            parameter.put("kdTransaksi", kdTransaksi);

            JasperPrint print = JasperFillManager.fillReport(reports, parameter, database.getConnection());
            JasperViewer.viewReport(print, false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Dokumen Tidak Ada " + ex);
        }
    }//GEN-LAST:event_bKeluar1ActionPerformed

    private void cbBulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBulanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbBulanActionPerformed

    private void cbTahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTahunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTahunActionPerformed

    private void btnCetakBlnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakBlnActionPerformed
        try {
            // Ambil bulan dan tahun yang dipilih dari ComboBox
            int selectedMonth = cbBulan.getSelectedIndex() + 1; // Karena indeks dimulai dari 0
            int selectedYear = Integer.parseInt(cbTahun.getSelectedItem().toString());

            // Buat parameter untuk laporan
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("selectedMonth", selectedMonth);
            parameters.put("selectedYear", selectedYear);

            // Path ke template laporan
            String path = getClass().getClassLoader().getResource("templates/LaporanBln.jrxml").getPath();
            JasperReport reports = JasperCompileManager.compileReport(path);

            // Koneksi ke database
            Connection conn = database.getConnection();

            // Eksekusi laporan dengan parameter dan koneksi database
            JasperPrint jprint = JasperFillManager.fillReport(reports, parameters, conn);

            // Tampilkan laporan dengan menggunakan JasperViewer
            JasperViewer.viewReport(jprint, false);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error saat mencetak laporan: " + e);
        }
    }//GEN-LAST:event_btnCetakBlnActionPerformed
                                    

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
            java.util.logging.Logger.getLogger(LaporanTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LaporanTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LaporanTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LaporanTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new LaporanTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCari;
    private javax.swing.JButton bHapus;
    private javax.swing.JButton bKeluar;
    private javax.swing.JButton bKeluar1;
    private javax.swing.JButton btnCetakBln;
    private javax.swing.JComboBox<String> cbBulan;
    private javax.swing.JComboBox<String> cbTahun;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPaneTblPaket;
    private javax.swing.JPanel tabel;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JTextField tfCari;
    // End of variables declaration//GEN-END:variables
}
