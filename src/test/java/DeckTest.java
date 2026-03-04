import domain.Card;
import domain.Deck;
import java.util.HashSet;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
    @Test
    @DisplayName("초기에 생성할 때, 완전한 카드 덱을 구성한다.")
    void createCardsTest() {
        Deck deck = new Deck();
        Set<Card> cardSet = new HashSet<>(deck.getCards());

        Assertions.assertThat(cardSet.size()).isEqualTo(52);
    }
}
