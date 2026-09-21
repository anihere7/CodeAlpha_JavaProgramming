import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChatbotGUI extends JFrame {

    private final Chatbot chatbot;
    private final JTextArea chatArea;
    private final JTextField inputField;
    private final JButton sendButton;

    public ChatbotGUI() {

        chatbot = new Chatbot();

        setTitle("JavaBot - AI Chatbot");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel("🤖 JavaBot");
        title.setFont(new Font("Arial", Font.BOLD, 26));

        JLabel subtitle = new JLabel("AI Chatbot • Java + NLP");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(245, 247, 250));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(new Color(245, 247, 250));

        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(4));
        titlePanel.add(subtitle);

        header.add(titlePanel, BorderLayout.WEST);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 16));
        chatArea.setBackground(Color.WHITE);
        chatArea.setBorder(new EmptyBorder(15, 15, 15, 15));

        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(210, 214, 220)
                        ),
                        new EmptyBorder(10, 10, 10, 10)
                )
        );

        sendButton = new JButton("Send");
        sendButton.setFont(new Font("Arial", Font.BOLD, 15));
        sendButton.setFocusPainted(false);

        JPanel inputPanel = new JPanel(new BorderLayout(10, 0));
        inputPanel.setBackground(new Color(245, 247, 250));

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        add(mainPanel);

        sendButton.addActionListener(this::sendMessage);

        inputField.addActionListener(this::sendMessage);

        showWelcomeMessage();
    }

    private void showWelcomeMessage() {

        chatArea.append(
                "JavaBot: Hello! 👋 I'm JavaBot.\n"
                        + "Ask me about Java, OOP, NLP, internships, "
                        + "or LeetCode.\n\n"
        );
    }

    private void sendMessage(ActionEvent event) {

        String userMessage = inputField.getText().trim();

        if (userMessage.isEmpty()) {
            return;
        }

        chatArea.append("You: " + userMessage + "\n");

        String response = chatbot.getResponse(userMessage);

        chatArea.append("JavaBot: " + response + "\n\n");

        inputField.setText("");

        inputField.requestFocus();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            ChatbotGUI gui = new ChatbotGUI();

            gui.setVisible(true);
        });
    }
}