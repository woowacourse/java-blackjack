package blackjack.domain;

import static blackjack.domain.Number.ACE;
import static blackjack.domain.Number.TWO;
import static blackjack.domain.Symbol.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DeckTest {

    @DisplayName("카드 덱에서 주어진 장수만큼 카드를 뽑는다.")
    @Test
    void should_DrawCards_As_Count() {
        List<Card> mockCards = List.of(new Card(SPADE, ACE), new Card(SPADE, TWO));
        Deck deck = new MockDeckGenerator(mockCards).generate();
        assertThat(deck.draw(2))
                .containsExactly(new Card(SPADE, ACE), new Card(SPADE, TWO));
    }
}
