package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameTest extends AbstractTestFixture {

    @Test
    @DisplayName("카드를 2번 돌린다")
    void test_deal_two_cards() {
        var dealer = new Dealer();
        // var participants = createParticipantsFrom(dealer, new User("조이"), new User("땡칠"));
        var game = createGameFrom(dealer, new User("조이"), new User("땡칠"));

        game.dealTwice();

        for (var user : game.getUsers()) {
            assertThat(user.hand().cards()).hasSize(2);
        }
        assertThat(dealer.hand().cards()).hasSize(2);
    }

    @Test
    @DisplayName("참가중이지 않은 플레이어에게 카드를 줄 수 없다")
    void test_dealing_card_to_not_playing_throws() {
        var participant = new User("조이", createCards("10", "2"));
        var notParticipant = new User("참가자아님");
        // var participants = createParticipantsFrom(new Dealer(createCards("K")), participant);

        var game = createGameFrom(new Dealer(createCards("K")), participant);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> game.dealTo(notParticipant));
    }
}
