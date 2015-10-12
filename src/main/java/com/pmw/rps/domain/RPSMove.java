package com.pmw.rps.domain;

/**
 * Created by Paul on 10/11/2015.
 */
public enum RPSMove {
    ROCK("Rock"), PAPER("Paper"), SCISSOR("Scissor");

    private final String display;

    RPSMove(String display) {
        this.display = display;
    }
}
