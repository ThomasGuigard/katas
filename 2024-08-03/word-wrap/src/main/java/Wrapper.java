public class Wrapper {

    private static final String BLANK_SPACE = " ";

    static public String wrap(String sentence, int columnMaxSize) {
        String sentenceToProcess = sentence.trim();

        if (sentenceToProcess.length() <= columnMaxSize) return sentenceToProcess;

        int lastSpaceAvailable = sentenceToProcess.substring(0, columnMaxSize).lastIndexOf(BLANK_SPACE);
        String wrappedSentence = sentenceToProcess.substring(0, lastSpaceAvailable);
        String remainingSentence = sentenceToProcess.substring(lastSpaceAvailable + 1);

        return String.join(System.lineSeparator(), wrappedSentence, wrap(remainingSentence, columnMaxSize));
    }
}
