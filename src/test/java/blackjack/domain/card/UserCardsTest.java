package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserCardsTest {

    private UserCards userCards;

    @BeforeEach
    void resetVariable() {
        userCards = new UserCards();
    }

    @DisplayName("같은 카드 추가시 예외 발생")
    @Test
    void add() {
        Card card = new Card(Type.DIAMOND, Symbol.ACE);
        userCards.add(card);
        assertThatThrownBy(() -> userCards.add(card)).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("중복");
    }

    @DisplayName("카드 숫자 합계")
    @Test
    void getTotalSum() {
        userCards.add(new Card(Type.DIAMOND, Symbol.ACE));
        userCards.add(new Card(Type.SPADE, Symbol.ACE));
        userCards.add(new Card(Type.HEART, Symbol.ACE));
        userCards.add(new Card(Type.CLUB, Symbol.ACE));
        userCards.add(new Card(Type.CLUB, Symbol.JACK));
        assertThat(userCards.getTotalScore()).isEqualTo(14);

        userCards = new UserCards();
        userCards.add(new Card(Type.DIAMOND, Symbol.ACE));
        userCards.add(new Card(Type.CLUB, Symbol.ACE));
        userCards.add(new Card(Type.CLUB, Symbol.TWO));
        assertThat(userCards.getTotalScore()).isEqualTo(14);
    }

    @DisplayName("Bust시 0으로 초기화 확인")
    @Test
    void getTotalSumBust() {
        userCards.add(new Card(Type.CLUB, Symbol.JACK));
        userCards.add(new Card(Type.CLUB, Symbol.QUEEN));
        userCards.add(new Card(Type.CLUB, Symbol.KING));
        assertThat(userCards.getTotalScore()).isEqualTo(0);
    }

    @DisplayName("카드가 21을 넘는지 확인")
    @Test
    void isOverBlackJack() {
        userCards.add(new Card(Type.DIAMOND, Symbol.TWO));
        userCards.add(new Card(Type.DIAMOND, Symbol.THREE));
        userCards.add(new Card(Type.DIAMOND, Symbol.JACK));
        assertThat(userCards.isBust()).isFalse();

        userCards.add(new Card(Type.CLUB, Symbol.JACK));
        assertThat(userCards.isBust()).isTrue();
    }

    @DisplayName("카드가 21인지 확인")
    @Test
    void isOneMoreAddThenBust() {
        userCards.add(new Card(Type.DIAMOND, Symbol.TWO));
        userCards.add(new Card(Type.DIAMOND, Symbol.THREE));
        userCards.add(new Card(Type.DIAMOND, Symbol.JACK));
        assertThat(userCards.isOneMoreAddThenBust()).isFalse();

        userCards.add(new Card(Type.CLUB, Symbol.SIX));
        assertThat(userCards.isOneMoreAddThenBust()).isTrue();
    }

    @DisplayName("카드가 블랙잭인지 확인")
    @Test
    void isBlackJack() {
        userCards.add(new Card(Type.CLUB, Symbol.ACE));
        userCards.add(new Card(Type.CLUB, Symbol.TEN));
        assertThat(userCards.isBlackJack()).isTrue();

        userCards.add(new Card(Type.SPADE, Symbol.EIGHT));
        assertThat(userCards.isBlackJack()).isFalse();

        userCards = new UserCards();
        userCards.add(new Card(Type.SPADE, Symbol.TEN));
        userCards.add(new Card(Type.SPADE, Symbol.EIGHT));
        userCards.add(new Card(Type.SPADE, Symbol.THREE));
        assertThat(userCards.isBlackJack()).isFalse();
    }
}

