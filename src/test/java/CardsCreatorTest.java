import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsCreatorTest {

    @Test
    @DisplayName("createDeck(): 모든 카드의 조합을 생성한다.")
    void createDeck() {
        List<Card> cards = CardsCreator.createLinkedCards();

        assertThat(cards.size()).isEqualTo(Rank.values().length * Suit.values().length);
    }

    @Test
    @DisplayName("[예외]: 중복된 카드가 있는지 확인한다.")
    void validateDuplicateCard() {
        List<Card> cards = CardsCreator.createLinkedCards();

        assertThat(cards.stream().distinct().toList().size()).isEqualTo(Rank.values().length * Suit.values().length);
    }
}
