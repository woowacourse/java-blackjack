package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {
    private Dealer dealer;
    private Users users;

    @BeforeEach
    void setUp() {
        List<User> userGroup = new ArrayList<>();
        dealer = new Dealer();
        dealer.addFirstCards(
                Card.of("스페이드", "10"),
                Card.of("하트", "4")
        );
        userGroup.add(dealer);

        Player player = new Player("pobi");
        player.addFirstCards(
                Card.of("스페이드", "10"),
                Card.of("하트", "5")
        );
        userGroup.add(player);

        Player player2 = new Player("jason");
        player2.addFirstCards(
                Card.of("스페이드", "2"),
                Card.of("하트", "3")
        );
        userGroup.add(player2);

        users = new Users(userGroup);
    }

    @DisplayName("Result 객체 정상 생성 테스트")
    @Test
    void result_generate_test() {
        Result result = new Result(users);
        Map<String, Outcome> playerResults = result.getPlayerOutcomes();
        assertThat(playerResults.get("pobi")).isEqualTo(Outcome.WIN);
        assertThat(playerResults.get("jason")).isEqualTo(Outcome.LOSE);
    }

    @DisplayName("딜러가 버스터일 때 승패 체크 테스트")
    @Test
    void result_buster_test() {
        dealer.addCard(Card.of("스페이드", "10"));
        users.getPlayers().get(0).addCard(Card.of("스페이드", "9"));

        Result result = new Result(users);
        Map<String, Outcome> playerResults = result.getPlayerOutcomes();

        assertThat(playerResults.get("pobi")).isEqualTo(Outcome.LOSE);
        assertThat(playerResults.get("jason")).isEqualTo(Outcome.WIN);
    }

    @DisplayName("딜러의 승패가 제대로 출력되는 지 테스트")
    @Test
    void result_test() {
        Result result = new Result(users);
        assertThat(result.findDealerWinCount()).isEqualTo(1);
        assertThat(result.findDealerLoseCount()).isEqualTo(1);
        assertThat(result.findDealerDrawCount()).isEqualTo(0);
    }
}