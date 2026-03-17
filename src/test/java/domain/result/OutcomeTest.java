package domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Money;
import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutcomeTest {

    @Test
    @DisplayName("플레이어만 처음 2장으로 블랙잭일 경우, 블랙잭 승리를 반환한다.")
    void playerOnlyBlackJack() {
        Player player = new Player("pobi", new Money("10000"));
        Dealer dealer = new Dealer();
        player.drawCard(new Card(0));
        player.drawCard(new Card(10));
        dealer.drawCard(new Card(10));
        dealer.drawCard(new Card(8));

        Outcome outcome = Outcome.decideWinner(player, dealer);
        assertThat(outcome).isEqualTo(Outcome.BLACKJACK_WIN);
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 처음 2장으로 블랙잭인 경우, 무승부를 반환한다.")
    void bothBlackJack() {
        Player player = new Player("pobi", new Money("10000"));
        Dealer dealer = new Dealer();
        player.drawCard(new Card(0));
        player.drawCard(new Card(10));
        dealer.drawCard(new Card(13));
        dealer.drawCard(new Card(23));

        Outcome outcome = Outcome.decideWinner(player, dealer);
        assertThat(outcome).isEqualTo(Outcome.DRAW);
    }

    @Test
    @DisplayName("딜러만 처음 2장으로 블랙잭인 경우, 플레이어는 패배한다.")
    void dealerOnlyBlackJack() {
        Player player = new Player("pobi", new Money("10000"));
        Dealer dealer = new Dealer();
        player.drawCard(new Card(10));
        player.drawCard(new Card(8));
        dealer.drawCard(new Card(0));
        dealer.drawCard(new Card(10));

        Outcome outcome = Outcome.decideWinner(player, dealer);
        assertThat(outcome).isEqualTo(Outcome.LOSE);
    }

    @Test
    @DisplayName("플레이어가 카드를 추가로 뽑아 21을 초과할 경우, 패배한다.")
    void playerBust() {
        Player player = new Player("pobi", new Money("10000"));
        Dealer dealer = new Dealer();
        player.drawCard(new Card(10));
        player.drawCard(new Card(5));
        player.drawCard(new Card(23));
        dealer.drawCard(new Card(10));
        dealer.drawCard(new Card(8));

        Outcome outcome = Outcome.decideWinner(player, dealer);
        assertThat(outcome).isEqualTo(Outcome.LOSE);
    }

    @Test
    @DisplayName("딜러가 21을 초과하고 플레이어는 21 이하일 경우, 플레이어가 승리한다.")
    void dealerBust_playerAlive() {
        Player player = new Player("pobi", new Money("10000"));
        Dealer dealer = new Dealer();
        player.drawCard(new Card(10));
        player.drawCard(new Card(5));
        dealer.drawCard(new Card(10));
        dealer.drawCard(new Card(5));
        dealer.drawCard(new Card(23));

        Outcome outcome = Outcome.decideWinner(player, dealer);
        assertThat(outcome).isEqualTo(Outcome.WIN);
    }

    @Test
    @DisplayName("둘 다 버스트되지 않은 상태에서 플레이어 점수가 더 높으면 승리한다.")
    void playerScoreHigher() {
        Player player = new Player("pobi", new Money("10000"));
        Dealer dealer = new Dealer();
        player.drawCard(new Card(10));
        player.drawCard(new Card(9));
        dealer.drawCard(new Card(10));
        dealer.drawCard(new Card(8));

        Outcome outcome = Outcome.decideWinner(player, dealer);
        assertThat(outcome).isEqualTo(Outcome.WIN);
    }

    @Test
    @DisplayName("둘 다 버스트되지 않은 상태에서 딜러 점수가 더 높으면 패배한다.")
    void dealerScoreHigher() {
        Player player = new Player("pobi", new Money("10000"));
        Dealer dealer = new Dealer();
        player.drawCard(new Card(10));
        player.drawCard(new Card(7));
        dealer.drawCard(new Card(10));
        dealer.drawCard(new Card(9));

        Outcome outcome = Outcome.decideWinner(player, dealer);
        assertThat(outcome).isEqualTo(Outcome.LOSE);
    }

    @Test
    @DisplayName("둘 다 버스트되지 않고 점수가 같으면 무승부를 반환한다.")
    void sameScore() {
        Player player = new Player("pobi", new Money("10000"));
        Dealer dealer = new Dealer();
        player.drawCard(new Card(10));
        player.drawCard(new Card(8));
        dealer.drawCard(new Card(11));
        dealer.drawCard(new Card(21));

        Outcome outcome = Outcome.decideWinner(player, dealer);
        assertThat(outcome).isEqualTo(Outcome.DRAW);
    }
}
