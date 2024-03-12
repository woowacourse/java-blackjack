package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardDeck;
import fixture.ParticipantsFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @DisplayName("모든 플레이어와 딜러는 두 장의 카드를 지급 받는다.")
    @Test
    void testDeal() {
        // given
        Participants participants = ParticipantsFixture.create();
        CardDeck cardDeck = CardDeck.createShuffledFullCardDeck();

        // when
        participants.deal(cardDeck);

        // then
        participants.getParticipants().forEach(participant ->
                assertThat(participant.getHand().getCards()).hasSize(2)
        );
    }
}
