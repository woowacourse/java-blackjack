package blackjack;

import blackjack.domain.card.Card;
import blackjack.manager.CardsGenerator;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class CardsGeneratorTest {

    @Test
    void 덱을_생성하면_카드의_개수는_52개이다() {
        // given
        CardsGenerator cardsGenerator = new CardsGenerator();

        // when
        List<Card> cards = cardsGenerator.generate();

        // then
        assertThat(cards).hasSize(52);
    }
}
