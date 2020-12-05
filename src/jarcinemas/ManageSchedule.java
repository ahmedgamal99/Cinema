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
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import oracle.net.aso.s;

/**
 *
 * @author asifrasheed
 */
public class ManageSchedule extends javax.swing.JFrame {

    /**
     * Creates new form ManageExistingMovies
     */
    ResultSet data;
    int movie_id;
    int schedule_id;
    public ManageSchedule(int id) {
        initComponents();
        
        movie_id = id;
        
        try {
            SQLConnect database = new SQLConnect();
            ResultSet data = database.getResultSet("select count(*) count from schedule");
            data.next();
            schedule_id = data.getInt("count");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManageSchedule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManageSchedule.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screen.width/4, screen.height/4);
        this.setLocationRelativeTo(null);
        
        int hh = this.getPreferredSize().height/5;
        jPanel1.setPreferredSize(new Dimension(this.getPreferredSize().width,hh));
        
        int hy = (this.getPreferredSize().height-hh)/4;
        jPanel2.setPreferredSize(new Dimension(this.getPreferredSize().width,hy));
        
        int hz = (this.getPreferredSize().height - (2*hy) - hh);
        jPanel3.setPreferredSize(new Dimension(this.getPreferredSize().width,hz));
        
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
                dispose();
            }
        });
        jPanel1.add(closeButton);
        jPanel2.requestFocus();
        
        moviescreen.addItem("Screen");
        try {
            SQLConnect database = new SQLConnect();
            ResultSet screens = database.getResultSet("select id from screen");
            while(screens.next()){
                moviescreen.addItem(screens.getString("id"));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManageSchedule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManageSchedule.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("d/M/y");
        LocalDate today = LocalDate.now();
        for(int i=0;i<31;i++)
            date.addItem(today.plusDays(i+1).format(dateformat));
         
        moviescreen.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!moviescreen.getSelectedItem().equals("Screen")){
                    try {
                        SQLConnect database = new SQLConnect();
                        ResultSet selectedtimes = database.getResultSet("select TO_CHAR( timedate, 'hh24:mi:ss' ) time_date from schedule where screen_id="+moviescreen.getSelectedItem());
                        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("HH:00");
                        LocalTime now = LocalTime.now();
                        time.removeAllItems();
                        
                        for(long i=0;i<24;i++){
                            selectedtimes.beforeFirst();
                            boolean add = true;
                            while(selectedtimes.next())
                                if(selectedtimes.getString("time_date").equals(now.plusHours(i).format(dateformat).toString()))
                                    add = false;
                            if(add)
                                time.addItem(now.plusHours(i).format(dateformat).toString());
                        }       
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ManageSchedule.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ManageSchedule.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else
                    time.removeAllItems();
            }
        });
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        date = new javax.swing.JComboBox<>();
        time = new javax.swing.JComboBox<>();
        previous1 = new javax.swing.JLabel();
        moviescreen = new javax.swing.JComboBox<>();

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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));
        getContentPane().add(jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Manage Schedule");
        jPanel2.add(jLabel1);

        getContentPane().add(jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(682, 98));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 97;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 517, 0, 0);
        jPanel3.add(date, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 97;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 517, 0, 0);
        jPanel3.add(time, gridBagConstraints);

        previous1.setBackground(new java.awt.Color(255, 51, 51));
        previous1.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        previous1.setForeground(new java.awt.Color(153, 153, 153));
        previous1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        previous1.setText("Add");
        previous1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 51, 51)));
        previous1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                previous1MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                previous1MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                previous1MouseEntered(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 133;
        gridBagConstraints.ipady = 24;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 517, 40, 611);
        jPanel3.add(previous1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 97;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(33, 517, 0, 0);
        jPanel3.add(moviescreen, gridBagConstraints);

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

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        this.requestFocus();
    }//GEN-LAST:event_formMouseClicked

    private void previous1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previous1MouseClicked
        // TODO add your handling code here:
        if(!moviescreen.getSelectedItem().equals("Screen")){
            try {
                SQLConnect database = new SQLConnect();
                if(database.executeQuery("insert into schedule (id,movie_id,screen_id,timedate) values ("+Integer.toString(schedule_id)+","+Integer.toString(movie_id)+","+moviescreen.getSelectedItem()+",TO_DATE('"+date.getSelectedItem()+" "+time.getSelectedItem()+"','DD/MM/YYYY HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'))"))
                {
                    (new popup((new ManageExistingMovies()),"Success!")).setVisible(true);
                    this.dispose();
                }
                else
                    (new popup((new ManageExistingMovies()),"Something went wrong!")).setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ManageSchedule.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ManageSchedule.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_previous1MouseClicked

    private void previous1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previous1MouseExited
        // TODO add your handling code here:
        previous1.setOpaque(false);
        previous1.setForeground(new Color(153,153,153));
    }//GEN-LAST:event_previous1MouseExited

    private void previous1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previous1MouseEntered
        // TODO add your handling code here:
        previous1.setOpaque(true);
        previous1.setForeground(Color.WHITE);
    }//GEN-LAST:event_previous1MouseEntered

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
            java.util.logging.Logger.getLogger(ManageSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new ManageSchedule().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JComboBox<String> moviescreen;
    private javax.swing.JLabel previous1;
    private javax.swing.JComboBox<String> time;
    // End of variables declaration//GEN-END:variables

}
