package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    @DisplayName("카드 뭉치에서 카드를 한 장 뽑아서 준다.")
    void drawCard() {
        // given
        CardDeck deck = new CardDeck();

        // when
        Card actual = deck.draw();

        // then
        assertThat(actual).isNotNull();
    }
}
