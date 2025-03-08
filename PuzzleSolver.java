import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class PuzzleSolver implements ActionListener 
{
    private Frame frame; // Main window frame
    private Panel menuPanel; // Panel for the menu
    private Panel puzzlePanel; // Panel for the puzzle
    private Label label1, label2, label3, label4, label5, label6, label7; // Labels for the puzzle question
    private Choice alexChoice, bellaChoice, chrisChoice, dianaChoice, ericChoice; // Dropdown choices for each engineer
    private Button solveButton, checkAnswerButton, showAnswerButton, clueButton, clearButton, backButton, exitButton; // Action buttons

    private final String[] engineers = {"Alex", "Bella", "Chris", "Diana", "Eric"}; // List of engineers
    private final String[] projects = {"Game Development", "Web Development", "Data Science", "AI", "Cybersecurity"}; // List of projects

    public PuzzleSolver() 
    {
        // Create the main frame
        frame = new Frame("Puzzle Solver"); 
        frame.setSize(800, 500); 
        frame.setLayout(new BorderLayout()); 
        initializeGUI(); 
        
        // Center the window on the screen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); 
    }

    private void initializeGUI() 
    {
        // Create menu panel
        menuPanel = new Panel();
        menuPanel.setLayout(new GridLayout(8, 1, 5, 5)); // Set layout for menu panel
        menuPanel.setBackground(Color.LIGHT_GRAY); // Set background color

        // Initialize all labels
        initializeLabels();
        
        // Add labels to menu panel
        menuPanel.add(label1);
        menuPanel.add(label2);
        menuPanel.add(label3);
        menuPanel.add(label4);
        menuPanel.add(label5);
        menuPanel.add(label6);
        menuPanel.add(label7);

        // Create button panel for solve button
        Panel buttonPanel = new Panel();
        solveButton = new Button("Solve Puzzle"); // Create solve button
        solveButton.setActionCommand("solve"); // Set action command for the button
        solveButton.addActionListener(this); // Add action listener to handle button clicks
        solveButton.setBackground(Color.BLUE); // Set button background color
        solveButton.setForeground(Color.WHITE); // Set button text color
        buttonPanel.add(solveButton); // Add button to button panel
        menuPanel.add(buttonPanel); // Add button panel to menu panel

        // Create puzzle panel
        puzzlePanel = new Panel();
        puzzlePanel.setLayout(new BorderLayout()); // Set layout for puzzle panel
        puzzlePanel.setBackground(Color.WHITE); // Set background color

        // Create and initialize the puzzle form panel
        Panel puzzleFormPanel = new Panel();
        puzzleFormPanel.setLayout(new GridLayout(5, 2, 10, 10)); // Set layout for puzzle form
        puzzleFormPanel.setBackground(Color.PINK); // Set background color
        initializePuzzleForm(puzzleFormPanel); // Initialize puzzle form with choices
        puzzlePanel.add(puzzleFormPanel, BorderLayout.CENTER); // Add puzzle form to puzzle panel

        // Create control button panel
        Panel controlButtonPanel = new Panel();
        controlButtonPanel.setLayout(new FlowLayout()); // Set layout for control buttons
        initializeButtons(controlButtonPanel); // Initialize control buttons
        puzzlePanel.add(controlButtonPanel, BorderLayout.SOUTH); // Add control buttons to puzzle panel

        // Add menu panel to the frame
        frame.add(menuPanel, BorderLayout.CENTER);
        
        // Add window listener to handle closing the window
        frame.addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) 
            {
                System.exit(0); // Exit the application when the window is closed
            }
        });
        frame.setVisible(true); // Make the frame visible
    }

    private void initializeLabels() 
    {
        Font labelFont = new Font("Arial", Font.PLAIN, 16); // Default font for labels

        // Initialize each label with text, font, background, and foreground colors
        label1 = new Label("WELCOME TO THE PROGRAMMING PROJECT PUZZLE!", Label.CENTER);
        label1.setFont(new Font("Serif", Font.BOLD, 18)); // Set font for label1
        label1.setBackground(Color.WHITE);
        label1.setForeground(Color.BLUE);

        label2 = new Label("There are five engineers - Alex, Bella, Chris, Diana, and Eric", Label.CENTER);
        label2.setFont(labelFont);
        label2.setBackground(Color.PINK);
        label2.setForeground(Color.BLACK);

        label3 = new Label("who each work on a unique programming project.", Label.CENTER);
        label3.setFont(labelFont);
        label3.setBackground(Color.PINK);
        label3.setForeground(Color.BLACK);

        label4 = new Label("The projects are classified into five categories", Label.CENTER);
        label4.setFont(labelFont);
        label4.setBackground(Color.PINK);
        label4.setForeground(Color.BLACK);

        label5 = new Label("(Web Development, Data Science, AI, Game Development, and Cybersecurity).", Label.CENTER);
        label5.setFont(labelFont);
        label5.setBackground(Color.PINK);
        label5.setForeground(Color.BLACK);

        label6 = new Label("Each engineer works on exactly two projects, and each project category is represented at least once.", Label.CENTER);
        label6.setFont(labelFont);
        label6.setBackground(Color.PINK);
        label6.setForeground(Color.BLACK);

        label7 = new Label("So, which engineer works on which projects?", Label.CENTER);
        label7.setFont(new Font("Arial", Font.BOLD, 16));
        label7.setBackground(Color.WHITE);
        label7.setForeground(Color.BLACK);
    }

    private void initializePuzzleForm(Panel panel) 
    {
        // Add labels and choices for each engineer
        panel.add(new Label("Alex:")); 
        alexChoice = createChoice(); 
        panel.add(alexChoice); 

        panel.add(new Label("Bella:"));
        bellaChoice = createChoice(); 
        panel.add(bellaChoice); 

        panel.add(new Label("Chris:")); 
        chrisChoice = createChoice(); 
        panel.add(chrisChoice); 

        panel.add(new Label("Diana:")); 
        dianaChoice = createChoice(); 
        panel.add(dianaChoice); 

        panel.add(new Label("Eric:")); 
        ericChoice = createChoice(); 
        panel.add(ericChoice); 
    }

    private Choice createChoice() 
    {
        Choice choice = new Choice(); // Create a new choice dropdown
        choice.setBackground(Color.WHITE); // Set background color
        choice.setForeground(Color.BLACK); // Set text color

        // Add project pairs to the dropdown
        choice.add("AI, Web Development");
        choice.add("Data Science, Game Development");
        choice.add("Game Development, AI");
        choice.add("Cybersecurity, Web Development");
        choice.add("Game Development, Cybersecurity");
        choice.add("Data Science, Web Development");

        return choice; // Return the created choice
    }

    private void initializeButtons(Panel panel)
    {
        // Initialize and add action buttons
        checkAnswerButton = new Button("Check Answer"); // Button to check the answer
        checkAnswerButton.setActionCommand("check"); 
        checkAnswerButton.addActionListener(this); 
        checkAnswerButton.setBackground(Color.GREEN); 
        checkAnswerButton.setForeground(Color.BLACK); 
        panel.add(checkAnswerButton); // Add button to panel

        showAnswerButton = new Button("Show Solution"); // Button to show the solution
        showAnswerButton.setActionCommand("showAnswer"); 
        showAnswerButton.addActionListener(this); 
        showAnswerButton.setBackground(Color.ORANGE); 
        showAnswerButton.setForeground(Color.BLACK); 
        panel.add(showAnswerButton); 

        clueButton = new Button("Clues"); // Button to show clues
        clueButton.setActionCommand("clue"); 
        clueButton.addActionListener(this); 
        clueButton.setBackground(Color.YELLOW); 
        clueButton.setForeground(Color.BLACK); 
        panel.add(clueButton);

        clearButton = new Button("Clear"); // Button to clear selections
        clearButton.setActionCommand("clear"); 
        clearButton.addActionListener(this); 
        clearButton.setBackground(Color.RED); 
        clearButton.setForeground(Color.WHITE); 
        panel.add(clearButton); 

        backButton = new Button("Back to Menu"); // Button to go back to menu
        backButton.setActionCommand("back"); 
        backButton.addActionListener(this); 
        backButton.setBackground(Color.CYAN); 
        backButton.setForeground(Color.BLACK); 
        panel.add(backButton); 
        
        exitButton = new Button("Exit"); // Button to exit the application
        exitButton.setActionCommand("exit");
        exitButton.addActionListener(this); 
        exitButton.setBackground(Color.MAGENTA); 
        exitButton.setForeground(Color.BLACK);
        panel.add(exitButton); 
    }

    public void actionPerformed(ActionEvent e) 
    {
        String command = e.getActionCommand(); // Get the action command from the event

        switch (command) 
        {
            case "solve":
                frame.remove(menuPanel); // Remove menu panel
                frame.add(puzzlePanel); // Add puzzle panel
                frame.revalidate(); // Refresh the frame
                frame.repaint(); // Repaint the frame
                break;

            case "check":
                checkAnswer(); // Check the user's answer
                break;

            case "showAnswer":
                showAnswer(); // Show the correct answer
                break;

            case "clue":
                showClues(); // Display clues
                break;

            case "clear":
                clearFields(); // Clear selections
                break;

            case "back":
                frame.remove(puzzlePanel); // Remove puzzle panel
                frame.add(menuPanel); // Add menu panel back
                frame.revalidate(); // Refresh the frame
                frame.repaint(); // Repaint the frame
                break;
                
            case "exit":
                System.exit(0); // Exit the application
                break;
        }
    }

    private void checkAnswer() 
    {
        // Get selected item for each engineer
        String[] selections = 
        {
            alexChoice.getSelectedItem(), 
            bellaChoice.getSelectedItem(), 
            chrisChoice.getSelectedItem(), 
            dianaChoice.getSelectedItem(), 
            ericChoice.getSelectedItem() 
        };

        if (isValidSolution(selections)) 
        {
            JOptionPane.showMessageDialog(frame, 
                "Congratulations! Your answer is correct!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE); // Show success message
        } 
        else 
        {
            JOptionPane.showMessageDialog(frame, 
                "Sorry, that's not correct. Try again!", 
                "Incorrect", 
                JOptionPane.ERROR_MESSAGE); // Show error message
        }
    }

    private boolean isValidSolution(String[] selections) 
    {
        Map<String, Set<String>> engineerProjects = new HashMap<>(); // Map to store engineer and their projects
        for (int i = 0; i < engineers.length; i++) 
        {
            engineerProjects.put(engineers[i], new HashSet<>(Arrays.asList(selections[i].split(", ")))); // Populate map with selections
        }

        // Validate each clue for correctness
        // Clue 1
        if (!engineerProjects.get("Alex").contains("Game Development") || 
            engineerProjects.get("Alex").contains("Web Development")) 
            return false;
        // Clue 2 and 6
        if (engineerProjects.get("Bella").contains("AI")) {
            if (engineerProjects.get("Bella").contains("Web Development")) 
            {
                // If Bella works on Web Development, Diana must work on Game Development
                if (engineerProjects.get("Diana").contains("Web Development")) 
                {
                    return false; // Diana cannot work on Web Development
                }
            } 
            else if (engineerProjects.get("Bella").contains("Game Development")) 
            {
                // If Bella works on Game Development, Diana must work on Web Development
                if (engineerProjects.get("Diana").contains("Game Development")) 
                {
                    return false; // Diana cannot work on Game Development
                }
            }
        }
        // Clue 3
        if (!engineerProjects.get("Chris").contains("Cybersecurity") || 
            !engineerProjects.get("Chris").contains("Web Development")) 
            return false;
        // Clue 4
        if (!engineerProjects.get("Diana").contains("Data Science") || 
            engineerProjects.get("Diana").contains("AI")) 
            return false;
        // Clue 5
        if (!engineerProjects.get("Eric").contains("Game Development") || 
            !engineerProjects.get("Eric").contains("Cybersecurity") || 
            engineerProjects.get("Eric").contains("Data Science")) 
            return false;
        // Clue 7
        long aiCount = engineerProjects.values().stream()
            .filter(p -> p.contains("AI")).count(); // Count how many engineers work on AI
        if (aiCount !=  2) 
            return false; // Ensure exactly two engineers work on AI

        return true; // All clues satisfied
    }

    private void showAnswer() 
    {
        String explanation = "Solution Explanation:\n\n" +
            "1. From clue 3: Chris works on Cybersecurity and Web Development\n\n" +
            "2. From clue 5: Eric works on Game Development and Cybersecurity but not Data Science\n\n" +
            "3. From clue 4: Diana works on Data Science but not AI\n" +
            "   - She must also work on either Game Development or Web Development\n\n" +
            "4. From clues 2 & 6: Bella works on AI but not Cybersecurity and Data Science\n" +
            "   - She must work on AI and either Web Development or Game Development\n\n" +
            "5. From clues 1 & 7: Alex works on Game Development but not on Web Development\n" +
            "   - He must also work on AI to satisfy clue 7 (AI needs two engineers)\n\n" +
            "(Note that if Bella works on Web Development, Diana must work on Game Development and otherwise)\n\n" +
            "Final Answer:\n" +
            "Alex: Game Development, AI\n" +
            "Bella: AI, Web Development / AI, Game Development\n" +
            "Chris: Cybersecurity, Web Development\n" +
            "Diana: Data Science, Game Development / Data Science, Web Development\n" +
            "Eric: Game Development, Cybersecurity";

        JOptionPane.showMessageDialog(frame, explanation, "Solution Explanation", 
            JOptionPane.INFORMATION_MESSAGE); // Show the solution explanation
    }

    private void showClues() 
    {
        String cluesText = "Clues:\n\n" +
            "1. Alex works on Game Development but not on Web Development.\n" +
            "2. Bella is involved in AI but not Cybersecurity.\n" +
            "3. The Cybersecurity project is handled by Chris, who also works on Web Development.\n" +
            "4. Diana does not work on AI but is involved in Data Science.\n" +
            "5. Eric handles Game Development and Cybersecurity but not Data Science.\n" +
            "6. Bella does not work on Data Science.\n" +
            "7. The AI project involves two engineers.";

        JOptionPane.showMessageDialog(frame, cluesText, "Puzzle Clues", 
            JOptionPane.INFORMATION_MESSAGE); // Show the clues
    }

    private void clearFields() 
    {
        // Reset all choices to the first option
        alexChoice.select(0); 
        bellaChoice.select(0); 
        chrisChoice.select(0); 
        dianaChoice.select(0); 
        ericChoice.select(0); 
        
        JOptionPane.showMessageDialog(frame, "All selections have been cleared!", 
            "Clear", JOptionPane.INFORMATION_MESSAGE); // Show clear confirmation
    }

    public static void main(String[] args) 
    {
        new PuzzleSolver(); // Start the application
    }
}