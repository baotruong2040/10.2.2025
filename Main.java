import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.*;
public class Main {
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
    public static void main(String[] args) {
        JFrame frame = new JFrame("File Reader");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JButton chooseButton = new JButton("Choose File");
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(chooseButton);

        JTextArea textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(textArea);

        JLabel label = new JLabel("File Reader");
        scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 15, 15, 15));

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);

        final JFileChooser fileDialog = new JFileChooser();
        
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileDialog.showOpenDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: " + fileDialog.getSelectedFile().getName());
                    System.out.println(fileDialog.getSelectedFile().getAbsolutePath());
                    textArea.setText(fileInput(fileDialog.getSelectedFile().getName()));
                }
            }
        });
    }
}