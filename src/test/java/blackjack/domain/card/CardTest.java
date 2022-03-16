package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void 중복없는_52개의_카드를_생성() {
        final List<Card> cards = Card.cards();
        assertThat(new HashSet<>(cards)).hasSize(52);
    }
}
