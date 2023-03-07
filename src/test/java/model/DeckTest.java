package model;

import model.card.Card;
import model.card.Deck;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    @Test
    @DisplayName("Deck 생성할 때 카드가 52장 생성되는지 테스트한다.")
    void createDeckTest() {
        // given, when
        final Deck deck = new Deck();

        // then
        assertThat(deck)
                .extracting("cards", InstanceOfAssertFactories.list(Card.class))
                .hasSize(52);
    }
}
