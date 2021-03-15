package blackjack.domain.player;

import static blackjack.domain.Fixture.FIXTURE_ACE;
import static blackjack.domain.Fixture.FIXTURE_KING;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.state.State;
import blackjack.domain.state.StateFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamerTest {

    @DisplayName("게이머의 점수가 21점이 초과되면 카드를 추가할 수 없다.")
    @Test
    void 카드_추가_테스트() {
        // given, when
        State state = StateFactory.generateState(FIXTURE_KING, FIXTURE_KING);
        state.draw(FIXTURE_ACE);
        Player gamer = new Gamer("테스트 게이머", state);

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            gamer.addCard(Card.of(Denomination.FIVE, Shape.CLUBS));
        });
    }
}
