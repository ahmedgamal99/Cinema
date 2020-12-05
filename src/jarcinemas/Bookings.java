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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author asifrasheed
 */
public class Bookings extends javax.swing.JFrame {

    /**
     * Creates new form Bookings
     */
    SQLConnect databse;
    ResultSet data, m, k;
    JLabel[] references, movies, seats, DateTime;
    final int i;
    
    public Bookings(int width, int height){
        initComponents();
        
        this.setSize(3*width/4,3*height/4);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        
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
        
        hh = (this.getHeight()- (2*hh))/6;
        jPanel2.setPreferredSize(new Dimension(this.getWidth(),hh));
        JLabel title = new JLabel("Your Booking History", SwingConstants.CENTER);
        title.setFont(new Font("Lucida Grande",Font.BOLD,30));
        jPanel2.add(title);
        
        hh = this.getHeight()- ( (this.getHeight()/30) + hh);
        jPanel3.setPreferredSize(new Dimension(this.getWidth(),hh));
        
        try {
            databse = new SQLConnect();
            data = databse.getResultSet("select ref, schedule_id, seats from booking where username='"+(new UserInfo()).username()+"'");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bookings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Bookings.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        hh = jPanel3.getPreferredSize().width/5;
        jPanel4.setPreferredSize(new Dimension(hh/2,jPanel3.getPreferredSize().height));
        jPanel6.setPreferredSize(new Dimension(hh/2,jPanel3.getPreferredSize().height));
        jPanel5.setPreferredSize(new Dimension(hh*4,jPanel3.getPreferredSize().height));
        
        i = 15;
        references = new JLabel[i];
        movies = new JLabel[i];
        seats = new JLabel[i];
        DateTime = new JLabel[i];
        
        for(int j=0;j<i;j++){
            references[j] = new JLabel("",SwingConstants.CENTER);
            movies[j] = new JLabel("",SwingConstants.CENTER);
            seats[j] = new JLabel("",SwingConstants.CENTER);
            DateTime[j] = new JLabel("",SwingConstants.CENTER);
            
            references[j].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(204,204,204)));
            movies[j].setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, new Color(204,204,204)));
            seats[j].setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(204,204,204)));
            DateTime[j].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(204,204,204)));
            
            jPanel5.add(references[j]);
            jPanel5.add(movies[j]);
            jPanel5.add(seats[j]);
            jPanel5.add(DateTime[j]);
        }
        
        int j = 0;
        try {
            while(data.next() & j<i){
                references[j].setText(data.getString("ref"));
                
                SQLConnect databse2 = new SQLConnect();
                m = databse2.getResultSet("SELECT movie_id, screen_id, TO_CHAR( timedate, 'dd/mm/yyyy hh24:mi:ss' ) from schedule where id="+data.getString("schedule_id"));
                m.next();
                DateTime[j].setText(m.getString(3));
                
                SQLConnect databse3 = new SQLConnect();
                k = databse3.getResultSet("SELECT name from movies where id="+Integer.toString(m.getInt("movie_id")));
                k.next();
                movies[j].setText(k.getString("name"));
                
                String seat = m.getString(2)+": ";
                ResultSet se = data.getArray("seats").getResultSet();
                while(se.next())
                    if(seat.equals(m.getString("screen_id")+": "))
                        seat = seat+se.getString(2);
                    else
                        seat = seat+", "+se.getString(2);
                seats[j].setText(seat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bookings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bookings.class.getName()).log(Level.SEVERE, null, ex);
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
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

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
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridLayout());
        getContentPane().add(jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new java.awt.GridLayout());
        getContentPane().add(jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel4MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel4MouseEntered(evt);
            }
        });
        jPanel4.setLayout(new java.awt.GridLayout());

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("<");
        jPanel4.add(jLabel5);

        jPanel3.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 1, new java.awt.Color(204, 204, 204)));
        jPanel5.setLayout(new java.awt.GridLayout(16, 4));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Reference");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        jPanel5.add(jLabel1);

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Movie");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 0, new java.awt.Color(204, 204, 204)));
        jPanel5.add(jLabel2);

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Seats");
        jLabel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        jPanel5.add(jLabel4);

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Date and Time");
        jLabel3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        jPanel5.add(jLabel3);

        jPanel3.add(jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel6MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel6MouseEntered(evt);
            }
        });
        jPanel6.setLayout(new java.awt.GridLayout());

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText(">");
        jPanel6.add(jLabel6);

        jPanel3.add(jPanel6);

        getContentPane().add(jPanel3);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private int clickx, clicky;
    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen()-clickx;
        int y = evt.getYOnScreen()-clicky;
        this.setLocation(x, y);
    }//GEN-LAST:event_formMouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        clickx = evt.getX();
        clicky = evt.getY();
        setCursor(new Cursor(Cursor.MOVE_CURSOR));
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_formMouseReleased

    private void jPanel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseEntered
        // TODO add your handling code here:
        jPanel4.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel4MouseEntered

    private void jPanel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseExited
        // TODO add your handling code here:
        jPanel4.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel4MouseExited

    private void jPanel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseEntered
        // TODO add your handling code here:
        jPanel6.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel6MouseEntered

    private void jPanel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseExited
        // TODO add your handling code here:
        jPanel6.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel6MouseExited

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        // TODO add your handling code here:
        int j = 0;

        try {
            while(data.next() & j<i){
                if(j==0)
                    for(int q=0;q<i;q++){
                        references[q].setText("");
                        movies[q].setText("");
                        seats[q].setText("");
                        DateTime[q].setText("");
                    } 
                references[j].setText(data.getString("ref"));
                
                SQLConnect databse2 = new SQLConnect();
                m = databse2.getResultSet("SELECT movie_id, screen_id, TO_CHAR( timedate, 'dd/mm/yyyy hh24:mi:ss' ) from schedule where id="+data.getString("schedule_id"));
                m.next();
                SQLConnect databse3 = new SQLConnect();
                k = databse3.getResultSet("SELECT name from movies where id="+Integer.toString(m.getInt("movie_id")));
                
                
                DateTime[j].setText(m.getString(3));
                k.next();
                movies[j].setText(k.getString("name"));
                
                String seat = m.getString(2)+": ";
                ResultSet se = data.getArray("seats").getResultSet();
                while(se.next())
                    if(seat.equals(m.getString("screen_id")+": "))
                        seat = seat+se.getString(2);
                    else
                        seat = seat+", "+se.getString(2);
                seats[j].setText(seat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bookings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bookings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        // TODO add your handling code here:        
        int j = 0;

        try {
            while(data.previous() & j<i){
                if(j==0)
                    for(int q=0;q<i;q++){
                        references[q].setText("");
                        movies[q].setText("");
                        seats[q].setText("");
                        DateTime[q].setText("");
                    }
                references[j].setText(data.getString("ref"));
                
                SQLConnect databse2 = new SQLConnect();
                m = databse2.getResultSet("SELECT movie_id, screen_id, TO_CHAR( timedate, 'dd/mm/yyyy hh24:mi:ss' ) from schedule where id="+data.getString("schedule_id"));
                m.next();
                SQLConnect databse3 = new SQLConnect();
                k = databse3.getResultSet("SELECT name from movies where id="+Integer.toString(m.getInt("movie_id")));
                
                DateTime[j].setText(m.getString(3));
                k.next();
                movies[j].setText(k.getString("name"));
                
                String seat = m.getString(2)+": ";
                ResultSet se = data.getArray("seats").getResultSet();
                while(se.next())
                    if(seat.equals(m.getString("screen_id")+": "))
                        seat = seat+se.getString(2);
                    else
                        seat = seat+", "+se.getString(2);
                seats[j].setText(seat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bookings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bookings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jPanel4MouseClicked

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
            java.util.logging.Logger.getLogger(Bookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new Bookings().setVisible(true);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables
}
