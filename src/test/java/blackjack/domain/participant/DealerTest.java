package blackjack.domain.participant;

import static blackjack.domain.result.Result.DRAW;
import static blackjack.domain.result.Result.LOSE;
import static blackjack.domain.result.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.result.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Suit;
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
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        Players players = new Players(List.of("1", "2", "3"));
        dealer = new Dealer(players);
        player1 = players.getPlayers().get(0);
        player2 = players.getPlayers().get(1);
        player3 = players.getPlayers().get(2);
    }

    @Test
    @DisplayName("참가자들과 본인에게 2장의 카드를 나눠준다.")
    void distributeTwoCards() {
        dealer.settingCards();

        assertAll(
                () -> assertThat(dealer.getCards().size()).isEqualTo(2),
                () -> assertThat(player1.getCards().size()).isEqualTo(2),
                () -> assertThat(player2.getCards().size()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("플레이어에게 카드 한 장을 더 준다.")
    void giveOneMoreCard() {
        dealer.giveCard(player1);

        assertThat(player1.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러의 점수가 16점 이하이면 16점을 초과할 때까지 계속해서 카드를 뽑는다.")
    void canDrawTest() {
        dealer.receiveCard(new Card(Number.TWO, Suit.HEART));
        dealer.receiveCard(new Card(Number.THREE, Suit.HEART));
        int initSize = dealer.getCards().size();

        while (dealer.canDraw()) {
            dealer.drawCard();
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

        Map<Result, Integer> dealerResult = dealer.countSelfResults(resultsFromPlayer);

        assertAll(
                () -> assertThat(dealerResult.get(WIN)).isEqualTo(1),
                () -> assertThat(dealerResult.get(DRAW)).isEqualTo(1),
                () -> assertThat(dealerResult.get(LOSE)).isEqualTo(1)
        );
    }
}
