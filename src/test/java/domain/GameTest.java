package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GameTest extends AbstractTestFixture {

    @Test
    @DisplayName("카드를 2장씩 분배한다.")
    void test_deal_two_cards() {
        var dealer = new Dealer();
        var participants = createParticipantsFrom(dealer, new User("조이"), new User("땡칠"));
        var game = new Game(participants, new Deck());

        game.dealCardsTwice();

        for (User user : participants.getUsers()) {
            assertThat(user.getCards()).hasSize(2);
        }
        assertThat(dealer.getCards()).hasSize(2);
    }

    @ParameterizedTest(name = "딜러와 플레이어중에 21에 가까운 사람이 이긴다.")
    @CsvSource({"0,LOSE", "1,WIN"})
    void test_21_win(int index, Result result) {
        var participants = createParticipantsFrom(
                new Dealer(createCards("K")),
                new User("조이", createCards("9")),
                new User("땡칠", createCards("A")));
        var game = new Game(participants, new Deck());

        var user = participants.getUsers().get(index);
        assertThat(game.getResultOf(user)).isEqualTo(result);
    }

    @DisplayName("점수가 동일하면 무승부로 한다")
    @Test
    void test_draw() {
        var user = new User("땡칠", createCards("10"));
        var participants = createParticipantsFrom(new Dealer(createCards("K")), user);

        var game = new Game(participants, new Deck());

        assertThat(game.getResultOf(user)).isEqualTo(Result.DRAW);
    }

    @DisplayName("플레이어와 딜러가 모두 죽으면 무승부로 한다")
    @Test
    void test_draw_all_busted() {
        var user = new User("땡칠", createCards("10", "K", "K"));
        var participants = createParticipantsFrom(new Dealer(createCards("K", "9", "9")), user);
        var game = new Game(participants, new Deck());

        assertThat(game.getResultOf(user)).isEqualTo(Result.DRAW);
    }

    @DisplayName("딜러가 살고, 플레이어가 죽으면 딜러가 이긴다")
    @Test
    void test_lose_player_busted() {
        var user = new User("땡칠", createCards("10", "K", "K"));
        var participants = createParticipantsFrom(new Dealer(createCards("K", "9")), user);
        var game = new Game(participants, new Deck());

        assertThat(game.getResultOf(user)).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어가 살고, 딜러가 죽으면 플레이어가 이긴다")
    @Test
    void test_win_dealer_busted() {
        var user = new User("땡칠", createCards("10", "K"));
        var participants = createParticipantsFrom(new Dealer(createCards("K", "9", "J")), user);
        var game = new Game(participants, new Deck());

        assertThat(game.getResultOf(user)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("특정 플레이어에게 카드를 한장 더 준다")
    void test_deal_a_card() {
        var user = new User("조이", createCards("10", "2"));
        var participants = createParticipantsFrom(new Dealer(createCards("K")), user);
        var game = new Game(participants, new Deck());

        game.dealCardTo(user);

        assertThat(user.getCards()).hasSize(3);
    }

    @Test
    @DisplayName("참가중이지 않은 플레이어에게 카드를 줄 수 없다")
    void test_dealing_card_to_not_playing_throws() {
        var participant = new User("조이", createCards("10", "2"));
        var notParticipant = new User("참가자아님");
        var participants = createParticipantsFrom(new Dealer(createCards("K")), participant);

        var game = new Game(participants, new Deck());

        assertThatIllegalArgumentException()
                .isThrownBy(() -> game.dealCardTo(notParticipant));
    }
}
