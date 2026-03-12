package blackjack.domain.participant;

import blackjack.domain.betting.BettingAmount;
import blackjack.domain.card.Card;
import blackjack.domain.card.Figure;
import blackjack.domain.card.Number;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {
    @Test
    @DisplayName("카드가 2장이고, 점수가 21이면 블랙잭이다")
    void isBlackjackTest() {
        // given
        Participant participant = new Player("usher", new BettingAmount(15000));
        participant.receiveCard(new Card(Figure.DIAMOND, Number.ACE));
        participant.receiveCard(new Card(Figure.DIAMOND, Number.KING));

        // when & then
        Assertions.assertThat(participant.isBlackjack()).isTrue();
    }
}
