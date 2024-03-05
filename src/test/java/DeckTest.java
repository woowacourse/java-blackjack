import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
    @DisplayName("카드 덱 초기화")
    @Test
    void initDeck() {
        Deck deck = new Deck();
        Assertions.assertThat(deck.getCards().size())
                .isEqualTo(52);
    }

    @DisplayName("카드 1장을 뽑는다.")
    @Test
    void draw() {
        Deck deck = new Deck();
        Card card = deck.draw(1);

        Assertions.assertThat(card).isEqualTo(new Card(Shape.HEART, Rank.TWO));
    }

    @DisplayName("카드를 1장 뽑으면 덱에서 삭제한다.")
    @Test
    void cardRemove() {
        Deck deck = new Deck();
        Card card = deck.draw(1);

        Assertions.assertThat(deck.getCards().size()).isEqualTo(51);
    }
}
