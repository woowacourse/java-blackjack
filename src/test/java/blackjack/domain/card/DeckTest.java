package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @DisplayName("기본 생성자로 덱을 생성한다.")
    @Test
    public void createDeck() {
        //given & when
        Deck deck = new Deck();

        //then
        assertThat(deck).isNotNull();
    }
}
