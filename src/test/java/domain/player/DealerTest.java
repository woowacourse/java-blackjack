package domain.player;

import static domain.card.Rank.FOUR;
import static domain.card.Rank.THREE;
import static domain.card.Rank.TWO;
import static domain.card.Suit.CLUBS;

import domain.card.Card;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("딜러는 히트 이후 카드의 합이 17을 초과한다")
    void canHit() {
        final Dealer dealer = new Dealer();

        dealer.init(new Card(TWO, CLUBS), new Card(TWO, CLUBS));
        dealer.automaticHit(() -> {
        });

        Assertions.assertThat(dealer.getScore()).isGreaterThanOrEqualTo(17);
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
