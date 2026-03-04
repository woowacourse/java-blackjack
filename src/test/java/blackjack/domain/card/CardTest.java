package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("ACE 카드인지 여부를 확인할 수 있다")
    void isAce() {
        Card aceCard = new Card(Suit.CLUB, Rank.ACE);
        Card kingCard = new Card(Suit.CLUB, Rank.KING);

        assertThat(aceCard.isAce()).isTrue();
        assertThat(kingCard.isAce()).isFalse();
    }
}
