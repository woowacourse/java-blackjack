package blackjack.domain.state.finished;

import static blackjack.domain.Fixture.FIXTURE_KING;
import static blackjack.domain.Fixture.FIXTURE_NINE;
import static blackjack.domain.Fixture.FIXTURE_THREE;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.State;
import blackjack.domain.state.running.Hit;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BustTest {

    @DisplayName("버스트 상태에서 카드를 더 뽑으면 예외가 발생한다.")
    @Test
    void draw() {
        // given
        List<Card> cards = Arrays.asList(FIXTURE_KING, FIXTURE_THREE);
        Hit hit = new Hit(Cards.of(cards));

        // when
        State bust = hit.draw(FIXTURE_NINE);

        // then
        assertThatIllegalArgumentException().isThrownBy(() ->{
            bust.draw(FIXTURE_THREE);
        });
    }
}
