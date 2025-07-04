package gymproject;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

public class GymGUI {

    // GUI Components Declaration
    private JFrame pDisplayFrame, frame, gFrame, rFrame, pFrame;
    private JButton rPlanPriceButton, pDueCalculate, pMarkButton, pPayButton, pCalculateButton, pRevertButton, pDisplayButton, pAddButton, gButton, rButton, pButton, acButton, deacButton, resButton, rAddButton, rClearButton, rMarkButton, rUpgradeButton, rRevertButton, rDisplayButton, saveButton,readButton;
    private JPanel pMSDPanel, rGenderPanel, rDPanel, pDatePanel, fTPanel, fBPanel, gTPanel, gFPanel, rTPanel, rMPanel, rFPanel, rFTPanel, rFFPanel, rAPanel, rATPanel, rAFPanel;
    private JLabel rPlanLabel, pDueLabel, pPaidAmtLabel, pMALabel, pPDALabel, pCDLabel, pRPMLabel, pDisplayLabel, pRRLabel, pTrainerLabel, pMSDLabel, pDobLabel, fTLabel, sTLabel, acLabel, deacLabel, resLabel, rTLabel, rFTLabel, rATLabel, rIdLabel, rFullNameLabel, rLocationLabel, rPhoneNumberLabel, rEmailLabel, rDobLabel, rGenderLabel, rMembershipStartDateLabel, rReferralLabel, rAMALabel, rAUPLabel, rARRMLabel, rRemovalLabel, rADeactivateLabel, rDisplayLabel;
    private JTextField rPlanId, rPriceField, pDueIDField, pMAField, pPDAField, pPaidAmtField, pCDField, pRPMField, pDisplayField, pTrainerField, acField, deacField, resField, rIdField, rFullNameField, rLocationField, rPhoneNumberField, rEmailField, rReferralField, rAMAField, rAUPField, rARRMField, rDeactivateField, rDisplayField;
    private JComboBox rPlanPrice, rDYear, rDMonth, rDDay, rMembershipStartYear, rMembershipStartMonth, rMembershipStartDay, rPlan, pDYear, pDMonth, pDDay, pMSDYear, pMSDMonth, pMSDDay;
    private JRadioButton rMale, rFemale, rOthers;
    private JTextArea pDisplayArea, pRRArea, rRemovalArea;
    
    //Color Declaration
    Color lightBlue=new Color(111,230,252);
    Color yellow=new Color(255,250,141);
    
    // Frame and Panels
    JPanel rMSDPanel, pTPanel, pMPanel, pFPanel, pAPanel, pFTPanel, pFFPanel, pATPanel, pAFPanel, pGenderPanel;
    JLabel pTLabel, pFTLabel, pIdLabel, pNameLabel, pGenderLabel, pEmailLabel, pAddressLabel, pPhoneLabel, pATLabel;
    JTextField pIdField, pNameField, pAddressField, pEmailField, pPhoneField;
    JRadioButton pMaleRadio, pFemaleRadio, pOthersRadio;
    ButtonGroup pGenderGroup;
    JButton pRegisterButton, pClearButton, pAttendanceButton;

    /**
 * Gym Management System GUI interface handling member operations.
 * Provides functionality for managing both Regular and Premium gym members,
 * including registration, attendance tracking, membership upgrades, and file I/O operations.
 */
    /** Main list storing all gym members */
    private ArrayList<GymMember> members = new ArrayList<>();

    public ArrayList<GymMember> getMembers() {
        return members;
    }

      /**
     * Constructor initializes the main application window
     * and sets up core UI components.
     */
    public GymGUI() {
        frame = new JFrame("Gym Management");
        frame.setBounds(0, 0, 700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.lightGray);
        frame.setLayout(new BorderLayout());

        // Title Panel
        fTPanel = new JPanel(new FlowLayout());
        fTLabel = new JLabel("Gym Management Software");
        fTLabel.setFont(new Font("Arial", Font.BOLD, 24));
        fTPanel.add(fTLabel);
        frame.add(fTPanel, BorderLayout.NORTH);

        // Button Panel
        fBPanel = new JPanel(new GridLayout(3, 1, 10, 10)); // 3 buttons vertically
        gButton = new JButton("Gym Members");
        gButton.setBackground(lightBlue);
        gButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gMGui();
            }
        });

        rButton = new JButton("Regular Gym Members");
        rButton.setBackground(yellow);
        rButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rMGui();
            }
        });
        pButton = new JButton("Premium Gym Members");
        pButton.setBackground(lightBlue);
        pButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pMGui();
            }
        });
        fBPanel.add(gButton);
        fBPanel.add(rButton);
        fBPanel.add(pButton);

        frame.add(fBPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    /**
     * Creates the general member management window with activation/deactivation features
     */
    public void gMGui() {
        gFrame = new JFrame("Gym Members - Methods");
        gFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // gFrame.setBounds(0, 0, 700, 700);
        gFrame.setLayout(new BorderLayout());

        // Title Panel
        gTPanel = new JPanel(new FlowLayout());
        sTLabel = new JLabel("Gym Members Actions");
        sTLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gTPanel.add(sTLabel);
        gFrame.add(gTPanel, BorderLayout.NORTH);

        // form Panel with gridbaglayout
        gFPanel = new JPanel(new GridBagLayout());
        gFPanel.setBackground(lightBlue);
        GridBagConstraints sFGbc = new GridBagConstraints();
        sFGbc.insets = new Insets(10, 10, 10, 10);

        acLabel = new JLabel("Activate");
        sFGbc.gridx = 0;
        sFGbc.gridy = 0;
        gFPanel.add(acLabel, sFGbc);

        acField = new JTextField(30);
        sFGbc.gridx = 1;
        sFGbc.gridy = 0;
        gFPanel.add(acField, sFGbc);

        acButton = new JButton("Activate");
        sFGbc.gridx = 2;
        sFGbc.gridy = 0;
        acButton.setBackground(yellow);
        acButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int memberId = Integer.parseInt(acField.getText().trim());
                    boolean idisThere = false;

                    for (GymMember member : members) {
                        if (member.getId() == memberId) {
                            member.activateMembership();
                            idisThere = true;
                            JOptionPane.showMessageDialog(null, "Member Activated Successfully", "Success", JOptionPane.PLAIN_MESSAGE);
                            break;
                        }
                    }
                    if (!idisThere) {
                        JOptionPane.showMessageDialog(null, "Member Id not not Registered!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    // You can add your logic here, like searching in the ArrayList and activating a member
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Enter a valid ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        acButton.setSize(new Dimension(10, 20));
        gFPanel.add(acButton, sFGbc);

        deacLabel = new JLabel("Deactivate Membership");
        sFGbc.gridx = 0;
        sFGbc.gridy = 1;
        gFPanel.add(deacLabel, sFGbc);
        gFrame.add(gFPanel, BorderLayout.CENTER);

        deacField = new JTextField(30);
       
        sFGbc.gridx = 1;
        sFGbc.gridy = 1;
        gFPanel.add(deacField, sFGbc);

        deacButton = new JButton("Deactivate");
        deacButton.setSize(new Dimension(10, 20));
        deacButton.setBackground(yellow);
        sFGbc.gridx = 2;
        sFGbc.gridy = 1;
        deacButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int memberId = Integer.parseInt(deacField.getText().trim());
                    boolean idisThere = false;

                    for (GymMember member : members) {
                        if (member.getId() == memberId) {
                            member.deactivateMembership();
                            idisThere = true;
                            JOptionPane.showMessageDialog(null, "Member De Activated Successfully", "Success", JOptionPane.PLAIN_MESSAGE);
                            break;
                        }
                    }
                    if (!idisThere) {
                        JOptionPane.showMessageDialog(null, "Member Id not not Registered!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Enter a valid ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        gFPanel.add(deacButton, sFGbc);

        resLabel = new JLabel("Reset Member");
        sFGbc.gridx = 0;
        sFGbc.gridy = 2;
        gFPanel.add(resLabel, sFGbc);

        resField = new JTextField(30);
        sFGbc.gridx = 1;
        sFGbc.gridy = 2;
        gFPanel.add(resField, sFGbc);

        resButton = new JButton("Reset");
        resButton.setSize(new Dimension(10, 20));
        resButton.setBackground(yellow);
        sFGbc.gridx = 2;
        sFGbc.gridy = 2;
        resButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int memberId = Integer.parseInt(resField.getText().trim());
                    boolean idisThere = false;

                    for (GymMember member : members) {
                        if (member.getId() == memberId) {
                            member.resetMember();
                            idisThere = true;
                            JOptionPane.showMessageDialog(null, "Member Reset Successfully", "Success", JOptionPane.PLAIN_MESSAGE);
                            break;
                        }
                    }
                    if (!idisThere) {
                        JOptionPane.showMessageDialog(null, "Member Id not Registered!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    // You can add your logic here, like searching in the ArrayList and activating a member
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Enter a valid ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        gFPanel.add(resButton,sFGbc);

        saveButton=new JButton("Save to file");
        saveButton.setBackground(yellow);
        saveButton.setSize(new Dimension(10, 20));
        sFGbc.gridx = 1;
        sFGbc.gridy = 3;
        saveButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        writeMembersToFile(members);
    }
});

        gFPanel.add(saveButton, sFGbc);

        readButton=new JButton("Read from file");
        readButton.setBackground(yellow);
        readButton.setSize(new Dimension(10, 20));
        sFGbc.gridx = 1;
        sFGbc.gridy = 4;
        readButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
       File file = new File("MemberDetails.txt");

    if (!file.exists()) {
        JOptionPane.showMessageDialog(null, "MemberDetails.txt not found!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        Scanner scanner = new Scanner(file);
        String content = "";

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            content = content + line + "\n";
        }

        scanner.close();

        JTextArea textArea = new JTextArea(content);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(900, 400));

        JOptionPane.showMessageDialog(null, scrollPane, "Member Details", JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception ap) {
        JOptionPane.showMessageDialog(null, "Error reading MemberDetails.txt!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

});



        gFPanel.add(readButton, sFGbc);

        gFrame.setVisible(true);

    }

    /**
     * Creates the Regular Member management interface with registration and task features
     */
    public void rMGui() {
        rFrame = new JFrame("Gym Member - Regular Members - Methods");
        rFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // rFrame.setBounds(700, 0, 700, 700);     
        rFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rFrame.setBackground(Color.lightGray);
        rFrame.setLayout(new BorderLayout());

        rTPanel = new JPanel();
        rTLabel = new JLabel("Regular Member Methods");
        rTLabel.setFont(new Font("Arial", Font.BOLD, 24));
        rTPanel.add(rTLabel);

        rMPanel = new JPanel();
        rMPanel.setLayout(new GridLayout(1, 2));

        rFPanel = new JPanel();
        rFPanel.setLayout(new BorderLayout());
        // rFPanel.setBackground(Color.gray);
        rMPanel.add(rFPanel);

        rAPanel = new JPanel();
        rAPanel.setLayout(new BorderLayout());
        rAPanel.setBackground(lightBlue);
        rMPanel.add(rAPanel);

        rFTPanel = new JPanel();
        rFTPanel.setBackground(yellow);
        rFTPanel.setLayout(new FlowLayout());
        rFTLabel = new JLabel("Regular Member Registration");
        rFTLabel.setFont(new Font("Arial", Font.BOLD, 18));
        rFTPanel.add(rFTLabel);
        rFPanel.add(rFTPanel, BorderLayout.NORTH);

        rFFPanel = new JPanel();
        rFFPanel.setBackground(lightBlue);
        rFFPanel.setLayout(new GridBagLayout());
        GridBagConstraints rFGbc = new GridBagConstraints();
        rFGbc.insets = new Insets(10, 10, 10, 10);

        rIdLabel = new JLabel("ID");
        rFGbc.gridx = 0;
        rFGbc.gridy = 0;
        rFFPanel.add(rIdLabel, rFGbc);

        rIdField = new JTextField(20);
        rFGbc.gridx = 1;
        rFGbc.gridy = 0;
        rFFPanel.add(rIdField, rFGbc);

        rFullNameLabel = new JLabel("Full Name");
        rFGbc.gridx = 0;
        rFGbc.gridy = 1;
        rFFPanel.add(rFullNameLabel, rFGbc);

        rFullNameField = new JTextField(20);
        rFGbc.gridx = 1;
        rFGbc.gridy = 1;
        rFFPanel.add(rFullNameField, rFGbc);

        // Location
        rLocationLabel = new JLabel("Location");
        rFGbc.gridx = 0;
        rFGbc.gridy = 2;
        rFFPanel.add(rLocationLabel, rFGbc);

        rLocationField = new JTextField(20);
        rFGbc.gridx = 1;
        rFGbc.gridy = 2;
        rFFPanel.add(rLocationField, rFGbc);

// Phone Number
        rPhoneNumberLabel = new JLabel("Phone Number");
        rFGbc.gridx = 0;
        rFGbc.gridy = 3;
        rFFPanel.add(rPhoneNumberLabel, rFGbc);

        rPhoneNumberField = new JTextField(20);
        rFGbc.gridx = 1;
        rFGbc.gridy = 3;
        rFFPanel.add(rPhoneNumberField, rFGbc);

        // Email
        rEmailLabel = new JLabel("Email");
        rFGbc.gridx = 0;
        rFGbc.gridy = 4;
        rFFPanel.add(rEmailLabel, rFGbc);

        rEmailField = new JTextField(20);
        rFGbc.gridx = 1;
        rFGbc.gridy = 4;
        rFFPanel.add(rEmailField, rFGbc);

        rDobLabel = new JLabel("Date of Birth");
        rFGbc.gridx = 0;
        rFGbc.gridy = 5;
        rFFPanel.add(rDobLabel, rFGbc);

        rDPanel = new JPanel();
        rFGbc.gridx = 1;
        rFGbc.gridy = 5;

        rDYear = new JComboBox<>();
        for (int i = 2025; i >= 1875; i--) {
            rDYear.addItem(String.valueOf(i));
        }

        rDMonth = new JComboBox<>();
        for (int i = 1; i <= 12; i++) {
            rDMonth.addItem(String.valueOf(i));
        }

        rDDay = new JComboBox<>();
        for (int i = 1; i <= 31; i++) {
            rDDay.addItem(String.valueOf(i));
        }
        rDPanel.add(rDYear);
        rDPanel.add(rDMonth);
        rDPanel.add(rDDay);
        rFFPanel.add(rDPanel, rFGbc);

        rGenderLabel = new JLabel("Gender");
        rFGbc.gridx = 0;
        rFGbc.gridy = 6;
        rFFPanel.add(rGenderLabel, rFGbc);

        rGenderPanel = new JPanel();
        rFGbc.gridx = 1;
        rFGbc.gridy = 6;
        rMale = new JRadioButton("Male");
        rFemale = new JRadioButton("Female");
        rOthers = new JRadioButton("Others");

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rMale);
        genderGroup.add(rFemale);
        genderGroup.add(rFemale);
        genderGroup.add(rOthers);

        rGenderPanel.add(rMale);
        rGenderPanel.add(rFemale);
        rGenderPanel.add(rOthers);
        rFFPanel.add(rGenderPanel, rFGbc);

        rMembershipStartDateLabel = new JLabel("Membership Start Date");
        rFGbc.gridx = 0;
        rFGbc.gridy = 7;
        rFFPanel.add(rMembershipStartDateLabel, rFGbc);

// Membership Start Year
        rMSDPanel = new JPanel();
        rFGbc.gridx = 1;
        rFGbc.gridy = 7;
        rMembershipStartYear = new JComboBox<>();
        for (int i = 2025; i >= 2000; i--) {
            rMembershipStartYear.addItem(String.valueOf(i));
        }
        rMSDPanel.add(rMembershipStartYear);

// Membership Start Month
        rMembershipStartMonth = new JComboBox<>();
        for (int i = 1; i <= 12; i++) {
            rMembershipStartMonth.addItem(String.valueOf(i));
        }
        rMSDPanel.add(rMembershipStartMonth);

// Membership Start Day
        rMembershipStartDay = new JComboBox<>();
        for (int i = 1; i <= 31; i++) {
            rMembershipStartDay.addItem(String.valueOf(i));
        }
        rMSDPanel.add(rMembershipStartDay);
        rFFPanel.add(rMSDPanel, rFGbc);

        rReferralLabel = new JLabel("Referral Source");
        rFGbc.gridx = 0;
        rFGbc.gridy = 8;
        rFFPanel.add(rReferralLabel, rFGbc);

        rReferralField = new JTextField(20);
        rFGbc.gridx = 1;
        rFGbc.gridy = 8;
        rFFPanel.add(rReferralField, rFGbc);

        rAddButton = new JButton("Add Regular Member");
        rAddButton.setBackground(yellow);
        rFGbc.gridx = 0;
        rFGbc.gridy = 9;
        rFGbc.gridwidth = 2;
        rFGbc.fill = GridBagConstraints.HORIZONTAL;
        rFFPanel.add(rAddButton, rFGbc);

        rAddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Retrieve input values
                    int id = Integer.parseInt(rIdField.getText().trim());
                    String fullName = rFullNameField.getText().trim();
                    String location = rLocationField.getText().trim();
                    String phone = rPhoneNumberField.getText().trim();
                    String email = rEmailField.getText().trim();
                    String dob = rDYear.getSelectedItem() + "-" + rDMonth.getSelectedItem() + "-" + rDDay.getSelectedItem();
                    String gender = "";

                    if (rMale.isSelected()) {
                        gender = "Male";
                    } else if (rFemale.isSelected()) {
                        gender = "Female";
                    } else if (rOthers.isSelected()) {
                        gender = "Others";
                    }

                    String startDate = rMembershipStartYear.getSelectedItem() + "-" + rMembershipStartMonth.getSelectedItem() + "-" + rMembershipStartDay.getSelectedItem();
                    String referral = rReferralField.getText().trim();

                    // Check if all fields are filled
                    if (rIdField.getText().isEmpty() || fullName.isEmpty() || location.isEmpty() || phone.isEmpty() || email.isEmpty() || dob.isEmpty() || gender.isEmpty() || startDate.isEmpty() || referral.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Return early if any field is empty
                    }

                    // Check if ID already exists in members list
                    for (GymMember member : members) {
                        if (member.getId() == id) {
                            JOptionPane.showMessageDialog(null, "ID already exists. Please use a unique ID.", "Error", JOptionPane.ERROR_MESSAGE);
                            return; // Return early if ID is not unique
                        }
                    }

                    // Create and add new RegularMember to list
                    RegularMember rm = new RegularMember(id, fullName, location, phone, email, dob, gender, startDate, referral);
                    members.add(rm);

                    JOptionPane.showMessageDialog(null, "Regular Member added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid ID! Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        rClearButton = new JButton("Clear");
        rFGbc.gridx = 2;
        rFGbc.gridy = 9;
        rFGbc.gridwidth = 2;
        rFGbc.fill = GridBagConstraints.HORIZONTAL;
        rClearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rIdField.setText("");
                rFullNameField.setText("");
                rLocationField.setText("");
                rPhoneNumberField.setText("");
                rEmailField.setText("");
                rReferralField.setText("");
                genderGroup.clearSelection();
                rDYear.setSelectedIndex(0); // index 0 selects the value which is in the 0th index of the list
                rDMonth.setSelectedIndex(0);
                rDDay.setSelectedIndex(0);
                rMembershipStartYear.setSelectedIndex(0);
                rMembershipStartMonth.setSelectedIndex(0);
                rMembershipStartDay.setSelectedIndex(0);
            }
        });

        rFFPanel.add(rClearButton, rFGbc);
        rFPanel.add(rFFPanel, BorderLayout.CENTER);

        rATPanel = new JPanel();
        rATPanel.setLayout(new FlowLayout());
        rATPanel.setBackground(lightBlue);
        rATLabel = new JLabel("Regular Member Task");
        rATLabel.setFont(new Font("Arial", Font.BOLD, 18));
        rATPanel.add(rATLabel);
        rAPanel.add(rATPanel, BorderLayout.NORTH);

        rAFPanel = new JPanel();
        rAFPanel.setLayout(new GridBagLayout());
        rAFPanel.setBackground(yellow);
        rAPanel.add(rAFPanel, BorderLayout.CENTER);
        GridBagConstraints rAGbc = new GridBagConstraints();
        rAGbc.insets = new Insets(10, 10, 10, 10);

        rAMALabel = new JLabel("Mark Attendance");
        rAGbc.gridx = 0;
        rAGbc.gridy = 0;
        rAFPanel.add(rAMALabel, rAGbc);

        rAMAField = new JTextField(20);
        rAGbc.gridx = 1;
        rAGbc.gridy = 0;
        rAFPanel.add(rAMAField, rAGbc);

        rMarkButton = new JButton("Mark");
        rAGbc.gridx = 1;
        rAGbc.gridy = 1;
        rAGbc.gridwidth = 2;
        rAGbc.fill = GridBagConstraints.HORIZONTAL;
        rMarkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Validate field first
                if (rAMAField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Member ID cannot be empty!", "Input Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
        
                try {
                    int id = Integer.parseInt(rAMAField.getText().trim());
                    boolean found = false;
        
                    for (GymMember member : members) {
                        if (member.getId() == id) {
                            found = true;
        
                            if (member instanceof PremiumMember) {
                                JOptionPane.showMessageDialog(null, "This ID belongs to a Premium Member.", "Error", JOptionPane.ERROR_MESSAGE);
                            } 
                            else if (member instanceof RegularMember) {
                                if (member.isActiveStatus()) {
                                    member.markAttendance();
                                    JOptionPane.showMessageDialog(null, "Attendance Marked", "Success", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Member ID is not activated yet.", "Info", JOptionPane.WARNING_MESSAGE);
                                }
                            }
                            return; // Stop after matching member is found
                        }
                    }
        
                    if (!found) {
                        JOptionPane.showMessageDialog(null, "Member ID not registered.", "Info", JOptionPane.ERROR_MESSAGE);
                    }
        
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid numeric Member ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        rAFPanel.add(rMarkButton, rAGbc);

        rAUPLabel = new JLabel("Upgrade Plan");
        rAGbc.gridx = 0;
        rAGbc.gridy = 2;
        rAFPanel.add(rAUPLabel, rAGbc);

        rAUPField = new JTextField(20);
        rAGbc.gridx = 1;
        rAGbc.gridy = 2;
        rAFPanel.add(rAUPField, rAGbc);

        rUpgradeButton = new JButton("Upgrade");
        rAGbc.gridx = 1;
        rAGbc.gridy = 3;
        rUpgradeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(rAUPField.getText().trim());
                    boolean isThere = false;

                    for (GymMember member : members) {
                        if (member.getId() == id) {
                            isThere = true; // Move this up to prevent false "not found" alert

                            if (member instanceof RegularMember) {
                                RegularMember regularMember = (RegularMember) member;
                                String selectedPlan = (String) rPlan.getSelectedItem();

                                // Get the message from the upgradePlan method
                                String message = regularMember.upgradePlan(selectedPlan);

                                // Show the message returned by upgradePlan
                                JOptionPane.showMessageDialog(null, message, "Upgrade Status", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "This id belongs to Premium Member", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        }
                    }

                    if (!isThere) {
                        JOptionPane.showMessageDialog(null, "Member ID not Registered.", "Info", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Please input a valid Member ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        rAFPanel.add(rUpgradeButton, rAGbc);
        rPlan = new JComboBox<>();
        rPlan.addItem("Basic");
        rPlan.addItem("Standard");
        rPlan.addItem("Deluxe");
        rAGbc.gridx = 2;
        rAGbc.gridy = 2;
        rAFPanel.add(rPlan, rAGbc);

        rARRMLabel = new JLabel("Revert Member");
        rAGbc.gridx = 0;
        rAGbc.gridy = 4;
        rAFPanel.add(rARRMLabel, rAGbc);

        rARRMField = new JTextField(20);
        rAGbc.gridx = 1;
        rAGbc.gridy = 4;
        rAGbc.gridwidth = 2;
        rAGbc.fill = GridBagConstraints.HORIZONTAL;
        rAFPanel.add(rARRMField, rAGbc);

        rRemovalArea = new JTextArea(5, 20);
        rAGbc.gridx = 1;
        rAGbc.gridy = 5;
        rAGbc.fill = GridBagConstraints.HORIZONTAL;
        String removalReason = rRemovalArea.getText();
        rAFPanel.add(rRemovalArea, rAGbc);

        rRevertButton = new JButton("Revert");
        rAGbc.gridx = 1;
        rAGbc.gridy = 6;
        rAGbc.gridwidth = 2;
        rAGbc.fill = GridBagConstraints.HORIZONTAL;
        rRevertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!rRemovalArea.getText().isEmpty() && !rARRMField.getText().trim().isEmpty()) {
                    try {
                        int id = Integer.parseInt(rARRMField.getText().trim());
                        boolean isThere = false;

                        for (GymMember member : members) {
                            if (member.getId() == id) {
                                isThere = true; // Move this up to prevent false "not found" alert

                                if (member instanceof RegularMember) {
                                        RegularMember regularMember = (RegularMember) member;
                                        regularMember.revertRegularMember(removalReason);
                                        JOptionPane.showMessageDialog(null, "Member Reverted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                } 
                                else if (member instanceof PremiumMember) {
                                    PremiumMember premium = (PremiumMember) member;
                                    premium.revertPremiumMember();
                                    JOptionPane.showMessageDialog(null, "Member Reverted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                } 
                                break;
                            }
                        }

                        if (!isThere) {
                            JOptionPane.showMessageDialog(null, "Member ID not Registered.", "Info", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(null, "Please input a valid Member ID", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please input both the field to continue", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        rAFPanel.add(rRevertButton, rAGbc);

        rRemovalLabel = new JLabel("Removal Reason");
        rAGbc.gridx = 0;
        rAGbc.gridy = 5;
        rAFPanel.add(rRemovalLabel, rAGbc);

        rDisplayLabel = new JLabel("Display Member");
        rAGbc.gridx = 0;
        rAGbc.gridy = 7;
        rAFPanel.add(rDisplayLabel, rAGbc);

        rDisplayField = new JTextField(20); // 20 columns wide
        rAGbc.gridx = 1;                    // next to the label
        rAGbc.gridy = 7;
        rAFPanel.add(rDisplayField, rAGbc);

        rDisplayButton = new JButton("Display");
        rAGbc.gridx = 1;                    // next to the text field
        rAGbc.gridy = 8;
        rDisplayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = rDisplayField.getText().trim();

                if (!input.isEmpty()) {
                    try {
                        int id = Integer.parseInt(input);
                        boolean found = false;

                        for (GymMember member : members) {
                            if (member.getId() == id) {
                                found = true;

                                if (member instanceof RegularMember) {
                                    RegularMember regularMember = (RegularMember) member;
                                    String message = regularMember.display();
                                    JOptionPane.showMessageDialog(null, message, "Member Information", JOptionPane.INFORMATION_MESSAGE);
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "This id belongs to a Premium Member", "NO!!", JOptionPane.ERROR_MESSAGE);
                                }
                                break;
                            }
                        }

                        if (!found) {
                            JOptionPane.showMessageDialog(null, "Member ID not registered.", "Info", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid numeric Member ID", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a Member ID to continue", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        rAFPanel.add(rDisplayButton, rAGbc);

        rPlanLabel = new JLabel("Member Id");
        rAGbc.gridx = 0;
        rAGbc.gridy = 9;
        rAFPanel.add(rPlanLabel, rAGbc);

        rPlanId = new JTextField(20);
        rAGbc.gridx = 1;
        rAGbc.gridy = 9;
        rAFPanel.add(rPlanId, rAGbc);

        rPlanPrice = new JComboBox<>();
        rAGbc.gridx = 2;
        rAGbc.gridy = 9;
        rPlanPrice.addItem("Basic");
        rPlanPrice.addItem("Standard");
        rPlanPrice.addItem("Deluxe");
        rAFPanel.add(rPlanPrice, rAGbc);

        rPriceField = new JTextField(20);
        rAGbc.gridx = 4;
        rAGbc.gridy = 9;
        rAFPanel.add(rPriceField, rAGbc);

        rPlanPriceButton = new JButton("Plan and price");
        rAGbc.gridx = 1;
        rAGbc.gridy = 10;
        rPlanPriceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Parse the member ID from text field
                    int id = Integer.parseInt(rPlanId.getText().trim());
                    boolean found = false;

                    for (GymMember member : members) {
                        if (member.getId() == id) {
                            found = true;

                            if (member instanceof RegularMember) {
                                RegularMember regularMember = (RegularMember) member;

                                // Get member's current plan
                                String currentPlan = regularMember.getPlan();

                                // Set the selected item in the JComboBox
                                rPlanPrice.setSelectedItem(currentPlan);
                                rPlanPrice.setEnabled(false);  // make it non-editable

                                // Get price for the plan and show it
                                double price = regularMember.getPlanPrice(currentPlan);
                                rPriceField.setText(String.valueOf(price));
                                rPriceField.setEditable(false);

                                JOptionPane.showMessageDialog(null,
                                        "Plan and Price loaded for Member ID: " + id,
                                        "Info", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "This ID belongs to a Premium Member.", "Info", JOptionPane.ERROR_MESSAGE);
                            }

                            break;
                        }
                    }

                    if (!found) {
                        JOptionPane.showMessageDialog(null,
                                "Member ID not registered.",
                                "Info", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter a valid numeric Member ID.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } 
            }
        });

        rAFPanel.add(rPlanPriceButton, rAGbc);

        rMPanel.add(rAPanel);
        rFrame.add(rTPanel, BorderLayout.NORTH);
        rFrame.add(rMPanel, BorderLayout.CENTER);

        rFrame.setVisible(true);

    }

    /**
     * Creates the Premium Member management interface with payment and trainer features
     */
    public void pMGui() {
        pFrame = new JFrame("Gym Member - Premium Members - Methods");
        pFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        pFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pFrame.setBackground(Color.lightGray);
        pFrame.setLayout(new BorderLayout());

        pTPanel = new JPanel();
        pTLabel = new JLabel("Premium Member Methods");
        pTLabel.setFont(new Font("Arial", Font.BOLD, 24));
        pTPanel.add(pTLabel);

        pMPanel = new JPanel();
        pMPanel.setLayout(new GridLayout(1, 2));

        pFPanel = new JPanel();
        pFPanel.setLayout(new BorderLayout());
        pMPanel.add(pFPanel);

        pAPanel = new JPanel();
        pAPanel.setLayout(new BorderLayout());
        pAPanel.setBackground(lightBlue);
        pMPanel.add(pAPanel);

        pFTPanel = new JPanel();
        pFTPanel.setBackground(yellow);
        pFTPanel.setLayout(new FlowLayout());
        pFTLabel = new JLabel("Premium Member Registration");
        pFTLabel.setFont(new Font("Arial", Font.BOLD, 18));
        pFTPanel.add(pFTLabel);
        pFPanel.add(pFTPanel, BorderLayout.NORTH);

        pFFPanel = new JPanel();
        pFFPanel.setBackground(lightBlue);
        pFFPanel.setLayout(new GridBagLayout());
        GridBagConstraints pFGbc = new GridBagConstraints();
        pFGbc.insets = new Insets(10, 10, 10, 10);

        pIdLabel = new JLabel("ID");
        pFGbc.gridx = 0;
        pFGbc.gridy = 0;
        pFFPanel.add(pIdLabel, pFGbc);

        pIdField = new JTextField(20);
        pFGbc.gridx = 1;
        pFGbc.gridy = 0;
        pFFPanel.add(pIdField, pFGbc);

        pNameLabel = new JLabel("Name");
        pFGbc.gridx = 0;
        pFGbc.gridy = 1;
        pFFPanel.add(pNameLabel, pFGbc);

        pNameField = new JTextField(20);
        pFGbc.gridx = 1;
        pFGbc.gridy = 1;
        pFFPanel.add(pNameField, pFGbc);

        pAddressLabel = new JLabel("Location");
        pFGbc.gridx = 0;
        pFGbc.gridy = 2;
        pFFPanel.add(pAddressLabel, pFGbc);

        pAddressField = new JTextField(20);
        pFGbc.gridx = 1;
        pFGbc.gridy = 2;
        pFFPanel.add(pAddressField, pFGbc);

        pEmailLabel = new JLabel("Email");
        pFGbc.gridx = 0;
        pFGbc.gridy = 4;
        pFFPanel.add(pEmailLabel, pFGbc);

        pEmailField = new JTextField(20);
        pFGbc.gridx = 1;
        pFGbc.gridy = 4;
        pFFPanel.add(pEmailField, pFGbc);

        pPhoneLabel = new JLabel("Phone No");
        pFGbc.gridx = 0;
        pFGbc.gridy = 3;
        pFFPanel.add(pPhoneLabel, pFGbc);

        pPhoneField = new JTextField(20);
        pFGbc.gridx = 1;
        pFGbc.gridy = 3;
        pFFPanel.add(pPhoneField, pFGbc);

        pGenderLabel = new JLabel("Gender");
        pFGbc.gridx = 0;
        pFGbc.gridy = 7;
        pFFPanel.add(pGenderLabel, pFGbc);

        pGenderPanel = new JPanel();
        pGenderGroup = new ButtonGroup();
        pMaleRadio = new JRadioButton("Male");
        pFemaleRadio = new JRadioButton("Female");
        pOthersRadio = new JRadioButton("Others");
        pGenderGroup.add(pMaleRadio);
        pGenderGroup.add(pFemaleRadio);
        pGenderGroup.add(pOthersRadio);
        pGenderPanel.add(pMaleRadio);
        pGenderPanel.add(pFemaleRadio);
        pGenderPanel.add(pOthersRadio);
        pFGbc.gridx = 1;
        pFGbc.gridy = 7;
        pFFPanel.add(pGenderPanel, pFGbc);

        pDobLabel = new JLabel("Date of Birth");
        pFGbc.gridx = 0;
        pFGbc.gridy = 6;
        pFFPanel.add(pDobLabel, pFGbc);

        pDatePanel = new JPanel();
        pFGbc.gridx = 1;
        pFGbc.gridy = 6;
        pDYear = new JComboBox<>();
        for (int i = 2025; i >= 1875; i--) {
            pDYear.addItem(String.valueOf(i));
        }

        pDMonth = new JComboBox<>();
        for (int i = 1; i <= 12; i++) {
            pDMonth.addItem(String.valueOf(i));
        }

        pDDay = new JComboBox<>();
        for (int i = 1; i <= 30; i++) {
            pDDay.addItem(String.valueOf(i));
        }

        pDatePanel.add(pDYear);
        pDatePanel.add(pDMonth);
        pDatePanel.add(pDDay);
        pFFPanel.add(pDatePanel, pFGbc);

        pMSDLabel = new JLabel("Membership Start Date");
        pFGbc.gridx = 0;
        pFGbc.gridy = 8;
        pFFPanel.add(pMSDLabel, pFGbc);

        pMSDPanel = new JPanel();
        pFGbc.gridx = 1;
        pFGbc.gridy = 8;
        pMSDYear = new JComboBox<>();
        for (int i = 2025; i >= 1875; i--) {
            pMSDYear.addItem(String.valueOf(i));
        }

        pMSDMonth = new JComboBox<>();
        for (int i = 1; i <= 12; i++) {
            pMSDMonth.addItem(String.valueOf(i));
        }

        pMSDDay = new JComboBox<>();
        for (int i = 1; i <= 30; i++) {
            pMSDDay.addItem(String.valueOf(i));
        }

        pMSDPanel.add(pMSDYear);
        pMSDPanel.add(pMSDMonth);
        pMSDPanel.add(pMSDDay);
        pFFPanel.add(pMSDPanel, pFGbc);

        pTrainerLabel = new JLabel("Trainer");
        pFGbc.gridx = 0;
        pFGbc.gridy = 9;
        pFFPanel.add(pTrainerLabel, pFGbc);
        pFPanel.add(pFFPanel, BorderLayout.CENTER);

        pTrainerField = new JTextField(20);
        pFGbc.gridx = 1;
        pFGbc.gridy = 9;
        pFFPanel.add(pTrainerField, pFGbc);

        pAddButton = new JButton("Add Premium Member");
        pAddButton.setBackground(yellow);
        pFGbc.gridx = 0;
        pFGbc.gridy = 10;
        pFGbc.gridwidth = 2;
        pFGbc.fill = GridBagConstraints.HORIZONTAL;
        pAddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Retrieve input values
                    int id = Integer.parseInt(pIdField.getText().trim());
                    String fullName = pNameField.getText().trim();
                    String location = pAddressField.getText().trim();
                    String phone = pPhoneField.getText().trim();
                    String email = pEmailField.getText().trim();
                    String dob = pDYear.getSelectedItem() + "-" + pDMonth.getSelectedItem() + "-" + pDDay.getSelectedItem();
                    String gender = "";

                    if (pMaleRadio.isSelected()) {
                        gender = "Male";
                    } else if (pFemaleRadio.isSelected()) {
                        gender = "Female";
                    } else if (pOthersRadio.isSelected()) {
                        gender = "Others";
                    }

                    String startDate = pMSDYear.getSelectedItem() + "-" + pMSDMonth.getSelectedItem() + "-" + pMSDDay.getSelectedItem();
                    String trainer = pTrainerField.getText().trim();

                    // Check if all fields are filled
                    if (pIdField.getText().isEmpty() || fullName.isEmpty() || location.isEmpty() || phone.isEmpty() || email.isEmpty() || dob.isEmpty() || gender.isEmpty() || startDate.isEmpty() || trainer.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Return early if any field is empty
                    }

                    // Check if ID already exists in members list
                    for (GymMember member : members) {
                        if (member.getId() == id) {
                            JOptionPane.showMessageDialog(null, "ID already exists. Please use a unique ID.", "Error", JOptionPane.ERROR_MESSAGE);
                            return; // Return early if ID is not unique
                        }
                    }

                    // Create and add new RegularMember to list
                    PremiumMember pm = new PremiumMember(id, fullName, location, phone, email, dob, gender, startDate, trainer);
                    members.add(pm);

                    JOptionPane.showMessageDialog(null, "Premium Member added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid ID! Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        pFFPanel.add(pAddButton, pFGbc);
            
        pClearButton = new JButton("Clear");
        pFGbc.gridx = 2;
        pFGbc.gridy = 10;
        pClearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Clear all text fields
                pIdField.setText("");
                pNameField.setText("");
                pAddressField.setText("");
                pEmailField.setText("");
                pPhoneField.setText("");
                pTrainerField.setText("");

                // Reset JComboBox selections
                pDYear.setSelectedIndex(0);  // Set to the first year 
                pDMonth.setSelectedIndex(0); // Set to the first month
                pDDay.setSelectedIndex(0);   // Set to the first day
                pMSDYear.setSelectedIndex(0); // Set to the first year (or reset it)
                pMSDMonth.setSelectedIndex(0); // Set to the first month
                pMSDDay.setSelectedIndex(0);   // Set to the first day

                // Deselect gender radio buttons
                pGenderGroup.clearSelection();
            }
        });
        pFFPanel.add(pClearButton, pFGbc);

        pFPanel.add(pFFPanel, BorderLayout.CENTER);

        pATPanel = new JPanel();
        pATPanel.setLayout(new FlowLayout());
        pATPanel.setBackground(lightBlue);
        pATLabel = new JLabel("Actions");
        pATLabel.setFont(new Font("Arial", Font.BOLD, 18));
        pATPanel.add(pATLabel);
        pAPanel.add(pATPanel, BorderLayout.NORTH);

        pAFPanel = new JPanel();
        pAFPanel.setLayout(new GridBagLayout());  // Using GridBagLayout
        GridBagConstraints pAFGbc = new GridBagConstraints();
        pAFGbc.insets = new Insets(10, 10, 10, 10);
        pAFPanel.setBackground(yellow);

        pMALabel = new JLabel("Mark Attendance");
        pAFGbc.gridx = 0;  // Setting the grid position
        pAFGbc.gridy = 0;
        pAFPanel.add(pMALabel, pAFGbc);  // Add label with GridBagConstraints

        pMAField = new JTextField(20);
        pAFGbc.gridx = 1;  // Setting the grid position
        pAFGbc.gridy = 0;
        pAFPanel.add(pMAField, pAFGbc);  // Add label with GridBagConstraints

        pMarkButton = new JButton("Mark");
        pAFGbc.gridx = 2;
        pAFGbc.gridy = 0;
        pMarkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (pMAField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please input Member ID to continue","Error",JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    int id =Integer.parseInt(pMAField.getText().trim());
                    boolean found=false;
                    

                    for (GymMember member : members) {
                        if (member.getId()==id) {
                            found=true;

                            if (member instanceof RegularMember) {
                                JOptionPane.showMessageDialog(null, "This ID belongs to Regular Member", "Information",JOptionPane.INFORMATION_MESSAGE);
                            }
                            else if (member instanceof PremiumMember) {
                                if (member.isActiveStatus()) {
                                    member.markAttendance();
                                    JOptionPane.showMessageDialog(null, "Attendance Marked Successfully", "Success",JOptionPane.INFORMATION_MESSAGE);
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "This is is not yet to be Activated!!!", "Information", JOptionPane.WARNING_MESSAGE);
                                }
                                }
                            return;
                        }
                    }
                        if (!found) {
                            JOptionPane.showMessageDialog(null, "This ID is not registered till now", "Warning", JOptionPane.ERROR_MESSAGE);
                        }
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid numeric id","Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });



        pAFPanel.add(pMarkButton, pAFGbc);

        pCDLabel = new JLabel("Calculate Discount");
        pAFGbc.gridx = 0;
        pAFGbc.gridy = 1;
        pAFPanel.add(pCDLabel, pAFGbc);

        pCDField = new JTextField(20);
        pAFGbc.gridx = 1;
        pAFGbc.gridy = 1;
        pAFPanel.add(pCDField, pAFGbc);

        pCalculateButton = new JButton("Calculate");
        pAFGbc.gridx = 2;
        pAFGbc.gridy = 1;
        pCalculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = pCDField.getText().trim();

                if (!input.isEmpty()) {
                    try {
                        int id = Integer.parseInt(input);
                        boolean found = false;

                        for (GymMember member : members) {
                            if (member.getId() == id) {
                                found = true;

                                if (member instanceof PremiumMember) {
                                    PremiumMember premiumMember = (PremiumMember) member;
                                    double message = premiumMember.calculateDiscount();
                                    JOptionPane.showMessageDialog(null, "Your Discount is: " + message, "Member Information", JOptionPane.INFORMATION_MESSAGE);
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "This id belongs to Regular Member, Thus No discount!!" , "Member Information", JOptionPane.ERROR_MESSAGE);
                                }
                                break;
                            }
                        }

                        if (!found) {
                            JOptionPane.showMessageDialog(null, "Member ID not registered.", "Info", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid numeric Member ID", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a Member ID to continue", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        pAFPanel.add(pCalculateButton, pAFGbc);

        pRPMLabel = new JLabel("Revert Premium Member");
        pAFGbc.gridx = 0;
        pAFGbc.gridy = 2;
        pAFPanel.add(pRPMLabel, pAFGbc);

        pRPMField = new JTextField(20);
        pAFGbc.gridx = 1;
        pAFGbc.gridy = 2;
        pAFPanel.add(pRPMField, pAFGbc);

        pRevertButton = new JButton("Revert");
        pAFGbc.gridx = 2;
        pAFGbc.gridy = 2;
        pRevertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = pRPMField.getText().trim();

                if (!input.isEmpty()) {
                    try {
                        int id = Integer.parseInt(input);
                        boolean found = false;

                        for (GymMember member : members) {
                            if (member.getId() == id) {
                                found = true;

                                if (member instanceof PremiumMember) {
                                    PremiumMember premiumMember = (PremiumMember) member;
                                    premiumMember.revertPremiumMember();
                                    JOptionPane.showMessageDialog(null, "Premium Member Reverted Successfully", "Member Information", JOptionPane.INFORMATION_MESSAGE);
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "This id belongs to Regular Member", "Information", JOptionPane.ERROR_MESSAGE);
                                }
                                break;
                            }
                        }

                        if (!found) {
                            JOptionPane.showMessageDialog(null, "Member ID not registered.", "Info", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid numeric Member ID", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a Member ID to continue", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        pAFPanel.add(pRevertButton, pAFGbc);

        pDisplayLabel = new JLabel("Display");
        pAFGbc.gridx = 0;
        pAFGbc.gridy = 3;
        pAFPanel.add(pDisplayLabel, pAFGbc);

        pDisplayField = new JTextField(20);
        pAFGbc.gridx = 1;
        pAFGbc.gridy = 3;
        pAFPanel.add(pDisplayField, pAFGbc);

        pDisplayButton = new JButton("Display");
        pAFGbc.gridx = 2;
        pAFGbc.gridy = 3;
        pDisplayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = pDisplayField.getText().trim();

                if (!input.isEmpty()) {
                    try {
                        int id = Integer.parseInt(input);
                        boolean found = false;

                        for (GymMember member : members) {
                            if (member.getId() == id) {
                                found = true;

                                if (member instanceof PremiumMember) {
                                    PremiumMember premiumMember = (PremiumMember) member;
                                    String message = premiumMember.display();
                                    JOptionPane.showMessageDialog(null, message, "Member Information", JOptionPane.INFORMATION_MESSAGE);

                                } else {
                                    JOptionPane.showMessageDialog(null, "This ID belongs to a Regular Member.", "Info", JOptionPane.ERROR_MESSAGE);
                                }
                                break;
                            }
                        }

                        if (!found) {
                            JOptionPane.showMessageDialog(null, "Member ID not registered.", "Info", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid numeric Member ID", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a Member ID to continue", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        pAFPanel.add(pDisplayButton, pAFGbc);

        pPDALabel = new JLabel("Pay Due Amount");
        pAFGbc.gridx = 0;
        pAFGbc.gridy = 4;
        pAFPanel.add(pPDALabel, pAFGbc);

        pPDAField = new JTextField(20);
        pAFGbc.gridx = 1;
        pAFGbc.gridy = 4;
        pAFPanel.add(pPDAField, pAFGbc);

        pPaidAmtLabel = new JLabel("Paid Amount");
        pAFGbc.gridx = 0;
        pAFGbc.gridy = 5;
        pAFPanel.add(pPaidAmtLabel, pAFGbc);

        pPaidAmtField = new JTextField(20);
        pAFGbc.gridx = 1;
        pAFGbc.gridy = 5;
        pAFPanel.add(pPaidAmtField, pAFGbc);

        pPayButton = new JButton("Pay");
        pAFGbc.gridx = 2;
        pAFGbc.gridy = 5;
        pPayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idInput = pPDAField.getText().trim();
                String amountInput = pPaidAmtField.getText().trim(); // assume this JTextField exists

                if (idInput.isEmpty() || amountInput.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both Member ID and Amount to proceed.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    int id = Integer.parseInt(idInput);
                    double paidAmount = Double.parseDouble(amountInput);
                    boolean found = false;

                    for (GymMember member : members) {
                        if (member.getId() == id) {
                            found = true;

                            if (member instanceof PremiumMember) {
                                PremiumMember premiumMember = (PremiumMember) member;
                                String message = premiumMember.payDueAmount(paidAmount);
                                JOptionPane.showMessageDialog(null, message, "Payment Info", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "This id belongs to Regular Member!!! " , "Member Information", JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        }
                    }

                    if (!found) {
                        JOptionPane.showMessageDialog(null, "Member ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid ID or Amount. Please enter numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        pAFPanel.add(pPayButton, pAFGbc);

        pAPanel.add(pAFPanel, BorderLayout.CENTER);

        pFrame.add(pTPanel, BorderLayout.NORTH);
        pFrame.add(pMPanel, BorderLayout.CENTER);
        pFrame.setVisible(true);
    }

     /**
     * Writes member data to a formatted text file
     * @param memberList ArrayList containing all gym members
     */
    public void writeMembersToFile(ArrayList<GymMember> memberList) {
    File file = new File("MemberDetails.txt");
    try {
        FileWriter writer = new FileWriter(file); // no 'true' = overwrite mode

        // Header row
        writer.write(String.format("%-5s %-15s %-15s %-15s %-25s %-25s %-10s %-10s %-10s %-15s %-10s %-17s %-15s %-15s\n",
            "ID", "Name", "Location", "Phone", "Email", "Membership Start Date", "Plan", "Price",
            "Attendance", "Loyalty Points", "Active", "Referral/Trainer", "Discount", "Net Paid"));

        for (GymMember member : memberList) {
            if (member instanceof RegularMember) {
                RegularMember rm = (RegularMember) member;

                writer.write(String.format("%-5d %-15s %-15s %-15s %-25s %-25s %-10s %-10.2f %-10d %-15.0f %-10s %-17s %-15s %-15s\n",
                    rm.getId(),
                    rm.getName(),
                    rm.getLocation(),
                    rm.getPhone(),
                    rm.getEmail(),
                    rm.getMembershipStartDate(),
                    rm.getPlan(),
                    rm.getPrice(),
                    rm.getAttendance(),
                    rm.getLoyaltyPoints(),
                    rm.isActiveStatus() ? "Yes" : "No",
                    rm.getReferralSource(),
                    "-",   // Discount
                    "-"    // Paid
                ));
            } else if (member instanceof PremiumMember) {
                PremiumMember pm = (PremiumMember) member;

                writer.write(String.format("%-5d %-15s %-15s %-15s %-25s %-25s %-10s %-10.2f %-10d %-15.0f %-10s %-17s %-15.2f %-15.2f\n",
                    pm.getId(),
                    pm.getName(),
                    pm.getLocation(),
                    pm.getPhone(),
                    pm.getEmail(),
                    pm.getMembershipStartDate(),
                    "Premium",
                    pm.getPremiumCharge(),
                    pm.getAttendance(),
                    pm.getLoyaltyPoints(),
                    pm.isActiveStatus() ? "Yes" : "No",
                    pm.getPersonalTrainer(),
                    pm.getDiscountAmount(),
                    pm.getPaidAmount()
                ));
            }
        }

        writer.close();
        JOptionPane.showMessageDialog(null, "Members saved to memberDetails.txt", "Success", JOptionPane.INFORMATION_MESSAGE);

    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error writing to file!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    /** 
    * Entry point for the application
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        new GymGUI();
    }
}
