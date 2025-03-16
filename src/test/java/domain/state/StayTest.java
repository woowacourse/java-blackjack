package domain.state;

import static domain.fixture.BlackjackCardFixture.ACE_HEART;
import static domain.fixture.BlackjackCardFixture.KING_HEART;
import static domain.fixture.BlackjackCardFixture.THREE_HEART;

import domain.card.Cards;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StayTest {

    @Test
    void 스테이_상태는_종료_상태다() {
        // given
        Cards cards = new Cards(
                List.of(ACE_HEART(), KING_HEART())
        );
        Stay stay = new Stay(cards);

        // when & then
        Assertions.assertThat(stay.isFinished()).isTrue();
    }

    @Test
    void 스테이_상태에서는_hit를_하지_못한다() {
        // given
        Cards cards = new Cards(
                List.of(ACE_HEART(), KING_HEART())
        );
        Stay stay = new Stay(cards);

        // when & then
        Assertions.assertThatThrownBy(() -> {
            stay.hit(THREE_HEART());
        }).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 스테이_상태에서는_stay를_하지_못한다() {
        // given
        Cards cards = new Cards(
                List.of(THREE_HEART(), KING_HEART())
        );
        Stay stay = new Stay(cards);

        // when & then
        Assertions.assertThatThrownBy(() -> {
            stay.stay();
        }).isInstanceOf(IllegalStateException.class);
    }
}
