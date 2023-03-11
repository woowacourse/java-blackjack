package domain.user.state;

import static domain.game.Winning.BLACKJACK;
import static domain.game.Winning.PUSH;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackTest {

    State state;

    @BeforeEach
    void setUpBlackJackState() {
        this.state = StateFixtures.createBlackJackState();
    }

    @DisplayName("딜러가 Bust일시 블랙잭으로 승리한다.")
    @Test
    void match_Bust_BlackJack() {
        State dealer = StateFixtures.createBustState();

        assertThat(state.match(dealer)).isSameAs(BLACKJACK);
    }

    @DisplayName("딜러가 Stay일시 블랙잭으로 승리한다.")
    @Test
    void match_Stay_BlackJack() {
        State dealer = StateFixtures.createStayState();

        assertThat(state.match(dealer)).isSameAs(BLACKJACK);
    }

    @DisplayName("딜러가 BlackJack일시 무승부다.")
    @Test
    void match_BlackJack_Push() {
        State dealer = StateFixtures.createBlackJackState();

        assertThat(state.match(dealer)).isSameAs(PUSH);
    }

    @DisplayName("블랙잭이 맞다.")
    @Test
    void isBlackJack_True() {
        assertThat(state.isBlackJack()).isTrue();
    }

    @DisplayName("버스트가 아니다.")
    @Test
    void isBust_False() {
        assertThat(state.isBust()).isFalse();
    }
}
