package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import domain.AbstractTestFixture;
import domain.Result;
import domain.card.Deck;

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

        user.drawFrom(deck);

        assertThat(user.getHand().getCards()).hasSize(1);
    }

    static Stream<Arguments> test_win_lose_draw() {
        return Stream.of(
                Arguments.of(
                        new User("땡칠", createCards("K", "K")),
                        new User("조이", createCards("K", "K")),
                        Result.DRAW),
                Arguments.of(
                        new User("땡칠", createCards("K", "K")),
                        new User("조이", createCards("A", "J")),
                        Result.LOSE),
                Arguments.of(
                        new User("땡칠", createCards("A", "J")),
                        new User("조이", createCards("A", "A")),
                        Result.WIN_BY_BLACKJACK)
        );
    }

    @ParameterizedTest(name = "플레이어 간 승패를 알 수 있다")
    @MethodSource
    void test_win_lose_draw(Player player, Player other, Result result) {
        assertThat(player.competeWith(other)).isEqualTo(result);
    }

    @DisplayName("블랙잭 승리인지 알 수 있다")
    @Test
    void test_win_by_blackjack() {
        var user = new User("땡칠", createCards("K", "A"));
        var other = new Dealer(createCards("K", "K"));

        assertThat(user.competeWith(other)).isEqualTo(Result.WIN_BY_BLACKJACK);
    }

    @DisplayName("플레이어와 딜러가 모두 죽으면 무승부로 한다")
    @Test
    void test_all_busted_is_draw() {
        var user = new User("조이", createCards("10", "K", "J"));
        var dealer = new Dealer(createCards("K", "8", "10"));

        assertThat(user.competeWith(dealer)).isEqualTo(Result.DRAW);
    }

    @DisplayName("유저만 죽으면 딜러가 이긴다")
    @Test
    void test_user_busted_dealer_wins() {
        var user = new User("조이", createCards("10", "K", "J"));
        var dealer = new Dealer(createCards("K", "9"));

        assertThat(user.competeWith(dealer)).isEqualTo(Result.LOSE);
        assertThat(dealer.competeWith(user)).isEqualTo(Result.WIN);
    }

    @DisplayName("딜러만 죽으면 유저가 이긴다")
    @Test
    void test_dealer_busted_user_wins() {
        var user = new User("조이", createCards("A", "J"));
        var dealer = new Dealer(createCards("K", "9", "10"));

        assertThat(user.competeWith(dealer)).isEqualTo(Result.WIN_BY_BLACKJACK);
        assertThat(dealer.competeWith(user)).isEqualTo(Result.LOSE);
    }
}