package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardDeckTest {

    @DisplayName("초기에 받는 카드 개수가 올바른지 확인한다.")
    @Test
    void init_cards_count() {
        CardDeck cardDeck = new CardDeck();

        assertThat(cardDeck.dealInit().size()).isEqualTo(2);
    }

    @DisplayName("정상적인 카드를 드로우 하는지 확인한다.")
    @Test
    void card_draw() {
        CardDeck cardDeck = new CardDeck();
        List<Card> deck = Card.getDeck();
        Boolean containsDeck = deck.contains(cardDeck.drawCard());

        assertThat(containsDeck).isTrue();
    }
}
