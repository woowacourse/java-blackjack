package domain.game;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import domain.participant.Dealer;
import domain.participant.Player;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayerBetsTest {

    EarningResult earningResult;
    Dealer dealer = new Dealer();
    Player p1 = new Player("P1");
    Player p2 = new Player("P2");
    Player p3 = new Player("P3");
    Player p4 = new Player("P4");

    @BeforeEach
    void setUp() {
        Card aceDiamond = new Card(Rank.ACE, Shape.DIAMOND);
        Card tenSpade = new Card(Rank.TEN, Shape.SPADE);
        Card tenDiamond = new Card(Rank.TEN, Shape.DIAMOND);
        Card tenHeart = new Card(Rank.TEN, Shape.HEART);
        Card nineSpade = new Card(Rank.NINE, Shape.SPADE);
        Card nineDiamond = new Card(Rank.NINE, Shape.DIAMOND);
        Card eightSpade = new Card(Rank.EIGHT, Shape.SPADE);
        Card fourSpade = new Card(Rank.FOUR, Shape.SPADE);
        Card threeSpade = new Card(Rank.THREE, Shape.SPADE);
        Card twoSpade = new Card(Rank.TWO, Shape.SPADE);

        dealer.setUpCardDeck(nineSpade, threeSpade); // 12점
        p1.setUpCardDeck(nineDiamond, twoSpade); // 11점 패배
        p2.setUpCardDeck(tenSpade, tenHeart); // 20점 승리
        p3.setUpCardDeck(eightSpade, fourSpade); // 12점 무
        p4.setUpCardDeck(tenDiamond, aceDiamond); // 블랙잭 승리

        BetMoney betMoney = new BetMoney(1000);
        Map<Player, BetMoney> playerBetMap = new HashMap<>();
        playerBetMap.put(p1, betMoney);
        playerBetMap.put(p2, betMoney);
        playerBetMap.put(p3, betMoney);
        playerBetMap.put(p4, betMoney);

        PlayerBets playerBets = new PlayerBets(playerBetMap);
        List<Player> players = List.of(p1, p2, p3, p4);
        earningResult = playerBets.evaluateEarning(players, dealer);
    }

    @Test
    void 플레이어는_패배할경우_배팅금액을_모두_잃는다() {
        Double earning = earningResult.getEarningResult().get(p1);
        assertThat(earning).isEqualTo(-1000);
    }

    @Test
    void 플레이어는_승리할경우_배팅금액만큼의_수익을_얻는다() {
        Double earning = earningResult.getEarningResult().get(p2);
        assertThat(earning).isEqualTo(1000);
    }

    @Test
    void 플레이어가_딜러와_무승부일경우_수익은_0이다() {
        Double earning = earningResult.getEarningResult().get(p3);
        assertThat(earning).isZero();
    }

    @Test
    void 플레이어가_블랙잭으로_승리할경우_추가수익을_얻는다() {
        Double earning = earningResult.getEarningResult().get(p4);
        assertThat(earning).isEqualTo(1500);
    }

    @Test
    void 딜러는_플레이어의_손익과_반대() {
        Double earning = earningResult.calcualteDealerEarning();
        assertThat(earning).isEqualTo(-1500);
    }
}