package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러의 점수가 16점 이하이면 카드를 뽑을 수 있다.")
    void isDrawable_True_WhenScoreIsExactly16() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.SPADES));
        dealer.receiveCard(new Card(Rank.SIX, Suit.HEARTS));

        assertThat(dealer.isDrawable()).isTrue();
    }

    @Test
    @DisplayName("딜러의 점수가 17점 이상이면 카드를 뽑을 수 없다.")
    void isDrawable_False_WhenScoreIsExactly17() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.SPADES));
        dealer.receiveCard(new Card(Rank.SEVEN, Suit.HEARTS));

        assertThat(dealer.isDrawable()).isFalse();
    }

    @Test
    @DisplayName("플레이어만 블랙잭일 경우 1.5배의 수익을 얻는다.")
    void determinePlayerProfit_WinBlackjack() {
        Player player = new Player("pobi", 10000);
        player.receiveCard(new Card(Rank.ACE, Suit.SPADES));
        player.receiveCard(new Card(Rank.TEN, Suit.HEARTS));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.SPADES));
        dealer.receiveCard(new Card(Rank.NINE, Suit.HEARTS));

        List<Integer> profits = dealer.determinePlayersProfit(List.of(player));

        assertThat(profits.getFirst()).isEqualTo(15000);
    }

    @Test
    @DisplayName("딜러와 플레이어가 모두 블랙잭일 경우 무승부 처리된다.")
    void determinePlayerProfit_Draw_WhenBothBlackjack() {
        Player player = new Player("pobi", 10000);
        player.receiveCard(new Card(Rank.ACE, Suit.SPADES));
        player.receiveCard(new Card(Rank.TEN, Suit.HEARTS));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.ACE, Suit.CLUBS));
        dealer.receiveCard(new Card(Rank.KING, Suit.DIAMONDS));

        List<Integer> profits = dealer.determinePlayersProfit(List.of(player));

        assertThat(profits.getFirst()).isEqualTo(0);
    }

    @Test
    @DisplayName("딜러와 플레이어가 모두 Bust일 경우 딜러가 승리한다.")
    void determinePlayerProfit_Win_WhenBothBust() {
        Player player = new Player("pobi", 10000);
        player.receiveCard(new Card(Rank.TEN, Suit.SPADES));
        player.receiveCard(new Card(Rank.TEN, Suit.HEARTS));
        player.receiveCard(new Card(Rank.TWO, Suit.CLUBS));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.DIAMONDS));
        dealer.receiveCard(new Card(Rank.SIX, Suit.CLUBS));
        dealer.receiveCard(new Card(Rank.SIX, Suit.SPADES));

        List<Integer> profits = dealer.determinePlayersProfit(List.of(player));

        assertThat(profits.getFirst()).isEqualTo(-10000);
    }

    @Test
    @DisplayName("플레이어들의 수익을 정산하고, 딜러는 그 반대 부호의 최종 수익을 가진다.")
    void determineProfit_DealerZeroSum() {
        Player winPlayer = new Player("pobi", 10000);
        winPlayer.receiveCard(new Card(Rank.TEN, Suit.SPADES));
        winPlayer.receiveCard(new Card(Rank.NINE, Suit.HEARTS));

        Player losePlayer = new Player("jason", 20000);
        losePlayer.receiveCard(new Card(Rank.TEN, Suit.CLUBS));
        losePlayer.receiveCard(new Card(Rank.SEVEN, Suit.DIAMONDS));

        Player drawPlayer = new Player("honux", 30000);
        drawPlayer.receiveCard(new Card(Rank.TEN, Suit.DIAMONDS));
        drawPlayer.receiveCard(new Card(Rank.EIGHT, Suit.SPADES));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.HEARTS));
        dealer.receiveCard(new Card(Rank.EIGHT, Suit.CLUBS));

        assertThat(dealer.determineProfit(List.of(winPlayer, losePlayer, drawPlayer))).isEqualTo(10000);
    }
}
