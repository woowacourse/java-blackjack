package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.state.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static blackjack.domain.state.CardFactory.*;
import static org.assertj.core.api.Assertions.*;

class PlayerTest {
    public static final int GAME_OVER_SCORE = 21;
    private Player player;
    private State state;

    @BeforeEach
    void setUp() {
        state = StateFactory.draw(SPADE_TWO, SPADE_TEN);

        player = new Player(state, "pobi");
    }

    @DisplayName("플레이어가 잘못 생성되는 경우 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"#pobi", "bro wn"})
    void create_invalid_player_test(String name) {
        assertThatThrownBy(() -> new Player(state, name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("이름을 잘못 입력하였습니다. (입력값 : %s)", name));
    }

    @DisplayName("21이 넘지 않을 경우 카드를 더 받도록 선택한다.")
    @Test
    void more_card_add_test() {
        State draw = player.getState().draw(HEART_TWO);

        assertThat(draw).isInstanceOf(Running.class);
    }

    @DisplayName("플레이어의 카드가 21점 초과하는 경우 카드를 받을 수 없는 상태가 된다.")
    @Test
    void game_over_score() {
        State draw = player.getState().draw(HEART_TEN);

        assertThat(draw).isInstanceOf(Finish.class);
    }

    @DisplayName("플레이어의 첫 카드가 21점 초과하는 경우(A가 2장) A를 21 이하일때까지 1로 계산한다.")
    @Test
    void first_cards_game_over_score() {
        State state = StateFactory.draw(SPADE_ACE, HEART_ACE);

        Player player = new Player(state, "pobi");

        assertThat(player.getState().calculateScore()).isEqualTo(12);
    }

    @DisplayName("플레이어의 추가 카드 포함한 점수가 21점 초과이고, A를 21 이하일때까지 A를 1로 계산한다.")
    @Test
    void add_card_game_over_score() {
        State state = StateFactory.draw(SPADE_TEN, HEART_TEN);
        State draw = state.draw(SPADE_ACE);

        Player player = new Player(draw, "pobi");

        assertThat(player.getState().calculateScore()).isEqualTo(21);
    }

    @DisplayName("A가 없고, 카드의 숫자가 21점 초과이면 플레이어의 게임이 끝난다.")
    @Test
    void add_card_game_over_test() {
        State state = StateFactory.draw(SPADE_TEN, HEART_TEN);
        State draw = state.draw(SPADE_TWO);

        Player player = new Player(draw, "pobi");

        assertThat(player.getState()).isInstanceOf(Finish.class);
    }
}
