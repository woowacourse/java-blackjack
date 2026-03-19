package domain;

import domain.card.Card;
import domain.card.TrumpNumber;
import domain.card.TrumpSuit;
import domain.participant.Dealer;
import domain.participant.HandCards;
import domain.participant.Participant;
import domain.participant.player.Player;
import domain.vo.Money;
import domain.vo.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BettingTableTest {
    private BettingTable bettingTable;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        bettingTable = new BettingTable();
        dealer = new Dealer(new HandCards());
    }

    @Test
    void 이름이_같은_플레이어_동일키_취급_테스트() {
        // given
        Player player1 = new Player(new Name("pobi"), new HandCards());
        Player player2 = new Player(new Name("pobi"), new HandCards());

        Map<Player, Money> table = new HashMap<>();
        table.put(player1, new Money("10000"));

        assertThat(table.get(player2).toLong()).isEqualTo(10000L);
    }

    @Test
    @DisplayName("블랙잭으로 승리하면 판돈의 1.5배가 수익이 된다")
    void 블랙잭_승리_판돈_테스트() {
        // given
        Player player = createPlayer("pobi");
        distributeCards(player, TrumpNumber.ACE, TrumpNumber.KING); // 블랙잭 (21점)
        distributeCards(dealer, TrumpNumber.EIGHT, TrumpNumber.NINE); // 일반 (17점)

        bettingTable.placeBet(player, new Money("10000"));

        // when
        Money playerProfit = bettingTable.calculateProfit(player, dealer);

        // then
        assertThat(playerProfit.toLong()).isEqualTo(15000L);
    }

    @Test
    void 승리_판돈_테스트() {
        // given
        Player player = createPlayer("pobi");
        distributeCards(player, TrumpNumber.KING, TrumpNumber.SEVEN); // 일반 (17점)
        distributeCards(dealer, TrumpNumber.JACK, TrumpNumber.SIX); // 일반 (16점)

        bettingTable.placeBet(player, new Money("10000"));

        // when
        Money playerProfit = bettingTable.calculateProfit(player, dealer);

        // then
        assertThat(playerProfit.toLong()).isEqualTo(10000L);
    }

    @Test
    void 일반_21점_승리_판돈_테스트() {
        // given
        Player player = createPlayer("pobi");
        distributeCards(player, TrumpNumber.ACE, TrumpNumber.KING, TrumpNumber.QUEEN); // 일반 (21점)
        distributeCards(dealer, TrumpNumber.JACK, TrumpNumber.TEN); // 일반 (20점)

        bettingTable.placeBet(player, new Money("10000"));

        // when
        Money playerProfit = bettingTable.calculateProfit(player, dealer);

        // then
        assertThat(playerProfit.toLong()).isEqualTo(10000L);
    }

    @Test
    void 패배_판돈_마이너스_테스트() {
        // given
        Player player = createPlayer("pobi");
        distributeCards(player, TrumpNumber.TWO, TrumpNumber.THREE); // 5점
        distributeCards(dealer, TrumpNumber.TEN, TrumpNumber.KING); // 20점

        bettingTable.placeBet(player, new Money("10000"));

        // when
        Money profit = bettingTable.calculateProfit(player, dealer);

        // then
        assertThat(profit.toLong()).isEqualTo(-10000L);
    }

    @Test
    void 플레이어_딜러_모두_버스트_테스트() {
        // given
        Player player = createPlayer("pobi");

        distributeCards(player, TrumpNumber.TEN, TrumpNumber.KING, TrumpNumber.TWO); // 22점 (Bust)
        distributeCards(dealer, TrumpNumber.JACK, TrumpNumber.NINE, TrumpNumber.FOUR); // 23점 (Bust)

        bettingTable.placeBet(player, new Money("10000"));

        // when
        Money profit = bettingTable.calculateProfit(player, dealer);

        // then
        assertThat(profit.toLong()).isEqualTo(-10000L);
    }

    @Test
    void 무승부_판돈_테스트() {
        // given
        Player player = createPlayer("woni");
        distributeCards(player, TrumpNumber.TEN, TrumpNumber.KING); // 20점
        distributeCards(dealer, TrumpNumber.TEN, TrumpNumber.QUEEN); // 20점

        bettingTable.placeBet(player, new Money("10000"));

        // when
        Money profit = bettingTable.calculateProfit(player, dealer);

        // then
        assertThat(profit.toLong()).isEqualTo(0L);
    }

    @Test
    void 양쪽_모두_블랙잭_무승부_테스트() {
        // given
        Player player = createPlayer("pobi");
        distributeCards(player, TrumpNumber.ACE, TrumpNumber.JACK);
        distributeCards(dealer, TrumpNumber.ACE, TrumpNumber.QUEEN);

        bettingTable.placeBet(player, new Money("10000"));

        // when
        Money profit = bettingTable.calculateProfit(player, dealer);

        // then
        assertThat(profit.toLong()).isEqualTo(0L);
    }

    @Test
    @DisplayName("딜러의 총 수익은 플레이어들의 수익 합계의 반전이다")
    void 딜러_총수익_계산_테스트() {
        // given
        Player player1 = createPlayer("pobi"); // 패배 (-10,000)
        distributeCards(player1, TrumpNumber.TWO, TrumpNumber.THREE);

        Player player2 = createPlayer("jason"); // 블랙잭 승리 (+15,000)
        distributeCards(player2, TrumpNumber.ACE, TrumpNumber.KING);

        distributeCards(dealer, TrumpNumber.TEN, TrumpNumber.SEVEN); // 17점

        bettingTable.placeBet(player1, new Money("10000"));
        bettingTable.placeBet(player2, new Money("10000"));

        // when
        Money dealerProfit = bettingTable.getDealerProfit(List.of(player1, player2), dealer);

        // then
        assertThat(dealerProfit.toLong()).isEqualTo(-5000L);
    }

    private Player createPlayer(String name) {
        return new Player(new Name(name), new HandCards());
    }

    private void distributeCards(Participant participant, TrumpNumber... numbers) {
        for (TrumpNumber number : numbers) {
            participant.drawCard(new Card(TrumpSuit.SPADE, number));
        }
    }
}
