package bet;

import constant.TrumpEmblem;
import constant.TrumpNumber;
import game.Card;
import game.Cards;
import org.junit.jupiter.api.Test;
import participant.Dealer;
import participant.Nickname;
import participant.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BetCenterTest {

    @Test
    void 게임_시작_시_베팅_금액을_정한다() {
        Integer bettingAmount1 = 10000;
        Integer bettingAmount2 = 20000;
        Integer bettingAmount3 = 30000;

        Map<Nickname, Integer> bettingAmounts = new HashMap<>();

        bettingAmounts.put(new Nickname("제프"), bettingAmount1);
        bettingAmounts.put(new Nickname("짱수"), bettingAmount2);
        bettingAmounts.put(new Nickname("빙봉"), bettingAmount3);

        assertThat(bettingAmounts.get(new Nickname("짱수"))).isEqualTo(20000);

    }

    @Test
    void 딜러만_블랙잭인_경우_모든_플레이어는_베팅금액을_잃는다() {

        Dealer dealer = new Dealer(() -> {
            List<Card> cards = new ArrayList<>();
            cards.add(new Card(TrumpNumber.FIVE, TrumpEmblem.HEART));
            cards.add(new Card(TrumpNumber.EIGHT, TrumpEmblem.CLOVER));
            cards.add(new Card(TrumpNumber.SIX, TrumpEmblem.HEART));
            cards.add(new Card(TrumpNumber.KING, TrumpEmblem.DIAMOND));
            cards.add(new Card(TrumpNumber.KING, TrumpEmblem.CLOVER));
            cards.add(new Card(TrumpNumber.ACE, TrumpEmblem.SPADE));
            return new Cards(cards);
        });

        Player player1 = new Player(new Nickname("제프"), dealer.drawInitialCards());
        Player player2 = new Player(new Nickname("빙봉"), dealer.drawInitialCards());

        BetCenter betCenter = new BetCenter(Map.of(
                player1, new BetAmount(10000),
                player2, new BetAmount(20000)
        ));
        Map<Player, BetAmount> bettingResults = betCenter.deriveBettingResults(dealer);

        assertThat(bettingResults.get(player1).getValue()).isEqualTo(-10000);
        assertThat(bettingResults.get(player2).getValue()).isEqualTo(-20000);
    }

    @Test
    void 플레이어만_블랙잭인_경우_베팅금액의_절반을_수익으로_얻는다() {
        Dealer dealer = new Dealer(() -> {
            List<Card> cards = new ArrayList<>();
            cards.add(new Card(TrumpNumber.ACE, TrumpEmblem.HEART));
            cards.add(new Card(TrumpNumber.KING, TrumpEmblem.DIAMOND));
            cards.add(new Card(TrumpNumber.JACK, TrumpEmblem.CLOVER));
            cards.add(new Card(TrumpNumber.KING, TrumpEmblem.SPADE));
            return new Cards(cards);
        });
        Player player = new Player(new Nickname("제프"), dealer.drawInitialCards());

        BetCenter betCenter = new BetCenter(Map.of(
                player, new BetAmount(10000)
        ));
        Map<Player, BetAmount> bettingResults = betCenter.deriveBettingResults(dealer);

        assertThat(bettingResults.get(player).getValue()).isEqualTo(15000);
    }


    @Test
    void 딜러와_플레이어가_블랙잭인_경우_수익은_0원이다() {
        Dealer dealer = new Dealer(() -> {
            List<Card> cards = new ArrayList<>();
            cards.add(new Card(TrumpNumber.ACE, TrumpEmblem.HEART));
            cards.add(new Card(TrumpNumber.KING, TrumpEmblem.DIAMOND));
            cards.add(new Card(TrumpNumber.ACE, TrumpEmblem.CLOVER));
            cards.add(new Card(TrumpNumber.KING, TrumpEmblem.SPADE));
            return new Cards(cards);
        });
        Player player = new Player(new Nickname("제프"), dealer.drawInitialCards());

        BetCenter betCenter = new BetCenter(Map.of(player, new BetAmount(10000)));
        Map<Player, BetAmount> bettingResults = betCenter.deriveBettingResults(dealer);

        assertThat(bettingResults.get(player).getValue()).isEqualTo(0);
    }

    @Test
    void 플레이어가_승리하면서_점수가_21점인_경우_베팅금액만큼의_수익을_얻는다() {
        Dealer dealer = new Dealer(() -> {
            List<Card> cards = new ArrayList<>();
            cards.add(new Card(TrumpNumber.FIVE, TrumpEmblem.HEART));
            cards.add(new Card(TrumpNumber.SIX, TrumpEmblem.HEART));
            cards.add(new Card(TrumpNumber.KING, TrumpEmblem.DIAMOND));
            cards.add(new Card(TrumpNumber.FIVE, TrumpEmblem.CLOVER));
            cards.add(new Card(TrumpNumber.KING, TrumpEmblem.SPADE));
            return new Cards(cards);
        });
        Player player = new Player(new Nickname("제프"), dealer.drawInitialCards());
        player.addOneCard(dealer.drawCard());

        BetCenter betCenter = new BetCenter(Map.of(player, new BetAmount(10000)));
        Map<Player, BetAmount> bettingResults = betCenter.deriveBettingResults(dealer);

        assertThat(bettingResults.get(player).getValue()).isEqualTo(10000);
    }

    @Test
    void 플레이어의_점수가_21을_초과하는_경우_베팅금액을_잃는다() {
        Dealer dealer = new Dealer(() -> {
            List<Card> cards = new ArrayList<>();
            cards.add(new Card(TrumpNumber.NINE, TrumpEmblem.HEART));
            cards.add(new Card(TrumpNumber.SIX, TrumpEmblem.HEART));
            cards.add(new Card(TrumpNumber.KING, TrumpEmblem.DIAMOND));
            cards.add(new Card(TrumpNumber.FIVE, TrumpEmblem.CLOVER));
            cards.add(new Card(TrumpNumber.KING, TrumpEmblem.SPADE));
            return new Cards(cards);
        });
        Player player = new Player(new Nickname("제프"), dealer.drawInitialCards());
        player.addOneCard(dealer.drawCard());

        BetCenter betCenter = new BetCenter(Map.of(
                player, new BetAmount(15000)
        ));
        Map<Player, BetAmount> bettingResults = betCenter.deriveBettingResults(dealer);

        assertThat(bettingResults.get(player).getValue()).isEqualTo(-15000);
    }

    @Test
    void 딜러의_점수가_21을_초과하는_경우_해당_시점의_플레이어는_모두_베팅금액만큼의_수익을_얻는다() {
        Dealer dealer = new Dealer(() -> {
            List<Card> cards = new ArrayList<>();

            // 딜러 추가 카드
            cards.add(new Card(TrumpNumber.EIGHT, TrumpEmblem.SPADE));

            // 플레이어 3 초기 덱
            cards.add(new Card(TrumpNumber.FIVE, TrumpEmblem.SPADE));
            cards.add(new Card(TrumpNumber.FOUR, TrumpEmblem.HEART));

            // 플레이어 2 초기 덱
            cards.add(new Card(TrumpNumber.NINE, TrumpEmblem.SPADE));
            cards.add(new Card(TrumpNumber.SEVEN, TrumpEmblem.HEART));

            // 플레이어 1 초기 덱
            cards.add(new Card(TrumpNumber.SIX, TrumpEmblem.HEART));
            cards.add(new Card(TrumpNumber.KING, TrumpEmblem.DIAMOND));

            // 딜러 초기 덱
            cards.add(new Card(TrumpNumber.FIVE, TrumpEmblem.CLOVER));
            cards.add(new Card(TrumpNumber.KING, TrumpEmblem.SPADE));
            return new Cards(cards);
        });
        Player player1 = new Player(new Nickname("제프"), dealer.drawInitialCards());
        Player player2 = new Player(new Nickname("짱수"), dealer.drawInitialCards());
        Player player3 = new Player(new Nickname("빙봉"), dealer.drawInitialCards());
        dealer.addOneCard(dealer.drawCard());

        BetCenter betCenter = new BetCenter(Map.of(
                player1, new BetAmount(15000),
                player2, new BetAmount(30000),
                player3, new BetAmount(12000)
        ));
        Map<Player, BetAmount> bettingResults = betCenter.deriveBettingResults(dealer);

        assertThat(bettingResults.get(player1).getValue()).isEqualTo(15000);
        assertThat(bettingResults.get(player2).getValue()).isEqualTo(30000);
        assertThat(bettingResults.get(player3).getValue()).isEqualTo(12000);
    }
}
