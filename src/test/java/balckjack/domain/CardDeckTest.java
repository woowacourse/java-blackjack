package balckjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    CardDeck deck;

    @BeforeEach
    void init() {
        deck = new CardDeck();
    }

    @Test
    void addNullCard() {
        Assertions.assertThatThrownBy(() -> deck.addCard(null))
            .isInstanceOf(IllegalArgumentException.class).hasMessage("아무런 카드도 입력되지 않았습니다.");
    }

    @Test
    void findCardCount() {
        deck.addCard(new CourtCard("K"));
        deck.addCard(new StandardCard(4));
        Assertions.assertThat(deck.getCardCount()).isEqualTo(2);
    }

    @Test
    void findCardScore() {
        deck.addCard(new CourtCard("K"));
        deck.addCard(new StandardCard(4));
        Assertions.assertThat(deck.calculateScore()).isEqualTo(14);

    }

}