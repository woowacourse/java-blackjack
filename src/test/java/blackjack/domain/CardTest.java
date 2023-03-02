package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardTest {

    @DisplayName("같은 Rank와 Suit를 갖는 Card는 동일하다.")
    @Test
    void checkCardWithSameRankAndSuit() {
        // given
        Card card1 = new Card(Rank.ACE, Suit.DIAMOND);
        Card card2 = new Card(Rank.ACE, Suit.DIAMOND);

        // when & then
        assertThat(card1.equals(card2)).isTrue();
    }
}
