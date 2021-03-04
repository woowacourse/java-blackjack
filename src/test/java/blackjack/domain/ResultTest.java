package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

class ResultTest {

    private Dealer dealer;
    private List<Player> players = new ArrayList<>();

    @BeforeEach
    void setUp() {
        dealer = new Dealer();

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
        player.addFirstCards(Arrays.asList(
                Card.of("스페이드", "2"),
                Card.of("하트", "3")
        ));
        players.addAll(Arrays.asList(player, player2));
    }

    @DisplayName("Result 객체 정상 생성 테스트")
    @Test
    void result_generate_test() {
        Result result = new Result();

        Map<String, List<Outcome>> results = result.finishGame(dealer, players);

        List<Outcome> outcomes = results.get(players.get(0).getName());
        List<Outcome> secondOutcomes = results.get(players.get(1).getName());

        Assertions.assertThat(outcomes).containsExactly(Outcome.WIN);
        Assertions.assertThat(secondOutcomes).containsExactly(Outcome.LOOSE);
    }
}