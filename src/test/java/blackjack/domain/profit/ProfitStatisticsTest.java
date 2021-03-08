package blackjack.domain.profit;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Symbol;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitStatisticsTest {
    private static final List<Card> CARDS_BLACKJACK = Arrays.asList(
            new Card(Symbol.JACK, Shape.HEART),
            new Card(Symbol.ACE, Shape.DIAMOND)
    );
    private static final List<Card> CARDS_SCORE_20 = Arrays.asList(
            new Card(Symbol.ACE, Shape.HEART),
            new Card(Symbol.KING, Shape.HEART),
            new Card(Symbol.NINE, Shape.HEART)
    );
    private static final List<Card> CARDS_SCORE_19 = Arrays.asList(
            new Card(Symbol.ACE, Shape.HEART),
            new Card(Symbol.KING, Shape.HEART),
            new Card(Symbol.EIGHT, Shape.HEART)
    );

    private Players players;
    private Dealer dealer;

    @BeforeEach
    void setup() {
        players = Players.of(Arrays.asList("pobi", "jason"), Arrays.asList(1000, 2000));
        dealer = new Dealer();
        List<Player> playerList = players.getPlayers();
        playerList.get(0).receiveCards(new Cards(CARDS_BLACKJACK));
        playerList.get(1).receiveCards(new Cards(CARDS_SCORE_19));
        dealer.receiveCards(new Cards(CARDS_SCORE_20));
    }

    @DisplayName("Player의 이름을 Key로, 각각의 이익금액을 value로 하는 Map을 생성")
    @Test
    void createGameResult() {
        ProfitStatistics profitStatistics = ProfitStatistics.of(dealer, players);

        Collection<Integer> values = profitStatistics.getProfitStatistics()
                .values();

        assertThat(values).containsExactly(1500, -2000);
    }

    @DisplayName("딜러의 손익은 플레이어 손익의 합 * -1과 같다.")
    @Test
    void calculateDealerProfit() {
        ProfitStatistics profitStatistics = ProfitStatistics.of(dealer, players);
        Collection<Integer> values = profitStatistics.getProfitStatistics()
                .values();

        int playerProfitTotal = values.stream().mapToInt(t -> t).sum();
        int dealerProfit = profitStatistics.calculateDealerProfit();

        assertThat(dealerProfit).isEqualTo(playerProfitTotal * -1);
    }
}
