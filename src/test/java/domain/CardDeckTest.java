package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.Type;
import domain.card.Value;
import domain.message.ExceptionMessage;
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
                .isThrownBy(() -> CardDeck.createShuffled(cards));
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
        assertThatThrownBy(() -> CardDeck.createShuffled(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.CARD_DECK_INVALID_SIZE.getMessage());
    }

    @DisplayName("카드가 52장 모두 개별적인 카드가 아니면 예외를 반환한다.")
    @Test
    void create_fail_by_duplicated_cards() {
        // given
        List<Card> cards = createFillSameCards();

        // when & then
        assertThatThrownBy(() -> CardDeck.createShuffled(cards))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private List<Card> createFillSameCards() {
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < 52; i++) {
            cards.add(new Card(Type.CLUB, Value.ACE));
        }
        return cards;
    }

    @DisplayName("카드를 한 장을 맨 위에서 뽑아서 반환한다.")
    @Test
    void returns_drawn_card() {
        // given
        List<Card> givenCards = createFillCards();
        CardDeck cardDeck = CardDeck.createShuffled(givenCards);
        Card expectedCard = givenCards.get(0);

        // when
        Card drawnCard = cardDeck.draw();

        // then
        assertThat(drawnCard).isEqualTo(expectedCard);
    }
}
