package domain.rule;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardScore;
import domain.card.CardSuit;
import domain.card.Cards;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StayTest {

    @Test
    @DisplayName("딜러가 버스트이면 수익률은 1.0이다.")
    void dealerBustTest() {
        Cards cards = new Cards(List.of(
                new Card(CardScore.TEN, CardSuit.CLUB),
                new Card(CardScore.SEVEN, CardSuit.HEART)
        ));
        Stay stay = new Stay(cards);
        State dealerState = new Bust(new Cards(List.of(
                new Card(CardScore.JACK, CardSuit.CLUB),
                new Card(CardScore.KING, CardSuit.HEART)
        )));
        assertThat(stay.earningRate(dealerState)).isEqualTo(1.0);
    }

    @Test
    @DisplayName("딜러가 블랙잭이면 수익률은 -1.0이다.")
    void dealerBlackjackTest() {
        Cards cards = new Cards(List.of(
                new Card(CardScore.TEN, CardSuit.CLUB),
                new Card(CardScore.SEVEN, CardSuit.HEART)
        ));
        Stay stay = new Stay(cards);
        State dealerState = new Blackjack(new Cards(List.of(
                new Card(CardScore.ACE, CardSuit.CLUB),
                new Card(CardScore.KING, CardSuit.HEART)
        )));
        assertThat(stay.earningRate(dealerState)).isEqualTo(-1.0);
    }

    @Test
    @DisplayName("플레이어 점수가 딜러 점수보다 높으면 수익률은 1.0이다.")
    void playerScoreHigherTest() {
        Cards cards = new Cards(List.of(
                new Card(CardScore.TEN, CardSuit.CLUB),
                new Card(CardScore.NINE, CardSuit.HEART)
        ));
        Stay stay = new Stay(cards);
        State dealerState = new Stay(new Cards(List.of(
                new Card(CardScore.TEN, CardSuit.CLUB),
                new Card(CardScore.SEVEN, CardSuit.HEART)
        )));
        assertThat(stay.earningRate(dealerState)).isEqualTo(1.0);
    }

    @Test
    @DisplayName("플레이어 점수가 딜러 점수보다 낮으면 수익률은 -1.0이다.")
    void playerScoreLowerTest() {
        Cards cards = new Cards(List.of(
                new Card(CardScore.TEN, CardSuit.CLUB),
                new Card(CardScore.SEVEN, CardSuit.HEART)
        ));
        Stay stay = new Stay(cards);
        State dealerState = new Stay(new Cards(List.of(
                new Card(CardScore.TEN, CardSuit.CLUB),
                new Card(CardScore.NINE, CardSuit.HEART)
        )));
        assertThat(stay.earningRate(dealerState)).isEqualTo(-1.0);
    }

    @Test
    @DisplayName("플레이어 점수와 딜러 점수가 같으면 수익률은 0이다.")
    void playerScoreEqualTest() {
        Cards cards = new Cards(List.of(
                new Card(CardScore.TEN, CardSuit.CLUB),
                new Card(CardScore.SEVEN, CardSuit.HEART)
        ));
        Stay stay = new Stay(cards);
        State dealerState = new Stay(new Cards(List.of(
                new Card(CardScore.TEN, CardSuit.HEART),
                new Card(CardScore.SEVEN, CardSuit.CLUB)
        )));
        assertThat(stay.earningRate(dealerState)).isEqualTo(0.0);
    }
}
