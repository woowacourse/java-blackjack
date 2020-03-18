package blackjack.domain.rule;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardType;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
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
    @DisplayName("플레이어가 이겼을 경우 수익 계산 테스트")
    void name() {
        Map<Player, Integer> map = new LinkedHashMap<>();
        map.put(pobi, 10000);
        map.put(jay, 20000);
        map.put(elly, 10000);
        map.put(jun, 20000);

        BettingTable bettingTable = new BettingTable(map);
        Map<Gamer, Integer> a = bettingTable.calculateBettingMoney(players, dealer);
        assertThat(a).containsEntry(pobi, -10000)
                .containsEntry(jay, 30000)
                .containsEntry(elly, 0)
                .containsEntry(jun, 20000)
                .containsEntry(dealer, -40000);
    }
}
