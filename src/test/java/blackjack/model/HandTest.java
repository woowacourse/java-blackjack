package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HandTest {

    private final Card card = new Card(Rank.ACE, Suit.CLUB);

    @Nested
    @DisplayName("1장 이상의 카드를 받아서 손패에 추가한다.")
    class AddCards {
        @Test
        @DisplayName("한장의 카드를 받아서 손패에 추가한다.")
        void addOneCard() {
            // given
            Hand hand = new Hand();
            List<Card> existCards = hand.getCards();

            // when
            hand.addCard(card);

            // then
            List<Card> addedCards = hand.getCards();
            assertThat(addedCards.size()).isEqualTo(existCards.size() + 1);
        }
    }

}
