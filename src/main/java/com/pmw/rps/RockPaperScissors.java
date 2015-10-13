package com.pmw.rps;

import com.pmw.rps.domain.PlayerMove;
import com.pmw.rps.domain.RPSResult;
import com.pmw.rps.exception.IllegalRPSMoveException;
import com.pmw.rps.service.RockPaperScissorsGameService;
import com.pmw.rps.service.RockPaperScissorsPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Paul on 10/11/2015.
 */
@Component
public class RockPaperScissors {
    @Autowired
    private RockPaperScissorsGameService gameService;

    @Autowired
    private RockPaperScissorsPlayerService playerService;

    public RPSResult playGame(PlayerMove player1Move, PlayerMove player2Move) {
        RPSResult result = null;
        if(player1Move == null || player1Move.getMove() == null
                || player1Move.getPlayer() == null
                || player2Move == null || player2Move.getMove() == null
                || player2Move.getPlayer() == null) {
            throw new IllegalArgumentException();
        }

        try {
            result = gameService.resolveMatch(player1Move.getMove(), player2Move.getMove());
        } catch (IllegalRPSMoveException e) {
            e.printStackTrace();
            result = RPSResult.DISQUALIFY;
        }
        playerService.postResults(player1Move, player2Move, result);

        return result;
    }

}
