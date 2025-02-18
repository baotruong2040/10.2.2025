import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.filechooser.FileNameExtensionFilter;

public class App extends JFrame implements ActionListener{
    public JFrame frame;
    public JTextArea textArea;
    private String currentPath = null;
    public JFileChooser j;
    public static String fileInput(String fileName) {
        String str = "";
        try {
            FileInputStream fi = new FileInputStream(fileName);
            int i;
            while ((i = fi.read()) != -1) {
                str += (char) i;
            }
            fi.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
    public App() {
        frame = new JFrame("PadThai") ;
        textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 4, 1, 4));

        JMenuBar mb= new JMenuBar();
        JMenu m1 = new JMenu("File");
        
        JMenuItem mi1 = new JMenuItem("New");
        JMenuItem mi2 = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem mi3 = new JMenuItem("Save As");

        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        save.addActionListener(this);

        m1.add(mi1);
        m1.add(mi2);
        m1.add(save);
        m1.add(mi3);
        // Create amenu for menu
        JMenu m2 = new JMenu("Edit");
 
        // Create menu items
        JMenuItem mi4 = new JMenuItem("cut");
        JMenuItem mi5 = new JMenuItem("copy");
        JMenuItem mi6 = new JMenuItem("paste");
 
        // Add action listener
        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);
 
        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);
 
        JMenuItem mc = new JMenuItem("close");
 
        mc.addActionListener(this);
 
        mb.add(m1);
        mb.add(m2);
        mb.add(mc);

        frame.setLayout(new BorderLayout());
        frame.setJMenuBar(mb);
        frame.setSize(700, 400);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        j = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        j.setFileFilter(filter);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if (str.equals("cut")) {
            textArea.cut();
        }
        else if (str.equals("copy")) {
            textArea.copy();
        }
        else if (str.equals("paste")) {
            textArea.paste();
        }
        else if (str.equals("Save As")) {
            
 
            int r = j.showSaveDialog(null);
 
            if (r == JFileChooser.APPROVE_OPTION) {
 
                File fi = new File(j.getSelectedFile().getAbsolutePath());
 
                try {
                    // Create a file writer
                    FileWriter wr = new FileWriter(fi, false);
 
                    // Create buffered writer to write
                    BufferedWriter w = new BufferedWriter(wr);
 
                    // Write
                    w.write(textArea.getText());
 
                    w.flush();
                    w.close();
                }
                catch (Exception evt) {
                    JOptionPane.showMessageDialog(frame, evt.getMessage());
                }
            }
            // If the user cancelled the operation
            else
                JOptionPane.showMessageDialog(frame, "the user cancelled the operation");
        }
        else if(str.equals("Open")) {
            
            int r = j.showOpenDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                textArea.setText(fileInput(j.getSelectedFile().getAbsolutePath()));
                currentPath = j.getSelectedFile().getAbsolutePath();
            }
        }
        else if (str.equals("New")) {
            textArea.setText("");
            currentPath = null;
        
        }
        else if(str.equals("close")) {
            System.exit(0);
        }
        else if(str.equals("Save")) {
            if (currentPath == null) {
                j.setSelectedFile(null);
                int r = j.showSaveDialog(null);
 
            if (r == JFileChooser.APPROVE_OPTION) {
 
                File fi = new File(j.getSelectedFile().getAbsolutePath());
 
                try {
                    // Create a file writer
                    FileWriter wr = new FileWriter(fi, false);
 
                    // Create buffered writer to write
                    BufferedWriter w = new BufferedWriter(wr);
 
                    // Write
                    w.write(textArea.getText());
 
                    w.flush();
                    w.close();
                }
                catch (Exception evt) {
                    JOptionPane.showMessageDialog(frame, evt.getMessage());
                }
                currentPath = j.getSelectedFile().getAbsolutePath();
            }
            // If the user cancelled the operation
            else
                JOptionPane.showMessageDialog(frame, "the user cancelled the operation");
            }
            else if(currentPath != null) {
                try{
                FileWriter wr = new FileWriter(currentPath);
                wr.write(textArea.getText());
                wr.flush();
                wr.close();
                }catch(Exception exc) {
                    exc.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        new App();
    }


    
}