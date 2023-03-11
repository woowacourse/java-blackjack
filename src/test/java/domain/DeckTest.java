package domain;

import domain.card.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeckTest {

    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck();
    }

    @Test
    @DisplayName("4개의 그림과 13개의 글자로 52장의 덱을 생성한다")
    void test_create() {
        var cards = deck.getCards();

        assertThat(cards).hasSize(52);
    }

    @Test
    @DisplayName("카드 한 장을 준다")
    void test_drawCard_success() {
        deck.drawCard();

        assertThat(deck.getCards()).hasSize(51);
    }

    @Test
    @DisplayName("뽑힌 카드는 덱에서 제거된다")
    void test_removeCardByDraw() {
        var card = deck.drawCard();

        assertThat(card).isNotIn(deck.getCards());
    }

    @Test
    @DisplayName("카드가 없는 덱에서 카드를 뽑으면 IllegalStateException을 던진다")
    void test_drawCardFromEmptyDeck() {
        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }

        assertThatThrownBy(deck::drawCard)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드가 모두 소진됐습니다.");
    }

}