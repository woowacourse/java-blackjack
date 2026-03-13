package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Money;
import domain.participant.Player;
import domain.result.Outcome;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutcomeTest {

    @Test
    @DisplayName("1. 플레이어만 처음 2장으로 블랙잭일 경우")
    void playerOnlyBlackJack() {
        Player player = new Player("pobi", new Money("10000"));
        Dealer dealer = new Dealer();

        player.drawCard(new Card(0));
        player.drawCard(new Card(10));

        dealer.drawCard(new Card(10));
        dealer.drawCard(new Card(8));

        Outcome outcome = Outcome.decideWinner(player, dealer);
        Money profit = player.getMoney().multiply(outcome.getRate());
        assertThat(outcome).isEqualTo(Outcome.BLACKJACK_WIN);
        assertThat(profit.getAmount()).isEqualTo(15000);
    }

    @Test
    @DisplayName("2. 플레이어와 딜러 모두 처음 2장으로 블랙잭인 경우")
    void bothBlackJack() {
        Player player = new Player("pobi", new Money("10000"));
        Dealer dealer = new Dealer();

        player.drawCard(new Card(0));
        player.drawCard(new Card(10));

        dealer.drawCard(new Card(0));
        dealer.drawCard(new Card(10));

        Outcome outcome = Outcome.decideWinner(player, dealer);
        Money profit = player.getMoney().multiply(outcome.getRate());
        assertThat(outcome).isEqualTo(Outcome.DRAW);
        assertThat(profit.getAmount()).isEqualTo(10000);
    }

    @Test
    @DisplayName("3. 딜러만 처음 2장으로 블랙잭인 경우")
    void dealerOnlyBlackJack() {
        Player player = new Player("pobi", new Money("10000"));
        Dealer dealer = new Dealer();

        player.drawCard(new Card(10));
        player.drawCard(new Card(8));

        dealer.drawCard(new Card(0));
        dealer.drawCard(new Card(10));

        Outcome outcome = Outcome.decideWinner(player, dealer);
        Money profit = player.getMoney().multiply(outcome.getRate());
        assertThat(outcome).isEqualTo(Outcome.LOSE);
        assertThat(profit.getAmount()).isEqualTo(-10000);
    }

    @Test
    @DisplayName("4. 플레이어가 카드를 추가로 뽑아 21을 초과할 경우")
    void playerBust() {
        Player player = new Player("pobi", new Money("10000"));
        Dealer dealer = new Dealer();
        player.drawCard(new Card(10));
        player.drawCard(new Card(5));
        player.drawCard(new Card(10));
        dealer.drawCard(new Card(10));
        dealer.drawCard(new Card(8));
        Outcome outcome = Outcome.decideWinner(player, dealer);
        Money profit = player.getMoney().multiply(outcome.getRate());
        assertThat(outcome).isEqualTo(Outcome.LOSE);
        assertThat(profit.getAmount()).isEqualTo(-10000);
    }

    @Test
    @DisplayName("5. 딜러가 21을 초과하고 플레이어는 21 이하일 경우")
    void dealerBust_playerAlive() {
        Player player = new Player("pobi", new Money("10000"));
        Dealer dealer = new Dealer();
        player.drawCard(new Card(10));
        player.drawCard(new Card(5));
        dealer.drawCard(new Card(10));
        dealer.drawCard(new Card(5));
        dealer.drawCard(new Card(10));
        Outcome outcome = Outcome.decideWinner(player, dealer);
        Money profit = player.getMoney().multiply(outcome.getRate());
        assertThat(outcome).isEqualTo(Outcome.WIN);
        assertThat(profit.getAmount()).isEqualTo(10000);
    }
}
