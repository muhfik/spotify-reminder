package com.frilsa.spotifyreminder.forms;

import com.frilsa.spotifyreminder.configurations.SQLConnector;
import com.frilsa.spotifyreminder.models.UserSession;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AdminPanel extends javax.swing.JFrame {

    private static final SQLConnector database = SQLConnector.getInstance();

    String kdPesanan;
    String idPel;
    String namaPel;
    String emailPel;

    private DefaultTableModel tabmode;

    private Map<String, String> dataPaket = new HashMap<>();
    private Map<String, String> dataSumberOrder = new HashMap<>();

    public AdminPanel() {
        initComponents();
        this.setLocationRelativeTo(this);

        initialize();
    }

    private void initialize() {
        lbTanggal.setText("Tanggal:  " + LocalDate.now());
        tfAdmin.setText("Administrator: " + UserSession.getUsername());

        searchData();
        durasiPaket();
        sumberOrder();
    }

    private void searchData() {
        Object[] baris = {
            "Kode Pesanan",
            "Kode Pelanggan",
            "Nama Pelanggan",
            "Email Pelanggan",
            "Kode Paket",
            "Admin",
            "Order By",
            "Tgl Aktif",
            "Tgl Exp",
            "Sisa Hari",
            "Status"
        };

        tabmode = new DefaultTableModel(null, baris);
        tblPesanan.setModel(tabmode);
        
        String cariItem = tfCari.getText();
        try {
            String sql = "SELECT "
                    + "tr.kd_transaksi, tr.kd_pelanggan, pe.nama AS nama_pelanggan, "
                    + "pe.email, tr.kd_paket, so.nama AS order_by, tr.tgl_aktif, "
                    + "tr.tgl_exp, ad.username AS kd_admin FROM transaksi tr "
                    + "JOIN admin ad ON tr.kd_admin = ad.kd_admin "
                    + "JOIN pelanggan pe ON tr.kd_pelanggan = pe.kd_pelanggan "
                    + "JOIN paket pa ON tr.kd_paket = pa.kd_paket "
                    + "JOIN sumber_order so ON tr.kd_sumber = so.kd_sumber "
                    + "WHERE tr.kd_transaksi LIKE '%" + cariItem + "%' "
                    + "OR tr.kd_pelanggan    LIKE '%" + cariItem + "%' "
                    + "OR tr.kd_admin        LIKE '%" + cariItem + "%' "
                    + "OR tr.kd_sumber       LIKE '%" + cariItem + "%' "
                    + "OR pe.nama            LIKE '%" + cariItem + "%' "
                    + "OR pe.email           LIKE '%" + cariItem + "%' "
                    + "ORDER BY tr.tgl_exp DESC, tr.kd_transaksi ASC LIMIT 20";

            PreparedStatement ps = database.getConnection().prepareStatement(sql);
            ResultSet hasil = ps.executeQuery(sql);

            while (hasil.next()) {
                String kodeTransaksi = hasil.getString("kd_transaksi");
                String kodePelanggan = hasil.getString("kd_pelanggan");
                String nama = hasil.getString("nama_pelanggan");
                String email = hasil.getString("email");
                String paket = hasil.getString("kd_paket");
                String admin = hasil.getString("kd_admin");
                String order = hasil.getString("order_by");
                LocalDate tglAktif = hasil.getDate("tgl_aktif").toLocalDate();
                LocalDate tglExp = hasil.getDate("tgl_exp").toLocalDate();

                // Sisa Hari
                String status = "Aktif";
                if (LocalDate.now().isAfter(tglExp)) {
                    status = "Expired";
                }

                long sisaHari = ChronoUnit.DAYS.between(LocalDate.now(), tglExp);

                Object[] data = {
                    kodeTransaksi,
                    kodePelanggan,
                    nama,
                    email,
                    paket,
                    admin,
                    order,
                    tglAktif,
                    tglExp,
                    sisaHari,
                    status
                };
                tabmode.addRow(data);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "[Code: 001] Terdapat kesalahan pada aplikasi, " + e.getMessage());
        }
    }

    private void durasiPaket() {
        DefaultComboBoxModel<String> cbmodel = new DefaultComboBoxModel<>();
        try {
            String sql = "SELECT kd_paket, durasi FROM paket ORDER BY durasi ASC";
            PreparedStatement ps = database.getConnection().prepareStatement(sql);
            ResultSet hasil = ps.executeQuery(sql);
            while (hasil.next()) {
                cbmodel.addElement(hasil.getString("durasi"));
                cbDurasi.setModel(cbmodel);
                dataPaket.put(hasil.getString("kd_paket"), hasil.getString("durasi"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "[Code: 002] Terdapat kesalahan pada aplikasi, " + e.getMessage());
        }
    }

    private void sumberOrder() {
        DefaultComboBoxModel<String> cbmodel = new DefaultComboBoxModel<>();
        try {
            String sql = "SELECT kd_sumber, nama FROM sumber_order ORDER BY id ASC";
            PreparedStatement ps = database.getConnection().prepareStatement(sql);
            ResultSet hasil = ps.executeQuery(sql);
            while (hasil.next()) {
                cbmodel.addElement(hasil.getString("nama"));
                cbOrder.setModel(cbmodel);
                dataSumberOrder.put(hasil.getString("kd_sumber"), hasil.getString("nama"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "[Code: 002] Terdapat kesalahan pada aplikasi, " + e.getMessage());
        }
    }

    private void kosong() {
        kdPesanan = null;
        tfNamaPelanggan.setText("Nama Pelanggan");
        tfNamaPelanggan.setForeground(new Color(100, 100, 100));
        tfEmail.setText("e-Mail Pelanggan");
        tfEmail.setForeground(new Color(100, 100, 100));
        cbDurasi.setSelectedItem(null);
        cbOrder.setSelectedItem(null);
    }

    private void cleanTable() {
        int baris = tabmode.getRowCount();
        for (int a = 0; a < baris; a++) {
            tabmode.removeRow(0);
        }
    }

    // TODO - AUTO NUMBERING
    private String autonumber() {
        int id = 0;
        try {
            String sql = "SELECT MAX(id) AS 'last_id' FROM transaksi";
            PreparedStatement stat = database.getConnection().prepareStatement(sql);
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt("last_id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "[Code: 001] Terdapat kesalahan pada aplikasi, " + e.getMessage());
            return null;
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
        return "TR" + ur;
    }

    private void placeholder1() {
        if (tfNamaPelanggan.getText().equals("") || tfNamaPelanggan.getText().toLowerCase().equals("username")) {
            tfNamaPelanggan.setText("Nama Pelanggan");
            tfNamaPelanggan.setForeground(new Color(100, 100, 100));
        }
    }

    private void placeholder2() {
        if (tfEmail.getText().equals("") || tfEmail.getText().toLowerCase().equals("username")) {
            tfEmail.setText("e-Mail Pelanggan");
            tfEmail.setForeground(new Color(100, 100, 100));
        }
    }

    public void dataTerpilih() {
        DataPelanggan dataPelanggan = new DataPelanggan();
        dataPelanggan.dataPel = this;

        tfNamaPelanggan.setText(namaPel);
        tfEmail.setText(emailPel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        lbTanggal = new javax.swing.JLabel();
        pDaftarPengguna = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfNamaPelanggan = new javax.swing.JTextField();
        tfEmail = new javax.swing.JTextField();
        cbDurasi = new javax.swing.JComboBox<>();
        cbOrder = new javax.swing.JComboBox<>();
        bBatal = new javax.swing.JButton();
        bSimpan = new javax.swing.JButton();
        bCariPelanggan = new javax.swing.JButton();
        pTabel = new javax.swing.JPanel();
        tfCari = new javax.swing.JTextField();
        bCari = new javax.swing.JButton();
        jScrollPaneTblPaket = new javax.swing.JScrollPane();
        tblPesanan = new javax.swing.JTable();
        bHapus = new javax.swing.JButton();
        tfAdmin = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        mnBarAdmin = new javax.swing.JMenuBar();
        mnAdministrator = new javax.swing.JMenu();
        mnTambahAdmin = new javax.swing.JMenuItem();
        mnLaporan = new javax.swing.JMenuItem();
        mnLaporanPaket = new javax.swing.JMenuItem();
        mnLaporanTransaksi = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnGantiPassword = new javax.swing.JMenuItem();
        mnLogout = new javax.swing.JMenuItem();
        mnData = new javax.swing.JMenu();
        mnSumberOrder = new javax.swing.JMenuItem();
        mnPelanggan = new javax.swing.JMenuItem();
        mnPaket = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Spotify Reminder - Admin Panel");
        setPreferredSize(new java.awt.Dimension(1000, 500));
        setSize(new java.awt.Dimension(1000, 500));

        pDaftarPengguna.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DAFTAR PESANAN", 0, 0, new java.awt.Font("Tahoma", 1, 14)));
        pDaftarPengguna.setPreferredSize(new java.awt.Dimension(215, 400));

        jLabel2.setText("Durasi (Bln):");

        jLabel3.setText("Order by:");

        tfNamaPelanggan.setEditable(false);
        tfNamaPelanggan.setForeground(new java.awt.Color(102, 102, 102));
        tfNamaPelanggan.setText("Nama Pelanggan");
        tfNamaPelanggan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfNamaPelangganFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfNamaPelangganFocusLost(evt);
            }
        });

        tfEmail.setEditable(false);
        tfEmail.setForeground(new java.awt.Color(102, 102, 102));
        tfEmail.setText("e-Mail Pelanggan");
        tfEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfEmailFocusLost(evt);
            }
        });

        cbOrder.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Line", "Tokopedia" }));
        cbOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOrderActionPerformed(evt);
            }
        });

        bBatal.setText("Batal");
        bBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBatalActionPerformed(evt);
            }
        });

        bSimpan.setText("Simpan");
        bSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSimpanActionPerformed(evt);
            }
        });

        bCariPelanggan.setText("Cari");
        bCariPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCariPelangganActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pDaftarPenggunaLayout = new javax.swing.GroupLayout(pDaftarPengguna);
        pDaftarPengguna.setLayout(pDaftarPenggunaLayout);
        pDaftarPenggunaLayout.setHorizontalGroup(
            pDaftarPenggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDaftarPenggunaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDaftarPenggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDaftarPenggunaLayout.createSequentialGroup()
                        .addComponent(tfNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bCariPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pDaftarPenggunaLayout.createSequentialGroup()
                        .addGroup(pDaftarPenggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDaftarPenggunaLayout.createSequentialGroup()
                        .addGroup(pDaftarPenggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bBatal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bSimpan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pDaftarPenggunaLayout.createSequentialGroup()
                                .addGroup(pDaftarPenggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pDaftarPenggunaLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(cbDurasi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pDaftarPenggunaLayout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(81, 81, 81))))
        );
        pDaftarPenggunaLayout.setVerticalGroup(
            pDaftarPenggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDaftarPenggunaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDaftarPenggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCariPelanggan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pDaftarPenggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pDaftarPenggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbDurasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(283, Short.MAX_VALUE))
        );

        pTabel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DAFTAR PESANAN", 0, 0, new java.awt.Font("Tahoma", 1, 14)));

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
        bHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pTabelLayout = new javax.swing.GroupLayout(pTabel);
        pTabel.setLayout(pTabelLayout);
        pTabelLayout.setHorizontalGroup(
            pTabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTabelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pTabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneTblPaket, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
                    .addGroup(pTabelLayout.createSequentialGroup()
                        .addComponent(tfCari, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bCari, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pTabelLayout.setVerticalGroup(
            pTabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTabelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pTabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCari)
                    .addComponent(bHapus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneTblPaket, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        tfAdmin.setEditable(false);
        tfAdmin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tfAdmin.setBorder(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("images/icon-frilsa.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_DEFAULT)));

        mnAdministrator.setText("Administrator");

        mnTambahAdmin.setText("Tambah Admin");
        mnTambahAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnTambahAdminActionPerformed(evt);
            }
        });
        mnAdministrator.add(mnTambahAdmin);

        mnLaporan.setText("Laporan Pelanggan");
        mnLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnLaporanActionPerformed(evt);
            }
        });
        mnAdministrator.add(mnLaporan);

        mnLaporanPaket.setText("Laporan Paket");
        mnLaporanPaket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnLaporanPaketActionPerformed(evt);
            }
        });
        mnAdministrator.add(mnLaporanPaket);

        mnLaporanTransaksi.setText("Laporan Transaksi");
        mnLaporanTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnLaporanTransaksiActionPerformed(evt);
            }
        });
        mnAdministrator.add(mnLaporanTransaksi);
        mnAdministrator.add(jSeparator1);

        mnGantiPassword.setText("Ganti Password");
        mnGantiPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGantiPasswordActionPerformed(evt);
            }
        });
        mnAdministrator.add(mnGantiPassword);

        mnLogout.setText("Logout");
        mnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnLogoutActionPerformed(evt);
            }
        });
        mnAdministrator.add(mnLogout);

        mnBarAdmin.add(mnAdministrator);

        mnData.setText("Data");

        mnSumberOrder.setText("Sumber Order");
        mnSumberOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnSumberOrderActionPerformed(evt);
            }
        });
        mnData.add(mnSumberOrder);

        mnPelanggan.setText("Pelanggan");
        mnPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnPelangganActionPerformed(evt);
            }
        });
        mnData.add(mnPelanggan);

        mnPaket.setText("Paket");
        mnPaket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnPaketActionPerformed(evt);
            }
        });
        mnData.add(mnPaket);

        mnBarAdmin.add(mnData);

        setJMenuBar(mnBarAdmin);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(pDaftarPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pTabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pDaftarPengguna, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                    .addComponent(pTabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfNamaPelangganFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNamaPelangganFocusGained
        if (tfNamaPelanggan.getText().trim().equals("Nama Pelanggan")) {
            tfNamaPelanggan.setText("");
            tfNamaPelanggan.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_tfNamaPelangganFocusGained

    private void tfNamaPelangganFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNamaPelangganFocusLost
        placeholder1();
    }//GEN-LAST:event_tfNamaPelangganFocusLost

    private void tfEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfEmailFocusGained
        if (tfEmail.getText().trim().equals("e-Mail Pelanggan")) {
            tfEmail.setText("");
            tfEmail.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_tfEmailFocusGained

    private void tfEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfEmailFocusLost
        placeholder2();
    }//GEN-LAST:event_tfEmailFocusLost

    private void cbOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOrderActionPerformed
//        sumberOrder();
    }//GEN-LAST:event_cbOrderActionPerformed

    private void bBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBatalActionPerformed
        kosong();
    }//GEN-LAST:event_bBatalActionPerformed

    private void bSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanActionPerformed
        if (tfNamaPelanggan.getText().equals("") || tfNamaPelanggan.getText().equals("Nama Pelanggan")) {
            JOptionPane.showMessageDialog(null, "Silahkan Masukan Nama Pelanggan!");
        } else if (tfEmail.getText().equals("") || tfNamaPelanggan.getText().equals("e-Mail Pelanggan")) {
            JOptionPane.showMessageDialog(null, "Silahkan Masukan Email Pelanggan!");
        } else if (cbDurasi.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Silahkan Pilih Durasi!");
        } else if (cbOrder.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Silahkan Pilih Order by!");
        } else {
            try {

                System.out.println("KD PESAN: " + kdPesanan);
                if (kdPesanan != null) {
                    String sql = "UPDATE transaksi SET kd_pelanggan = ?, kd_paket = ?, kd_admin = ?, kd_sumber = ?, tgl_aktif = ?, tgl_exp = ? WHERE kd_transaksi = ?";
                    
                    // Kode Pesanan
                    PreparedStatement stat = database.getConnection().prepareStatement(sql);

                    // Pengguna
                    stat.setString(1, idPel);

                    // Paket
                    for (Map.Entry<String, String> entry : dataPaket.entrySet()) {
                        if (cbDurasi.getSelectedItem().toString().equals(entry.getValue())) {
                            stat.setString(2, entry.getKey());
                        }
                    }

                    // Admin
                    stat.setString(3, UserSession.getId());

                    // Sumber
                    for (Map.Entry<String, String> entry : dataSumberOrder.entrySet()) {
                        if (cbOrder.getSelectedItem().toString().equals(entry.getValue())) {
                            stat.setString(4, entry.getKey());
                        }
                    }

                    // Tgl Aktif
                    LocalDate dateNow = LocalDate.now();
                    Date tglSekarang = Date.valueOf(dateNow);
                    stat.setDate(5, tglSekarang);
                    System.out.println(tglSekarang);

                    // Tgl Expired
                    LocalDate dateExpired = dateNow.plusMonths(Integer.parseInt(cbDurasi.getSelectedItem().toString()));
                    Date tglExpired = Date.valueOf(dateExpired);
                    stat.setDate(6, tglExpired);
                    
                    stat.setString(7, kdPesanan);

                    stat.executeUpdate();
                } else {
                    String sql = "INSERT INTO transaksi(kd_transaksi, kd_pelanggan, kd_paket, kd_admin, kd_sumber, tgl_aktif, tgl_exp) VALUES (?,?,?,?,?,?,?)";

                    // Kode Pesanan
                    PreparedStatement stat = database.getConnection().prepareStatement(sql);
                    stat.setString(1, autonumber());

                    // Pengguna
                    stat.setString(2, idPel);

                    // Paket
                    for (Map.Entry<String, String> entry : dataPaket.entrySet()) {
                        if (cbDurasi.getSelectedItem().toString().equals(entry.getValue())) {
                            stat.setString(3, entry.getKey());
                        }
                    }

                    // Admin
                    stat.setString(4, UserSession.getId());

                    // Sumber
                    for (Map.Entry<String, String> entry : dataSumberOrder.entrySet()) {
                        if (cbOrder.getSelectedItem().toString().equals(entry.getValue())) {
                            stat.setString(5, entry.getKey());
                        }
                    }

                    // Tgl Aktif
                    LocalDate dateNow = LocalDate.now();
                    Date tglSekarang = Date.valueOf(dateNow);
                    stat.setDate(6, tglSekarang);
                    System.out.println(tglSekarang);

                    // Tgl Expired
                    LocalDate dateExpired = dateNow.plusMonths(Integer.parseInt(cbDurasi.getSelectedItem().toString()));
                    Date tglExpired = Date.valueOf(dateExpired);
                    stat.setDate(7, tglExpired);

                    stat.executeUpdate();
                }
                
                kosong();
                searchData();

                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan! " + e.getMessage());
            }
        }
    }//GEN-LAST:event_bSimpanActionPerformed

    private void bCariPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCariPelangganActionPerformed
        DataPelanggan dataPelanggan = new DataPelanggan();
        dataPelanggan.dataPel = this;
        dataPelanggan.setVisible(true);
    }//GEN-LAST:event_bCariPelangganActionPerformed

    private void tfCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            initialize();
        }
    }//GEN-LAST:event_tfCariKeyPressed

    private void bCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCariActionPerformed
        initialize();
    }//GEN-LAST:event_bCariActionPerformed

    private void tblPesananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPesananMouseClicked
        int bar = tblPesanan.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
//        String e = tabmode.getValueAt(bar, 4).toString();
//        String f = tabmode.getValueAt(bar, 5).toString();
//        String g = tabmode.getValueAt(bar, 6).toString();
//        String h = tabmode.getValueAt(bar, 7).toString();
//        String i = tabmode.getValueAt(bar, 8).toString();

        kdPesanan = a;
        idPel = b;
        tfNamaPelanggan.setText(c);
        tfEmail.setText(d);
    }//GEN-LAST:event_tblPesananMouseClicked

    private void bHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                String sql = "DELETE FROM transaksi WHERE kd_transaksi ='" + kdPesanan + "'";
                PreparedStatement stat = database.getConnection().prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Paket Berhasil Dihapus!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Paket Gagal Dihapus!" + e);
            }
            initialize();
            kosong();
        }
    }//GEN-LAST:event_bHapusActionPerformed

    private void mnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnLogoutActionPerformed
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mnLogoutActionPerformed

    private void mnTambahAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnTambahAdminActionPerformed
        new TambahAdmin().setVisible(true);
    }//GEN-LAST:event_mnTambahAdminActionPerformed

    private void mnLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnLaporanActionPerformed
        new Laporan().setVisible(true);
    }//GEN-LAST:event_mnLaporanActionPerformed

    private void mnGantiPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGantiPasswordActionPerformed
        new GantiPassword().setVisible(true);
    }//GEN-LAST:event_mnGantiPasswordActionPerformed

    private void mnSumberOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnSumberOrderActionPerformed
        new SumberOrder().setVisible(true);
    }//GEN-LAST:event_mnSumberOrderActionPerformed

    private void mnPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnPelangganActionPerformed
        new Pelanggan().setVisible(true);
    }//GEN-LAST:event_mnPelangganActionPerformed

    private void mnPaketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnPaketActionPerformed
        new Paket().setVisible(true);
    }//GEN-LAST:event_mnPaketActionPerformed

    private void mnLaporanPaketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnLaporanPaketActionPerformed
        new LaporanPaket().setVisible(true);
    }//GEN-LAST:event_mnLaporanPaketActionPerformed

    private void mnLaporanTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnLaporanTransaksiActionPerformed
        new LaporanTransaksi().setVisible(true);
    }//GEN-LAST:event_mnLaporanTransaksiActionPerformed

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
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bBatal;
    private javax.swing.JButton bCari;
    private javax.swing.JButton bCariPelanggan;
    private javax.swing.JButton bHapus;
    private javax.swing.JButton bSimpan;
    private javax.swing.JComboBox<String> cbDurasi;
    private javax.swing.JComboBox<String> cbOrder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPaneTblPaket;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lbTanggal;
    private javax.swing.JMenu mnAdministrator;
    private javax.swing.JMenuBar mnBarAdmin;
    private javax.swing.JMenu mnData;
    private javax.swing.JMenuItem mnGantiPassword;
    private javax.swing.JMenuItem mnLaporan;
    private javax.swing.JMenuItem mnLaporanPaket;
    private javax.swing.JMenuItem mnLaporanTransaksi;
    private javax.swing.JMenuItem mnLogout;
    private javax.swing.JMenuItem mnPaket;
    private javax.swing.JMenuItem mnPelanggan;
    private javax.swing.JMenuItem mnSumberOrder;
    private javax.swing.JMenuItem mnTambahAdmin;
    private javax.swing.JPanel pDaftarPengguna;
    private javax.swing.JPanel pTabel;
    private javax.swing.JTable tblPesanan;
    private javax.swing.JTextField tfAdmin;
    private javax.swing.JTextField tfCari;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfNamaPelanggan;
    // End of variables declaration//GEN-END:variables
}
