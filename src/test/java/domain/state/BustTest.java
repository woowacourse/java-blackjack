package domain.state;

import static domain.fixture.BlackjackCardFixture.ACE_HEART;
import static domain.fixture.BlackjackCardFixture.FIVE_HEART;
import static domain.fixture.BlackjackCardFixture.KING_HEART;
import static domain.fixture.BlackjackCardFixture.TEN_HEART;
import static domain.fixture.BlackjackCardFixture.THREE_HEART;
import static domain.fixture.BlackjackCardFixture.TWO_HEART;

import domain.card.Cards;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

;

class BustTest {

    @Test
    void 버스트_상태는_종료_상태다() {
        // given
        Cards cards = new Cards(
                List.of(ACE_HEART(), KING_HEART())
        );
        Bust bust = new Bust(cards);

        // when & then
        Assertions.assertThat(bust.isFinished()).isTrue();
    }

    @Test
    void 버스트_상태에서는_hit를_하지_못한다() {
        // given
        Cards cards = new Cards(
                List.of(ACE_HEART(), KING_HEART())
        );
        Bust bust = new Bust(cards);

        // when & then
        Assertions.assertThatThrownBy(() -> {
            bust.hit(THREE_HEART());
        }).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 버스트_상태에서는_stay를_하지_못한다() {
        // given
        Cards cards = new Cards(
                List.of(ACE_HEART(), KING_HEART())
        );
        Bust bust = new Bust(cards);

        // when & then
        Assertions.assertThatThrownBy(() -> {
            bust.stay();
        }).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 카드목록을_받아_버스트_상태이면_true를_반환한다() {
        // given
        Cards cards = new Cards(List.of(TEN_HEART(), TEN_HEART(), TWO_HEART()));

        // when & then
        Assertions.assertThat(Bust.isBust(cards))
                .isTrue();
    }

    @Test
    void 카드목록을_받아_버스트_상태가_아니면_false를_반환한다() {
        // given
        Cards cards = new Cards(List.of(FIVE_HEART(), FIVE_HEART(), ACE_HEART()));

        // when & then
        Assertions.assertThat(Bust.isBust(cards))
                .isFalse();
    }
}
