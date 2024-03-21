package domain.player;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @DisplayName("플레이어는 카드의 합이 21미만일 때만 히트할 수 있다")
    @Test
    void canHit() {
        final Player player = new Player(new Name("상냥한 찰리"), new BetAmount(100),new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));
        Assertions.assertThat(player.getState().isRunning()).isTrue();
    }

    @DisplayName("플레이어는 카드의 합이 21이상이면 히트할 수 없다")
    @Test
    void canNotHit() {
        final Player player = new Player(new Name("지쳐버린 종이"), new BetAmount(100),new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.ACE, Suit.CLUBS));
        Assertions.assertThat(player.getState().isRunning()).isFalse();
    }

    @DisplayName("플레이어가 블랙잭이 아니면서 이기면 배팅 금액만큼 얻는다")
    @Test
    void playerWinProfit() {
        final Player player = new Player(new Name("친절한 테바"), new BetAmount(100),new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));
        final Dealer dealer = new Dealer(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.NINE, Suit.CLUBS));

        player.standIfRunning();
        dealer.standIfRunning();

        assertThat(player.calculateProfit(dealer)).isEqualTo(100);
    }

    @DisplayName("플레이어가 패배하면 배팅 금액만큼 잃는다")
    @Test
    void playerLoseProfit() {
        final Player player = new Player(new Name("종이"), new BetAmount(100),new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));
        final Dealer dealer = new Dealer(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.ACE, Suit.CLUBS));

        player.standIfRunning();

        assertThat(player.calculateProfit(dealer)).isEqualTo(-100);
    }

    @DisplayName("플레이어가 블랙잭이라면 수익은 배팅금액의 1.5배다")
    @Test
    void blackjackProfit() {
        final Player player = new Player(new Name("종이"), new BetAmount(100),new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.ACE, Suit.CLUBS));
        final Dealer dealer = new Dealer(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));

        dealer.standIfRunning();

        assertThat(player.calculateProfit(dealer)).isEqualTo(150);
    }

    @DisplayName("무승부라면 수익이 0원이다")
    @Test
    void profit() {
        final Player player = new Player(new Name("종이"), new BetAmount(100),new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.ACE, Suit.CLUBS));
        final Dealer dealer = new Dealer(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.ACE, Suit.CLUBS));


        assertThat(player.calculateProfit(dealer)).isEqualTo(0);
    }
}
