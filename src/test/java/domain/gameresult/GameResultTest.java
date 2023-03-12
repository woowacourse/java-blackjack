package domain.gameresult;

import domain.card.Hand;
import domain.player.Name;
import domain.player.Gambler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameResultTest {
    @Test
    @DisplayName("주어진 플레이어의 무승부 카운트를 올리면 딜러와 참가자 양 쪽에 무승부 카운트가 1 올라간다.")
    void givenParticipant_thenCountDraw() {
        Gambler gambler = new Gambler(Hand.makeEmptyHolder(), Name.of("테스트"));
        GameResult gameResult = new GameResult();

        gameResult.addDrawCount(gambler);

        Result participantResult = gameResult.getResultByParticipant(gambler);
        int drawingCountOfDealer = gameResult.getDrawingCountOfDealer();
        assertAll(
                () -> assertThat(participantResult).isEqualTo(Result.DRAW),
                () -> assertThat(drawingCountOfDealer).isEqualTo(1)
        );
    }

    @Test
    @DisplayName("주어진 플레이어의 승리 카운트를 올리면 딜러는 패배 카운트가 올라간다.")
    void givenWinningParticipant_thenCountWinning() {
        Gambler gambler = new Gambler(Hand.makeEmptyHolder(), Name.of("테스트"));
        GameResult gameResult = new GameResult();

        gameResult.addParticipantWinningCase(gambler);

        Result participantResult = gameResult.getResultByParticipant(gambler);
        int losingCountOfDealer = gameResult.getLosingCountOfDealer();
        assertAll(
                () -> assertThat(participantResult).isEqualTo(Result.WIN),
                () -> assertThat(losingCountOfDealer).isEqualTo(1)
        );
    }

    @Test
    @DisplayName("주어진 플레이어의 패배 카운트를 올리면 딜러는 승리 카운트가 올라간다.")
    void givenLosingParticipant_thenCountLosing() {
        Gambler gambler = new Gambler(Hand.makeEmptyHolder(), Name.of("테스트"));
        GameResult gameResult = new GameResult();

        gameResult.addParticipantLosingCase(gambler);

        Result participantResult = gameResult.getResultByParticipant(gambler);
        int winningCountOfDealer = gameResult.getWinningCountOfDealer();
        assertAll(
                () -> assertThat(participantResult).isEqualTo(Result.LOSE),
                () -> assertThat(winningCountOfDealer).isEqualTo(1)
        );
    }
}