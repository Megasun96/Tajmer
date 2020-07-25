/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tajmer;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Dimitrijevic
 */
public class Tajmer {

    /**
     * @param args the command line arguments
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws ParseException  {
         
      int odabir =  JOptionPane.showOptionDialog(null, "Choose option", "Option dialog", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {"Settings", "Close"}, null);
      if(odabir  == 0 ) {
         JFrame jf = new JFrame();
          JFrame frame = new JFrame();
          frame.setLayout(new FlowLayout(FlowLayout.LEFT));
       JColorChooser jcc = new JColorChooser();
       ButtonGroup buttonGroup = new   ButtonGroup();  
       JRadioButton jRadioButton = new JRadioButton();
       buttonGroup.add(jRadioButton);
       JRadioButton jRadioButton1 = new JRadioButton();
       buttonGroup.add(jRadioButton1);
       JLabel jlOnTime = new JLabel("OnTime");
       JLabel jlCoundown = new JLabel("Countdown( mins)");
       JLabel jlNoColorSelected = new JLabel("No choose color");
       JLabel jlSpeed = new JLabel("Speed");
       JComboBox jcb = new JComboBox(new DefaultComboBoxModel());
       jcb.addItem(1);
       jcb.addItem(2); 
       jcb.addItem(3);
       jcb.addItem(4);
       JButton jbChooseColor = new JButton("Choose color");
       JButton jbStart = new JButton("Start");
       JButton jbStop = new JButton("Stop");
       JFormattedTextField JFormattedTextField = new JFormattedTextField(new MaskFormatter("##:##:##"));
       JFormattedTextField.setColumns(17);
       JTextField jtf = new JTextField(12);
       frame.add(jRadioButton);
       frame.add(jlOnTime);
       frame.add(JFormattedTextField);
       frame.add(jRadioButton1);
       frame.add(jlCoundown);
       frame.add(jtf);
       frame.add(jbChooseColor);
       frame.add(jlNoColorSelected);
       frame.add(jlSpeed);
       frame.add(jcb);
       frame.add(jbStart);
       frame.add(jbStop);
       frame.setSize(300,200);
       frame.setLocationRelativeTo(null);
       frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
       frame.setVisible(true);
      
           jbChooseColor.addActionListener((ActionEvent e) -> {
               Color c = JColorChooser.showDialog(null, "Choose background color", Color.yellow);
               jlNoColorSelected.setBackground(c);
               jlNoColorSelected.setOpaque(true);
               jlNoColorSelected.setText("izabrali ste boja");
          });
        
       jbStart.addActionListener((ActionEvent ae) -> {
                        
           Color c = jlNoColorSelected.getBackground();
           String sat = JFormattedTextField.getText();
           if(jRadioButton.isSelected()){
               Vreme vr = new Vreme();
               if(! vr.vreme(sat)){
                    JOptionPane.showMessageDialog(null, "Dozvoljeni unosi su sledeci:\n Za sate: 00-23\n Za minute: 00-59\n Za sekunde: 00-59");
               }
                else
                   if(vr.vreme( sat)){
               String[] time =  JFormattedTextField.getText().split(":");
               int sati = Integer.valueOf(time[0]);
               int min = Integer.valueOf(time[1]);
               int sek = Integer.valueOf(time[2]);
               LocalTime time1 = LocalTime.now();
               LocalTime time2  = LocalTime.of(sati, min, sek);
               Duration duration = Duration.between(time1, time2);
               long sacekaj = (duration.getSeconds())*1000 ;
               int brzina =  jcb.getSelectedIndex()*1000;
               try {
                   Thread.sleep(sacekaj);
                   Timer t = new Timer(brzina, (ActionEvent e) -> {
                      SwingWorker sw = new SwingWorker() {
                           @Override
                           protected Object doInBackground() {
                               int onOff = (int) (System.currentTimeMillis() /1000);
                               if (onOff % 2 == 1) {
                                   jf.getContentPane().setBackground(c);
                               } else if (onOff % 2 == 0) {
                                   jf.getContentPane().setBackground(Color.white);
                               }
                               return null;
                           }
                       };
                              sw.execute();
                   });
                
                   jRadioButton.setEnabled(false);
                   jlOnTime.setEnabled(false);
                   JFormattedTextField.setEnabled(false);
                   jRadioButton1.setEnabled(false);
                   jlCoundown.setEnabled(false);
                   jtf.setEnabled(false);
                   jlNoColorSelected.setEnabled(false);
                   jbChooseColor.setEnabled(false);
                   jlSpeed.setEnabled(false);
                   jcb.setEnabled(false);
                   jbStart.setEnabled(false);
                    t.start();
                    jf.setSize(200, 200);
                    jf.setUndecorated(true);
                    jf.setVisible(true);
               } catch (InterruptedException ex) {
                   
               }
                   } 
           }
               if (jRadioButton1.isSelected()){
                   int countdown = (Integer.parseInt(jtf.getText())) * 1000;
                   int brzina =  jcb.getSelectedIndex()*1000;
               try {
                   Thread.sleep(countdown);
                    Timer t = new Timer(brzina, (ActionEvent e) -> {
                      SwingWorker sw = new SwingWorker() {
                           @Override
                           protected Object doInBackground() {
                               int onOff = (int) (System.currentTimeMillis() /1000);
                               if (onOff % 2 == 1) {
                                   jf.getContentPane().setBackground(c);
                               } else if (onOff % 2 == 0) {
                                   jf.getContentPane().setBackground(Color.white);
                               }
                               return null;
                           }
                       };
                              sw.execute();
                   });
                
                   jRadioButton.setEnabled(false);
                   jlOnTime.setEnabled(false);
                   JFormattedTextField.setEnabled(false);
                   jRadioButton1.setEnabled(false);
                   jlCoundown.setEnabled(false);
                   jtf.setEnabled(false);
                   jlNoColorSelected.setEnabled(false);
                   jbChooseColor.setEnabled(false);
                   jlSpeed.setEnabled(false);
                   jcb.setEnabled(false);
                   jbStart.setEnabled(false);
                    t.start();
                    jf.setSize(200, 200);
                    jf.setUndecorated(true);
                    jf.setVisible(true);
                   
               } catch (InterruptedException ex) {
                   Logger.getLogger(Tajmer.class.getName()).log(Level.SEVERE, null, ex);
               }
                }
          });
       
         jbStop.addActionListener((ActionEvent ae) -> {
           
          jRadioButton.setEnabled(true);
          jlOnTime.setEnabled(true);
          JFormattedTextField.setEnabled(true);
          jRadioButton1.setEnabled(true);
          jlCoundown.setEnabled(true);
          jtf.setEnabled(true);
          jlNoColorSelected.setEnabled(true);
          jbChooseColor.setEnabled(true);
          jlSpeed.setEnabled(true);
          jcb.setEnabled(true);
          jbStart.setEnabled(true);
          jf.dispose();
       
          
       });
       
        }
    }
}     