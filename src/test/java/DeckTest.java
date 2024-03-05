import domain.Card;
import domain.Deck;
import domain.Name;
import domain.Player;
import domain.Rank;
import domain.Shape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    @DisplayName("덱에 카드가 잘 추가된다.")
    void addCardTest() {
        Deck deck = new Deck();
        Card card = new Card(Shape.CLOVER, Rank.EIGHT);

        deck.addCard(card);
        Card result = deck.pickRandomCard();

        Assertions.assertThat(result).isEqualTo(card);
    }
    
}
