package domain.rule;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardScore;
import domain.card.CardSuit;
import domain.card.Hand;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @Test
    @DisplayName("딜러가 블랙잭이면 수익률은 0이다.")
    void dealerBlackjackTest() {
        Hand hand = new Hand(List.of(
                new Card(CardScore.ACE, CardSuit.CLUB),
                new Card(CardScore.KING, CardSuit.HEART)
        ));
        Blackjack blackjack = new Blackjack(hand);
        State dealerState = new Blackjack(new Hand(List.of(
                new Card(CardScore.ACE, CardSuit.HEART),
                new Card(CardScore.KING, CardSuit.CLUB)
        )));
        assertThat(blackjack.earningRate(dealerState)).isEqualTo(0);
    }

    @Test
    @DisplayName("딜러가 블랙잭이 아니면 수익률은 1.5이다.")
    void dealerNotBlackjackTest() {
        Hand hand = new Hand(List.of(
                new Card(CardScore.ACE, CardSuit.CLUB),
                new Card(CardScore.KING, CardSuit.HEART)
        ));
        Blackjack blackjack = new Blackjack(hand);
        State dealerState = new Stay(new Hand(List.of(
                new Card(CardScore.TEN, CardSuit.CLUB),
                new Card(CardScore.SEVEN, CardSuit.HEART)
        )));
        assertThat(blackjack.earningRate(dealerState)).isEqualTo(1.5);
    }
}
