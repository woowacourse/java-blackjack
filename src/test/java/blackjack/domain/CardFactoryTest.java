package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardFactoryTest {

    @DisplayName("카드 52장을 반환한다")
    @Test
    public void create() {
        CardFactory cardFactory = new CardFactory();

        List<Card> cards = cardFactory.createBlackJackCard();

        assertThat(cards.size()).isEqualTo(52);
    }

}
