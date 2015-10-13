package com.pmw.rps.domain;

/**
 * Created by Paul on 10/11/2015.
 */
public class PlayerMove {

    private Player player;
    private RPSMove move;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerMove that = (PlayerMove) o;

        if (player != null ? !player.equals(that.player) : that.player != null) return false;
        return move == that.move;

    }

    @Override
    public int hashCode() {
        int result = player != null ? player.hashCode() : 0;
        result = 31 * result + (move != null ? move.hashCode() : 0);
        return result;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public RPSMove getMove() {
        return move;
    }

    public void setMove(RPSMove move) {
        this.move = move;
    }
}
