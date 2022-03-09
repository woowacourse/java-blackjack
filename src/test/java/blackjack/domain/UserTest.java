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
    @DisplayName("유저는 보유한 카드의 합이 21이 넘으면 패배한다.")
    void userLoseExceedTwentyOneTest() {
        User user = new User("Pobi");
        user.receiveCard(new Card(CardNumber.EIGHT, CardType.CLOVER));
        user.receiveCard(new Card(CardNumber.SEVEN, CardType.CLOVER));
        user.receiveCard(new Card(CardNumber.QUEEN, CardType.CLOVER));

        assertThat(user.checkResult(19)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("유저는 보유한 카드의 합이 딜러보다 작으면 패배한다.")
    void userLoseTest() {
        User user = new User("Pobi");
        user.receiveCard(new Card(CardNumber.EIGHT, CardType.CLOVER));
        user.receiveCard(new Card(CardNumber.QUEEN, CardType.CLOVER));

        assertThat(user.checkResult(20)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("유저는 보유한 카드의 합이 딜러의 카드 합보다 크면 승리한다.")
    void userWinTest() {
        User user = new User("Pobi");
        user.receiveCard(new Card(CardNumber.EIGHT, CardType.CLOVER));
        user.receiveCard(new Card(CardNumber.QUEEN, CardType.CLOVER));

        assertThat(user.checkResult(17)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("유저는 보유한 카드의 합이 딜러의 카드의 합과 같으면 무승부이다.")
    void userDrawTest() {
        User user = new User("Pobi");
        user.receiveCard(new Card(CardNumber.EIGHT, CardType.CLOVER));
        user.receiveCard(new Card(CardNumber.QUEEN, CardType.CLOVER));

        assertThat(user.checkResult(18)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("Ace는 1 또는 11로 계산될 수 있다.")
    void userDrawTest2() {
        User user = new User("Pobi");
        user.receiveCard(new Card(CardNumber.ACE, CardType.CLOVER));
        user.receiveCard(new Card(CardNumber.ACE, CardType.HEART));
        user.receiveCard(new Card(CardNumber.ACE, CardType.DIAMOND));

        assertThat(user.checkResult(13)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("유저가 버스트인 경우를 체크한다.")
    void burstTest() {
        User user = new User("Pobi");
        user.receiveCard(new Card(CardNumber.QUEEN, CardType.CLOVER));
        user.receiveCard(new Card(CardNumber.QUEEN, CardType.HEART));
        user.receiveCard(new Card(CardNumber.QUEEN, CardType.DIAMOND));

        assertThat(user.checkBust()).isTrue();
    }

    @Test
    @DisplayName("유저가 버스트가 아닌 경우를 체크한다.")
    void notBurstTest() {
        User user = new User("Pobi");
        user.receiveCard(new Card(CardNumber.ACE, CardType.CLOVER));
        user.receiveCard(new Card(CardNumber.ACE, CardType.HEART));
        user.receiveCard(new Card(CardNumber.ACE, CardType.DIAMOND));

        assertThat(user.checkBust()).isFalse();
    }
}
