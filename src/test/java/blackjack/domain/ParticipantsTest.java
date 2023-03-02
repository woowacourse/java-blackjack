package blackjack.domain;

import static blackjack.domain.Number.ACE;
import static blackjack.domain.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
}

class Participants {

    private final List<Participant> participants;

    public Participants(final List<Participant> participants) {
        validate(participants);
        this.participants = List.copyOf(participants);
    }

    private void validate(final List<Participant> participants) {
        final Set<Participant> uniqueParticipants = new HashSet<>(participants);

        if (uniqueParticipants.size() != participants.size()) {
            throw new IllegalArgumentException("참가자 이름은 중복될 수 없습니다.");
        }
    }

    public List<Participant> getParticipants() {
        return participants;
    }
}
