package player;

import card.Deck;
import card.DeckGenerator;
import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @Test
    void 이름이_같다면_같은_참여자다() {
        // given
        final String targetName = "훌라";
        Participant participant = new Participant(targetName);

        // when & then
        Assertions.assertThat(participant)
                .isEqualTo(new Participant(targetName));
    }


    @Test
    void 참여자는_초기에_카드를_2장씩_받는다() {
        // given
        Participant participant = new Participant("훌라");
        Deck deck = DeckGenerator.generateDeck();

        // when
        participant.receiveInitialCards(deck);

        // then
        Assertions.assertThat(participant.getHandCards().size())
                .isEqualTo(2);
    }
}
