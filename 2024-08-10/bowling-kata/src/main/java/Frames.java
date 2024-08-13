import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

record Frames(Collection<Frame> frames) {

    public static Frames empty() {
        return new Frames(new ArrayList<>());
    }

    public void add(Frame frame) {
        frames.add(frame);
    }

    public int count() {
        return frames.size();
    }

    public void registerRoll(int pinsDowned) {
        currentFrame().registerRoll(pinsDowned);
    }

    public boolean currentOneIsCompletedAndUnderMaxFrames(int maxFrames) {
        return currentFrameHasRolledMoreThanOnce() && count() < maxFrames;
    }

    public boolean exceedMaxFrames(int maxFrames) {
        return count() == maxFrames;
    }

    public boolean currentOneIsASpare() {
        return frameAtIndexIsASpare(count() - 1);
    }

    public boolean previousFrameIsASpare() {
        return frameAtIndexIsASpare(count() - 2);
    }

    public boolean currentOneIsAStrike() {
        return frameAtIndexIsAStrike(count() - 1);

    }

    public boolean previousFrameIsAStrike() {
        return frameAtIndexIsAStrike(count() - 2);
    }

    public boolean currentFrameHasRolledMoreThanOnce() {
        return currentFrame().rollCount() > 1;
    }

    public boolean currentFrameHasBeenCalledOnce() {
        return currentFrame().rollCount() == 1;
    }

    private Frame currentFrame() {
        return asList().get(count() - 1);
    }

    private boolean frameAtIndexIsASpare(int index) {
        if (index < 0) return false;

        return asList().get(index).isASpare();
    }

    private boolean frameAtIndexIsAStrike(int index) {
        if (index < 0) return false;

        return asList().get(index).isAStrike();
    }

    private List<Frame> asList() {
        return frames.stream().toList();
    }
}
