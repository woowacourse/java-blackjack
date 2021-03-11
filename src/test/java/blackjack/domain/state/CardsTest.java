package blackjack.domain.state;

import blackjack.domain.Fixture;
import blackjack.state.Cards;
import blackjack.state.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardsTest {

    @DisplayName("Bust인지 아닌지 판별")
    @Test
    void isBust() {
        Cards cards = new Cards(Arrays.asList(Fixture.CLUBS_TEN, Fixture.CLUBS_ACE, Fixture.CLUBS_KING));

        assertTrue(cards.isBust());
    }

    @DisplayName("카드 점수 총합 확인")
    @Test
    void score() {
        Cards cards = new Cards(Arrays.asList(Fixture.CLUBS_TEN, Fixture.CLUBS_ACE));

        assertThat(cards.score()).isEqualTo(new Score(21));
    }

    @DisplayName("블랙잭인지 확인")
    @Test
    void blackJack() {
        Cards cards = new Cards(Arrays.asList(Fixture.CLUBS_TEN, Fixture.CLUBS_ACE));

        assertTrue(cards.score().isBlackJack());
    }

    @DisplayName("Bust인지 확인")
    @Test
    void isBlackJack() {
        Cards cards = new Cards(Arrays.asList(Fixture.CLUBS_TEN, Fixture.CLUBS_ACE, Fixture.CLUBS_KING));

        assertTrue(cards.score().isBust());
    }
}
