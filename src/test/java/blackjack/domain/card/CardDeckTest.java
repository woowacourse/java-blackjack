package blackjack.domain.card;

import static blackjack.domain.Fixtures.THREE_DIAMOND;
import static blackjack.domain.Fixtures.TWO_DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    private final CardDeckGenerator cardDeckGenerator = new TestDeckGenerator();
    private final CardDeck cardDeck = cardDeckGenerator.createCardDeck();

    @Test
    @DisplayName("카드덱에서 처음 2장의 카드를 뽑는다.")
    void test() {
        final List<Card> expected = List.of(TWO_DIAMOND, THREE_DIAMOND);

        final List<Card> actual = cardDeck.drawInitialCard();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("카드덱에서 카드를 뽑는다.")
    void drawCard() {
        final Card expected = TWO_DIAMOND;

        final Card actual = cardDeck.drawCard();
        assertThat(actual).isEqualTo(expected);
    }
}
