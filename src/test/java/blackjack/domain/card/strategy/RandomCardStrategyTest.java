package blackjack.domain.card.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;

public class RandomCardStrategyTest {

    private final RandomDeckGenerator randomCardStrategy = new RandomDeckGenerator();

    @Test
    @DisplayName("생성한 카드들이 52개인지 확인한다.")
    void validateCardsSizeTest() {
        final List<Card> cards = randomCardStrategy.generate();
        assertThat(cards.size()).isEqualTo(52);
    }

}
