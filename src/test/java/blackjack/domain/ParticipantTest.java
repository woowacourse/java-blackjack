package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ParticipantTest {

    @DisplayName("참가자는 카드를 받으면 가지고 있는 카드의 개수가 1증가한다.")
    @Test
    void increaseNumberOfCardWhenGivenCard() {
        // given
        Participant participant = new Participant();
        Card card = new Card(Rank.ACE, Suit.DIAMOND);

        // when
        participant.receiveCard(card);

        // then
        assertThat(participant.getCards().size()).isEqualTo(1);
    }


}
