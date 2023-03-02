package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardTest {

    @Test
    void 문양_이름을_확인한다() {
        final Suit suit = Suit.SPADE;

        final Card card = new Card(suit);

        assertThat(card.getSuitName()).isEqualTo(suit.getName());
    }
}

class Card {

    private final Suit suit;

    public Card(final Suit suit) {
        this.suit = suit;
    }

    public String getSuitName() {
        return suit.getName();
    }
}