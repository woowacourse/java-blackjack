package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
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
    private List<Player> players = new ArrayList<>();

    @BeforeEach
    void setUp() {
        dealer = new Dealer(Arrays.asList(Card.of("스페이드", "10"),
                Card.of("하트", "4")));

        Player player = new Player("pobi", 1000,
                Arrays.asList(Card.of("스페이드", "10"),
                        Card.of("하트", "5")));
        players.add(player);

        Player player2 = new Player("jason", 2000,
                Arrays.asList(Card.of("스페이드", "2"),
                        Card.of("하트", "3")));
        players.add(player2);
    }

    @DisplayName("Result 객체 정상 생성 테스트")
    @Test
    void result_generate_test() {
        Result result = new Result(dealer, players);
        Map<String, Outcome> playerResults = result.getPlayerOutcomes();
        assertThat(playerResults.get("pobi")).isEqualTo(Outcome.WIN);
        assertThat(playerResults.get("jason")).isEqualTo(Outcome.LOSE);
    }

    @DisplayName("딜러가 버스터일 때 승패 체크 테스트")
    @Test
    void result_buster_test() {
        dealer.addCard(Card.of("스페이드", "10"));
        players.get(0).addCard(Card.of("스페이드", "9"));

        Result result = new Result(dealer, players);
        Map<String, Outcome> playerResults = result.getPlayerOutcomes();

        assertThat(playerResults.get("pobi")).isEqualTo(Outcome.LOSE);
        assertThat(playerResults.get("jason")).isEqualTo(Outcome.WIN);
    }

    @DisplayName("딜러의 승패가 제대로 출력되는 지 테스트")
    @Test
    void result_test() {
        Result result = new Result(dealer, players);
        assertThat(result.findDealerWinCount()).isEqualTo(1);
        assertThat(result.findDealerLoseCount()).isEqualTo(1);
        assertThat(result.findDealerDrawCount()).isEqualTo(0);
    }
}