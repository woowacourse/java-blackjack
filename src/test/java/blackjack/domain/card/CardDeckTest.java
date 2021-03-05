package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardDeckTest {
    private static final int INIT_CARD_DECK_SIZE = Shape.values().length * Symbol.values().length;

    @DisplayName("카드덱 생성 시, 카드덱은 총 52장의 카드로 이루어져있는 지 테스트")
    @Test
    public void cardDeckSize() {
        CardDeck cardDeck = new CardDeck(CardsGenerator.generateCards());

        int cardDeckSize = cardDeck.size();

        assertThat(cardDeckSize).isEqualTo(INIT_CARD_DECK_SIZE);
    }

    @DisplayName("카드 덱에서 카드 1장을 뽑는다.")
    @Test
    void draw() {
        CardDeck cardDeck = new CardDeck(CardsGenerator.generateCards());

        cardDeck.draw();
        int cardDeckSize = cardDeck.size();

        assertThat(cardDeckSize).isEqualTo(INIT_CARD_DECK_SIZE - 1);
    }

    @DisplayName("카드 덱에서 기본 카드 2장을 뽑는다.")
    @Test
    void drawDefaultCards() {
        CardDeck cardDeck = new CardDeck(CardsGenerator.generateCards());

        Cards cards = cardDeck.drawDefaultCards();
        int cardSize = cards.getCards().size();

        assertThat(cardSize).isEqualTo(2);
    }
}
