package ui;

import control.MaintainProgramme;
import domain.Programme;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MaintainProgrammeFrame extends JFrame {

    private MaintainProgramme progControl;
    private JTextField jtfCode = new JTextField();
    private JTextField jtfName = new JTextField();
    private JTextField jtfFaculty = new JTextField();
    private JButton jbtAdd = new JButton("Create");
    private JButton jbtRetrieve = new JButton("Retrieve");
    private JButton jbtUpdate = new JButton("Update");
    private JButton jbtDelete = new JButton("Delete");

    public MaintainProgrammeFrame() {
        progControl = new MaintainProgramme();
        JPanel jpCenter = new JPanel(new GridLayout(3, 2));
        jpCenter.add(new JLabel("Programme Code"));
        jpCenter.add(jtfCode);
        jpCenter.add(new JLabel("Programme Name"));
        jpCenter.add(jtfName);
        jpCenter.add(new JLabel("Faculty"));
        jpCenter.add(jtfFaculty);
        add(jpCenter);

        JPanel jpSouth = new JPanel();
        jpSouth.add(jbtAdd);
        jpSouth.add(jbtRetrieve);
        jpSouth.add(jbtUpdate);
        jpSouth.add(jbtDelete);
        add(jpSouth, BorderLayout.SOUTH);

        jbtAdd.addActionListener(new AddListener());
        jbtRetrieve.addActionListener(new RetrieveListener());
        jbtUpdate.addActionListener(new UpdateListener());
        jbtDelete.addActionListener(new DeleteListener());

        setTitle("Programme CRUD");
        setSize(600, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void clearText() {
        jtfCode.setText("");
        jtfName.setText("");
        jtfFaculty.setText("");
        jtfCode.requestFocusInWindow();
    }

    private class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String code = jtfCode.getText();
            Programme p = progControl.selectRecord(code);

            if (p != null) {
                JOptionPane.showMessageDialog(null, "This programme code already exists.", "DUPLICATE RECORD", JOptionPane.ERROR_MESSAGE);
            } else {
                p = new Programme(code, jtfName.getText(), jtfFaculty.getText());
                progControl.addRecord(p);
                JOptionPane.showMessageDialog(null, "New Programme added.");
                clearText();
            }

        }
    }

    private class RetrieveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String code = jtfCode.getText();
            Programme p = progControl.selectRecord(code);
            if (p != null) {
                jtfName.setText(p.getName());
                jtfFaculty.setText(p.getFaculty());
            } else {
                JOptionPane.showMessageDialog(null, "No such programme code.", "RECORD NOT FOUND", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class UpdateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String code = jtfCode.getText();
            Programme p = progControl.selectRecord(code);
            if (p != null) {
                p.setName(jtfName.getText());
                p.setFaculty(jtfFaculty.getText());

                progControl.updateRecord(p);
                JOptionPane.showMessageDialog(null, "Record updated.");
                clearText();
            } else {
                JOptionPane.showMessageDialog(null, "No such programme code.", "RECORD NOT FOUND", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String code = jtfCode.getText();
            Programme p = progControl.selectRecord(code);
            if (p != null) {
                jtfName.setText(p.getName());
                jtfFaculty.setText(p.getFaculty());
                int option = JOptionPane.showConfirmDialog(null, "Are you sure? ");
                if (option == JOptionPane.YES_OPTION) {
                    progControl.deleteRecord(code);
                    JOptionPane.showMessageDialog(null, "Record deleted.");
                    clearText();
                }
            } else {
                JOptionPane.showMessageDialog(null, "No such programme code.", "RECORD NOT FOUND", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new MaintainProgrammeFrame();
    }
}