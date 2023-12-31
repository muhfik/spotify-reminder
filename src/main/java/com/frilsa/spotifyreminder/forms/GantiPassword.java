package com.frilsa.spotifyreminder.forms;

import com.frilsa.spotifyreminder.configurations.SQLConnector;
import com.frilsa.spotifyreminder.models.UserSession;
import java.awt.event.KeyEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class GantiPassword extends javax.swing.JFrame {

    private SQLConnector database = SQLConnector.getInstance();

    public GantiPassword() {
        initComponents();
        this.setLocationRelativeTo(this);
    }

    protected void kosong() {
        tfPasswordLama.setText("");
        tfPasswordBaru1.setText("");
        tfPasswordBaru2.setText("");
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfPasswordLama = new javax.swing.JPasswordField();
        tfPasswordBaru1 = new javax.swing.JPasswordField();
        tfPasswordBaru2 = new javax.swing.JPasswordField();
        bGanti = new javax.swing.JButton();
        bBatal = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ganti Password");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ganti Password", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel1.setText("Password Lama");

        jLabel2.setText("Password Baru");

        tfPasswordLama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfPasswordLamaKeyTyped(evt);
            }
        });

        tfPasswordBaru1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfPasswordBaru1KeyTyped(evt);
            }
        });

        tfPasswordBaru2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfPasswordBaru2KeyTyped(evt);
            }
        });

        bGanti.setText("Ganti");
        bGanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGantiActionPerformed(evt);
            }
        });

        bBatal.setText("Batal");
        bBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBatalActionPerformed(evt);
            }
        });

        jLabel3.setText("Verifikasi Password Baru");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfPasswordLama)
                    .addComponent(tfPasswordBaru1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bGanti, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(bBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfPasswordBaru2))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPasswordLama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPasswordBaru1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPasswordBaru2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bBatal)
                    .addComponent(bGanti))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGantiActionPerformed
        String username = UserSession.getUsername();
        String password = UserSession.getPassword();

        String passLama = new String(tfPasswordLama.getPassword());
        String pass1 = new String(tfPasswordBaru1.getPassword());
        String pass2 = new String(tfPasswordBaru2.getPassword());

        try {
            if (passLama.length() <= 0) {
                JOptionPane.showMessageDialog(null, "Harap isi Field Password Lama!");
                tfPasswordLama.requestFocus();
            } else if (pass1.length() <= 0) {
                JOptionPane.showMessageDialog(null, "Harap isi Field Password Baru!");
                tfPasswordBaru1.requestFocus();
            } else if (pass2.length() <= 0) {
                JOptionPane.showMessageDialog(null, "Harap isi Field Verifikasi Password Baru!");
                tfPasswordBaru2.requestFocus();
            } else if (!pass1.equals(pass2)) {
                JOptionPane.showMessageDialog(null, "Password Tidak Sesuai!");
                tfPasswordBaru1.setText("");
                tfPasswordBaru2.setText("");
                tfPasswordBaru1.requestFocus();
            } else if (!getHashedPassword(tfPasswordLama.getPassword()).equals(password)) {
                JOptionPane.showMessageDialog(null, "Password Lama Anda Tidak Valid!");
                tfPasswordLama.setText("");
                tfPasswordLama.requestFocus();
            } else {
                String sql = "UPDATE admin SET password = ? WHERE kd_admin = ? AND password = ?";
                PreparedStatement ps = database.getConnection().prepareStatement(sql);
                ps.setString(1, getHashedPassword(tfPasswordBaru1.getPassword()));
                ps.setString(2, username);
                ps.setString(3, password);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Password Berhasil Diubah");
                UserSession.setPassword(getHashedPassword(tfPasswordBaru1.getPassword()));
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(null, "Password Gagal Diubah! " + e.getMessage());
        }
    }//GEN-LAST:event_bGantiActionPerformed

    private void bBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBatalActionPerformed
        dispose();
    }//GEN-LAST:event_bBatalActionPerformed

    private void tfPasswordLamaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPasswordLamaKeyTyped
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || tfPasswordLama.getText().length() == 8) {
            evt.consume();
        }
    }//GEN-LAST:event_tfPasswordLamaKeyTyped

    private void tfPasswordBaru1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPasswordBaru1KeyTyped
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || tfPasswordBaru1.getText().length() == 8) {
            evt.consume();
        }
    }//GEN-LAST:event_tfPasswordBaru1KeyTyped

    private void tfPasswordBaru2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPasswordBaru2KeyTyped
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || tfPasswordBaru2.getText().length() == 8) {
            evt.consume();
        }
    }//GEN-LAST:event_tfPasswordBaru2KeyTyped

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
            java.util.logging.Logger.getLogger(GantiPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GantiPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GantiPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GantiPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new GantiPassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bBatal;
    private javax.swing.JButton bGanti;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField tfPasswordBaru1;
    private javax.swing.JPasswordField tfPasswordBaru2;
    private javax.swing.JPasswordField tfPasswordLama;
    // End of variables declaration//GEN-END:variables
}
