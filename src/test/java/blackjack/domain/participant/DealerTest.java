package blackjack.domain.participant;

import static blackjack.domain.Result.DRAW;
import static blackjack.domain.Result.LOSE;
import static blackjack.domain.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Pattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DealerTest {

    private Player player1;
    private Player player2;
    private Player player3;
    private Players players;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        players = new Players(List.of("1", "2", "3"));
        dealer = new Dealer(players);
        player1 = players.getPlayers().get(0);
        player2 = players.getPlayers().get(1);
        player3 = players.getPlayers().get(2);
    }

    @Test
    @DisplayName("참가자들에게 2장의 카드를 나눠준다.")
    void distributeTwoCards() {
        dealer.settingCards();

        assertAll(
                () -> assertThat(player1.getCards().size()).isEqualTo(2),
                () -> assertThat(player2.getCards().size()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("딜러가 자신의 카드 2장을 가져온다.")
    void drawSelfCards() {
        dealer.settingSelfCards();

        assertThat(dealer.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어에게 카드 한 장을 더 준다.")
    void giveOneMoreCard() {
        dealer.giveACard(player1);

        assertThat(player1.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러의 점수가 16점 이하이면 16점을 초과할 때까지 계속해서 카드를 뽑는다.")
    void canDrawTest() {
        dealer.receiveCard(new Card(Number.TWO, Pattern.HEART));
        dealer.receiveCard(new Card(Number.THREE, Pattern.HEART));
        int initSize = dealer.getCards().size();

        while (dealer.canDraw()) {
            dealer.drawACard();
        }

        assertAll(
                () -> assertThat(initSize).isNotEqualTo(dealer.getCards().size()),
                () -> assertThat(dealer.calculateTotalScore()).isGreaterThan(16)
        );
    }

    @Test
    @DisplayName("플레이어의 결과를 이용하여 딜러의 승패를 결정한다.")
    void makeSelfResultTest() {
        Map<Player, Result> resultsFromPlayer = new HashMap<>() {{
            put(player1, WIN);
            put(player2, DRAW);
            put(player3, LOSE);
        }};

        List<Integer> resultCountOfDealer = dealer.countSelfResults(resultsFromPlayer);

        assertAll(
                () -> assertThat(resultCountOfDealer.get(0)).isEqualTo(1),
                () -> assertThat(resultCountOfDealer.get(1)).isEqualTo(1),
                () -> assertThat(resultCountOfDealer.get(2)).isEqualTo(1)
        );
    }
}
