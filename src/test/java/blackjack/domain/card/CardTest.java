package blackjack.domain.card;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("에이스일 때 true 반환")
    void test_return_ture_when_ace() {
        Card card = new Card(Suit.DIAMOND, Rank.ACE);
        assertThat(card.isAce()).isTrue();
    }

    @Test
    @DisplayName("에이스가 아니면 false 반환")
    void test_return_false_when_not_ace() {
        Card card = new Card(Suit.DIAMOND, Rank.TWO);
        assertThat(card.isAce()).isFalse();
    }
}
