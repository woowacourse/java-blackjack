package blackjack.domain;

import static blackjack.domain.Number.ACE;
import static blackjack.domain.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParticipantsTest {

    @Test
    void 중복되는_이름이_존재하면_예외를_던진다() {
        final Cards cards = new Cards(List.of(
                new Card(ACE, SPADE)
        ));
        final List<Participant> participants = List.of(
                new Dealer(cards),
                new Player("dazzle", cards),
                new Player("dazzle", cards),
                new Player("kokodak", cards));

        assertThatThrownBy(() -> new Participants(participants))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 딜러_포함_참여자가_6명_초과라면_예외를_던진다() {
        final Cards cards = new Cards(List.of(
                new Card(ACE, SPADE)
        ));
        final List<Participant> participants = List.of(
                new Dealer(cards),
                new Player("dazzle", cards),
                new Player("kokodak", cards),
                new Player("odo", cards),
                new Player("hoi", cards),
                new Player("gray", cards),
                new Player("pobi", cards)
        );

        assertThatThrownBy(() -> new Participants(participants))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
