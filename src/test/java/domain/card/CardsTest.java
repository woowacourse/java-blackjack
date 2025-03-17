package domain.card;

import static domain.fixture.BlackjackCardFixture.ACE_HEART;
import static domain.fixture.BlackjackCardFixture.FIVE_HEART;
import static domain.fixture.BlackjackCardFixture.FOUR_HEART;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    void 카드를_한장_추가한다() {
        // given
        Cards cards = new Cards(List.of(FIVE_HEART(), FOUR_HEART()));

        // when
        final int beforeSize = cards.size();
        cards.addCard(ACE_HEART());

        // then
        final int afterSize = cards.size();
        Assertions.assertThat(afterSize).isEqualTo(beforeSize + 1);
    }

    @Test
    void 가진_카드로_최적의_합을_계산한다() {
        // given
        Cards cards = new Cards(List.of(FIVE_HEART(), FOUR_HEART(), ACE_HEART()));

        // when
        final int sum = cards.computeOptimalSum();

        // then
        Assertions.assertThat(sum).isEqualTo(20);
    }
}