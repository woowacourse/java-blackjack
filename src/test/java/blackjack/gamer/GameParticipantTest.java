package blackjack.gamer;

import blackjack.domain.card.CardFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameParticipantTest {

    @Test
    @DisplayName("카드를 한 장씩 받을 수 있다")
    void canAddCard() {
        // given
        GameParticipant player = GameParticipantFixture.createPlayer("강산");
        int initialCardCount = player.getCards().size();

        // when
        player.addCard(CardFixture.createCard());

        // then
        assertThat(player.getCards().size() - initialCardCount).isEqualTo(1);
    }
}
