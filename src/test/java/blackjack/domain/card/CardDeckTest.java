package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class CardDeckTest {
    private static final int INIT_CARD_DECK_SIZE = Shape.values().length * Symbol.values().length;

    @DisplayName("52장의 카드가 아닌 카드를 입력받는 경우 생성 예외 발생")
    @Test
    void cannotCreateCardDeck() {
        assertThatCode(() -> {
            new CardDeck(Arrays.asList(new Card(Symbol.ACE, Shape.CLOVER)));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드덱 생성에 필요한 카드의 숫자가 유효하지 않습니다.");
    }

    @DisplayName("카드 덱에서 기본 카드 2장을 뽑는다.")
    @Test
    void drawDefaultCards() {
        CardDeck cardDeck = new CardDeck(CardsGenerator.generateShuffledCards());

        Cards cards = cardDeck.drawDefaultCards();

        assertThat(cards.getCards()).hasSize(2);
    }

    @DisplayName("카드 덱이 비어있는 경우 추가로 카드를 뽑으려고 시도하면 예외 발생")
    @Test
    void cannotDrawCard() {
        CardDeck cardDeck = new CardDeck(CardsGenerator.generateShuffledCards());
        for (int i = 0; i < INIT_CARD_DECK_SIZE; i++) {
            cardDeck.draw();
        }

        assertThatCode(cardDeck::draw).isInstanceOf(IllegalStateException.class)
                .hasMessage("더 이상 카드를 뽑을 수 없습니다.");
    }
}
