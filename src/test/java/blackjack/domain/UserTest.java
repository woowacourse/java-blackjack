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

    @DisplayName("카드 받는 기능 확인")
    @Test
    void hitCard() {
        User user = new Player("youngE");

        user.hit(new Card(Suit.SPADE, CardNumber.ACE));

        assertThat(user.getCards()).hasSize(1);
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
        user.hit(new Card(Suit.SPADE, CardNumber.ACE));
        user.hit(new Card(Suit.SPADE, CardNumber.NINE));

        assertThat(user.getScore()).isEqualTo(20);
    }

    @DisplayName("BUST 되는지 확인")
    @Test
    void isBust() {
        User user = new Player("youngE");
        user.hit(new Card(Suit.SPADE, CardNumber.TEN));
        user.hit(new Card(Suit.SPADE, CardNumber.NINE));
        user.hit(new Card(Suit.SPADE, CardNumber.NINE));

        assertTrue(user.isBust());
    }

    @DisplayName("BUST 점수 확인")
    @Test
    void checkBustScore() {
        User user = new Player("youngE");
        user.hit(new Card(Suit.SPADE, CardNumber.TEN));
        user.hit(new Card(Suit.SPADE, CardNumber.NINE));
        user.hit(new Card(Suit.SPADE, CardNumber.NINE));

        assertThat(user.getScore()).isEqualTo(BlackJackConstant.BUST);
    }

    @DisplayName("블랙잭인 경우를 판별한다. - 참")
    @Test
    void isBlackJackTrueTest() {
        User user = new Player("youngE");
        user.hit(new Card(Suit.SPADE, CardNumber.ACE));
        user.hit(new Card(Suit.SPADE, CardNumber.JACK));
        assertTrue(user.isBlackJack());
    }

    @DisplayName("블랙잭인 경우를 판별한다. - 거짓")
    @Test
    void isBlackJackFalseTest() {
        User user = new Player("youngE");
        user.hit(new Card(Suit.SPADE, CardNumber.ACE));
        user.hit(new Card(Suit.SPADE, CardNumber.NINE));
        assertFalse(user.isBlackJack());
    }

    @DisplayName("블랙잭 점수 확인")
    @Test
    void checkBlackJackScore() {
        User user = new Player("youngE");
        user.hit(new Card(Suit.SPADE, CardNumber.ACE));
        user.hit(new Card(Suit.SPADE, CardNumber.JACK));

        assertThat(user.getScore()).isEqualTo(BlackJackConstant.BLACKJACK_SCORE);
    }
}