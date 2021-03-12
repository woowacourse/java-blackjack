package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static blackjack.domain.Fixture.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.ResultType;
import blackjack.domain.state.State;
import blackjack.domain.state.StateFactory;
import java.util.Arrays;
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
