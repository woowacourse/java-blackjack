package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardFactoryTest {

    @DisplayName("고유한 카드 52장을 생성한다")
    @Test
    public void generate() {
        CardFactory cardFactory = new CardFactory();

        List<Card> generatedCards = cardFactory.generate();

        assertThat(generatedCards.size()).isEqualTo(52);
    }
}
