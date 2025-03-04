import domain.Card;
import domain.Deck;
import domain.DeckGenerator;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckGeneratorTest {
    @Test
    void _52장으로_구성된_덱을_생성한다() {
        // given
        DeckGenerator deckGenerator = new DeckGenerator();
        final int expected = 52;

        // when
        Deck deck = deckGenerator.generateDeck();

        // then
        Assertions.assertThat(deck.size()).isEqualTo(expected);
    }

    @Test
    void 덱에서_카드를_한_장_뽑는다() {
        // given
        DeckGenerator deckGenerator = new DeckGenerator();
        final int cardCount = 1;

        // when
        Deck deck = deckGenerator.generateDeck();
        List<Card> card = deck.drawCards(cardCount);

        // then
        Assertions.assertThat(card.size()).isEqualTo(cardCount);
    }

    @Test
    void 덱에서_카드를_두_장_뽑는다() {
        // given
        DeckGenerator deckGenerator = new DeckGenerator();
        final int cardCount = 2;

        // when
        Deck deck = deckGenerator.generateDeck();
        List<Card> card = deck.drawCards(cardCount);

        // then
        Assertions.assertThat(card.size()).isEqualTo(cardCount);
    }
}
