package com.pmw.rps.service;

import com.pmw.rps.domain.RPSMove;
import com.pmw.rps.domain.RPSResult;
import com.pmw.rps.exception.IllegalRPSMoveException;
import org.springframework.stereotype.Service;

/**
 * Created by Paul on 10/11/2015.
 */
@Service
public class RockPaperScissorsGameService {
    public RPSResult resolveMatch(RPSMove move1, RPSMove move2) throws IllegalRPSMoveException {
        if(RPSMove.ROCK == move1) {
            if(RPSMove.ROCK == move2) {
                return RPSResult.TIE;
            }
            if(RPSMove.PAPER == move2) {
                return RPSResult.PLAYER_2_WINS;
            }
            if(RPSMove.SCISSOR == move2) {
                return RPSResult.PLAYER_1_WINS;
            }
        }
        if(RPSMove.PAPER == move1) {
            if(RPSMove.PAPER == move2) {
                return RPSResult.TIE;
            }
            if(RPSMove.SCISSOR == move2) {
                return RPSResult.PLAYER_2_WINS;
            }
            if (RPSMove.ROCK == move2) {
                return RPSResult.PLAYER_1_WINS;
            }
        }
        if(RPSMove.SCISSOR == move1) {
            if(RPSMove.SCISSOR == move2) {
                return RPSResult.TIE;
            }
            if(RPSMove.ROCK == move2) {
                return RPSResult.PLAYER_2_WINS;
            }
            if(RPSMove.PAPER == move2) {
                return RPSResult.PLAYER_1_WINS;
            }
        }
        throw new IllegalRPSMoveException("No legal move detected.");
    }
}
