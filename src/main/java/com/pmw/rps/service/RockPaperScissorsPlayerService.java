package com.pmw.rps.service;

import com.pmw.rps.domain.PlayerMove;
import com.pmw.rps.domain.RPSResult;
import org.springframework.stereotype.Service;

/**
 * Created by Paul on 10/11/2015.
 */
@Service
public class RockPaperScissorsPlayerService {
    public void postResults(PlayerMove player1Move, PlayerMove player2Move, RPSResult result) {
        // this is where results would be posted to data stores
    }
}
