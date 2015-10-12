package com.pmw.rps

import com.pmw.rps.config.RPSConfig
import com.pmw.rps.domain.RPSMove
import com.pmw.rps.domain.RPSResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import spock.lang.Specification

/**
 * Created by Paul on 10/11/2015.
 */
@SpringApplicationConfiguration(classes = RockPapersScissorsApplication.class)
class RockPaperScissorsTest extends Specification {

    @Autowired
    RockPaperScissors rockPaperScissors

    def "test happy path for RPS"() {
        when:
        RPSResult actualResult = rockPaperScissors.playGame(player1Move, player2Move)

        then:
        notThrown(Exception)
        expectedResult == actualResult

        where:
        expectedResult          | player1Move     | player2Move
        RPSResult.PLAYER_1_WINS | RPSMove.ROCK    | RPSMove.SCISSOR
        RPSResult.PLAYER_1_WINS | RPSMove.PAPER   | RPSMove.ROCK
        RPSResult.PLAYER_1_WINS | RPSMove.SCISSOR | RPSMove.PAPER
        RPSResult.PLAYER_2_WINS | RPSMove.ROCK    | RPSMove.PAPER
        RPSResult.PLAYER_2_WINS | RPSMove.PAPER   | RPSMove.SCISSOR
        RPSResult.PLAYER_2_WINS | RPSMove.SCISSOR | RPSMove.ROCK
        RPSResult.TIE           | RPSMove.SCISSOR | RPSMove.SCISSOR
        RPSResult.TIE           | RPSMove.ROCK    | RPSMove.ROCK
        RPSResult.TIE           | RPSMove.PAPER   | RPSMove.PAPER
    }

}
