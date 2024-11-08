package edu.mephi.java.logic;

public class Session {

    private int score;

    public Session() {
        score = 0;
    }

    public void addScore(int additionScore) {
        score += additionScore;
    }

    public int getScore() {
        return score;
    }

}
