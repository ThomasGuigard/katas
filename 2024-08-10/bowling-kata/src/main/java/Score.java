record Score(int score) {

    public static Score newScore() {
        return new Score(0);
    }

    public Score add(int newScore) {
        return new Score(score + newScore);
    }
}
