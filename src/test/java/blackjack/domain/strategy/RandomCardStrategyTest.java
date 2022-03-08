package blackjack.domain.strategy;

import blackjack.domain.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomCardStrategyTest {

    private final RandomCardStrategy randomCardStrategy = new RandomCardStrategy();

    @Test
    @DisplayName("생성한 카드들이 52개인지 확인한다.")
    void validateCardsSizeTest() {
        final List<Card> cards = randomCardStrategy.generate();
        assertThat(cards.size()).isEqualTo(52);
    }

}
