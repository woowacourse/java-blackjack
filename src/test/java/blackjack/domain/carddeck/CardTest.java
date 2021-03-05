package blackjack.domain.carddeck;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("카드생성 테스트")
    void testCreateCard() {
        Card card = new Card(Pattern.CLOVER, Number.ACE);

        assertThat(card).isEqualTo(new Card(Pattern.CLOVER, Number.ACE));
        assertThat(card).isNotEqualTo(new Card(Pattern.HEART, Number.ACE));
        assertThat(card).isNotEqualTo(new Card(Pattern.CLOVER, Number.KING));
    }

    @Test
    @DisplayName("자신이 ACE 카드인지 확인한다.")
    void testIsAceCard() {
        Card card1 = new Card(Pattern.CLOVER, Number.ACE);
        Card card2 = new Card(Pattern.CLOVER, Number.TWO);

        assertThat(card1.isAce()).isTrue();
        assertThat(card2.isAce()).isFalse();
    }
}
