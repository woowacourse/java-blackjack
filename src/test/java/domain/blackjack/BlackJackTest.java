package domain.blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Bet.BetResult;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackTest {

    @DisplayName("참가자의 승패를 결정한다.")
    @Test
    void isWinner() {
        Dealer dealer = new Dealer();
        Players players = new Players(List.of(new Player("one", 1000), new Player("two", 1000)));
        BlackJack blackJack = new BlackJack(dealer, players);

        Player one = players.getValue().get(0);
        one.receiveCard(new Card(Shape.HEARTS, Rank.KING));

        Player two = players.getValue().get(1);
        two.receiveCard(new Card(Shape.DIAMONDS, Rank.TWO));
        two.receiveCard(new Card(Shape.DIAMONDS, Rank.THREE));

        dealer.receiveCard(new Card(Shape.DIAMONDS, Rank.THREE));
        dealer.receiveCard(new Card(Shape.DIAMONDS, Rank.FOUR));
        /*
         * one 참가자의 점수: 10점
         * two 참가자의 점수: 5점
         * 딜러의 점수: 7점인 상황
         */
        BetResult betResult = blackJack.makeBetResult();
        Assertions.assertAll(
                () -> assertThat(betResult.getBetAmountByParticipant()).containsEntry(one, 1000.),
                () -> assertThat(betResult.getBetAmountByParticipant()).containsEntry(two, -1000.)

        );
    }

    @DisplayName("참가자와 딜러의 점수가 같은경우 ")
    @Test
    void isWinnerWhenSameScore() {
        Dealer dealer = new Dealer();
        Players players = new Players(List.of(new Player("one", 1000), new Player("two", 1000)));
        BlackJack blackJack = new BlackJack(dealer, players);

        Player one = players.getValue().get(0);
        one.receiveCard(new Card(Shape.HEARTS, Rank.TWO));
        one.receiveCard(new Card(Shape.HEARTS, Rank.THREE));

        Player two = players.getValue().get(1);
        two.receiveCard(new Card(Shape.CLUBS, Rank.TWO));
        two.receiveCard(new Card(Shape.CLUBS, Rank.THREE));

        dealer.receiveCard(new Card(Shape.DIAMONDS, Rank.TWO));
        dealer.receiveCard(new Card(Shape.DIAMONDS, Rank.THREE));

        /*
         * one 참가자의 점수: 5점
         * two 참가자의 점수: 5점
         * 딜러의 점수: 5점인
         */
        BetResult betResult = blackJack.makeBetResult();
        Assertions.assertAll(
                () -> assertThat(betResult.getBetAmountByParticipant()).containsEntry(one, 0.),
                () -> assertThat(betResult.getBetAmountByParticipant()).containsEntry(two, 0.)
        );
    }

    @DisplayName("참가자가 블랙잭인 경우 ")
    @Test
    void whenParticipantBlackJack() {
        Dealer dealer = new Dealer();
        Players players = new Players(List.of(new Player("one", 1000), new Player("two", 1000)));
        BlackJack blackJack = new BlackJack(dealer, players);

        Player one = players.getValue().get(0);
        one.receiveCard(new Card(Shape.HEARTS, Rank.KING));
        one.receiveCard(new Card(Shape.HEARTS, Rank.ACE));

        Player two = players.getValue().get(1);
        two.receiveCard(new Card(Shape.CLUBS, Rank.ACE));
        two.receiveCard(new Card(Shape.CLUBS, Rank.TWO));

        dealer.receiveCard(new Card(Shape.DIAMONDS, Rank.QUEEN));
        /*
         * one 참가자의 점수: 블랙잭
         * two 참가자의 점수: 13점
         * 딜러의 점수: 10점인
         */
        BetResult betResult = blackJack.makeBetResult();
        Assertions.assertAll(
                () -> assertThat(betResult.getBetAmountByParticipant()).containsEntry(one, 1500.),
                () -> assertThat(betResult.getBetAmountByParticipant()).containsEntry(two, 1000.)
        );
    }

    @DisplayName("참가자와 딜러가 블랙잭인 경우 ")
    @Test
    void whenParticipantAndDealerBlackJack() {
        Dealer dealer = new Dealer();
        Players players = new Players(List.of(new Player("one", 1000), new Player("two", 1000)));
        BlackJack blackJack = new BlackJack(dealer, players);

        Player one = players.getValue().get(0);
        one.receiveCard(new Card(Shape.HEARTS, Rank.KING));
        one.receiveCard(new Card(Shape.HEARTS, Rank.ACE));

        Player two = players.getValue().get(1);
        two.receiveCard(new Card(Shape.CLUBS, Rank.ACE));
        two.receiveCard(new Card(Shape.CLUBS, Rank.TWO));

        dealer.receiveCard(new Card(Shape.CLUBS, Rank.KING));
        dealer.receiveCard(new Card(Shape.CLUBS, Rank.ACE));
        /*
         * one 참가자의 점수: 블랙잭
         * two 참가자의 점수: 13점
         * 딜러의 점수: 블랙잭
         */
        BetResult betResult = blackJack.makeBetResult();
        Assertions.assertAll(
                () -> assertThat(betResult.getBetAmountByParticipant()).containsEntry(one, 0.),
                () -> assertThat(betResult.getBetAmountByParticipant()).containsEntry(two, -1000.)
        );
    }

    @DisplayName("딜러가 버스트시 참가자는 승리한다. ")
    @Test
    void whenDealerIsBust() {
        Dealer dealer = new Dealer();
        Players players = new Players(List.of(new Player("one", 1000), new Player("two", 1000)));
        BlackJack blackJack = new BlackJack(dealer, players);

        Player one = players.getValue().get(0);
        one.receiveCard(new Card(Shape.HEARTS, Rank.TWO));
        one.receiveCard(new Card(Shape.HEARTS, Rank.THREE));

        Player two = players.getValue().get(1);
        two.receiveCard(new Card(Shape.CLUBS, Rank.TWO));
        two.receiveCard(new Card(Shape.CLUBS, Rank.THREE));

        dealer.receiveCard(new Card(Shape.CLUBS, Rank.KING));
        dealer.receiveCard(new Card(Shape.CLUBS, Rank.QUEEN));
        dealer.receiveCard(new Card(Shape.CLUBS, Rank.JACK));
        /*
         * one 참가자의 점수: 5점
         * two 참가자의 점수: 5점
         * 딜러의 점수: 버스트
         */
        BetResult betResult = blackJack.makeBetResult();
        Assertions.assertAll(
                () -> assertThat(betResult.getBetAmountByParticipant()).containsEntry(one, 1000.),
                () -> assertThat(betResult.getBetAmountByParticipant()).containsEntry(two, 1000.)
        );
    }
}
