package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @Test
    @DisplayName("카드를 한 장 드로우 한다.")
    void drawTest() {
        // given
        List<Card> cards = new ArrayList<>();
        cards.add(new Card());
        cards.add(new Card());
        cards.add(new Card());

        CardDeck cardDeck = new CardDeck(cards);

        // when
        Card draw = cardDeck.draw();

        // then
        assertThat(draw).isNotNull();
    }
}