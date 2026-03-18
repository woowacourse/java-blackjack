package domain.betting;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BettingRuleTest {

    @ParameterizedTest(name = "{0} -> {2}")
    @CsvSource({
            "PLAYER_BUST, 1000, -1000",
            "BOTH_NATURAL_BLACKJACK, 1000, 0",
            "PLAYER_NATURAL, 1000, 1500",
            "DEALER_NATURAL, 1000, -1000",
            "PLAYER_WIN, 1000, 1000",
            "DRAW, 1000, 0",
            "PLAYER_LOSE, 1000, -1000"
    })
    void 배팅룰에_따라_수익금_정상_계산(MatchingRule bettingRule, int betting, int expectedProfit) {
        BettingAmount bettingAmount = BettingAmount.of(betting);

        int profit = bettingRule.calculateProfit(bettingAmount);

        assertThat(expectedProfit).isEqualTo(profit);
    }

    @Test
    void 플레이어가_버스트면_딜러와_무관하게_플레이어버스트_판정() {
        Player player = new Player(Name.from("나무"));
        player.add(new Card(Rank.TWO, Suit.SPADE));
        player.add(new Card(Rank.TEN, Suit.SPADE));
        player.add(new Card(Rank.JACK, Suit.SPADE));
        Dealer dealer = new Dealer();
        dealer.add(new Card(Rank.TWO, Suit.SPADE));
        dealer.add(new Card(Rank.TEN, Suit.SPADE));
        dealer.add(new Card(Rank.JACK, Suit.SPADE));

        MatchingRule rule = MatchingRule.determine(dealer, player);

        assertThat(rule).isEqualTo(MatchingRule.PLAYER_BUST);
    }

    @Test
    void 플레이어가_버스트가_아니고_딜러가_버스트인_경우_플레이어승리_판정() {
        Player player = new Player(Name.from("나무"));
        player.add(new Card(Rank.TEN, Suit.SPADE));
        player.add(new Card(Rank.JACK, Suit.SPADE));
        Dealer dealer = new Dealer();
        dealer.add(new Card(Rank.TWO, Suit.SPADE));
        dealer.add(new Card(Rank.TEN, Suit.SPADE));
        dealer.add(new Card(Rank.JACK, Suit.SPADE));

        MatchingRule rule = MatchingRule.determine(dealer, player);

        assertThat(rule).isEqualTo(MatchingRule.PLAYER_WIN);
    }

    @Test
    void 플레이어와_딜러_둘다_내추럴인_경우_정상판정() {
        Player player = new Player(Name.from("나무"));
        player.add(new Card(Rank.ACE, Suit.SPADE));
        player.add(new Card(Rank.TEN, Suit.SPADE));
        Dealer dealer = new Dealer();
        dealer.add(new Card(Rank.ACE, Suit.SPADE));
        dealer.add(new Card(Rank.TEN, Suit.SPADE));

        MatchingRule rule = MatchingRule.determine(dealer, player);

        assertThat(rule).isEqualTo(MatchingRule.BOTH_NATURAL_BLACKJACK);
    }

    @Test
    void 플레이어만_내추럴인_경우_정상판정() {
        Player player = new Player(Name.from("나무"));
        player.add(new Card(Rank.ACE, Suit.SPADE));
        player.add(new Card(Rank.TEN, Suit.SPADE));
        Dealer dealer = new Dealer();
        dealer.add(new Card(Rank.TEN, Suit.SPADE));
        dealer.add(new Card(Rank.JACK, Suit.SPADE));

        MatchingRule rule = MatchingRule.determine(dealer, player);

        assertThat(rule).isEqualTo(MatchingRule.PLAYER_NATURAL);
    }

    @Test
    void 딜러만_내추럴인_경우_정상판정() {
        Player player = new Player(Name.from("나무"));
        player.add(new Card(Rank.TEN, Suit.SPADE));
        player.add(new Card(Rank.JACK, Suit.SPADE));
        Dealer dealer = new Dealer();
        dealer.add(new Card(Rank.ACE, Suit.SPADE));
        dealer.add(new Card(Rank.TEN, Suit.SPADE));

        MatchingRule rule = MatchingRule.determine(dealer, player);

        assertThat(rule).isEqualTo(MatchingRule.DEALER_NATURAL);
    }

    @Test
    void 버스트나_내추럴이_없을경우_플레이어점수가_높으면_플레이어승리_판정() {
        Player player = new Player(Name.from("나무"));
        player.add(new Card(Rank.TEN, Suit.SPADE));
        player.add(new Card(Rank.JACK, Suit.SPADE));
        Dealer dealer = new Dealer();
        dealer.add(new Card(Rank.NINE, Suit.SPADE));
        dealer.add(new Card(Rank.TEN, Suit.SPADE));

        MatchingRule rule = MatchingRule.determine(dealer, player);

        assertThat(rule).isEqualTo(MatchingRule.PLAYER_WIN);
    }

    @Test
    void 버스트나_내추럴이_없을경우_플레이어와_딜러_점수가_같으면_무승부_판정() {
        Player player = new Player(Name.from("나무"));
        player.add(new Card(Rank.NINE, Suit.SPADE));
        player.add(new Card(Rank.TEN, Suit.SPADE));
        Dealer dealer = new Dealer();
        dealer.add(new Card(Rank.NINE, Suit.SPADE));
        dealer.add(new Card(Rank.TEN, Suit.SPADE));

        MatchingRule rule = MatchingRule.determine(dealer, player);

        assertThat(rule).isEqualTo(MatchingRule.DRAW);
    }

    @Test
    void 버스트나_내추럴이_없을경우_플레이어점수가_낮으면_플레이어패배_판정() {
        Player player = new Player(Name.from("나무"));
        player.add(new Card(Rank.EIGHT, Suit.SPADE));
        player.add(new Card(Rank.TEN, Suit.SPADE));
        Dealer dealer = new Dealer();
        dealer.add(new Card(Rank.NINE, Suit.SPADE));
        dealer.add(new Card(Rank.TEN, Suit.SPADE));

        MatchingRule rule = MatchingRule.determine(dealer, player);

        assertThat(rule).isEqualTo(MatchingRule.PLAYER_LOSE);
    }

}