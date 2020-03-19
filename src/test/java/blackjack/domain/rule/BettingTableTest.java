package blackjack.domain.rule;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardType;
import blackjack.domain.gamer.BettingMoney;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.gamer.Profit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BettingTableTest {

    private Player pobi = new Player("pobi");
    private Player jay = new Player("jay");
    private Player elly = new Player("elly");
    private Player jun = new Player("jun");
    private Players players;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();

        pobi.draw(new Card(CardSymbol.TWO, CardType.SPADE));
        pobi.draw(new Card(CardSymbol.ACE, CardType.SPADE));

        jay.draw(new Card(CardSymbol.ACE, CardType.HEART));
        jay.draw(new Card(CardSymbol.KING, CardType.HEART));

        elly.draw(new Card(CardSymbol.KING, CardType.DIAMOND));
        elly.draw(new Card(CardSymbol.FIVE, CardType.DIAMOND));

        jun.draw(new Card(CardSymbol.KING, CardType.DIAMOND));
        jun.draw(new Card(CardSymbol.SIX, CardType.DIAMOND));

        dealer.draw(new Card(CardSymbol.KING, CardType.HEART));
        dealer.draw(new Card(CardSymbol.FIVE, CardType.HEART));

        List<Player> playerList = Arrays.asList(pobi, jay, elly, jun);
        players = new Players(playerList);
    }

    @Test
    @DisplayName("플레이어별 수익 계산 테스트")
    void playerProfit() {
        Map<Player, BettingMoney> playerBettingMoney = new LinkedHashMap<>();
        playerBettingMoney.put(pobi, BettingMoney.from(10000)); // 패
        playerBettingMoney.put(jay, BettingMoney.from(20000)); // 블랙잭 승
        playerBettingMoney.put(elly, BettingMoney.from(10000)); // 무승부
        playerBettingMoney.put(jun, BettingMoney.from(20000)); // 승

        BettingTable bettingTable = new BettingTable(playerBettingMoney);
        Map<Gamer, Profit> gamerProfitMap = bettingTable.calculateBettingResult(players, dealer);
        assertThat(gamerProfitMap.entrySet()).size().isEqualTo(5);
    }
}
