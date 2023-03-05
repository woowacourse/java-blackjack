package domain;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import domain.gameresult.GameResult;
import domain.gameresult.Result;
import domain.player.Dealer;
import domain.player.Participant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {
    private Dealer dealer;
    private Participant participant;
    private GameResult gameResult;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        participant = new Participant("테스트");
        gameResult = new GameResult();
    }

    @Test
    @DisplayName("딜러가 상대 플레이어보다 점수가 높으면 승이 추가된다.")
    void givenDealerWinningFromPlayer_thenAddWinning() {
        dealer.addCard(new Card(Shape.DIAMOND, Number.KING)); // 딜러 : 20
        participant.addCard(new Card(Shape.DIAMOND, Number.NINE)); // 참가자 : 19

        dealer.battle(participant, gameResult);

        Assertions.assertAll(
                () -> assertThat(gameResult.getWinningCountOfDealer()).isEqualTo(1),
                () -> assertThat(gameResult.getResultByParticipant(participant)).isEqualTo(Result.LOSE)
        );
    }

    @Test
    @DisplayName("딜러가 상대 플레이어보다 점수가 낮으면 패가 추가된다.")
    void givenDealerLosingFromPlayer_thenAddLose() {
        dealer.addCard(new Card(Shape.DIAMOND, Number.NINE)); // 딜러 : 19
        participant.addCard(new Card(Shape.DIAMOND, Number.ACE)); // 참가자 : 21

        dealer.battle(participant, gameResult);

        Assertions.assertAll(
                () -> assertThat(gameResult.getLosingCountOfDealer()).isEqualTo(1),
                () -> assertThat(gameResult.getResultByParticipant(participant)).isEqualTo(Result.WIN)
        );
    }

    @Test
    @DisplayName("딜러가 상대 플레이어와 점수가 같으면 무승부가 추가된다.")
    void givenDealerDrawingWithPlayer_thenAddDraw() {
        dealer.addCard(new Card(Shape.DIAMOND, Number.NINE)); // 딜러 : 19
        participant.addCard(new Card(Shape.DIAMOND, Number.NINE)); // 참가자 : 19

        dealer.battle(participant, gameResult);

        Assertions.assertAll(
                () -> assertThat(gameResult.getDrawingCountOfDealer()).isEqualTo(1),
                () -> assertThat(gameResult.getResultByParticipant(participant)).isEqualTo(Result.DRAW)
        );
    }
}