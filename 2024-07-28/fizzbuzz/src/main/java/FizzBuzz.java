import static io.vavr.API.println;

public class FizzBuzz {
    private final String FIZZ = "Fizz";
    private final String BUZZ = "Buzz";
    private final String FIZZ_BUZZ = String.join("", FIZZ, BUZZ);

    public void printOnAHundredLines() {
        for (int i = 1; i <= 100; i++) {
            if (validateFizzBuzzCondition(i)) {
                println(FIZZ_BUZZ);
            } else if (validateFizzCondition(i)) {
                println(FIZZ);
            } else if (validateBuzzCondition(i)) {
                println(BUZZ);
            } else {
                println(i);
            }
        }
    }

    private boolean validateFizzBuzzCondition(int i) {
        return validateBuzzCondition(i) && validateFizzCondition(i);
    }

    private boolean validateFizzCondition(int i) {
        return i % 3 == 0;
    }

    private boolean validateBuzzCondition(int i) {
        return i % 5 == 0;
    }
}
