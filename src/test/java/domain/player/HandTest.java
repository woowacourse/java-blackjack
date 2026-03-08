package domain.player;

import domain.Card;
import domain.CardNumber;
import domain.Suit;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HandTest {

    @Test
    void 카드숫자_2에서_10은_그대로_계산한다() {
        Hand hand = new Hand();
        List<Card> cards = List.of(
                new Card(Suit.SPADE, CardNumber.TWO),
                new Card(Suit.SPADE, CardNumber.THREE),
                new Card(Suit.SPADE, CardNumber.FOUR),
                new Card(Suit.SPADE, CardNumber.FIVE),
                new Card(Suit.SPADE, CardNumber.SIX),
                new Card(Suit.SPADE, CardNumber.SEVEN),
                new Card(Suit.SPADE, CardNumber.EIGHT),
                new Card(Suit.SPADE, CardNumber.NINE),
                new Card(Suit.SPADE, CardNumber.TEN));
        for (Card card : cards) {
            hand.add(card);
        }

        int score = hand.calculateScore();

        Assertions.assertThat(score).isEqualTo(54);
    }
}

