package domain;

import static org.assertj.core.api.Assertions.assertThat;
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
        List<Card> cards = createFillCards();

        // when & then
        assertThatNoException()
                .isThrownBy(() -> new CardDeck(cards));
    }

    private List<Card> createFillCards() {
        List<Card> cards = new ArrayList<>();

        for (Type type : Type.values()) {
            for (Value value : Value.values()) {
                cards.add(new Card(type, value));
            }
        }

        return cards;
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
            cards.add(new Card(Type.CLUB, Value.ACE));
        }
        return cards;
    }

    //TODO: 추후에 로직 분리 예정
//    @DisplayName("카드 섞였는지 테스트")
//    @Test
//    void a1() {
//        // given
//        List<Card> cardsNotShuffled = createFillCards();
//
//        // when & then
//        assertThatThrownBy(() -> new CardDeck(cardsNotShuffled))
//                .isInstanceOf(IllegalArgumentException.class);
//    }

    @DisplayName("카드를 한 장 뽑아서 반환한다.")
    @Test
    void returns_drawn_card() {
        // given
        List<Card> givenCards = createFillCards();
        CardDeck cardDeck = new CardDeck(givenCards);
        Card expectedCard = givenCards.get(givenCards.size() - 1);

        // when
        Card drawnCard = cardDeck.draw();

        // then
        assertThat(drawnCard).isEqualTo(expectedCard);
    }
}
