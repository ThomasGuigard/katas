import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.vavr.API.println;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

public class FizzBuzzTest {

    static final String FIZZ = "Fizz";
    static final String BUZZ = "Buzz";
    static final String FIZZ_BUZZ = String.join("", FIZZ, BUZZ);

    @Test()
    void itShouldPrintALine() {
        ByteArrayOutputStream outContent = openWriteStream();

        // After this all System.out.println() statements will come to outContent stream.

        // So, you can normally call,
        println(""); // I will assume items is already initialized properly.

        //Now you have to validate the output. Let's say items had 1 element.
        // With name as FirstElement and number as 1.
        String expectedOutput  = "\n";

        // Do the actual assertion.
        assertContent(expectedOutput, outContent);
    }

    @Test()
    void itShouldPrintLinesFromOneToHundred() {
        ByteArrayOutputStream outContent = openWriteStream();

        for (int i = 1; i <= 100; i++) {
            println("");
        }

        String expectedOutput  = "\n".repeat(100);

        assertContent(expectedOutput, outContent);
    }

    @Test()
    void itShouldPrintLinesFromOneToHundredWithIndex() {
        ByteArrayOutputStream outContent = openWriteStream();

        for (int i = 1; i <= 100; i++) {
            println(i);
        }

        String expectedOutput = IntStream.range(1, 101)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining("\n"));

        assertContent(expectedOutput, outContent);
    }

    @Test()
    void itShouldPrintFizzOnLinesWhichAreMultiplesOfThree() {
        ByteArrayOutputStream outContent = openWriteStream();

        for (int i = 1; i <= 100; i++) {
            if (validateFizzCondition(i)) {
                println(FIZZ);
            } else {
                println(i);
            }
        }

        String expectedOutput = IntStream.range(1, 101)
                .mapToObj(i -> validateFizzCondition(i) ? FIZZ : String.valueOf(i))
                .collect(Collectors.joining("\n"));

        assertContent(expectedOutput, outContent);
    }

    @Test()
    void itShouldPrintBuzzOnLinesWhichAreMultiplesOfFive() {
        ByteArrayOutputStream outContent = openWriteStream();

        for (int i = 1; i <= 100; i++) {
            if (validateBuzzCondition(i)) {
                println(BUZZ);
            } else {
                println(i);
            }
        }

        String expectedOutput = IntStream.range(1, 101)
                .mapToObj(i -> validateBuzzCondition(i) ? BUZZ : String.valueOf(i))
                .collect(Collectors.joining("\n"));

        assertContent(expectedOutput, outContent);
    }

    @Test()
    void itShouldPrintBuzzOnLinesWhichAreMultiplesOfThreeAndFive() {
        ByteArrayOutputStream outContent = openWriteStream();

        for (int i = 1; i <= 100; i++) {
            if (validateFizzBuzzCondition(i)) {
                println(FIZZ_BUZZ);
            } else {
                println(i);
            }
        }

        String expectedOutput = IntStream.range(1, 101)
                .mapToObj(i -> validateFizzBuzzCondition(i) ? FIZZ_BUZZ : String.valueOf(i))
                .collect(Collectors.joining("\n"));

        assertContent(expectedOutput, outContent);
    }

    @Test()
    void itShouldPrintFizzOrBuzzOrFizzButtOnAHundredLines() {
        ByteArrayOutputStream outContent = openWriteStream();
        FizzBuzz fizzBuzz = new FizzBuzz();

        fizzBuzz.printOnAHundredLines();

        String expectedOutput = IntStream.range(1, 101)
                .mapToObj(i -> {
                    if (validateFizzBuzzCondition(i)) {
                        return (FIZZ_BUZZ);
                    } else if (validateFizzCondition(i)) {
                        return (FIZZ);
                    } else if (validateBuzzCondition(i)) {
                        return (BUZZ);
                    } else {
                        return String.valueOf(i);
                    }
                })
                .collect(Collectors.joining("\n"));

        assertContent(expectedOutput, outContent);
    }

    private static boolean validateFizzBuzzCondition(int i) {
        return validateBuzzCondition(i) && validateFizzCondition(i);
    }

    private static boolean validateFizzCondition(int i) {
        return i % 3 == 0;
    }

    private static boolean validateBuzzCondition(int i) {
        return i % 5 == 0;
    }

    private static void assertContent(String expectedOutput, ByteArrayOutputStream outContent) {
        assertLinesMatch(expectedOutput.lines(), outContent.toString().lines());
    }

    private static ByteArrayOutputStream openWriteStream() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        return outContent;
    }
}
