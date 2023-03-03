package blackjack.domain.participant;

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
        final List<Participant> participants = List.of(
                new Dealer(),
                new Player("dazzle"),
                new Player("dazzle"),
                new Player("kokodak"));

        assertThatThrownBy(() -> new Participants(participants))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 딜러_포함_참여자가_6명_초과라면_예외를_던진다() {
        final List<Participant> participants = List.of(
                new Dealer(),
                new Player("dazzle"),
                new Player("kokodak"),
                new Player("odo"),
                new Player("hoi"),
                new Player("gray"),
                new Player("pobi")
        );

        assertThatThrownBy(() -> new Participants(participants))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
