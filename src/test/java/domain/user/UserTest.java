package domain.user;

import static domain.card.Denomination.JACK;
import static domain.card.Denomination.TEN;
import static domain.card.Denomination.THREE;
import static domain.card.Denomination.TWO;
import static domain.card.Suits.HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suits;
import domain.user.state.Stay;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    User user;

    @BeforeEach
    void setUpUser() {
        user = new User() {
            @Override
            public boolean isHittable() {
                return true;
            }
        };
    }


    @DisplayName("카드를 받아 자신의 카드 더미에 추가할 수 있다")
    @Test
    void hit() {
        Card card1 = Card.of(Denomination.TWO, Suits.HEART);
        Card card2 = Card.of(Denomination.THREE, Suits.DIAMOND);
        user.hit(card1);
        user.hit(card2);
        List<Card> cards = user.getCards();

        assertThat(cards).containsExactly(card1, card2);
    }

    @DisplayName("Running상태인 유저를 Stay상태로 바꾼다.")
    @Test
    void stay() {
        user.hit(Card.of(TWO, HEART));
        user.hit(Card.of(THREE, HEART));
        user.stay();

        assertThat(user.getState()).isInstanceOf(Stay.class);
    }

    @DisplayName("Terminated 상태인 유저를 stay할시 예외가 발생한다.")
    @Test
    void stayExceptionIfTerminated() {
        user.hit(Card.of(TEN, HEART));
        user.hit(Card.of(JACK, HEART));
        user.hit(Card.of(TWO, HEART));

        assertThatThrownBy(() -> user.stay())
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessage("이미 종료되었습니다.");
    }

    @DisplayName("Ready 상태인 유저를 stay할시 예외가 발생한다.")
    @Test
    void stayExceptionIfReady() {
        assertThatThrownBy(() -> user.stay())
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessage("게임 시작 전입니다.");
    }
}
