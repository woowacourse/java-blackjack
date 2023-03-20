package blackjack.domain.card;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @DisplayName("Card는 Rank와 Suit를 통해 생성한다.")
    @Test
    void createCard() {
        // when & then
        assertDoesNotThrow(() -> new Card(Rank.ACE, Suit.DIAMOND));
    }

    @DisplayName("같은 Rank와 Suit를 갖는 Card는 동일하다.")
    @Test
    void checkCardWithSameRankAndSuit() {
        // given
        Card firstSameCard = new Card(Rank.ACE, Suit.DIAMOND);
        Card secondSameCard = new Card(Rank.ACE, Suit.DIAMOND);

        // when & then
        assertThat(firstSameCard).isEqualTo(secondSameCard);
    }
}
