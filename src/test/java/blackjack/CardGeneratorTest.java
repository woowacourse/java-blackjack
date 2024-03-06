package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.Card;
import blackjack.model.CardGenerator;
import blackjack.model.CardNumber;
import blackjack.model.CardShape;
import blackjack.model.generator.IndexGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardGeneratorTest {
    @DisplayName("카드를 조합한다")
    @Test
    void drawCard() {
        IndexGenerator indexGenerator = maxRange -> 1;
        CardGenerator cardGenerator = new CardGenerator(indexGenerator);

        Card card = cardGenerator.drawCard();
        assertThat(card.getCardNumber()).isEqualTo(CardNumber.ACE);
        assertThat(card.getCardShape()).isEqualTo(CardShape.SPADE);
    }

    @DisplayName("카드 여러장을 발급한다")
    @Test
    void drawCards() {
        IndexGenerator indexGenerator = maxRange -> 1;
        CardGenerator cardGenerator = new CardGenerator(indexGenerator);

        List<Card> cards = cardGenerator.drawCards();

        assertThat(cards).hasSize(2);
    }
}
