package com.pmw.rps.domain;

/**
 * Created by Paul on 10/11/2015.
 */
public enum RPSResult {
    PLAYER_1_WINS("Player 1 Wins"), PLAYER_2_WINS("Player 2 Wins"), TIE("Tie"), DISQUALIFY("Disqualify");

    private final String display;

    RPSResult(String display) {
        this.display = display;
    }
}
