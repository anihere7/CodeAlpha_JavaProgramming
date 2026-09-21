import java.util.*;

public class NLPProcessor {

    private final FAQ faq;

    public NLPProcessor(FAQ faq) {
        this.faq = faq;
    }

    public String process(String input) {

        String text = input.toLowerCase()
                .replaceAll("[^a-zA-Z0-9\\s]", " ")
                .replaceAll("\\s+", " ")
                .trim();

        if (text.isEmpty()) {
            return "Please type something so I can help you. 😊";
        }

        String[] words = text.split("\\s+");

        String exactResponse = faq.getResponse(text);

        if (exactResponse != null) {
            return exactResponse;
        }

        String bestMatch = findBestMatch(words);

        if (bestMatch != null) {
            return faq.getResponse(bestMatch);
        }

        return "I'm not sure I understand that yet. 🤔 Try asking me about Java, OOP, NLP, internships, or LeetCode.";
    }

    private String findBestMatch(String[] words) {

        String bestKeyword = null;
        int highestScore = 0;

        for (String keyword : faq.getKeywords()) {

            int score = 0;

            String[] keywordWords = keyword.split("\\s+");

            for (String word : words) {
                for (String keywordWord : keywordWords) {

                    if (word.equals(keywordWord)) {
                        score += 2;
                    } else if (word.contains(keywordWord)
                            || keywordWord.contains(word)) {
                        score++;
                    }
                }
            }

            if (score > highestScore) {
                highestScore = score;
                bestKeyword = keyword;
            }
        }

        return highestScore > 0 ? bestKeyword : null;
    }
}