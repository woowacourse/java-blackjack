package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import java.util.List;
import org.junit.jupiter.api.Test;

class HandTest {

    private final Card card = new Card(Rank.ACE, Suit.CLOVER);

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
