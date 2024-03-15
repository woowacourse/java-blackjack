package domain.player;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("딜러는 카드의 합이 17 미만이라면 히트할 수 있다")
    void canHit() {
        final Dealer dealer = new Dealer();
        dealer.init(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.SIX, Suit.CLUBS));

        Assertions.assertThat(dealer.canHit()).isTrue();
    }

    @Test
    @DisplayName("딜러는 카드의 합이 17 이상이면 히트할 수 없다")
    void canNotHit() {
        final Dealer dealer = new Dealer();
        dealer.init(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.SEVEN, Suit.CLUBS));

        Assertions.assertThat(dealer.canHit()).isFalse();
    }

    @Test
    @DisplayName("딜러는 플레이어의 핸드를 보고 승패무를 결정할 수 있다")
    void compare() {
        final Dealer dealer = new Dealer();
        final Player playerWin = new Player(new Name("win"));
        final Player playerLose = new Player(new Name("lose"));
        final Player playerTie = new Player(new Name("tie"));

        dealer.init(new Card(Rank.THREE, Suit.CLUBS), new Card(Rank.THREE, Suit.CLUBS));

        playerWin.init(new Card(Rank.FOUR, Suit.CLUBS), new Card(Rank.FOUR, Suit.CLUBS));
        playerLose.init(new Card(Rank.TWO, Suit.CLUBS), new Card(Rank.TWO, Suit.CLUBS));
        playerTie.init(new Card(Rank.THREE, Suit.CLUBS), new Card(Rank.THREE, Suit.CLUBS));

        Assertions.assertThat(dealer.compareHandsWith(playerWin)).isSameAs(PlayerResult.LOSE);
        Assertions.assertThat(dealer.compareHandsWith(playerLose)).isSameAs(PlayerResult.WIN);
        Assertions.assertThat(dealer.compareHandsWith(playerTie)).isSameAs(PlayerResult.TIE);
    }

}
