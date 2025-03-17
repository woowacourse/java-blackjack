package bet;

import constant.Rank;
import constant.Suit;
import game.Card;
import game.Cards;
import game.Deck;
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
        // given
        Integer bettingAmount1 = 10000;
        Integer bettingAmount2 = 20000;
        Integer bettingAmount3 = 30000;

        Map<Nickname, Integer> bettingAmounts = new HashMap<>();

        // when
        bettingAmounts.put(new Nickname("제프"), bettingAmount1);
        bettingAmounts.put(new Nickname("짱수"), bettingAmount2);
        bettingAmounts.put(new Nickname("빙봉"), bettingAmount3);

        // then
        assertThat(bettingAmounts.get(new Nickname("짱수"))).isEqualTo(20000);

    }

    @Test
    void 딜러만_블랙잭인_경우_모든_플레이어는_베팅금액을_잃는다() {
        // given
        Deck deck = new Deck(() -> {
            List<Card> cards = new ArrayList<>();

            // 플레이어 2
            cards.add(new Card(Rank.FIVE, Suit.HEART));
            cards.add(new Card(Rank.EIGHT, Suit.CLOVER));

            // 플레이어 1
            cards.add(new Card(Rank.SIX, Suit.HEART));
            cards.add(new Card(Rank.KING, Suit.DIAMOND));

            // 딜러
            cards.add(new Card(Rank.KING, Suit.CLOVER));
            cards.add(new Card(Rank.ACE, Suit.SPADE));
            return new Cards(cards);
        });
        Dealer dealer = new Dealer(deck);

        Player player1 = new Player(new Nickname("제프"), deck);
        Player player2 = new Player(new Nickname("빙봉"), deck);
        player1.addOneCard(new Card(Rank.FIVE, Suit.DIAMOND));

        BetCenter betCenter = new BetCenter(Map.of(
                player1, new BetAmount(10000),
                player2, new BetAmount(20000)
        ));

        // when
        Map<Player, Integer> bettingResults = betCenter.deriveBettingResults(dealer);

        // then
        assertThat(bettingResults.get(player1)).isEqualTo(-10000);
        assertThat(bettingResults.get(player2)).isEqualTo(-20000);
    }

    @Test
    void 플레이어만_블랙잭인_경우_베팅금액의_절반을_수익으로_얻는다() {
        // given
        Deck deck = new Deck(() -> {
            List<Card> cards = new ArrayList<>();
            cards.add(new Card(Rank.ACE, Suit.HEART));
            cards.add(new Card(Rank.KING, Suit.DIAMOND));
            cards.add(new Card(Rank.JACK, Suit.CLOVER));
            cards.add(new Card(Rank.KING, Suit.SPADE));

            return new Cards(cards);
        });
        Dealer dealer = new Dealer(deck);
        Player player = new Player(new Nickname("제프"), deck);

        BetCenter betCenter = new BetCenter(Map.of(
                player, new BetAmount(10000)
        ));

        // when
        Map<Player, Integer> bettingResults = betCenter.deriveBettingResults(dealer);

        // then
        assertThat(bettingResults.get(player)).isEqualTo(15000);
    }


    @Test
    void 딜러와_플레이어가_블랙잭인_경우_수익은_0원이다() {
        // given
        Deck deck = new Deck(() -> {
            List<Card> cards = new ArrayList<>();

            // 플레이어
            cards.add(new Card(Rank.ACE, Suit.HEART));
            cards.add(new Card(Rank.KING, Suit.DIAMOND));

            // 딜러
            cards.add(new Card(Rank.ACE, Suit.CLOVER));
            cards.add(new Card(Rank.KING, Suit.SPADE));
            return new Cards(cards);
        });
        Dealer dealer = new Dealer(deck);
        Player player = new Player(new Nickname("제프"), deck);

        BetCenter betCenter = new BetCenter(Map.of(player, new BetAmount(10000)));

        // when
        Map<Player, Integer> bettingResults = betCenter.deriveBettingResults(dealer);

        // then
        assertThat(bettingResults.get(player)).isEqualTo(0);
    }

    @Test
    void 플레이어가_승리하면서_점수가_21점인_경우_베팅금액만큼의_수익을_얻는다() {
        // given
        Deck deck = new Deck(() -> {
            List<Card> cards = new ArrayList<>();

            // 플레이어
            cards.add(new Card(Rank.FIVE, Suit.HEART));
            cards.add(new Card(Rank.SIX, Suit.HEART));
            cards.add(new Card(Rank.KING, Suit.DIAMOND));

            // 딜러
            cards.add(new Card(Rank.FIVE, Suit.CLOVER));
            cards.add(new Card(Rank.KING, Suit.SPADE));
            return new Cards(cards);
        });
        Dealer dealer = new Dealer(deck);
        Player player = new Player(new Nickname("제프"), deck);
        player.addOneCard(deck.drawOneCard());
        BetCenter betCenter = new BetCenter(Map.of(player, new BetAmount(10000)));

        // when
        Map<Player, Integer> bettingResults = betCenter.deriveBettingResults(dealer);

        // then
        assertThat(bettingResults.get(player)).isEqualTo(10000);
    }

    @Test
    void 플레이어의_점수가_21을_초과하는_경우_베팅금액을_잃는다() {
        // given
        Deck deck = new Deck(() -> {
            List<Card> cards = new ArrayList<>();

            // 플레이어
            cards.add(new Card(Rank.NINE, Suit.HEART));
            cards.add(new Card(Rank.SIX, Suit.HEART));
            cards.add(new Card(Rank.KING, Suit.DIAMOND));

            // 딜러
            cards.add(new Card(Rank.FIVE, Suit.CLOVER));
            cards.add(new Card(Rank.KING, Suit.SPADE));
            return new Cards(cards);
        });
        Dealer dealer = new Dealer(deck);
        Player player = new Player(new Nickname("제프"), deck);
        player.addOneCard(deck.drawOneCard());

        BetCenter betCenter = new BetCenter(Map.of(
                player, new BetAmount(15000)
        ));

        // when
        Map<Player, Integer> bettingResults = betCenter.deriveBettingResults(dealer);

        // then
        assertThat(bettingResults.get(player)).isEqualTo(-15000);
    }

    @Test
    void 딜러의_점수가_21을_초과하는_경우_해당_시점의_플레이어는_모두_베팅금액만큼의_수익을_얻는다() {
        // given
        Deck deck = new Deck(() -> {
            List<Card> cards = new ArrayList<>();

            // 딜러 추가 카드
            cards.add(new Card(Rank.EIGHT, Suit.SPADE));

            // 플레이어 3 초기 덱
            cards.add(new Card(Rank.FIVE, Suit.SPADE));
            cards.add(new Card(Rank.FOUR, Suit.HEART));

            // 플레이어 2 초기 덱
            cards.add(new Card(Rank.NINE, Suit.SPADE));
            cards.add(new Card(Rank.SEVEN, Suit.HEART));

            // 플레이어 1 초기 덱
            cards.add(new Card(Rank.SIX, Suit.HEART));
            cards.add(new Card(Rank.KING, Suit.DIAMOND));

            // 딜러 초기 덱
            cards.add(new Card(Rank.FIVE, Suit.CLOVER));
            cards.add(new Card(Rank.KING, Suit.SPADE));
            return new Cards(cards);
        });
        Dealer dealer = new Dealer(deck);
        Player player1 = new Player(new Nickname("제프"), deck);
        Player player2 = new Player(new Nickname("짱수"), deck);
        Player player3 = new Player(new Nickname("빙봉"), deck);
        dealer.addOneCard(deck.drawOneCard());

        BetCenter betCenter = new BetCenter(Map.of(
                player1, new BetAmount(15000),
                player2, new BetAmount(30000),
                player3, new BetAmount(12000)
        ));

        // when
        Map<Player, Integer> bettingResults = betCenter.deriveBettingResults(dealer);

        // then
        assertThat(bettingResults.get(player1)).isEqualTo(15000);
        assertThat(bettingResults.get(player2)).isEqualTo(30000);
        assertThat(bettingResults.get(player3)).isEqualTo(12000);
    }

    @Test
    void 딜러의_수익을_계산한다() {
        // given
        Deck deck = new Deck(() -> {
            List<Card> cards = new ArrayList<>();

            // 플레이어 3 초기 덱
            cards.add(new Card(Rank.FIVE, Suit.SPADE));
            cards.add(new Card(Rank.FOUR, Suit.HEART));

            // 플레이어 2 초기 덱
            cards.add(new Card(Rank.NINE, Suit.SPADE));
            cards.add(new Card(Rank.SIX, Suit.CLOVER));

            // 플레이어 1 초기 덱
            cards.add(new Card(Rank.SIX, Suit.HEART));
            cards.add(new Card(Rank.KING, Suit.DIAMOND));

            // 딜러 초기 덱
            cards.add(new Card(Rank.FIVE, Suit.CLOVER));
            cards.add(new Card(Rank.KING, Suit.SPADE));
            return new Cards(cards);
        });
        Dealer dealer = new Dealer(deck);
        Player player1 = new Player(new Nickname("제프"), deck);
        Player player2 = new Player(new Nickname("짱수"), deck);
        Player player3 = new Player(new Nickname("빙봉"), deck);

        BetCenter betCenter = new BetCenter(Map.of(
                player1, new BetAmount(15000),
                player2, new BetAmount(30000),
                player3, new BetAmount(12000)
        ));

        // when
        int dealerProfit = betCenter.calculateDealerProfit(dealer);

        // then
        assertThat(dealerProfit).isEqualTo(-3000);
    }
}
