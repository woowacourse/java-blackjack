import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeckTest {
    @Test
    void name1() {
        Deck deck = new Deck();
        Assertions.assertThat(deck.getCards().size())
                .isEqualTo(52);
    }

    @Test
    void name() {
        Deck deck = new Deck();
        Card card = deck.draw(1);

        Assertions.assertThat(card).isEqualTo(new Card(Shape.HEART, Rank.TWO));
    }
}
