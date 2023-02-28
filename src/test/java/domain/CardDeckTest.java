package domain;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @DisplayName("정상적으로 카드 덱을 생성한다.")
    @Test
    void create_success() {
        // given
        List<Card> cards = new ArrayList<>();
        createFillCards(cards);
        System.out.println(cards.size());

        // when & then
        assertThatNoException()
                .isThrownBy(() -> new CardDeck(cards));
    }

    private void createFillCards(final List<Card> cards) {
        for (Type type : Type.values()) {
            for (Value value : Value.values()) {
                cards.add(new Card(type, value));
            }
        }
    }

    @DisplayName("카드의 전체 수가 52장이 아니면 예외를 반환한다.")
    @Test
    void create_fail_by_wrong_size() {
        // given
        List<Card> cards = new ArrayList<>();

        // when & then
        assertThatThrownBy(() -> new CardDeck(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("전체 카드의 수는 52장이어야 합니다.");
    }

    @DisplayName("카드가 52장 모두 개별적인 카드가 아니면 예외를 반환한다.")
    @Test
    void create_fail_by_duplicated_cards() {
        // given
        List<Card> cards = createFillSameCards();

        // when & then
        assertThatThrownBy(() -> new CardDeck(cards))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private List<Card> createFillSameCards() {
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < 52; i++) {
            cards.add(new Card(Type.CLUB, Value.A));
        }
        return cards;
    }
}
