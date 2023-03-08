package domain.gameresult;

import domain.card.CardHolder;
import domain.player.Name;
import domain.player.Participant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameResultTest {
    @Test
    @DisplayName("주어진 플레이어의 무승부 카운트를 올리면 딜러와 참가자 양 쪽에 무승부 카운트가 1 올라간다.")
    void givenParticipant_thenCountDraw() {
        Participant participant = new Participant(CardHolder.makeEmptyHolder(), Name.of("테스트"));
        GameResult gameResult = new GameResult();

        gameResult.addDrawCount(participant);

        Result participantResult = gameResult.getResultByParticipant(participant);
        int drawingCountOfDealer = gameResult.getDrawingCountOfDealer();
        assertAll(
                () -> assertThat(participantResult).isEqualTo(Result.DRAW),
                () -> assertThat(drawingCountOfDealer).isEqualTo(1)
        );
    }

    @Test
    @DisplayName("주어진 플레이어의 승리 카운트를 올리면 딜러는 패배 카운트가 올라간다.")
    void givenWinningParticipant_thenCountWinning() {
        Participant participant = new Participant(CardHolder.makeEmptyHolder(), Name.of("테스트"));
        GameResult gameResult = new GameResult();

        gameResult.addParticipantWinningCase(participant);

        Result participantResult = gameResult.getResultByParticipant(participant);
        int losingCountOfDealer = gameResult.getLosingCountOfDealer();
        assertAll(
                () -> assertThat(participantResult).isEqualTo(Result.WIN),
                () -> assertThat(losingCountOfDealer).isEqualTo(1)
        );
    }

    @Test
    @DisplayName("주어진 플레이어의 패배 카운트를 올리면 딜러는 승리 카운트가 올라간다.")
    void givenLosingParticipant_thenCountLosing() {
        Participant participant = new Participant(CardHolder.makeEmptyHolder(), Name.of("테스트"));
        GameResult gameResult = new GameResult();

        gameResult.addParticipantLosingCase(participant);

        Result participantResult = gameResult.getResultByParticipant(participant);
        int winningCountOfDealer = gameResult.getWinningCountOfDealer();
        assertAll(
                () -> assertThat(participantResult).isEqualTo(Result.LOSE),
                () -> assertThat(winningCountOfDealer).isEqualTo(1)
        );
    }
}