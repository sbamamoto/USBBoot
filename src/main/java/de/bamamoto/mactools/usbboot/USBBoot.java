/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bamamoto.mactools.usbboot;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author barmeier
 */
public class USBBoot extends javax.swing.JFrame {
    
    ArrayList<String> driveList = new ArrayList<>();
    
    /**
     * Creates new form USBBoot
     */
    public USBBoot() {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/icon/usbboot.png")).getImage());

    }

    protected String runProcess(List<String> commandLine) throws IOException {
        StringBuilder cl = new StringBuilder();
        for (String i : commandLine) {
            cl.append(i);
            cl.append(" ");
        }
        
        String result = "";
       
        ProcessBuilder builder = new ProcessBuilder(commandLine);
        Map<String, String> env = builder.environment();
        env.put("PATH", "/usr/sbin:/usr/bin:/sbin:/bin");
        builder.redirectErrorStream(true);

        Process process = builder.start();

        String line;

        InputStream stdout = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));

        while ((line = reader.readLine()) != null) {
            result += line + "\n";
        }

        boolean isProcessRunning = true;
        int maxRetries = 60;
        
        do {
            try {
                isProcessRunning = process.exitValue() < 0;
            } catch (IllegalThreadStateException ex) {
                System.out.println ("Process not terminated. Waiting ...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException iex) {
                    //nothing todo
                }
                maxRetries--;
            }
        } while (isProcessRunning && maxRetries > 0);
        System.out.println("Process has terminated");
        if (process.exitValue() != 0) {
            throw new IllegalStateException("Exit value not equal to 0: "+result);
        }
        if (maxRetries == 0 && isProcessRunning) {
            System.out.println("Process does not terminate. Try to kill the process now.");
            process.destroy();
        }
        
        return result;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txIsoFile = new javax.swing.JTextField();
        btSelectIso = new javax.swing.JButton();
        btStart = new javax.swing.JButton();
        btWrite = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbVolumes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Select ISO file:");

        btSelectIso.setText("...");
        btSelectIso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSelectIsoActionPerformed(evt);
            }
        });

        btStart.setText("Prepare");
        btStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStartActionPerformed(evt);
            }
        });

        btWrite.setText("Write To Stick");
        btWrite.setEnabled(false);
        btWrite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btWriteActionPerformed(evt);
            }
        });

        tbVolumes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Volume Name", "Drive"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbVolumes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbVolumesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbVolumes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txIsoFile, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btSelectIso, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btStart, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btWrite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txIsoFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSelectIso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btStart)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btWrite)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btSelectIsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSelectIsoActionPerformed
        JFileChooser jfc = new JFileChooser();
        jfc.setSize(800, 500);
        int returnc = jfc.showOpenDialog(this); 
        if (returnc == JFileChooser.APPROVE_OPTION) {
            txIsoFile.setText(jfc.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_btSelectIsoActionPerformed

    private void btStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStartActionPerformed
        if (new File(txIsoFile.getText()).exists()) {
            try {
                
                if (new File("/tmp/image.dmg").exists()) {
                    int result = JOptionPane.showConfirmDialog(this, "Press Yes to overwrite old image file.","Old image file found ...",JOptionPane.YES_NO_OPTION); 
                    if (result == JOptionPane.YES_OPTION) {
                        if (!new File("/tmp/image.dmg").delete()) {
                            JOptionPane.showMessageDialog(this, "Cannot delete [/tmp/image.dmg]. Preparing aborted.", "Removing old image failed", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Preparing operation canceld.");
                        return;
                    
                    }
                }
                
                runProcess (new ArrayList<>(Arrays.asList(new String []{"hdiutil", "convert", "-format", "UDRW", "-o", "/tmp/image", txIsoFile.getText()})));
                int result = JOptionPane.showConfirmDialog(this, "Be sure your USB Stick is inserted and available to the system.\nPress OK when ready.", 
                        "Scanning Drives", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(this, "Creating bootable USB-Stick - Aborted.");
                    return;
                }
                driveList.clear();
                String list = runProcess (new ArrayList<>(Arrays.asList(new String []{"/usr/sbin/diskutil", "list"})));
//                String list = runProcess (new String []{"/usr/sbin/diskutil", "list"});
                String drives[] = list.split("\n");
             
                for (String line:drives) {
                    if (line.contains("/dev/disk") && line.contains("external")) {
                        String [] devices = line.split(" ");
                        driveList.add(devices[0]);
                    }
                }
                
                list = runProcess (new ArrayList<>(Arrays.asList(new String []{"mount"})));
//                list = runProcess (new String []{"mount"});
                String mounts[] = list.split("\n");
             
                ArrayList<String> volumes = new ArrayList<>();
                Pattern p = Pattern.compile(".*\\/Volumes\\/(.*)\\(.*");
                DefaultListModel dlm = new DefaultListModel();
                DefaultTableModel dtm = new DefaultTableModel();
                Vector<Vector> rowData = new Vector<>();
                for (String drive:driveList) {
                    for (String mount : mounts) {
                        System.out.println("Mount: " + mount + " Drive: " + drive);
                        if (mount.startsWith(drive)) {
                            Matcher m = p.matcher(mount);
                            if (m.find()) {
                                Vector<String> row = new Vector<>();
                                row.add(m.group(1).trim());
                                row.add(drive.trim());
                                rowData.add(row);
                                System.out.println("Vol: "+m.group(1));
                            }
                        }
                    }
                }
                Vector<String> titles = new Vector<>();
                titles.add("Volume Name");
                titles.add("Device");
                tbVolumes.setModel(new DefaultTableModel(rowData, titles));
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.toString(), "Unexpected Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
            
        }
    }//GEN-LAST:event_btStartActionPerformed

    private void btWriteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btWriteActionPerformed
        try {
            String volume = (String)tbVolumes.getValueAt(tbVolumes.getSelectedRow(), 0);
            String device = (String)tbVolumes.getValueAt(tbVolumes.getSelectedRow(), 1);
            
            int result = JOptionPane.showConfirmDialog(this,
                    "All data on drive ["+volume+"] ond device [" + device + "] will be destroyed.\n Are you sure ?",
                    "Warning", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            
            if (result == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(this, "Write canceled", "Cancel", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            String list = runProcess (new ArrayList<>(Arrays.asList(new String []{"diskutil", "umountDisk", device})));
//            String list = runProcess (new String []{"diskutil", "umountDisk", device});
            String ddDevice = device.replace("disk", "rdisk");
            result = JOptionPane.showConfirmDialog(this,
                    "Running command:\n sudo dd if=/tmp/image.dmg of="
                            +ddDevice+" bs=1m\n\nAll data on the selected drive will be destroyed. Still sure ?",
                    "Warning", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (result == JOptionPane.YES_OPTION) { 
                System.out.println("cocoasudo dd if=/tmp/image.dmg of="
                            +ddDevice+" bs=1m");
                list = runProcess (new ArrayList<>(Arrays.asList(
                        new String []{
                            "Contents/Resources/cocoasudo",
                            "--prompt=Need root permissions to access USB stick.",                            
                            "dd", "if=/tmp/image.dmg", 
                            "of="+ddDevice, "bs=1m"})));  
                JOptionPane.showMessageDialog(this, "Your bootable USB Stick is now ready for use.");
            }
        } catch (Exception ex) {
            Logger.getLogger(USBBoot.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btWriteActionPerformed

    private void tbVolumesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVolumesMouseClicked
        btWrite.setEnabled(true);
    }//GEN-LAST:event_tbVolumesMouseClicked

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
            java.util.logging.Logger.getLogger(USBBoot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(USBBoot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(USBBoot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(USBBoot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new USBBoot().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSelectIso;
    private javax.swing.JButton btStart;
    private javax.swing.JButton btWrite;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbVolumes;
    private javax.swing.JTextField txIsoFile;
    // End of variables declaration//GEN-END:variables
}
