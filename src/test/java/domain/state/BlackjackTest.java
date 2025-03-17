package domain.state;

import static domain.fixture.BlackjackCardFixture.ACE_HEART;
import static domain.fixture.BlackjackCardFixture.FIVE_HEART;
import static domain.fixture.BlackjackCardFixture.KING_HEART;
import static domain.fixture.BlackjackCardFixture.TEN_HEART;
import static domain.fixture.BlackjackCardFixture.THREE_HEART;

import domain.card.Cards;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @Test
    void 블랙잭_상태는_종료_상태다() {
        // given
        Cards cards = new Cards(
                List.of(ACE_HEART(), KING_HEART())
        );
        Blackjack blackjack = new Blackjack(cards);

        // when & then
        Assertions.assertThat(blackjack.isFinished()).isTrue();
    }

    @Test
    void 블랙잭_상태에서는_hit를_하지_못한다() {
        // given
        Cards cards = new Cards(
                List.of(ACE_HEART(), KING_HEART())
        );
        Blackjack blackjack = new Blackjack(cards);

        // when & then
        Assertions.assertThatThrownBy(() -> {
            blackjack.hit(THREE_HEART());
        }).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 블랙잭_상태에서는_stay를_하지_못한다() {
        // given
        Cards cards = new Cards(
                List.of(ACE_HEART(), KING_HEART())
        );
        Blackjack blackjack = new Blackjack(cards);

        // when & then
        Assertions.assertThatThrownBy(() -> {
            blackjack.stay();
        }).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 카드목록을_받아_블랙잭_상태이면_true를_반환한다() {
        // given
        Cards cards = new Cards(List.of(TEN_HEART(), ACE_HEART()));

        // when & then
        Assertions.assertThat(Blackjack.isBlackjack(cards))
                .isTrue();
    }

    @Test
    void 카드목록을_받아_블랙잭_상태가_아니면_false를_반환한다() {
        // given
        Cards cards = new Cards(List.of(FIVE_HEART(), FIVE_HEART(), ACE_HEART()));

        // when & then
        Assertions.assertThat(Blackjack.isBlackjack(cards))
                .isFalse();
    }
}
