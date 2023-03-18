package blackjack.domain.person;

import blackjack.domain.blackjackGame.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PlayerTest {

    @Test
    @DisplayName("딜러가 버스트면 플레이어는 가지고 있는 카드와 상관없이 승리한다.")
    void matchGame_dealer_bust() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(Card.of(Suit.SPADE, Rank.KING));
        dealer.addCard(Card.of(Suit.SPADE, Rank.FIVE));
        dealer.addCard(Card.of(Suit.SPADE, Rank.QUEEN));

        Player player = new Player("encho");
        player.addCard(Card.of(Suit.DIAMOND, Rank.KING));
        player.addCard(Card.of(Suit.DIAMOND, Rank.QUEEN));
        player.addCard(Card.of(Suit.DIAMOND, Rank.JACK));

        // when
        GameResult gameResult = player.matchGame(dealer);

        // then
        assertThat(gameResult)
                .isEqualTo(GameResult.WIN);

    }

    @Test
    @DisplayName("딜러와 플레이어가 동시에 블랙잭이면 무승부이다.")
    void matchGame_DealerAndPlayerBust() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard((Card.of(Suit.SPADE, Rank.JACK)));
        dealer.addCard(Card.of(Suit.SPADE, Rank.ACE));

        Player player = new Player("encho");
        player.addCard(Card.of(Suit.DIAMOND, Rank.KING));
        player.addCard(Card.of(Suit.DIAMOND, Rank.ACE));

        // when
        GameResult gameResult = player.matchGame(dealer);

        // then
        assertThat(gameResult)
                .isEqualTo(GameResult.DRAW);

    }

    @Test
    @DisplayName("플레이어가 블랙잭이고 딜러는 블랙잭이 아니면 플레이어의 게임 결과는 블랙잭이다.")
    void matchGame_blackjack() {
        // given
        Dealer dealer = new Dealer();
        Player player = new Player("encho");
        player.addCard(Card.of(Suit.DIAMOND, Rank.KING));
        player.addCard(Card.of(Suit.DIAMOND, Rank.ACE));

        // when
        GameResult gameResult = player.matchGame(dealer);

        // then
        assertThat(gameResult)
                .isEqualTo(GameResult.BLACKJACK);

    }

    @Test
    @DisplayName("딜러가 버스트가 아니고 플레이어가 버스트이면 게임 결과는 패배이다.")
    void matchGame_bust() {
        // given
        Dealer dealer = new Dealer();
        Player player = new Player("encho");
        player.addCard(Card.of(Suit.DIAMOND, Rank.KING));
        player.addCard(Card.of(Suit.DIAMOND, Rank.JACK));
        player.addCard(Card.of(Suit.DIAMOND, Rank.QUEEN));

        // when
        GameResult gameResult = player.matchGame(dealer);

        // then
        assertThat(gameResult)
                .isEqualTo(GameResult.LOSE);

    }

    @Test
    @DisplayName("상대의 점수가 21점을 넘고 내가 넘지 않으면 승리해야 한다.")
    void matchGame_otherOverScore() {
        // given
        Player player1 = new Player("glen");
        Player player2 = new Player("encho");
        player1.addCard(Card.of(Suit.DIAMOND, Rank.TWO));
        player2.addCard(Card.of(Suit.DIAMOND, Rank.KING));
        player2.addCard(Card.of(Suit.DIAMOND, Rank.KING));
        player2.addCard(Card.of(Suit.DIAMOND, Rank.KING));

        // when
        GameResult gameResult = player1.matchGame(player2);

        // then
        assertThat(gameResult)
                .isEqualTo(GameResult.WIN);
    }

    @Nested
    @DisplayName("내가 블랙잭이거나 버스트가 아닐 때 ")
    class whenBlackjackOrNotBust {
        @Test
        @DisplayName("딜러의 점수가 내 점수보다 낮으면 승리해야 한다.")
        void matchGame_win() {
            // given
            Player player1 = new Player("glen");
            Player player2 = new Player("encho");
            player1.addCard(Card.of(Suit.DIAMOND, Rank.KING));
            player2.addCard(Card.of(Suit.DIAMOND, Rank.FIVE));

            // when
            GameResult gameResult = player1.matchGame(player2);

            // then
            assertThat(gameResult)
                    .isEqualTo(GameResult.WIN);
        }

        @Test
        @DisplayName("딜러의 점수가 내 점수보다 높으면 패배해야 한다.")
        void matchGame_lose() {
            // given
            Player player1 = new Player("glen");
            Player player2 = new Player("encho");
            player1.addCard(Card.of(Suit.DIAMOND, Rank.KING));
            player2.addCard(Card.of(Suit.DIAMOND, Rank.ACE));

            // when
            GameResult gameResult = player1.matchGame(player2);

            // then
            assertThat(gameResult)
                    .isEqualTo(GameResult.LOSE);
        }

        @Test
        @DisplayName("딜러의 점수가 나와 같으면 무승부여야 한다.")
        void matchGame_draw() {
            // given
            Player player1 = new Player("glen");
            Player player2 = new Player("encho");
            player1.addCard(Card.of(Suit.DIAMOND, Rank.FIVE));
            player2.addCard(Card.of(Suit.DIAMOND, Rank.FIVE));

            // when
            GameResult gameResult = player1.matchGame(player2);

            // then
            assertThat(gameResult)
                    .isEqualTo(GameResult.DRAW);
        }
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

    @ParameterizedTest(name = "이름이 \"{0}\"일 때")
    @ValueSource(strings = {" ", "  ", "", "\n"})
    @DisplayName("이름이 공백이면 예외가 발생해야 한다.")
    void validateBlankName(String input) {
        // expect
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Player(input)).withMessage("[ERROR] 이름은 공백일 수 없습니다.");
    }

    @Test
    @DisplayName("이름이 딜러면 예외가 발생해야 한다.")
    void validateBlacklist() {
        // expect
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Player("딜러"))
                .withMessage("[ERROR] 딜러는 사용할 수 없는 이름입니다.");
    }

    @Test
    @DisplayName("이름이 5글자를 초과하면 예외가 발생해야 한다.")
    void validateNameLength() {
        // expect
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Player("123456"))
                .withMessage("[ERROR] 이름은 5글자를 넘을 수 없습니다.");
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
