package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {
    
    @Test
    @DisplayName("더 이상 뽑을 수 있는 카드가 없는 경우 확인")
    void noCardTest() {
        for (int i = 0; i < 52; i++) {
            Card.draw();
        }

        assertThatThrownBy(Card::draw)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("더 이상 뽑을 수 있는 카드가 없습니다.");
    }

    @Test
    @DisplayName("중복된 카드가 뽑히는지 확인")
    void duplicateCardTest() {
        Set<Card> cards = new HashSet<>();
        for (int i = 0; i < 52; i++) {
            cards.add(Card.draw());
        }

        assertThat(cards.size()).isEqualTo(52);
    }
}
