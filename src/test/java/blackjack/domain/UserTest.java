package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class UserTest {

    @DisplayName("올바른 유저의 이름을 테스트한다.")
    @ParameterizedTest(name = "{index} {displayName} name = {0}")
    @ValueSource(strings = {"jason", "pobi"})
    void validNameTest(final String name) {
        User user = new User(name);
        assertThat(user.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("유저의 이름이 공백인 경우 예외를 발생시킨다.")
    void invalidNameTest() {
        assertThatThrownBy(() -> new User(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유저의 이름은 한 글자 이상이어야 합니다.");
    }

    @Test
    @DisplayName("유저는 보유한 카드의 합을 구할 수 있다.")
    void calculateCardSum() {
        User user = new User("Pobi");
        user.receiveCard(new Card(CardNumber.EIGHT, CardType.CLOVER));
        user.receiveCard(new Card(CardNumber.SEVEN, CardType.CLOVER));
        user.receiveCard(new Card(CardNumber.QUEEN, CardType.CLOVER));

        assertThat(user.cardSum()).isEqualTo(25);
    }
}
