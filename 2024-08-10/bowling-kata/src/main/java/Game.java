public class Game {
    public static final int MAX_FRAMES = 10;
    private Score score = Score.newScore();
    Frames frames = Frames.empty();

    public Game() {
        moveToANewFrame();
    }

    public int score() {
        return score.score();
    }

    public void roll(int pinsDowned) {
        if (frames.count() == MAX_FRAMES && frames.currentFrameHasRolledMoreThanOnce()) throw new ExceedingMaxFrames();

        frames.registerRoll(pinsDowned);

        this.addToScore(pinsDowned);

        if (shouldEndCurrentFrame()) {
            moveToANewFrame();
        }
    }

    private boolean shouldEndCurrentFrame() {
        boolean shouldNotEndFrame = shouldNotEndFrame();
        return (frames.currentOneIsCompletedAndUnderMaxFrames(MAX_FRAMES)
                || frames.currentOneIsAStrike()) && !shouldNotEndFrame;
    }

    private boolean shouldNotEndFrame() {
        return frames.currentOneIsASpare() && frames.exceedMaxFrames(MAX_FRAMES);
    }

    private void moveToANewFrame() {
        frames.add(new Frame());
    }

    private void addToScore(int pinsDowned) {
        int newScore = pinsDowned;

        if (frames.previousFrameIsAStrike()) {
            newScore += pinsDowned;
        } else if (frames.previousFrameIsASpare() && frames.currentFrameHasBeenCalledOnce()) {
            newScore += pinsDowned;
        }

        score = score.add(newScore);
    }
}
