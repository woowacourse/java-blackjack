package domain.rule;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardScore;
import domain.card.CardSuit;
import domain.card.Cards;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @Test
    @DisplayName("딜러가 블랙잭이면 수익률은 0이다.")
    void dealerBlackjackTest() {
        Cards cards = new Cards(List.of(
                new Card(CardScore.ACE, CardSuit.CLUB),
                new Card(CardScore.KING, CardSuit.HEART)
        ));
        Blackjack blackjack = new Blackjack(cards);
        State dealerState = new Blackjack(new Cards(List.of(
                new Card(CardScore.ACE, CardSuit.HEART),
                new Card(CardScore.KING, CardSuit.CLUB)
        )));
        assertThat(blackjack.earningRate(dealerState)).isEqualTo(0);
    }

    @Test
    @DisplayName("딜러가 블랙잭이 아니면 수익률은 1.5이다.")
    void dealerNotBlackjackTest() {
        Cards cards = new Cards(List.of(
                new Card(CardScore.ACE, CardSuit.CLUB),
                new Card(CardScore.KING, CardSuit.HEART)
        ));
        Blackjack blackjack = new Blackjack(cards);
        State dealerState = new Stay(new Cards(List.of(
                new Card(CardScore.TEN, CardSuit.CLUB),
                new Card(CardScore.SEVEN, CardSuit.HEART)
        )));
        assertThat(blackjack.earningRate(dealerState)).isEqualTo(1.5);
    }
}
