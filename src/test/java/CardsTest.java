import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {

    @DisplayName("카드가 모두 소진됐는지 확인한다.")
    @Test
    void allCardsUsedTest() {
        CardsGenerator cardsGenerator = new CardsGenerator();
        List<Card> randomCards = cardsGenerator.generateRandomCards();
        Cards cards = new Cards(randomCards);
        for (int i = 0; i < 52; i++) {
            cards.pickOneCard();
        }
        assertThat(cards.isUsedAll()).isTrue();
    }
}
