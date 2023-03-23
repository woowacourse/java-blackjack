package domain.user.state;

import static domain.game.Winning.LOSE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BustTest {

    State state;

    @BeforeEach
    void setUpBustState() {
        this.state = StateFixtures.createBustState();
    }

    @DisplayName("딜러가 Bust일시 패배한다.")
    @Test
    void match_Bust_Lose() {
        State dealer = StateFixtures.createBustState();

        assertThat(state.match(dealer)).isSameAs(LOSE);
    }

    @DisplayName("딜러가 Stay일시 패배한다.")
    @Test
    void match_Stay_Lose() {
        State dealer = StateFixtures.createStayState();

        assertThat(state.match(dealer)).isSameAs(LOSE);
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

    @DisplayName("버스트가 맞다.")
    @Test
    void isBust_True() {
        assertThat(state.isBust()).isTrue();
    }
}
