/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jarcinemas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author asifrasheed
 */
public class ScheduleSeatSelect extends javax.swing.JFrame {

    /**
     * Creates new form ScheduleSeatSelect
     */
    int movieid;
    public ScheduleSeatSelect(int id) {
        initComponents();
        movieid = id;
        
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(3*screen.width/4, 3*screen.height/4);
        this.setLocationRelativeTo(null);
        
        int hh = this.getHeight()/30;
        JLabel closeButton = new JLabel("☒",SwingConstants.RIGHT);
        closeButton.setFont(new Font("Lucida Grande",Font.BOLD,24));
        closeButton.setForeground(new Color(255,51,51));
        closeButton.addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseEntered(MouseEvent evt){
                closeButton.setForeground(new Color(153,153,153));
            }
            public void mouseExited(MouseEvent evt){
                closeButton.setForeground(new Color(255,51,51));
            }
        });
        closeButton.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evt){
                (new Home()).setVisible(true);
                dispose();
            }
        });
        jPanel1.add(closeButton);
        
        jPanel2.add(new JLabel(Integer.toString(id)));
        
        try {
            ResultSet schedule_info = (new SQLConnect()).getResultSet("SELECT id, TO_CHAR( timedate, 'dd/mm/yyyy hh24:mi:ss' ) date_time from schedule where movie_id="+Integer.toString(movieid));
            while(schedule_info.next()){
                schedules.addItem(schedule_info.getString("datee_time"));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ScheduleSeatSelect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleSeatSelect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        schedules = new javax.swing.JComboBox<>();
        usernameLogin = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        loginButton = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        schedules.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schedulesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 238;
        gridBagConstraints.ipady = 172;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel1.add(schedules, gridBagConstraints);

        usernameLogin.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        usernameLogin.setForeground(new java.awt.Color(153, 153, 153));
        usernameLogin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usernameLogin.setText("number of seats");
        usernameLogin.setToolTipText("");
        usernameLogin.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        usernameLogin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameLoginFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernameLoginFocusLost(evt);
            }
        });
        usernameLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameLoginActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 300;
        gridBagConstraints.ipady = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 20, 17, 0);
        jPanel1.add(usernameLogin, gridBagConstraints);

        getContentPane().add(jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridLayout());

        loginButton.setBackground(new java.awt.Color(255, 51, 51));
        loginButton.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        loginButton.setForeground(new java.awt.Color(153, 153, 153));
        loginButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginButton.setText("Book");
        loginButton.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 51, 51)));
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginButtonMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButtonMouseEntered(evt);
            }
        });
        jPanel2.add(loginButton);

        getContentPane().add(jPanel2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void schedulesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schedulesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_schedulesActionPerformed

    static String getAlphaNumericString(int n) 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
    
    private void loginButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMouseClicked
        try {
            // TODO add your handling code here:
            if((new SQLConnect()).executeQuery("INSERT INTO BOOKING("+getAlphaNumericString(10)+","+(new UserInfo()).username()+","+schedules.getSelectedIndex()+", seat())"))
                (new popup(this,"Success")).setVisible(true);
                
                } catch (ClassNotFoundException ex) {
            Logger.getLogger(ScheduleSeatSelect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleSeatSelect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_loginButtonMouseClicked

    private void loginButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMouseExited
        // TODO add your handling code here:
        loginButton.setOpaque(false);
        loginButton.setForeground(new Color(153,153,153));
    }//GEN-LAST:event_loginButtonMouseExited

    private void loginButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMouseEntered
        // TODO add your handling code here:
        loginButton.setOpaque(true);
        loginButton.setForeground(Color.WHITE);
    }//GEN-LAST:event_loginButtonMouseEntered

    private void usernameLoginFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameLoginFocusGained
        // TODO add your handling code here:
        usernameLogin.setText("");
        usernameLogin.setForeground(Color.BLACK);
    }//GEN-LAST:event_usernameLoginFocusGained

    private void usernameLoginFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameLoginFocusLost
        // TODO add your handling code here:
        if(usernameLogin.getText().equals("")){
            usernameLogin.setText("username");
            usernameLogin.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_usernameLoginFocusLost

    private void usernameLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameLoginActionPerformed

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
            java.util.logging.Logger.getLogger(ScheduleSeatSelect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScheduleSeatSelect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScheduleSeatSelect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScheduleSeatSelect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new ScheduleSeatSelect().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel loginButton;
    private javax.swing.JComboBox<String> schedules;
    private javax.swing.JTextField usernameLogin;
    // End of variables declaration//GEN-END:variables
}
