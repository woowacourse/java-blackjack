package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class UserTest extends AbstractTestFixture {

    @Test
    @DisplayName("이름을 가진다")
    void test_has_name() {
        var name = "이름이름";
        var user = new User(name);

        assertThat(user.getName()).isEqualTo("이름이름");
    }

    @ParameterizedTest(name = "점수가 21미만이면 더 뽑을 수 있고, 21이상이면 불가하다")
    @CsvSource({"A,false", "J,true"})
    void test_can_hit(String lastLetter, boolean canHit) {
        var user = new User("조이", createCards("K", lastLetter));

        assertThat(user.canHit()).isEqualTo(canHit);
    }

    @Test
    @DisplayName("덱에서 카드를 뽑을 수 있다")
    void test_draw_from_deck() {
        var deck = new Deck();
        var user = new User("땡칠");

        user.drawCardFrom(deck);

        assertThat(user.getCards()).hasSize(1);
    }
}