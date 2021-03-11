package blackjack.domain.state;

import static blackjack.domain.state.Blackjack.BLACKJACK_DRAW_ERROR;
import static blackjack.domain.state.Blackjack.BLACKJACK_STAY_ERROR;
import static blackjack.domain.state.Bust.BUST_DRAW_ERROR;
import static blackjack.domain.state.Bust.BUST_STAY_ERROR;
import static blackjack.domain.state.Stay.STAY_DRAW_ERROR;
import static blackjack.domain.state.Stay.STAY_STAY_ERROR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StateTest {

    @DisplayName("첫 카드 2장이 블랙잭이라, 블랙잭을 반환한다.")
    @Test
    void makeBlackjackState() {
        Cards cards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.ACE_ELEVEN),
                new Card(Type.CLUB, Denomination.TEN)
            )
        );
        assertThat(State.makeState(cards)).isInstanceOf(Blackjack.class);
    }

    @DisplayName("첫 카드 2장이 블랙잭이 아니라면, 힛을 반환한다.")
    @Test
    void makeHitState() {
        Cards cards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.FIVE),
                new Card(Type.CLUB, Denomination.TEN)
            )
        );
        assertThat(State.makeState(cards)).isInstanceOf(Hit.class);
    }

    @DisplayName("힛 상태에서 카드를 드로우 했는데 21을 초과하면, 버스트를 반환한다.")
    @Test
    void hitDrawBust() {
        Cards cards = new Cards(
            new ArrayList<>(Arrays.asList(
                new Card(Type.CLUB, Denomination.ACE_ELEVEN),
                new Card(Type.CLUB, Denomination.TEN)
            ))
        );
        State state = new Hit(cards);
        assertThat(state.draw(new Card(Type.DIAMOND, Denomination.THREE))).isInstanceOf(Bust.class);
    }

    @DisplayName("힛 강태에서 카드를 드로우 했는데 21이하라면 힛을 반환한다.")
    @Test
    void hitDrawHit() {
        Cards cards = new Cards(
            new ArrayList<>(Arrays.asList(
                new Card(Type.CLUB, Denomination.TEN)
            ))
        );
        State state = new Hit(cards);
        assertThat(state.draw(new Card(Type.DIAMOND, Denomination.THREE))).isInstanceOf(Hit.class);
    }

    @DisplayName("블랙잭 상태에서 드로우를 하면 예외가 발생한다.")
    @Test
    void blackjackDrawException() {
        Cards cards = new Cards(
            new ArrayList<>(Arrays.asList(
                new Card(Type.CLUB, Denomination.TEN)
            ))
        );
        State state = new Blackjack(cards);

        assertThatThrownBy(() -> state.draw(new Card(Type.DIAMOND, Denomination.THREE)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(BLACKJACK_DRAW_ERROR);
    }

    @DisplayName("블랙잭 상태에서 스테이를 하면 예외가 발생한다.")
    @Test
    void blackjackStayException() {
        Cards cards = new Cards(
            new ArrayList<>(Collections.singletonList(
                new Card(Type.CLUB, Denomination.TEN)
            ))
        );
        State state = new Blackjack(cards);

        assertThatThrownBy(state::stay)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(BLACKJACK_STAY_ERROR);
    }

    @DisplayName("버스트 상태에서 드로우를 하면 예외가 발생한다.")
    @Test
    void bustDrawException() {
        Cards cards = new Cards(
            new ArrayList<>(Arrays.asList(
                new Card(Type.CLUB, Denomination.TEN),
                new Card(Type.SPADE, Denomination.ACE_ELEVEN)
            ))
        );
        State state = new Bust(cards);

        assertThatThrownBy(() -> state.draw(new Card(Type.DIAMOND, Denomination.THREE)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(BUST_DRAW_ERROR);
    }

    @DisplayName("버스트 상태에서 스테이를 하면 예외가 발생한다.")
    @Test
    void bustStayException() {
        Cards cards = new Cards(
            new ArrayList<>(Collections.singletonList(
                new Card(Type.CLUB, Denomination.TEN)
            ))
        );
        State state = new Bust(cards);

        assertThatThrownBy(state::stay)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(BUST_STAY_ERROR);
    }

    @DisplayName("스테이 상태에서 드로우 하면 예외가 발생한다.")
    @Test
    void stayDrawException() {
        Cards cards = new Cards(
            new ArrayList<>(Collections.singletonList(
                new Card(Type.CLUB, Denomination.TEN)
            ))
        );
        State state = new Stay(cards);

        assertThatThrownBy(() -> state.draw(new Card(Type.DIAMOND, Denomination.THREE)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(STAY_DRAW_ERROR);
    }

    @DisplayName("스테이 상태에서 스테이를 하면 예외가 발생한다.")
    @Test
    void stayStayException() {
        Cards cards = new Cards(
            new ArrayList<>(Collections.singletonList(
                new Card(Type.CLUB, Denomination.TEN)
            ))
        );
        State state = new Stay(cards);

        assertThatThrownBy(state::stay)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(STAY_STAY_ERROR);
    }

}