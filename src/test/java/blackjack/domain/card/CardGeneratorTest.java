package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardGeneratorTest {

    @Test
    @DisplayName("52장의 카드를 포함하고 있는 카드덱을 정상 생성한다.")
    void createCardDeck() {
        List<Card> cardDeck = CardGenerator.createCardDeckByCardNumber();
        final int expected = 52;

        final int actual = cardDeck.size();
        assertThat(actual).isEqualTo(expected);
    }
}
