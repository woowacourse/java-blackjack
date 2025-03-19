package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
public class CardDeckTest {

    @Test
    void 카드팩은_Rank와_Shape의_모든_조합의_카드를_가지고_있다() {
        CardDeck pack = new CardDeck();

        var cards = pack.getCards();

        assertCardPackHasAllCombinations(cards);
    }

    @Test
    void 카드팩을_섞을때_모든_조합을_가지고있다() {
        CardDeck pack = new CardDeck();

        pack.shuffle();
        var cards = pack.getCards();

        assertCardPackHasAllCombinations(cards);
    }

    private static void assertCardPackHasAllCombinations(List<Card> cards) {
        for (Rank rank : Rank.values()) {
            for (Shape shape : Shape.values()) {
                Card card = new Card(rank, shape);
                assertThat(cards).contains(card);
            }
        }
        assertThat(cards).hasSize(52);
    }

    @Test
    void 카드팩_상단의_한장을_꺼낸다() {
        CardDeck pack = new CardDeck();
        Card card = pack.poll();

        assertAll(
                () -> assertThat(card).isNotNull(),
                () -> assertThat(pack.getCards()).doesNotContain(card)
        );
    }
}
