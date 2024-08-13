class Frame {
    private static final int MAX_PINS_DOWNED = 10;
    private enum State {
        NORMAL,
        SPARE,
        STRIKE
    }
    private State state = State.NORMAL;


    private int pinsAlreadyDowned = 0;
    private int rollCount = 0;


    public void registerRoll(int pinsDowned) {
        if (newRollExceedMaxPinsDowned(pinsDowned)) throw new ExceedingMaxPinsDowns();

        if (pinsDowned == MAX_PINS_DOWNED &&  pinsAlreadyDowned == 0) {
            state = State.STRIKE;
        } else if ((pinsAlreadyDowned + pinsDowned) == MAX_PINS_DOWNED) {
            state = State.SPARE;
        }

        pinsAlreadyDowned += pinsDowned;
        rollCount++;
    }

    public int rollCount() {
        return rollCount;
    }

    public boolean isASpare() {
        return state.equals(State.SPARE);
    }

    public boolean isAStrike() {
        return state.equals(State.STRIKE);
    }

    private boolean newRollExceedMaxPinsDowned(int pinsDowned) {
        return (pinsAlreadyDowned + pinsDowned) > MAX_PINS_DOWNED;
    }


}
