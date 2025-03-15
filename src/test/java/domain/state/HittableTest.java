package domain.state;

import static domain.fixture.BlackjackCardFixture.ACE_HEART;
import static domain.fixture.BlackjackCardFixture.KING_HEART;
import static domain.fixture.BlackjackCardFixture.NINE_HEART;
import static domain.fixture.BlackjackCardFixture.QUEEN_HEART;
import static domain.fixture.BlackjackCardFixture.TEN_HEART;
import static domain.fixture.BlackjackCardFixture.TWO_HEART;

import domain.card.Cards;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class HittableTest {

    @Test
    void Hittable상태는_히트를_더_할_수_있는_상태다() {
        // given
        Cards cards = new Cards(
                List.of(ACE_HEART, NINE_HEART)
        );
        State hittable = new Hittable(cards);

        // when & then
        Assertions.assertThat(hittable.isFinished()).isFalse();
    }

    @Test
    void 히트를_해서_블랙잭_상태가_된다() {
        // given
        Cards cards = new Cards(
                List.of(ACE_HEART)
        );
        State hittable = new Hittable(cards);

        // when
        State newState = hittable.hit(TEN_HEART);

        // then
        Assertions.assertThat(newState).isInstanceOf(Blackjack.class);
    }

    @Test
    void 히트를_해서_버스트_상태가_된다() {
        // given
        Cards cards = new Cards(
                List.of(KING_HEART, QUEEN_HEART)
        );
        State hittable = new Hittable(cards);

        // when
        State newState = hittable.hit(TWO_HEART);

        // then
        Assertions.assertThat(newState).isInstanceOf(Bust.class);
    }

    @Test
    void 히트를_해서_합이_21이_되면_스테이_상태가_된다() {
        // given
        Cards cards = new Cards(
                List.of(ACE_HEART, NINE_HEART)
        );
        State hittable = new Hittable(cards);

        // when
        State newState = hittable.hit(ACE_HEART);

        // then
        Assertions.assertThat(newState).isInstanceOf(Stay.class);
    }

    @Test
    void 스테이를_해서_스테이_상태가_된다() {
        // given
        Cards cards = new Cards(
                List.of(ACE_HEART, NINE_HEART)
        );
        Hittable hittable = new Hittable(cards);

        // when
        State newState = hittable.stay();

        // then
        Assertions.assertThat(newState).isInstanceOf(Stay.class);
    }
}