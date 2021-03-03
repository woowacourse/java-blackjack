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
}
