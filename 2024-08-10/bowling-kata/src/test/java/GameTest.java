
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {

    @Nested
    class ScoreValidation {
        @Test()
        void shouldHaveScoreOfZeroAtStartOfGame() {
            Game game = new Game();

            assertThat(game.score(), is(0));
        }

        @Test()
        void shouldHaveAScoreOfFiveOnARollOfFivePins() {
            Game game = new Game();

            game.roll(5);

            assertThat(game.score(), is(5));
        }

        @Test()
        void shouldLimitMaxScoreToTenInAFrame() {
            Game game = new Game();

            assertThrows(ExceedingMaxPinsDowns.class, () -> game.roll(11));
        }

        @Test()
        void shouldLimitMaxScoreToTenInAFrameWithTwoRolls() {
            Game game = new Game();

            game.roll(5);
            assertThrows(ExceedingMaxPinsDowns.class, () -> game.roll(6));
        }

        @Test()
        void shouldAddNextRollScoreAsBonusWhenCurrentOneIsASpare() {
            Game game = new Game();

            game.roll(5);
            game.roll(5);

            game.roll(5);
            game.roll(2);

            assertThat(game.score(), is(22));
        }

        @Test()
        void shouldAddTwoNextTwoRollsScoreAsBonusWhenCurrentOneIsAStrike() {
            Game game = new Game();

            game.roll(10);

            game.roll(5);
            game.roll(2);

            assertThat(game.score(), is(24));
        }
    }

    @Nested()
    class FrameValidation {
        @Test()
        void shouldLimitGameToTenFrames() {
            Game game = new Game();

            for (int i = 0; i < 10; i++) {
                game.roll(2);
                game.roll(2);
            }

            assertThrows(ExceedingMaxFrames.class, () -> game.roll(6));
        }

        @Test()
        void shouldAllowExtraBallIfSpareOnTenthFrame() {
            Game game = new Game();
            int score = 0;

            for (int i = 0; i < 9; i++) {
                game.roll(2);
                game.roll(2);
                score += 4;
            }

            game.roll(4);
            score += 4;
            game.roll(6);
            score += 6;

            assertDoesNotThrow(() -> game.roll(6));
            score += 6;

            assertThat(game.score(), is(score));
        }
    }
}
