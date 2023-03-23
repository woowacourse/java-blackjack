package domain.user.state;

import static domain.card.Denomination.ACE;
import static domain.card.Denomination.EIGHT;
import static domain.card.Denomination.NINE;
import static domain.card.Denomination.SEVEN;
import static domain.card.Denomination.SIX;
import static domain.card.Denomination.TEN;
import static domain.card.Denomination.THREE;
import static domain.card.Denomination.TWO;
import static domain.card.Suits.DIAMOND;
import static domain.game.Winning.LOSE;
import static domain.game.Winning.PUSH;
import static domain.game.Winning.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StayTest {

    State state;

    @BeforeEach
    void setUpStayState() {
        this.state = State.create()
            .hit(Card.of(TWO, DIAMOND))
            .hit(Card.of(SIX, DIAMOND)) // Ready -> Running
            .hit(Card.of(TEN, DIAMOND))
            .stay(); // Running -> Stay (18);
    }

    @DisplayName("딜러가 Bust일시 승리한다.")
    @Test
    void match_Bust_Lose() {
        State dealer = StateFixtures.createBustState();

        assertThat(state.match(dealer)).isSameAs(WIN);
    }

    @DisplayName("딜러가 Stay일시 점수가 더 높으면 승리한다.")
    @Test
    void match_Stay_Win() {
        State dealer = State.create()
            .hit(Card.of(TEN, DIAMOND))
            .hit(Card.of(SIX, DIAMOND)) // Ready -> Running
            .hit(Card.of(ACE, DIAMOND))
            .stay(); // Running -> Stay (17)

        assertThat(state.match(dealer)).isSameAs(WIN);
    }

    @DisplayName("딜러가 Stay일시 점수가 더 낮으면 패배한다.")
    @Test
    void match_Stay_Lose() {
        State dealer = State.create()
            .hit(Card.of(TEN, DIAMOND))
            .hit(Card.of(SIX, DIAMOND)) // Ready -> Running
            .hit(Card.of(THREE, DIAMOND))
            .stay(); // Running -> Stay (19)

        assertThat(state.match(dealer)).isSameAs(LOSE);
    }

    @DisplayName("딜러가 Stay일시 점수가 같으면 무승부다.")
    @Test
    void match_Stay_Push() {
        State dealer = State.create()
            .hit(Card.of(TWO, DIAMOND))
            .hit(Card.of(SEVEN, DIAMOND)) // Ready -> Running
            .hit(Card.of(NINE, DIAMOND))
            .stay(); // Running -> Stay (18);

        assertThat(state.match(dealer)).isSameAs(PUSH);
    }

    @DisplayName("21점이 되면 Stay가 된다.")
    @Test
    void StayWhen21() {
        state = State.create()
            .hit(Card.of(THREE, DIAMOND))
            .hit(Card.of(EIGHT, DIAMOND)) // Ready -> Running
            .hit(Card.of(TEN, DIAMOND)); // Running -> Stay (21)

        assertThat(state).isInstanceOf(Stay.class);
    }

    @DisplayName("딜러가 BlackJack일시 패배한다.")
    @Test
    void match_BlackJack_Lose() {
        State dealer = StateFixtures.createBlackJackState();

        assertThat(state.match(dealer)).isSameAs(LOSE);
    }

    @DisplayName("블랙잭이 아니다.")
    @Test
    void isBlackJack_False() {
        assertThat(state.isBlackJack()).isFalse();
    }

    @DisplayName("버스트가 아니다.")
    @Test
    void isBust_False() {
        assertThat(state.isBust()).isFalse();
    }
}
