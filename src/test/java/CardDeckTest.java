import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import config.CardDeckFactory;
import domain.card.CardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {
    @Test
    @DisplayName("카드 개수 테스트")
    void cardSizeTest() {
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        CardDeck cardDeck = cardDeckFactory.create();

        assertThat(cardDeck.getCardsSize()).isEqualTo(52);
    }
}
