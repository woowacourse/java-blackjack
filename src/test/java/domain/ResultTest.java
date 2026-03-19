package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.constant.Result;
import domain.participant.Dealer;
import domain.participant.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    @Test
    void 딜러가_bust이면_플레이어는_승리한다() {
        Player player = new Player(new Name("pobi"), new BettingMoney(1000L));
        Dealer dealer = new Dealer();

        player.addCard(new Card(Rank.TEN, Suit.SPADE));
        player.addCard(new Card(Rank.EIGHT, Suit.HEART));

        dealer.addCard(new Card(Rank.KING, Suit.SPADE));
        dealer.addCard(new Card(Rank.QUEEN, Suit.HEART));
        dealer.addCard(new Card(Rank.TWO, Suit.CLUB));

        Result result = Result.from(player, dealer);

        assertThat(result).isEqualTo(Result.WIN);
    }

    @Test
    void 플레이어가_bust이면_딜러가_bust여도_플레이어는_패배한다() {
        Player player = new Player(new Name("pobi"), new BettingMoney(1000L));
        Dealer dealer = new Dealer();

        player.addCard(new Card(Rank.KING, Suit.SPADE));
        player.addCard(new Card(Rank.QUEEN, Suit.HEART));
        player.addCard(new Card(Rank.TWO, Suit.CLUB));

        dealer.addCard(new Card(Rank.KING, Suit.SPADE));
        dealer.addCard(new Card(Rank.QUEEN, Suit.HEART));
        dealer.addCard(new Card(Rank.TWO, Suit.CLUB));

        Result result = Result.from(player, dealer);

        assertThat(result).isEqualTo(Result.BUST);
    }

    @Test
    void 플레이어와_딜러가_모두_내추럴_블랙잭이면_DRAW이다() {
        Player player = new Player(new Name("pobi"), new BettingMoney(1000L));
        Dealer dealer = new Dealer();

        player.addCard(new Card(Rank.ACE, Suit.SPADE));
        player.addCard(new Card(Rank.KING, Suit.HEART));

        dealer.addCard(new Card(Rank.ACE, Suit.CLUB));
        dealer.addCard(new Card(Rank.QUEEN, Suit.DIAMOND));

        Result result = Result.from(player, dealer);

        assertThat(result).isEqualTo(Result.DRAW);
    }

    @Test
    void 플레이어만_내추럴_블랙잭이면_BLACKJACK이다() {
        Player player = new Player(new Name("pobi"), new BettingMoney(1000L));
        Dealer dealer = new Dealer();

        player.addCard(new Card(Rank.ACE, Suit.SPADE));
        player.addCard(new Card(Rank.KING, Suit.HEART));

        dealer.addCard(new Card(Rank.TEN, Suit.CLUB));
        dealer.addCard(new Card(Rank.NINE, Suit.DIAMOND));

        Result result = Result.from(player, dealer);

        assertThat(result).isEqualTo(Result.BLACKJACK);
    }
}