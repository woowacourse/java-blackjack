package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.painting.Suit;
import blackjack.domain.card.painting.Value;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {
    @DisplayName("유저의 카드 뽑기 테스트")
    @Test
    void testDrawCard() {
        //given
        User user = new User("욘");

        //when
        user.drawCard(new Card(Suit.DIAMOND, Value.ACE));
        user.drawCard(new Card(Suit.CLOVER, Value.FOUR));

        //then
        assertThat(user.getCards()).containsExactly(new Card(Suit.DIAMOND, Value.ACE), new Card(Suit.CLOVER, Value.FOUR));
    }

    @DisplayName("유저의 이름이 딜러와 같을 때 검증")
    @Test
    void whenUserNameEqualsDealer() {
        //given
        String dealerName = "딜러";
        //when
        //then
        assertThatThrownBy(() -> new User(dealerName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("딜러는 이름으로 사용할 수 없습니다.");
    }

    @DisplayName("유저의 이름이 공백일 때 검증")
    @Test
    void whenUserNameEmpty() {
        //given
        String empty = "";
        //when
        //then
        assertThatThrownBy(() -> new User(empty))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름이 공백일 수 없습니다.");
    }
}