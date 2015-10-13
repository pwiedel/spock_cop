package com.pmw.rps

import com.pmw.rps.domain.Player
import com.pmw.rps.domain.PlayerMove
import com.pmw.rps.domain.RPSMove
import com.pmw.rps.domain.RPSResult
import com.pmw.rps.exception.IllegalRPSMoveException
import com.pmw.rps.service.RockPaperScissorsGameService
import com.pmw.rps.service.RockPaperScissorsPlayerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * Created by Paul on 10/12/2015.
 */
@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = RockPapersScissorsApplication.class)
class RockPaperScissorsTest extends Specification {

    @Autowired
    RockPaperScissors rockPaperScissors

    def "test playGame"() {
        given:
        // we need to mock out behavior for this service
        RockPaperScissorsGameService rockPaperScissorsGameService = Mock()
        // we don't need to mock out anything for this service
        RockPaperScissorsPlayerService rockPaperScissorsPlayerService = Mock()

        1 * rockPaperScissorsGameService.resolveMatch(RPSMove.PAPER, RPSMove.ROCK) >> RPSResult.PLAYER_2_WINS

        rockPaperScissors.gameService = rockPaperScissorsGameService
        rockPaperScissors.playerService = rockPaperScissorsPlayerService
        PlayerMove player1Move = new PlayerMove(player: new Player(id: "ID1", name: "NAME 1"), move: RPSMove.PAPER)
        PlayerMove player2Move = new PlayerMove(player: new Player(id: "ID2", name: "NAME 2"), move: RPSMove.ROCK)

        when:
        RPSResult result = rockPaperScissors.playGame(player1Move, player2Move)

        then:
        notThrown(Exception)
        RPSResult.PLAYER_2_WINS == result
    }

    def "test IllegalMove exception"() {
        given:
        RockPaperScissorsPlayerService playerService = Mock()

        // we want to assert that should an illegal move be played that a disqualify result is posted
        1 * playerService.postResults(player1Move, player2Move, RPSResult.DISQUALIFY)
        rockPaperScissors.playerService = playerService

        RockPaperScissorsGameService gameService = Mock()
        gameService.resolveMatch(_ as RPSMove, _ as RPSMove) >> { throw new IllegalRPSMoveException() }
        rockPaperScissors.gameService = gameService

        when:
        RPSResult actualResult = rockPaperScissors.playGame(player1Move, player2Move)


        then:
        // the exception is caught within the class
        notThrown(IllegalRPSMoveException)
        expectedResult == actualResult

        where:
        expectedResult       | player1Move                                                                       | player2Move
        RPSResult.DISQUALIFY | new PlayerMove(player: new Player(id: "1", name: "Cheater"), move: RPSMove.PAPER) | new PlayerMove(player: new Player(id: "2", name: "not Cheater"), move: RPSMove.ROCK)
    }

    def "test for illegal arguments"() {
        given:
        RockPaperScissorsPlayerService playerService = Mock()
        rockPaperScissors.playerService = playerService

        when:
        RPSResult actualResult = rockPaperScissors.playGame(player1Move, player2Move)

        then:
        thrown(IllegalArgumentException)

        where:
        player1Move                                               | player2Move
        null                                                      | null
        new PlayerMove()                                          | null
        new PlayerMove(move: RPSMove.PAPER)                       | null
        new PlayerMove(move: RPSMove.PAPER, player: new Player()) | null
        null                                                      | new PlayerMove()
        new PlayerMove()                                          | new PlayerMove()
        new PlayerMove(move: RPSMove.PAPER)                       | new PlayerMove()
        new PlayerMove(move: RPSMove.PAPER, player: new Player()) | new PlayerMove()
        null                                                      | new PlayerMove(move: RPSMove.PAPER)
        new PlayerMove()                                          | new PlayerMove(move: RPSMove.PAPER)
        new PlayerMove(move: RPSMove.PAPER)                       | new PlayerMove(move: RPSMove.PAPER)
        new PlayerMove(move: RPSMove.PAPER, player: new Player()) | new PlayerMove(move: RPSMove.PAPER)
        null                                                      | new PlayerMove(move: RPSMove.PAPER, player: new Player())
        new PlayerMove()                                          | new PlayerMove(move: RPSMove.PAPER, player: new Player())
        new PlayerMove(move: RPSMove.PAPER)                       | new PlayerMove(move: RPSMove.PAPER, player: new Player())
    }


}
