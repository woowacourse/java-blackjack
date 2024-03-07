import domain.*;
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

        Participant participant = participants.getValue().get(0);
        participant.receiveCard(new Card(Shape.HEART, Rank.KING));
        participant.receiveCard(new Card(Shape.HEART, Rank.ACE));

        dealer.receiveCard(new Card(Shape.HEART, Rank.QUEEN));

        boolean isWinner = blackJack.isWinner(participant);
        assertThat(isWinner).isTrue();
    }

    @DisplayName("참가자와 딜러의 점수가 같은경우 ")
    @Test
    void isWinnerWhenSameScore() {
        Dealer dealer = new Dealer();
        Participants participants = new Participants(List.of("one", "two"));
        BlackJack blackJack = new BlackJack(dealer, participants);

        Participant participant = participants.getValue().get(0);
        participant.receiveCard(new Card(Shape.HEART, Rank.KING));
        participant.receiveCard(new Card(Shape.HEART, Rank.THREE));
        participant.receiveCard(new Card(Shape.HEART, Rank.EIGHT));

        dealer.receiveCard(new Card(Shape.HEART, Rank.QUEEN));
        dealer.receiveCard(new Card(Shape.HEART, Rank.ACE));

        boolean isWinner = blackJack.isWinner(participant);
        assertThat(isWinner).isFalse();
    }
}
