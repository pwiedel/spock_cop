package com.pmw.rps;

import com.pmw.rps.domain.PlayerMove;
import com.pmw.rps.domain.RPSResult;
import com.pmw.rps.exception.IllegalRPSMoveException;
import com.pmw.rps.service.RockPaperScissorsGameService;
import com.pmw.rps.service.RockPaperScissorsPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by Paul on 10/11/2015.
 */
@Component
public class RockPaperScissors {
    @Autowired
    private RockPaperScissorsGameService rpsService;

    @Autowired
    private RockPaperScissorsPlayerService rpspService;

    public RPSResult playGame(PlayerMove player1Move, PlayerMove player2Move) {
        RPSResult result = null;
        try {
            result = rpsService.resolveMatch(player1Move.getMove(), player2Move.getMove());
        } catch (IllegalRPSMoveException e) {
            e.printStackTrace();
        }
        rpspService.postResults(player1Move, player2Move, result);

        return result;
    }

}
