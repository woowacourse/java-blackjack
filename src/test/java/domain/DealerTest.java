package domain;

import domain.card.Card;
import domain.card.CardHolder;
import domain.card.Number;
import domain.card.Shape;
import domain.gameresult.GameResult;
import domain.gameresult.Result;
import domain.player.Dealer;
import domain.player.Name;
import domain.player.Participant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {
    private GameResult gameResult;

    @BeforeEach
    void setUp() {
        gameResult = new GameResult();
    }

    @Test
    @DisplayName("딜러가 상대 플레이어보다 점수가 높으면 승이 추가된다.")
    void givenDealerWinningFromPlayer_thenAddWinning() {
        Dealer dealer = makeDealer(Shape.DIAMOND, Number.KING);
        Participant participant = makeParticipant(Shape.DIAMOND, Number.NINE);

        dealer.battle(participant, gameResult);

        Assertions.assertAll(
                () -> assertThat(gameResult.getWinningCountOfDealer()).isEqualTo(1),
                () -> assertThat(gameResult.getResultByParticipant(participant)).isEqualTo(Result.LOSE)
        );
    }

    @Test
    @DisplayName("딜러가 상대 플레이어보다 점수가 낮으면 패가 추가된다.")
    void givenDealerLosingFromPlayer_thenAddLose() {
        Dealer dealer = makeDealer(Shape.SPADE, Number.NINE);
        Participant participant = makeParticipant(Shape.DIAMOND, Number.ACE);

        dealer.battle(participant, gameResult);

        Assertions.assertAll(
                () -> assertThat(gameResult.getLosingCountOfDealer()).isEqualTo(1),
                () -> assertThat(gameResult.getResultByParticipant(participant)).isEqualTo(Result.WIN)
        );
    }

    @Test
    @DisplayName("딜러가 상대 플레이어와 점수가 같으면 무승부가 추가된다.")
    void givenDealerDrawingWithPlayer_thenAddDraw() {
        Dealer dealer = makeDealer(Shape.DIAMOND, Number.NINE);
        Participant participant = makeParticipant(Shape.HEART, Number.NINE);

        dealer.battle(participant, gameResult);

        Assertions.assertAll(
                () -> assertThat(gameResult.getDrawingCountOfDealer()).isEqualTo(1),
                () -> assertThat(gameResult.getResultByParticipant(participant)).isEqualTo(Result.DRAW)
        );
    }

    private static Participant makeParticipant(Shape shape, Number number) {
        return new Participant(
                new CardHolder(List.of(new Card(shape, number))),
                Name.of("참가자")
        );
    }

    private static Dealer makeDealer(Shape shape, Number number) {
        return new Dealer(new CardHolder(
                List.of(new Card(shape, number))
        ));
    }
}