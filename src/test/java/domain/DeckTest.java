package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
    Deck deck;

    @BeforeEach
    void beforeEach() {
        deck = new Deck();
    }

    @DisplayName("덱에서 52번 뽑을 수 있고 53번째는 예외가 발생한다")
    @Test
    void 덱에서_52번_뽑을_수_있다() {
        assertThatNoException().isThrownBy(() -> {
            for (int i = 0; i < 52; i++) {
                deck.draw();
            }
        });
        assertThatThrownBy(() -> deck.draw())
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("덱의 모든 카드는 중복 없이 유니크하다")
    @Test
    void 덱의_모든_카드는_중복_없이_유니크하다() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            cards.add(deck.draw());
        }
        Set<Card> uniqueCards = new HashSet<>(cards);
        assertThat(uniqueCards.size()).isEqualTo(52);
    }
}
