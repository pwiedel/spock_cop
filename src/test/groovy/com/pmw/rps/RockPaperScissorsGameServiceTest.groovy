package com.pmw.rps

import com.pmw.rps.domain.RPSMove
import com.pmw.rps.domain.RPSResult
import com.pmw.rps.service.RockPaperScissorsGameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by Paul on 10/11/2015.
 */
@ContextConfiguration(loader=SpringApplicationContextLoader.class, classes=RockPapersScissorsApplication.class)
class RockPaperScissorsGameServiceTest extends Specification {

    @Autowired
    RockPaperScissorsGameService service

    @Unroll
    def "test happy path for RPS: expecting result of #expectedResult where player1 performs #player1Move and player2 performs #player2Move"() {
        when:
        RPSResult actualResult = service.resolveMatch(player1Move, player2Move)

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
