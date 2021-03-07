package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

class ResultTest {

    private Dealer dealer;
    private List<Player> players;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        players = new ArrayList<>();

        dealer.addFirstCards(Arrays.asList(
                Card.of("스페이드", "10"),
                Card.of("하트", "4")
        ));

        Player player = new Player("pobi");
        player.addFirstCards(Arrays.asList(
                Card.of("스페이드", "10"),
                Card.of("하트", "5")
        ));

        Player player2 = new Player("jason");
        player2.addFirstCards(Arrays.asList(
                Card.of("스페이드", "2"),
                Card.of("하트", "3")
        ));
        players.addAll(Arrays.asList(player, player2));
    }

    @DisplayName("Result 객체 정상 생성 테스트")
    @Test
    void result_generate_test() {
        Result result = new Result();
        Round round = Round.generateWithRandomCards(dealer, players);

        Map<String, Queue<Outcome>> results = Result.findResults(round);

        Queue<Outcome> firstPlayerOutcomes = results.get(players.get(0).getName());
        Queue<Outcome> secondPlayerOutcomes = results.get(players.get(1).getName());

        Assertions.assertThat(firstPlayerOutcomes.poll()).isEqualTo(Outcome.WIN);
        Assertions.assertThat(secondPlayerOutcomes.poll()).isEqualTo(Outcome.LOSE);
    }

    @DisplayName("딜러가 버스터 일때 승패 체크 테스트")
    @Test
    void result_buster_test() {
        Result result = new Result();

        dealer.addCard(Card.of("스페이드", "10"));
        players.get(0).addCard(Card.of("스페이드", "9"));

        Round round = Round.generateWithRandomCards(dealer, players);
        Map<String, Queue<Outcome>> results = Result.findResults(round);

        Queue<Outcome> firstPlayerOutcomes = results.get(players.get(0).getName());      //딜러 24, 플레이어 24, 딜러 승,  플레이어 패
        Queue<Outcome> secondPlayerOutcomes = results.get(players.get(1).getName());    //딜러 24, 플레이어 5, 딜러 패 플레이어 승

        Assertions.assertThat(firstPlayerOutcomes.poll()).isEqualTo(Outcome.LOSE);
        Assertions.assertThat(secondPlayerOutcomes.poll()).isEqualTo(Outcome.WIN);
    }
}