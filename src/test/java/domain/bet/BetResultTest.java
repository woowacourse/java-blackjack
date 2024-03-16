package domain.bet;

import static org.assertj.core.api.Assertions.assertThat;

import domain.blackjack.BlackJack;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetResultTest {

    @DisplayName("딜러의 수익을 계산한다.")
    @Test
    void startBet() {
        Dealer dealer = new Dealer();
        Players players = new Players(List.of(new Player("one", 1000), new Player("two", 1000)));
        BlackJack blackJack = new BlackJack(dealer, players);

        Player one = players.getValue().get(0);
        one.receiveCard(new Card(Shape.HEARTS, Rank.TWO));
        one.receiveCard(new Card(Shape.HEARTS, Rank.THREE));

        Player two = players.getValue().get(1);
        two.receiveCard(new Card(Shape.DIAMONDS, Rank.TWO));
        two.receiveCard(new Card(Shape.DIAMONDS, Rank.THREE));

        dealer.receiveCard(new Card(Shape.DIAMONDS, Rank.THREE));
        dealer.receiveCard(new Card(Shape.DIAMONDS, Rank.FOUR));
        /*
         * one 참가자의 점수: 5점 LOSE
         * two 참가자의 점수: 5점 LOSE
         * 딜러의 점수: 7점인 상황
         */
        BetResult betResult = blackJack.makeBetResult();
        assertThat(betResult.calculateDealerProfit()).isEqualTo(2000);
    }
}
