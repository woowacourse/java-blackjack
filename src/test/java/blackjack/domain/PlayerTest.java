package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {

    @Test
    @DisplayName("상대의 점수가 내 점수보다 낮으면 승리해야 한다.")
    void matchGame_win() {
        // given
        Player player1 = new Player("glen");
        Player player2 = new Player("encho");
        player1.addCard(new Card(Suit.DIAMOND, Rank.KING));
        player2.addCard(new Card(Suit.DIAMOND, Rank.FIVE));

        // when
        GameResult gameResult = player1.matchGame(player2);

        // then
        assertThat(gameResult)
                .isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("상대의 점수가 내 점수보다 높으면 패배해야 한다.")
    void matchGame_lose() {
        // given
        Player player1 = new Player("glen");
        Player player2 = new Player("encho");
        player1.addCard(new Card(Suit.DIAMOND, Rank.KING));
        player2.addCard(new Card(Suit.DIAMOND, Rank.ACE));

        // when
        GameResult gameResult = player1.matchGame(player2);

        // then
        assertThat(gameResult)
                .isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("상대의 점수가 나와 같으면 무승부여야 한다.")
    void matchGame_draw() {
        // given
        Player player1 = new Player("glen");
        Player player2 = new Player("encho");
        player1.addCard(new Card(Suit.DIAMOND, Rank.FIVE));
        player2.addCard(new Card(Suit.DIAMOND, Rank.FIVE));

        // when
        GameResult gameResult = player1.matchGame(player2);

        // then
        assertThat(gameResult)
                .isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("상대의 점수와 나의 점수가 21점을 넘으면 무승부여야 한다.")
    void matchGame_sameOverScore() {
        // given
        Player player1 = new Player("glen");
        Player player2 = new Player("encho");
        player1.addCard(new Card(Suit.DIAMOND, Rank.KING));
        player1.addCard(new Card(Suit.DIAMOND, Rank.KING));
        player1.addCard(new Card(Suit.DIAMOND, Rank.KING));
        player2.addCard(new Card(Suit.DIAMOND, Rank.KING));
        player2.addCard(new Card(Suit.DIAMOND, Rank.KING));
        player2.addCard(new Card(Suit.DIAMOND, Rank.KING));

        // when
        GameResult gameResult = player1.matchGame(player2);

        // then
        assertThat(gameResult)
                .isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("상대의 점수가 21점을 넘고 내가 넘지 않으면 승리해야 한다.")
    void matchGame_otherOverScore() {
        // given
        Player player1 = new Player("glen");
        Player player2 = new Player("encho");
        player1.addCard(new Card(Suit.DIAMOND, Rank.TWO));
        player2.addCard(new Card(Suit.DIAMOND, Rank.KING));
        player2.addCard(new Card(Suit.DIAMOND, Rank.KING));
        player2.addCard(new Card(Suit.DIAMOND, Rank.KING));

        // when
        GameResult gameResult = player1.matchGame(player2);

        // then
        assertThat(gameResult)
                .isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("점수가 21점을 넘을 때 카드를 뽑으면 예외가 발생해야 한다.")
    void addCard_overScore() {
        // given
        Player player = new Player("encho");
        player.addCard(new Card(Suit.DIAMOND, Rank.KING));
        player.addCard(new Card(Suit.DIAMOND, Rank.KING));
        player.addCard(new Card(Suit.DIAMOND, Rank.KING));

        // expect
        assertThatIllegalArgumentException().isThrownBy(() -> {
            player.addCard(new Card(Suit.DIAMOND, Rank.ACE));
        }).withMessage("[ERROR] 점수가 21점을 넘으면 카드를 더 뽑을 수 없습니다.");
    }

    @Test
    @DisplayName("Player는 플레이어여야 한다.")
    void isPlayer_true() {
        // given
        Player player = new Player("glen");

        // expect
        assertThat(player.isPlayer())
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "  ", "", "\n"})
    @DisplayName("이름이 공백이면 예외가 발생해야 한다.")
    void validateBlankName(String input) {
        // expect
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Player(input);
        }).withMessage("[ERROR] 이름은 공백일 수 없습니다.");
    }

    @Test
    @DisplayName("이름이 딜러면 예외가 발생해야 한다.")
    void validateBlacklist() {
        // expect
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Player("딜러");
        }).withMessage("[ERROR] 딜러는 사용할 수 없는 이름입니다.");
    }

    @Test
    @DisplayName("이름이 5글자를 초과하면 예외가 발생해야 한다.")
    void validateNameLength() {
        // expect
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Player("123456");
        }).withMessage("[ERROR] 이름은 5글자를 넘을 수 없습니다.");
    }

    @Test
    @DisplayName("Player가 정상적으로 생성되어야 한다.")
    void create_success() {
        // given
        Player player = new Player("123");

        // expect
        assertThat(player.getName())
                .isEqualTo("123");
    }
}
