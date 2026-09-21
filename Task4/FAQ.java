import java.util.*;

public class FAQ {

    private final Map<String, String> responses;

    public FAQ() {
        responses = new LinkedHashMap<>();

        responses.put("hello", "Hello! 👋 How can I help you today?");
        responses.put("hi", "Hi there! 👋 What would you like to know?");
        responses.put("hey", "Hey! 😊 How can I assist you?");

        responses.put("name", "I'm JavaBot, an AI chatbot built using Java.");
        responses.put("who are you", "I'm JavaBot, a rule-based AI chatbot that uses basic NLP techniques.");

        responses.put("java", "Java is a popular object-oriented programming language used for web, mobile, desktop, and enterprise applications.");
        responses.put("oop", "OOP stands for Object-Oriented Programming. Its main concepts are Encapsulation, Inheritance, Polymorphism, and Abstraction.");
        responses.put("nlp", "NLP stands for Natural Language Processing. It helps computers process and understand human language.");

        responses.put("internship", "This chatbot is developed as part of a Java Programming internship project.");
        responses.put("leetcode", "LeetCode is a platform where you can practice programming and prepare for coding interviews.");

        responses.put("thank", "You're welcome! 😊");
        responses.put("thanks", "You're welcome! Happy to help! 😄");

        responses.put("bye", "Goodbye! 👋 Have a great day!");
        responses.put("goodbye", "See you later! 👋");
    }

    public String getResponse(String key) {
        return responses.get(key);
    }

    public Set<String> getKeywords() {
        return responses.keySet();
    }
}