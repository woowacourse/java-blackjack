package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    @DisplayName("카드 뭉치에서 카드를 한 장 뽑아서 준다.")
    void drawCard() {
        // given
        CardDeck deck = new CardDeck(new BlackJackCardsGenerator());

        // when
        Card actual = deck.draw();

        // then
        assertThat(actual).isNotNull();
    }

    @Test
    @DisplayName("뽑은 카드는 카드 뭉치에서 제거된다.")
    void removeCard() {
        // given
        CardDeck deck = new CardDeck(() -> new ArrayList<>(List.of(new Card(Pattern.CLOVER, Denomination.THREE))));

        // when
        deck.draw();

        // then
        assertThat(deck.isEmpty()).isTrue();
    }
}
