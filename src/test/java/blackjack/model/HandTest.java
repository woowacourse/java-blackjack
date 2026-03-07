package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HandTest {

    private final Card card = new Card(Rank.ACE, Suit.CLOVER);
    private final ScoreCalculator scoreCalculator = new ScoreCalculator();

    @Nested
    @DisplayName("1장 이상의 카드를 받아서 손패에 추가한다.")
    class AddCards {
        @Test
        void 한장의_카드를_받아서_손패에_추가한다() {
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
