package blackjack.domain.state.running;

import static blackjack.domain.Fixture.FIXTURE_ACE;
import static blackjack.domain.Fixture.FIXTURE_KING;
import static blackjack.domain.Fixture.FIXTURE_THREE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.Bust;
import blackjack.domain.state.finished.Stay;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HitTest {

    private Hit hit;

    @BeforeEach
    void setUp() {
        List<Card> cards = Arrays.asList(FIXTURE_KING, FIXTURE_THREE);
        hit = new Hit(Cards.of(cards));
    }

    @DisplayName("게이머가 한 장을 더 뽑아서 20을 넘지 않으면 Hit 상태가 다시 된다.")
    @Test
    void againHit() {
        // given, when
        State againHit = hit.draw(FIXTURE_ACE);

        // then
        assertThat(againHit).isInstanceOf(Hit.class);
    }

    @DisplayName("게이머가 Stay를 원하면 Stay 상태가 되야 한다.")
    @Test
    void stay() {
        // given, when
        State stay = hit.stay();

        // then
        assertThat(stay).isInstanceOf(Stay.class);
    }

    @DisplayName("게이머가 한 장을 더 뽑아서 21이 넘으면 Bust 상태가 된다.")
    @Test
    void bust() {
        // given, when
        State bust = hit.draw(FIXTURE_KING);

        // then
        assertThat(bust).isInstanceOf(Bust.class);
    }
}
