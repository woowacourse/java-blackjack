package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackCardFactoryTest {

    @DisplayName("고유한 카드 52장을 생성한다")
    @Test
    public void generate() {
        BlackjackCardFactory blackjackCardFactory = new BlackjackCardFactory();

        List<Card> generatedCards = blackjackCardFactory.generate();

        assertThat(generatedCards.size()).isEqualTo(52);
    }
}
