/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jarcinemas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author asifrasheed
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    JLabel accountMenu, accountButton, logoutButton, date, time, adminMenu, moviesButton, usersButton;
    boolean accountFocus, adminFocus;
    ResultSet data;
    UserInfo user;
    JLabel[] movies;
    public Home() {
        initComponents();
        
        accountFocus = false;
        adminFocus = false;
        
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(3*screen.width/4, 3*screen.height/4);
        this.setLocationRelativeTo(null);
        
        jPanel1.setPreferredSize(new Dimension(this.getWidth()/5,this.getHeight()));
        user = new UserInfo();
        
        Font labelfont = new Font("Sans",Font.BOLD,18);
        Font buttonfont = new Font("Sans",Font.PLAIN,16);
        
        accountMenu = new JLabel(user.getName().toUpperCase(), SwingConstants.CENTER);
        accountMenu.setFont(labelfont);
        accountMenu.setForeground(Color.WHITE);
        accountMenu.setBackground(Color.WHITE);
        
        accountButton = new JLabel("My Account", SwingConstants.CENTER);
        accountButton.setFont(buttonfont);
        accountButton.setForeground(Color.WHITE);
        accountButton.setVisible(false);
        
        logoutButton = new JLabel("Log Out", SwingConstants.CENTER);
        logoutButton.setFont(buttonfont);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setVisible(false);
        
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("E, d MMMM y");
        date = new JLabel(LocalDate.now().format(dateformat).toString(),SwingConstants.CENTER);
        date.setFont(labelfont);
        date.setForeground(Color.WHITE);
        
        DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm:ss a");
        time = new JLabel(LocalTime.now().format(format).toString(),SwingConstants.CENTER);
        time.setFont(labelfont);
        time.setForeground(Color.WHITE);
        
        moviesButton = new JLabel("Manage Movies",SwingConstants.CENTER);
        moviesButton.setFont(buttonfont);
        moviesButton.setForeground(Color.WHITE);
        moviesButton.setVisible(false);
        
        usersButton = new JLabel("Manage Users", SwingConstants.CENTER);
        usersButton.setFont(buttonfont);
        usersButton.setForeground(Color.WHITE);
        usersButton.setVisible(false);
        
        adminMenu = new JLabel("Administration".toUpperCase(),SwingConstants.CENTER);
        adminMenu.setFont(labelfont);
        adminMenu.setForeground(Color.WHITE);
        adminMenu.setBackground(Color.WHITE);
        
        if(!user.isAdmin()) adminMenu.setVisible(false);
        
        Timer timer = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                time.setText(LocalTime.now().format(format).toString());
            } 
        });
        
        timer.start();
        
        jPanel1.add(accountMenu);
        jPanel1.add(accountButton);
        jPanel1.add(logoutButton);
        jPanel1.add(new JLabel());
        jPanel1.add(date);
        jPanel1.add(time);
        jPanel1.add(new JLabel());
        jPanel1.add(moviesButton);
        jPanel1.add(usersButton);
        jPanel1.add(adminMenu);
        
        jPanel3.setPreferredSize(new Dimension(this.getWidth()-jPanel1.getSize().width,this.getHeight()-jPanel1.getSize().height));
        
        accountMenu.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evt){
                if (!accountFocus){
                    accountMenu.setForeground(Color.BLACK);
                    accountMenu.setOpaque(true);
                    accountButton.setVisible(true);
                    logoutButton.setVisible(true);
                    accountFocus = !accountFocus;
                }
                else{
                    accountMenu.setForeground(Color.WHITE);
                    accountMenu.setOpaque(false);
                    accountButton.setVisible(false);
                    logoutButton.setVisible(false);
                    accountFocus = !accountFocus;
                }
            }
        });
        
        adminMenu.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evt){
                if(!adminFocus){
                    adminMenu.setForeground(Color.BLACK);
                    adminMenu.setOpaque(true);
                    usersButton.setVisible(true);
                    moviesButton.setVisible(true);
                    adminFocus = !adminFocus;
                }
                else{
                    adminMenu.setForeground(Color.WHITE);
                    adminMenu.setOpaque(false);
                    usersButton.setVisible(false);
                    moviesButton.setVisible(false);
                    adminFocus = !adminFocus;
                }
            }
        });
        
        logoutButton.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evt){
                (new JarCinemas()).setVisible(true);
                dispose();
            }
        });
        
        accountButton.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evt){
                (new AccountMenu(getWidth(),getHeight())).setVisible(true);
                dispose();
            }
        });
        
        usersButton.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evt){
                (new ManageUsers()).setVisible(true);
                dispose();
            }
        });
        
        moviesButton.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evt){
                (new ManageMovies()).setVisible(true);
                dispose();
            }
        });
        
        movies = new JLabel[8];
        movies[0] = jLabel1;
        movies[1] = jLabel3;
        movies[2] = jLabel5;
        movies[3] = jLabel7;
        movies[4] = jLabel2;
        movies[5] = jLabel4;
        movies[6] = jLabel6;
        movies[7] = jLabel8;
        for(int i=0;i<8;i++){
            movies[i].setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, new Color(204,204,204)));
            jPanel4.add(movies[i]);
        }
        
        try {
            SQLConnect database = new SQLConnect();
            data = database.getResultSet("select * from movies");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int j=0;
        try {
            while(data.next()&j<7){
                movies[j].setText(data.getString("name"));
                movies[j].addMouseListener(new MouseAdapter(){
                    public void mouseClicked(MouseEvent evt){
                        (new ScheduleSeatSelect(Integer.parseInt(((JLabel)evt.getSource()).getText()))).setVisible(true);
                    }
                });
                j++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 1, new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new java.awt.GridLayout(10, 0));
        getContentPane().add(jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.GridLayout(2, 4));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Coming Soon");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(204, 204, 204)));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
        });
        jPanel4.add(jLabel1);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Coming Soon");
        jLabel3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(204, 204, 204)));
        jPanel4.add(jLabel3);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Coming Soon");
        jLabel5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(204, 204, 204)));
        jPanel4.add(jLabel5);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Coming Soon");
        jLabel7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        jPanel4.add(jLabel7);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Coming Soon");
        jPanel4.add(jLabel2);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Coming Soon");
        jLabel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(204, 204, 204)));
        jPanel4.add(jLabel4);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Coming Soon");
        jLabel6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(204, 204, 204)));
        jPanel4.add(jLabel6);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Coming Soon");
        jLabel8.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(204, 204, 204)));
        jPanel4.add(jLabel8);

        jPanel3.add(jPanel4);

        getContentPane().add(jPanel3);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private int clickx, clicky;
    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_formMouseReleased

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        clickx = evt.getX();
        clicky = evt.getY();
        setCursor(new Cursor(Cursor.MOVE_CURSOR));
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen()-clickx;
        int y = evt.getYOnScreen()-clicky;
        this.setLocation(x, y);
    }//GEN-LAST:event_formMouseDragged

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
