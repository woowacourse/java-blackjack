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
        final Dealer dealer = new Dealer(new Card(TWO, CLUBS), new Card(TWO, CLUBS));

        dealer.automaticHit(() -> {
        });

        Assertions.assertThat(dealer.getScore()).isGreaterThanOrEqualTo(17);
    }

    @Test
    @DisplayName("딜러는 플레이어의 핸드를 보고 승패무를 결정할 수 있다")
    void compare() {
        final Dealer dealer = new Dealer(new Card(THREE, CLUBS), new Card(THREE, CLUBS));
        final Player playerWin = new Player(new Name("win"), new BetAmount(100), new Card(FOUR, CLUBS),
                new Card(FOUR, CLUBS));
        final Player playerLose = new Player(new Name("lose"), new BetAmount(100), new Card(TWO, CLUBS),
                new Card(TWO, CLUBS));
        final Player playerTie = new Player(new Name("tie"), new BetAmount(100), new Card(THREE, CLUBS),
                new Card(THREE, CLUBS));

        dealer.standIfRunning();
        playerWin.standIfRunning();
        playerLose.standIfRunning();
        playerTie.standIfRunning();

        Assertions.assertThat(dealer.compareHandsWith(playerWin)).isSameAs(Result.LOSE);
        Assertions.assertThat(dealer.compareHandsWith(playerLose)).isSameAs(Result.WIN);
        Assertions.assertThat(dealer.compareHandsWith(playerTie)).isSameAs(Result.TIE);
    }

}
