package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    @DisplayName("52장의 카드가 존재한다.")
    void checkDeckCardsSize(){
        Deck deck = Deck.init();

        assertThat(deck.findDeckSize()).isEqualTo(52);
    }
}
