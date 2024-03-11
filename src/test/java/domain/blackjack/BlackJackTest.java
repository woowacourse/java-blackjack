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
        two.receiveCard(new Card(Shape.DIA, Rank.KING));
        two.receiveCard(new Card(Shape.DIA, Rank.FOUR));

        dealer.receiveCard(new Card(Shape.DIA, Rank.QUEEN));
        dealer.receiveCard(new Card(Shape.DIA, Rank.ACE));
        /*
         * one 참가자의 점수: 10점
         * two 참가자의 점수: 14점
         * 딜러의 점수: 21점인 상황
         */
        BlackJackResult blackJackResult = blackJack.saveParticipantResult();
        assertThat(blackJackResult.getDealerWinCount()).isEqualTo(2);
    }

    @DisplayName("참가자와 딜러의 점수가 같은경우 ")
    @Test
    void isWinnerWhenSameScore() {
        Dealer dealer = new Dealer();
        Participants participants = new Participants(List.of("one", "two"));
        BlackJack blackJack = new BlackJack(dealer, participants);

        Participant one = participants.getValue().get(0);
        one.receiveCard(new Card(Shape.HEART, Rank.KING));
        one.receiveCard(new Card(Shape.HEART, Rank.TWO));
        one.receiveCard(new Card(Shape.HEART, Rank.EIGHT));
        one.receiveCard(new Card(Shape.HEART, Rank.ACE));

        Participant two = participants.getValue().get(1);
        two.receiveCard(new Card(Shape.CLOVER, Rank.KING));
        two.receiveCard(new Card(Shape.CLOVER, Rank.ACE));

        dealer.receiveCard(new Card(Shape.DIA, Rank.QUEEN));
        dealer.receiveCard(new Card(Shape.DIA, Rank.KING));
        dealer.receiveCard(new Card(Shape.DIA, Rank.ACE));
        /*
         * one 참가자의 점수: 21점, 카드 4장
         * two 참가자의 점수: 21점, 카드 2장
         * 딜러의 점수: 21점인, 카드 3장인 상황
         */
        BlackJackResult blackJackResult = blackJack.saveParticipantResult();
        Assertions.assertAll(
                () -> assertThat(blackJackResult.getTotalCount()).isEqualTo(2),
                () -> assertThat(blackJackResult.getDealerWinCount()).isEqualTo(1)
        );
    }
}
