package domain.player;

import static domain.card.Rank.FOUR;
import static domain.card.Rank.SEVEN;
import static domain.card.Rank.SIX;
import static domain.card.Rank.TEN;
import static domain.card.Rank.THREE;
import static domain.card.Rank.TWO;
import static domain.card.Suit.CLUBS;

import domain.card.Card;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("딜러는 카드의 합이 17 미만이라면 히트할 수 있다")
    void canHit() {
        final Dealer dealer = new Dealer();
        dealer.init(new Card(TEN, CLUBS), new Card(SIX, CLUBS));
        dealer.finish();

//        Assertions.assertThat(dealer.getState().isStand()).isTrue();
    }

    @Test
    @DisplayName("딜러는 카드의 합이 17 이상이면 히트할 수 없다")
    void canNotHit() {
        final Dealer dealer = new Dealer();
        dealer.init(new Card(TEN, CLUBS), new Card(SEVEN, CLUBS));
        dealer.finish();
        Assertions.assertThat(dealer.getState().isRunning()).isFalse();
    }

    @Test
    @DisplayName("딜러는 플레이어의 핸드를 보고 승패무를 결정할 수 있다")
    void compare() {
        final Dealer dealer = new Dealer();
        final Player playerWin = new Player(new Name("win"));
        final Player playerLose = new Player(new Name("lose"));
        final Player playerTie = new Player(new Name("tie"));

        dealer.init(new Card(THREE, CLUBS), new Card(THREE, CLUBS));

        playerWin.init(new Card(FOUR, CLUBS), new Card(FOUR, CLUBS));
        playerLose.init(new Card(TWO, CLUBS), new Card(TWO, CLUBS));
        playerTie.init(new Card(THREE, CLUBS), new Card(THREE, CLUBS));
        dealer.finish();
        playerWin.finish();
        playerLose.finish();
        playerTie.finish();

        Assertions.assertThat(dealer.compareHandsWith(playerWin)).isSameAs(Result.LOSE);
        Assertions.assertThat(dealer.compareHandsWith(playerLose)).isSameAs(Result.WIN);
        Assertions.assertThat(dealer.compareHandsWith(playerTie)).isSameAs(Result.TIE);
    }

}
