package blackjack.domain;

import blackjack.domain.deck.Card;
import blackjack.domain.deck.Hands;
import blackjack.domain.deck.Rank;
import blackjack.domain.deck.Shape;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandsTest {

    @Test
    @DisplayName("손패에 카드를 추가한다.")
    void receiveCardTest() {
        Hands hands = new Hands(List.of(
                new Card(Shape.HEART, Rank.ACE),
                new Card(Shape.HEART, Rank.TEN)
        ));

        hands.addCard(new Card(Shape.HEART, Rank.EIGHT));

        Assertions.assertThat(hands.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("손패에 있는 카드 점수를 계산한다.")
    void calculateScoreTest() {
        Hands hands = new Hands(List.of(
                new Card(Shape.HEART, Rank.ACE),
                new Card(Shape.HEART, Rank.TEN)
        ));

        int score = hands.calculateScore();

        Assertions.assertThat(score).isEqualTo(21);
    }

}
