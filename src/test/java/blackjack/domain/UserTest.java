package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Suit;
import blackjack.util.BlackJackConstant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {
    @DisplayName("user 이름 생성 확인")
    @Test
    void userName() {
        User user = new Player("youngE");

        assertThat(user.getName()).isEqualTo("youngE");
    }

    @DisplayName("카드 처음 2장 받는 기능 확인")
    @Test
    void hitCard() {
        User user = new Player("youngE");

        user.hit(Fixture.CLUBS_KING, Fixture.CLUBS_TEN);

        assertThat(user.getCards()).hasSize(2);
    }


    @DisplayName("플레이어인지 판별 기능")
    @Test
    void isPlayer() {
        User user = new Player("test");

        assertTrue(user.isPlayer());
        assertFalse(user.isDealer());
    }

    @DisplayName("딜러인지 판별 기능")
    @Test
    void isDealer() {
        User user = new Dealer();

        assertTrue(user.isDealer());
        assertFalse(user.isPlayer());
    }

    @DisplayName("점수 반환 확인")
    @Test
    void checkScore() {
        User user = new Player("youngE");
        user.hit(Fixture.CLUBS_TEN, Fixture.CLUBS_KING);

        assertThat(user.getScore()).isEqualTo(20);
    }

    @DisplayName("BUST 되는지 확인")
    @Test
    void isBust() {
        User user = new Player("youngE");
        user.hit(Fixture.CLUBS_TEN, Fixture.CLUBS_KING);
        user.hit(Fixture.CLUBS_TWO);

        assertTrue(user.isBust());
    }

    @DisplayName("블랙잭인 경우를 판별한다. - 참")
    @Test
    void isBlackJackTrueTest() {
        User user = new Player("youngE");
        user.hit(Fixture.CLUBS_ACE, Fixture.CLUBS_TEN);

        assertTrue(user.isBlackJack());
    }

    @DisplayName("블랙잭인 경우를 판별한다. - 거짓")
    @Test
    void isBlackJackFalseTest() {
        User user = new Player("youngE");
        user.hit(Fixture.CLUBS_KING, Fixture.CLUBS_TWO);

        assertFalse(user.isBlackJack());
    }

    @DisplayName("플레이어 상태가 Finished 인지 확인")
    @Test
    void isFinished() {
        User user = new Player("youngE");
        user.hit(Fixture.CLUBS_KING, Fixture.CLUBS_TWO);

        assertFalse(user.isFinished());
    }

}