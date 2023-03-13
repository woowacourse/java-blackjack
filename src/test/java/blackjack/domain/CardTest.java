package blackjack.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    void isAce() {
        Card card = new Card(Pattern.SPADE, Denomination.ACE);
        Assertions.assertThat(card.isAce()).isTrue();
    }

    @Test
    void isNotAce() {
        Card card = new Card(Pattern.SPADE, Denomination.FOUR);
        Assertions.assertThat(card.isAce()).isFalse();
    }

    @Test
    void createTest() {
        List<Card> cards = Card.create(Pattern.SPADE);
        Assertions.assertThat(cards.size()).isEqualTo(13);

    }

}
