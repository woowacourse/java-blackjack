package player;

import static org.assertj.core.api.Assertions.*;

import card.Deck;
import card.DeckGenerator;
import org.junit.jupiter.api.Test;

public class ParticipantTest {

    @Test
    void 참여자는_초기에_카드를_2장_공개한다() {
        // given
        Deck deck = DeckGenerator.generateDeck();
        Participant participant = new Participant("시소");
        participant.receiveInitialCards(deck);

        // when & then
        assertThat(participant.openInitialCards().size())
                .isEqualTo(2);
    }

    @Test
    void 참여자_이름은_2자_이상이어야_한다() {
        // given
        String name = "a";

        // when & then
        assertThatThrownBy(() -> new Participant(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 참여자_이름은_10자_이하여야_한다() {
        // given
        String name = "12345678901";

        // when & then
        assertThatThrownBy(() -> new Participant(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
