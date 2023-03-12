package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.bank.Money;
import domain.participant.Dealer;
import domain.participant.User;

public class GameTest extends AbstractTestFixture {

    @Test
    @DisplayName("카드를 2번 돌린다")
    void test_deal_two_cards() {
        var dealer = new Dealer();
        var game = createGameFrom(dealer, new User("조이"), new User("땡칠"));

        game.dealTwice();

        for (var user : game.getUsers()) {
            assertThat(user.getCards()).hasSize(2);
        }
        assertThat(dealer.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("참가중이지 않은 플레이어에게 카드를 줄 수 없다")
    void test_dealing_card_to_not_playing_throws() {
        var participant = new User("조이", createCards("10", "2"));
        var notParticipant = new User("참가자아님");

        var game = createGameFrom(new Dealer(createCards("K")), participant);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> game.dealTo(notParticipant));
    }

    @DisplayName("유저의 승패를 알 수 있다")
    @ParameterizedTest(name = "유저의 승패를 알 수 있다")
    @CsvSource({"K,LOSE", "A,DRAW", "3,WIN"})
    void test_win_lose_draw_user(String lastCardLetter, Result result) {
        var user = new User("조이", createCards("8", "K", lastCardLetter));
        var dealer = new Dealer(createCards("K", "9"));
        var game = createGameFrom(dealer, user);

        assertThat(game.getResultOf(user)).isEqualTo(result);
    }

    @Test
    @DisplayName("딜러의 승패를 모두 알 수 있다")
    void test_win_lose_draw_dealer() {
        var user = new User("조이", createCards("9", "K"));
        var user2 = new User("조이", createCards("8", "K", "K"));
        var dealer = new Dealer(createCards("K", "9"));
        var game = createGameFrom(dealer, user, user2);

        assertThat(game.getDealerResults()).containsOnly(Result.DRAW, Result.WIN);
    }

    @Test
    @DisplayName("배팅할 수 있다")
    void test_bet() {
        var user = new User("조이", createCards("J", "K"));
        var dealer = new Dealer(createCards("K", "9"));
        var game = createGameFrom(dealer, user);

        game.bet(user, Money.of(500));

        assertThat(game.getPrizeOf(user)).isEqualTo(Money.of(500));
    }

    @Test
    @DisplayName("정산할 수 있다")
    void test_evaluate() {
        var user = new User("조이", createCards("A", "K"));
        var dealer = new Dealer(createCards("K", "9"));
        var game = createGameFrom(dealer, user);
        game.bet(user, Money.of(500));

        game.evaluate();

        assertThat(game.getPrizeOf(user)).isEqualTo(Money.of(1250));
    }
}
