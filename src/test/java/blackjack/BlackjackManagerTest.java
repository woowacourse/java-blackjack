package blackjack;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackManagerTest {

    @DisplayName("카드 덱을 설정한다.")
    @Test
    void test1() {
        List<Card> cards = new CardsGenerator().generate();

        assertThatCode(() -> new BlackjackManager(new Deck(cards)))
                .doesNotThrowAnyException();
    }


}
