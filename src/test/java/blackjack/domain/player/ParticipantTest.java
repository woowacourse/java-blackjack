package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import fixture.CardFixture;
import fixture.HandFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("참가자 테스트")
class ParticipantTest {

    @DisplayName("플레이어 핸드에 카드를 추가할 수 있다")
    @Test
    void testAppendCardToPlayer() {
        PlayerName playerName = new PlayerName("pobi");
        Hand hand = HandFixture.of(10, 9);
        Participant participant = new Participant(playerName, hand);

        Card card = CardFixture.from(2);
        participant.appendCard(card);

        assertThat(hand.calculateCardSummation()).isEqualTo(21);
    }
}
