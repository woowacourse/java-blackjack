package domain.blackjack;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackTest {

    @DisplayName("카드를 두장씩 나눠준다.")
    @Test
    void beginDealing() {
        Dealer dealer = new Dealer();
        Participants participants = new Participants(List.of("one", "two"));
        BlackJack blackJack = new BlackJack(dealer, participants);
        blackJack.beginDealing();

        Assertions.assertAll(
                () -> assertThat(dealer.getCardCount()).isEqualTo(2),
                () -> assertThat(participants.getValue().get(0).getCardCount()).isEqualTo(2),
                () -> assertThat(participants.getValue().get(1).getCardCount()).isEqualTo(2)
        );
    }

    @DisplayName("참가자의 승패를 결정한다.")
    @Test
    void isWinner() {
        Dealer dealer = new Dealer();
        Participants participants = new Participants(List.of("one", "two"));
        BlackJack blackJack = new BlackJack(dealer, participants);

        Participant one = participants.getValue().get(0);
        one.receiveCard(new Card(Shape.HEART, Rank.KING));

        Participant two = participants.getValue().get(1);
        two.receiveCard(new Card(Shape.DIA, Rank.TWO));
        two.receiveCard(new Card(Shape.DIA, Rank.THREE));

        dealer.receiveCard(new Card(Shape.DIA, Rank.THREE));
        dealer.receiveCard(new Card(Shape.DIA, Rank.FOUR));
        /*
         * one 참가자의 점수: 10점
         * two 참가자의 점수: 5점
         * 딜러의 점수: 7점인 상황
         */
        BlackJackResult blackJackResult = blackJack.saveParticipantResult();
        LinkedHashMap<Participant, WinStatus> resultByParticipant = blackJackResult.getResultByParticipant();

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
        one.receiveCard(new Card(Shape.HEART, Rank.TWO));
        one.receiveCard(new Card(Shape.HEART, Rank.THREE));


        Participant two = participants.getValue().get(1);
        two.receiveCard(new Card(Shape.CLOVER, Rank.TWO));
        two.receiveCard(new Card(Shape.CLOVER, Rank.THREE));

        dealer.receiveCard(new Card(Shape.DIA, Rank.TWO));
        dealer.receiveCard(new Card(Shape.DIA, Rank.THREE));

        /*
         * one 참가자의 점수: 3점
         * two 참가자의 점수: 3점
         * 딜러의 점수: 3점인
         */
        BlackJackResult blackJackResult = blackJack.saveParticipantResult();
        LinkedHashMap<Participant, WinStatus> resultByParticipant = blackJackResult.getResultByParticipant();

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
        one.receiveCard(new Card(Shape.HEART, Rank.KING));
        one.receiveCard(new Card(Shape.HEART, Rank.ACE));

        Participant two = participants.getValue().get(1);
        two.receiveCard(new Card(Shape.CLOVER, Rank.ACE));
        two.receiveCard(new Card(Shape.CLOVER, Rank.TWO));

        dealer.receiveCard(new Card(Shape.DIA, Rank.QUEEN));
        /*
         * one 참가자의 점수: 블랙잭
         * two 참가자의 점수: 13점
         * 딜러의 점수: 10점인
         */
        BlackJackResult blackJackResult = blackJack.saveParticipantResult();
        LinkedHashMap<Participant, WinStatus> resultByParticipant = blackJackResult.getResultByParticipant();

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
        one.receiveCard(new Card(Shape.HEART, Rank.KING));
        one.receiveCard(new Card(Shape.HEART, Rank.ACE));

        Participant two = participants.getValue().get(1);
        two.receiveCard(new Card(Shape.CLOVER, Rank.ACE));
        two.receiveCard(new Card(Shape.CLOVER, Rank.TWO));

        dealer.receiveCard(new Card(Shape.CLOVER, Rank.KING));
        dealer.receiveCard(new Card(Shape.CLOVER, Rank.ACE));
        /*
         * one 참가자의 점수: 블랙잭
         * two 참가자의 점수: 13점
         * 딜러의 점수: 블랙잭
         */
        BlackJackResult blackJackResult = blackJack.saveParticipantResult();
        LinkedHashMap<Participant, WinStatus> resultByParticipant = blackJackResult.getResultByParticipant();

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
        one.receiveCard(new Card(Shape.HEART, Rank.TWO));
        one.receiveCard(new Card(Shape.HEART, Rank.THREE));

        Participant two = participants.getValue().get(1);
        two.receiveCard(new Card(Shape.CLOVER, Rank.TWO));
        two.receiveCard(new Card(Shape.CLOVER, Rank.THREE));

        dealer.receiveCard(new Card(Shape.CLOVER, Rank.KING));
        dealer.receiveCard(new Card(Shape.CLOVER, Rank.QUEEN));
        dealer.receiveCard(new Card(Shape.CLOVER, Rank.JACK));
        /*
         * one 참가자의 점수: 5점
         * two 참가자의 점수: 5점
         * 딜러의 점수: 버스트
         */
        BlackJackResult blackJackResult = blackJack.saveParticipantResult();
        LinkedHashMap<Participant, WinStatus> resultByParticipant = blackJackResult.getResultByParticipant();

        Assertions.assertAll(
                () -> assertThat(resultByParticipant).containsEntry(one, WinStatus.WIN),
                () -> assertThat(resultByParticipant).containsEntry(two, WinStatus.WIN)
        );
    }
}
