package domain.blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackTest {

    @DisplayName("참가자의 승패를 결정한다.")
    @Test
    void isWinner() {
        Dealer dealer = new Dealer();
        Participants participants = new Participants(List.of("one", "two"));
        BlackJack blackJack = new BlackJack(dealer, participants);

        Participant one = participants.getValue().get(0);
        one.receiveCard(new Card(Shape.HEARTS, Rank.KING));

        Participant two = participants.getValue().get(1);
        two.receiveCard(new Card(Shape.DIAMONDS, Rank.TWO));
        two.receiveCard(new Card(Shape.DIAMONDS, Rank.THREE));

        dealer.receiveCard(new Card(Shape.DIAMONDS, Rank.THREE));
        dealer.receiveCard(new Card(Shape.DIAMONDS, Rank.FOUR));
        /*
         * one 참가자의 점수: 10점
         * two 참가자의 점수: 5점
         * 딜러의 점수: 7점인 상황
         */
        Map<Participant, WinStatus> resultByParticipant = blackJack.makeResult();

        Assertions.assertAll(
                () -> assertThat(resultByParticipant).containsEntry(one, WinStatus.WIN),
                () -> assertThat(resultByParticipant).containsEntry(two, WinStatus.LOSE)

        );
    }

    @DisplayName("참가자와 딜러의 점수가 같은경우 ")
    @Test
    void isWinnerWhenSameScore() {
        Dealer dealer = new Dealer();
        Participants participants = new Participants(List.of("one", "two"));
        BlackJack blackJack = new BlackJack(dealer, participants);

        Participant one = participants.getValue().get(0);
        one.receiveCard(new Card(Shape.HEARTS, Rank.TWO));
        one.receiveCard(new Card(Shape.HEARTS, Rank.THREE));

        Participant two = participants.getValue().get(1);
        two.receiveCard(new Card(Shape.CLUBS, Rank.TWO));
        two.receiveCard(new Card(Shape.CLUBS, Rank.THREE));

        dealer.receiveCard(new Card(Shape.DIAMONDS, Rank.TWO));
        dealer.receiveCard(new Card(Shape.DIAMONDS, Rank.THREE));

        /*
         * one 참가자의 점수: 5점
         * two 참가자의 점수: 5점
         * 딜러의 점수: 5점인
         */
        Map<Participant, WinStatus> resultByParticipant = blackJack.makeResult();

        Assertions.assertAll(
                () -> assertThat(resultByParticipant).containsEntry(one, WinStatus.PUSH),
                () -> assertThat(resultByParticipant).containsEntry(two, WinStatus.PUSH)
        );
    }

    @DisplayName("참가자가 블랙잭인 경우 ")
    @Test
    void whenParticipantBlackJack() {
        Dealer dealer = new Dealer();
        Participants participants = new Participants(List.of("one", "two"));
        BlackJack blackJack = new BlackJack(dealer, participants);

        Participant one = participants.getValue().get(0);
        one.receiveCard(new Card(Shape.HEARTS, Rank.KING));
        one.receiveCard(new Card(Shape.HEARTS, Rank.ACE));

        Participant two = participants.getValue().get(1);
        two.receiveCard(new Card(Shape.CLUBS, Rank.ACE));
        two.receiveCard(new Card(Shape.CLUBS, Rank.TWO));

        dealer.receiveCard(new Card(Shape.DIAMONDS, Rank.QUEEN));
        /*
         * one 참가자의 점수: 블랙잭
         * two 참가자의 점수: 13점
         * 딜러의 점수: 10점인
         */
        Map<Participant, WinStatus> resultByParticipant = blackJack.makeResult();

        Assertions.assertAll(
                () -> assertThat(resultByParticipant).containsEntry(one, WinStatus.BLACKJACK),
                () -> assertThat(resultByParticipant).containsEntry(two, WinStatus.WIN)
        );
    }

    @DisplayName("참가자와 딜러가 블랙잭인 경우 ")
    @Test
    void whenParticipantAndDealerBlackJack() {
        Dealer dealer = new Dealer();
        Participants participants = new Participants(List.of("one", "two"));
        BlackJack blackJack = new BlackJack(dealer, participants);

        Participant one = participants.getValue().get(0);
        one.receiveCard(new Card(Shape.HEARTS, Rank.KING));
        one.receiveCard(new Card(Shape.HEARTS, Rank.ACE));

        Participant two = participants.getValue().get(1);
        two.receiveCard(new Card(Shape.CLUBS, Rank.ACE));
        two.receiveCard(new Card(Shape.CLUBS, Rank.TWO));

        dealer.receiveCard(new Card(Shape.CLUBS, Rank.KING));
        dealer.receiveCard(new Card(Shape.CLUBS, Rank.ACE));
        /*
         * one 참가자의 점수: 블랙잭
         * two 참가자의 점수: 13점
         * 딜러의 점수: 블랙잭
         */
        Map<Participant, WinStatus> resultByParticipant = blackJack.makeResult();

        Assertions.assertAll(
                () -> assertThat(resultByParticipant).containsEntry(one, WinStatus.PUSH),
                () -> assertThat(resultByParticipant).containsEntry(two, WinStatus.LOSE)
        );
    }

    @DisplayName("딜러가 버스트시 참가자는 승리한다. ")
    @Test
    void whenDealerIsBust() {
        Dealer dealer = new Dealer();
        Participants participants = new Participants(List.of("one", "two"));
        BlackJack blackJack = new BlackJack(dealer, participants);

        Participant one = participants.getValue().get(0);
        one.receiveCard(new Card(Shape.HEARTS, Rank.TWO));
        one.receiveCard(new Card(Shape.HEARTS, Rank.THREE));

        Participant two = participants.getValue().get(1);
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
        Map<Participant, WinStatus> resultByParticipant = blackJack.makeResult();

        Assertions.assertAll(
                () -> assertThat(resultByParticipant).containsEntry(one, WinStatus.WIN),
                () -> assertThat(resultByParticipant).containsEntry(two, WinStatus.WIN)
        );
    }
}
