import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WrapperTest {

    @Test
    void shouldWrapBlankLine() {
        String sentence = "";
        String wrappedSentence = Wrapper.wrap(sentence, 0);

        assertEquals("", wrappedSentence);
    }

    @Nested
    class shouldWrapWithoutLineBreaks {
        @Test
        void whenSentenceIsShortEnough() {
            String sentence = "Salut mon pote";
            String wrappedSentence = Wrapper.wrap(sentence, sentence.length() + 2);

            assertEquals(sentence, wrappedSentence);
        }

        @Test
        void whenSentenceEndMatchColumnMaxSize() {
            String sentence = "Salut mon pote";
            String wrappedSentence = Wrapper.wrap(sentence, sentence.length());

            assertEquals(sentence, wrappedSentence);
        }

        @Test
        void whenSentenceOverflowColumnMaxSizeButCanBeTrimmed() {
            String sentence = "Salut mon pote         ";
            String wrappedSentence = Wrapper.wrap(sentence, sentence.length());

            String expected = "Salut mon pote";

            assertEquals(expected, wrappedSentence);
        }
    }

    @Nested
    class shouldWrapWithLineBreaks {

        @Test
        void whenSentenceOverflowColumnMaxSizeByReplacingLastChar() {
            String sentence = "Salut mon pote";
            int columnMaxSize = 10;
            String wrappedSentence = Wrapper.wrap(sentence, columnMaxSize);

            String expected = String.join(System.lineSeparator(), "Salut mon", "pote");

            assertEquals(expected, wrappedSentence);
        }

        @Test
        void whenSentenceOverflowColumnMaxSizeSeveralTimes() {
            String sentence = "Salut mon pote";
            int columnMaxSize = 8;
            String wrappedSentence = Wrapper.wrap(sentence, columnMaxSize);

            String expected = String.join(System.lineSeparator(), "Salut", "mon pote");

            assertEquals(expected, wrappedSentence);
        }

        @Test
        void whenSentenceOverflowColumnMaxSizeWithoutCuttingWord() {
            String sentence = "Salut mon pote, tu vas bien ?";
            int columnMaxSize = 8;
            String wrappedSentence = Wrapper.wrap(sentence, columnMaxSize);

            String expected = String.join(System.lineSeparator(), "Salut", "mon", "pote,", "tu vas", "bien ?");

            assertEquals(expected, wrappedSentence);
        }
    }

}
