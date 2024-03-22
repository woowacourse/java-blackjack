package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardFactoryTest {

    @DisplayName("카드 52장을 반환한다")
    @Test
    void create() {
        CardFactory cardFactory = new CardFactory();

        List<Card> cards = cardFactory.createBlackJackCard();

        assertThat(cards).hasSize(52);
    }

}
