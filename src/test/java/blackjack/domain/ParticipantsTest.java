package blackjack.domain;

import blackjack.domain.game.Dealer;
import blackjack.domain.game.Hand;
import blackjack.domain.game.Participant;
import blackjack.domain.game.Participants;
import blackjack.domain.game.Player;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Test
    void 참여자가_7명을_초과하면_예외를_던진다() {
        // given
        List<Participant> participants = List.of(
                new Dealer(new Hand()),
                new Player("히로", new Hand()),
                new Player("히포", new Hand()),
                new Player("모루", new Hand()),
                new Player("서프", new Hand()),
                new Player("웨이드", new Hand()),
                new Player("줄리", new Hand()),
                new Player("새로이", new Hand())
        );

        // when & then
        assertThatThrownBy(() -> new Participants(participants))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 참가자의_이름을_가져올_수_있다() {
        // given
        List<String> names = List.of("히로", "히포");
        List<Participant> participantsToBeSaved = new java.util.ArrayList<>(names.stream()
                .map(name -> (Participant) new Player(name, new Hand()))
                .toList());

        participantsToBeSaved.add(new Dealer(new Hand()));
        Participants participants = new Participants(participantsToBeSaved);

        // when
        List<String> namesOfParticipants = participants.getNamesOfParticipants();

        // then
        assertThat(namesOfParticipants).containsAll(names);
    }

    @Test
    void 딜러를_찾을_수_있다() {
        // given
        List<String> names = List.of("히로", "히포");
        List<Participant> participantsToBeSaved = new java.util.ArrayList<>(names.stream()
                .map(name -> (Participant) new Player(name, new Hand()))
                .toList());

        Dealer dealer = new Dealer(new Hand());
        participantsToBeSaved.add(dealer);
        Participants participants = new Participants(participantsToBeSaved);

        // when
        Participant actual = participants.findDefender();

        // then
        assertThat(actual).isEqualTo(dealer);
    }
}
