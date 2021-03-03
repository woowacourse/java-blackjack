package blackjack.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class PlayerTest {
    private Player player;

    @DisplayName("플레이어가 올바르게 생성되는 지 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"pobi", "brown"})
    void create_player_test(String name) {
        assertThatCode(() -> new Player(name))
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어가 잘못 생성되는 경우 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"#pobi", "bro wn"})
    void create_invalid_player_test(String name) {
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("이름을 잘못 입력하였습니다. (입력값 : %s)", name));
    }

    @BeforeEach
    void setUp() {
        player = new Player("pobi");
        player.addFirstCard(Arrays.asList(
                Card.of("스페이드", "10"),
                Card.of("하트", "A")
        ));
    }

    @AfterEach
    void afterEach() {
        player.removeAll();
    }

    @DisplayName("딜러에게 첫 카드 2장을 받는다.")
    @Test
    void cards_add_test() {
        assertThat(player.getCards()).hasSize(2);
    }

    @DisplayName("21이 넘지 않을 경우 카드를 더 받도록 선택한다.")
    @Test
    void more_card_add_test() {
        player.addCard(Card.of("클로버", "A"));
        assertThat(player.getCards()).hasSize(3);
    }

    @DisplayName("플레이어의 카드가 21점 초과하는 경우 카드를 받을 수 없는 상태가 된다.")
    @Test
    void game_over_score() {
        int gameOverScore = 21;
        assertThat(player.isGameOver(gameOverScore)).isEqualTo(false);
    }

    @DisplayName("플레이어의 첫 카드가 21점 초과하는 경우(A가 2장) A를 21 이하일때까지 1로 계산한다.")
    @Test
    void first_cards_game_over_score() {
        int gameOverScore = 21;
        Player player = new Player("pobi");
        player.addFirstCard(Arrays.asList(
                Card.of("스페이드", "A"),
                Card.of("하트", "A")
        ));

        assertThat(player.isGameOver(gameOverScore)).isEqualTo(false);
        assertThat(player.calculateScore(gameOverScore)).isEqualTo(12);
    }

    @DisplayName("플레이어의 추가 카드 포함한 점수가 21점 초과이고, A를 21 이하일때까지 A를 1로 계산한다.")
    @Test
    void add_card_game_over_score() {
        int gameOverScore = 21;
        Player player = new Player("pobi");
        player.addFirstCard(Arrays.asList(
                Card.of("스페이드", "A"),
                Card.of("하트", "A")
        ));
        player.addCard(Card.of("클로버", "A"));
        assertThat(player.isGameOver(gameOverScore)).isEqualTo(false);
        assertThat(player.calculateScore(gameOverScore)).isEqualTo(13);
    }
}