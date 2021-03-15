package blackjack.domain.player;

import static blackjack.domain.Fixture.FIXTURE_FIVE;
import static blackjack.domain.Fixture.FIXTURE_KING;
import static blackjack.domain.Fixture.FIXTURE_SEVEN;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import blackjack.domain.state.State;
import blackjack.domain.state.StateFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러 점수가 16점이 초과되면 카드를 추가할 수 없다.")
    @Test
    void 카드_추가_테스트() {
        // given, when
        State state = StateFactory.generateState(FIXTURE_KING, FIXTURE_SEVEN);
        Player dealer = new Dealer(state);
        System.out.println(dealer.calculateScore());

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            dealer.addCard(FIXTURE_FIVE);
        });
    }
}
