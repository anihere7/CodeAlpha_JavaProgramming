public class Chatbot {

    private final NLPProcessor nlpProcessor;

    public Chatbot() {
        FAQ faq = new FAQ();
        nlpProcessor = new NLPProcessor(faq);
    }

    public String getResponse(String userInput) {
        return nlpProcessor.process(userInput);
    }
}