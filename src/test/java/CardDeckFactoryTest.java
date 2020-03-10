import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CardDeckFactoryTest {
    @Test
    void 카드_리스트_생성_메서드() {
        List<Card> cards = CardDeckFactory.create();

        Assertions.assertThat(cards.size() == 52).isTrue();
    }
}
