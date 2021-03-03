package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {
    @DisplayName("동일 객체 테스트")
    @Test
    void create() {
        Cards cards = Cards.getCards();
        assertThat(cards).isSameAs(Cards.getCards());
    }

    @DisplayName("card 뽑기 테스트")
    @Test
    void drawTest() {
        Cards cards = Cards.getCards();
        Set<Card> cardsNotDuplicate = new HashSet<>();
        for (int i = 0; i < 52; i++) {
            cardsNotDuplicate.add(cards.draw());
        }
        assertThat(cardsNotDuplicate.size()).isEqualTo(52);
        assertThatThrownBy(cards::draw)
            .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }
}
