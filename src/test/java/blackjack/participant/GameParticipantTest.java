package blackjack.participant;

import blackjack.card.CardFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameParticipantTest {

    @Test
    @DisplayName("카드를 한 장씩 뽑을 수 있다")
    void canDrawCard() {
        // given
        GameParticipant player = GameParticipantFixture.createPlayer("강산");
        int initialCardCount = player.getHand().size();

        // when
        player.drawCard(CardFixture.createCard());

        // then
        assertThat(player.getHand().size() - initialCardCount).isEqualTo(1);
    }
}
