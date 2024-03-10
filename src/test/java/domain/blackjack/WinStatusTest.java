package domain.blackjack;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WinStatusTest {

    @DisplayName("참가자와 딜러 중 점수가 높은 사람이 승리한다.")
    @Test
    void isWinner() {
        Dealer dealer = new Dealer();

        Participant participant = new Participant(new Name("one"));
        participant.receiveCard(new Card(Shape.HEART, Rank.KING));
        participant.receiveCard(new Card(Shape.HEART, Rank.ACE));

        dealer.receiveCard(new Card(Shape.HEART, Rank.QUEEN));

        WinStatus winStatus = WinStatus.of(participant, dealer);
        assertThat(winStatus).isEqualTo(WinStatus.WIN);
    }

    @DisplayName("참가자와 딜러의 점수가 같은 경우 카드의 장수가 더 적은 사람이 승리한다.")
    @Test
    void isWinnerWhenSameScore() {
        Dealer dealer = new Dealer();

        Participant participant = new Participant(new Name("one"));
        participant.receiveCard(new Card(Shape.HEART, Rank.KING));
        participant.receiveCard(new Card(Shape.HEART, Rank.THREE));
        participant.receiveCard(new Card(Shape.HEART, Rank.EIGHT));

        dealer.receiveCard(new Card(Shape.HEART, Rank.QUEEN));
        dealer.receiveCard(new Card(Shape.HEART, Rank.ACE));

        WinStatus winStatus = WinStatus.of(participant, dealer);
        assertThat(winStatus).isEqualTo(WinStatus.LOSE);
    }
}
