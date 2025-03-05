package domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ParticipantsTest {
    @Test
    void 참가자_인원이_5인_초과인_경우_예외가_발생한다() {
        // given
        List<Participant> crews = List.of(
                new Participant("시소"),
                new Participant("헤일러"),
                new Participant("부기"),
                new Participant("사나"),
                new Participant("수양"),
                new Participant("포스티")
        );
        Assertions.assertThatThrownBy(() -> new Participants(crews))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 플레이어에게_카드를_2장씩_나누어_줄_수_없으면_예외가_발생한다() {
        // given
        Participants participants = new Participants(
                List.of(
                        new Participant("시소"),
                        new Participant("헤일러"),
                        new Participant("부기"),
                        new Participant("사나")
                )
        );
        Deck deck = DeckGenerator.generateDeck();
        int count = 10;

        // when & then
        Assertions.assertThatThrownBy(() -> participants.distributeTwoCards(deck.drawCards(count)))
                .isInstanceOf(IllegalArgumentException.class);


    }
}