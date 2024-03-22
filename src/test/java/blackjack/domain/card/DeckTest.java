package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("덱")
public class DeckTest {
    @Test
    @DisplayName("카드를 두장 뽑는다.")
    void cardsPickTest() {
        // given
        Deck deck = Deck.make();

        // when
        List<Card> card = deck.pickCards(2);

        // then
        assertThat(card.size()).isEqualTo(2);
    }
}
