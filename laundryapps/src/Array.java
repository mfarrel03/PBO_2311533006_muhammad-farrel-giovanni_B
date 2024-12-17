package TugasPertemuan7;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.awt.Font;
import java.util.ArrayList;

public class Array extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inputField;
    private JTextField checkField;
    private JTextArea dataArea;
    private ArrayList<Integer> dataList;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Array frame = new Array();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public Array() {
        dataList = new ArrayList<>();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 605, 294);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblData = new JLabel("Masukkan Data");
        lblData.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblData.setBounds(10, 25, 137, 14);
        contentPane.add(lblData);

        inputField = new JTextField();
        inputField.setBounds(10, 50, 391, 38);
        contentPane.add(inputField);
        inputField.setColumns(10);

        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String inputText = inputField.getText();
                    String[] numbers = inputText.split(",");
                    dataList.clear();
                    for (String num : numbers) {
                        dataList.add(Integer.parseInt(num.trim()));
                    }
                    updateDataArea();
                    inputField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Input harus berupa angka yang dipisahkan dengan koma.");
                }
            }
        });
        btnSimpan.setBounds(411, 50, 167, 38);
        contentPane.add(btnSimpan);

        JLabel lblData_2 = new JLabel("Data :");
        lblData_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblData_2.setBounds(10, 104, 137, 14);
        contentPane.add(lblData_2);

        dataArea = new JTextArea();
        dataArea.setBounds(61, 101, 518, 38);
        dataArea.setEditable(false);
        contentPane.add(dataArea);

        JLabel lbkCheckArray = new JLabel("Check Array Ke-");
        lbkCheckArray.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lbkCheckArray.setBounds(10, 154, 137, 26);
        contentPane.add(lbkCheckArray);

        checkField = new JTextField();
        checkField.setBounds(138, 150, 263, 38);
        contentPane.add(checkField);
        checkField.setColumns(10);

        JButton btnCheck = new JButton("Check");
        btnCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int index = Integer.parseInt(checkField.getText()) - 1; 
                    if (index >= 0 && index < dataList.size()) {
                        int value = dataList.get(index);
                        textField.setText("Index ke-" + (index + 1) + " adalah " + value);
                    } else {
                        textField.setText("Index di luar batas array.");
                    }
                } catch (NumberFormatException ex) {
                    textField.setText("Input harus berupa angka.");
                }
            }
        });

        btnCheck.setBounds(411, 150, 167, 38);
        contentPane.add(btnCheck);
        
        JLabel lblHasil = new JLabel("Hasil : ");
        lblHasil.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblHasil.setBounds(10, 198, 66, 37);
        contentPane.add(lblHasil);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(73, 200, 328, 38);
        contentPane.add(textField);
    }

    private void updateDataArea() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dataList.size(); i++) {
            sb.append(dataList.get(i));
            if (i < dataList.size() - 1) {
                sb.append(", ");
            }
        }
        dataArea.setText(sb.toString());
    }
}