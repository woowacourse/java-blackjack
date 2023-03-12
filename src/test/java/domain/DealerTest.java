package domain;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Denomination;
import domain.card.Suit;
import domain.gameresult.GameResult;
import domain.gameresult.Result;
import domain.player.Dealer;
import domain.player.Name;
import domain.player.Gambler;
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
        Dealer dealer = makeDealer(Suit.DIAMOND, Denomination.KING);
        Gambler gambler = makeParticipant(Suit.DIAMOND, Denomination.NINE);

        dealer.battle(gambler, gameResult);

        Assertions.assertAll(
                () -> assertThat(gameResult.getWinningCountOfDealer()).isEqualTo(1),
                () -> assertThat(gameResult.getResultByParticipant(gambler)).isEqualTo(Result.LOSE)
        );
    }

    @Test
    @DisplayName("딜러가 상대 플레이어보다 점수가 낮으면 패가 추가된다.")
    void givenDealerLosingFromPlayer_thenAddLose() {
        Dealer dealer = makeDealer(Suit.SPADE, Denomination.NINE);
        Gambler gambler = makeParticipant(Suit.DIAMOND, Denomination.ACE);

        dealer.battle(gambler, gameResult);

        Assertions.assertAll(
                () -> assertThat(gameResult.getLosingCountOfDealer()).isEqualTo(1),
                () -> assertThat(gameResult.getResultByParticipant(gambler)).isEqualTo(Result.WIN)
        );
    }

    @Test
    @DisplayName("딜러가 상대 플레이어와 점수가 같으면 무승부가 추가된다.")
    void givenDealerDrawingWithPlayer_thenAddDraw() {
        Dealer dealer = makeDealer(Suit.DIAMOND, Denomination.NINE);
        Gambler gambler = makeParticipant(Suit.HEART, Denomination.NINE);

        dealer.battle(gambler, gameResult);

        Assertions.assertAll(
                () -> assertThat(gameResult.getDrawingCountOfDealer()).isEqualTo(1),
                () -> assertThat(gameResult.getResultByParticipant(gambler)).isEqualTo(Result.DRAW)
        );
    }

    private static Gambler makeParticipant(Suit suit, Denomination denomination) {
        return new Gambler(
                new Hand(List.of(new Card(suit, denomination))),
                Name.of("참가자")
        );
    }

    private static Dealer makeDealer(Suit suit, Denomination denomination) {
        return new Dealer(new Hand(
                List.of(new Card(suit, denomination))
        ));
    }
}