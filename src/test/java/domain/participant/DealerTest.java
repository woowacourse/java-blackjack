package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardScore;
import domain.card.CardSuit;
import domain.card.Cards;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    @Test
    @DisplayName("딜러는 점수의 합이 16 이하이면 카드를 한 장 받는다.")
    void receiveCardTest() {
        List<Card> hand = new ArrayList<>(List.of(
                new Card(CardScore.THREE, CardSuit.CLUB),
                new Card(CardScore.FOUR, CardSuit.CLUB)));
        Dealer dealer = new Dealer(new Cards(hand));

        assertThat(dealer.canReceiveCard()).isTrue();
    }

    @Test
    @DisplayName("딜러는 점수의 합이 17 이상이면 카드를 받지 않는다.")
    void notReceiveCardTest() {
        List<Card> hand = new ArrayList<>(List.of(
                new Card(CardScore.TEN, CardSuit.CLUB),
                new Card(CardScore.SEVEN, CardSuit.CLUB)));
        Dealer dealer = new Dealer(new Cards(hand));

        assertThat(dealer.canReceiveCard()).isFalse();
    }
}
