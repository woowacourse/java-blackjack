package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class ShuffledDeckTest {

    @Test
    void 카드를_반환한다() {
        Deck deck = ShuffledDeck.getInstance();

        assertThat(deck.draw()).isInstanceOf(Card.class);
    }
}
