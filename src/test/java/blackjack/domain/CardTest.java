package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("카드는 무늬와 숫자를 가진다")
    @Test
    public void create() {
        Card card = new Card(Suit.CLOVER, Denomination.ACE);

        assertThat(card.getSuit()).isEqualTo(Suit.CLOVER);
        assertThat(card.getDenomination()).isEqualTo(Denomination.ACE);
    }

    @DisplayName("카드의 점수를 반환한다")
    @Test
    public void getScore() {
        Card card = new Card(Suit.CLOVER, Denomination.TWO);

        assertThat(card.getScore()).isEqualTo(new Score(2));
    }
}
