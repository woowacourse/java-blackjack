package blackjack.domain;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;

class ParticipantsTest {
    @Test
    void 참여자가_7명이_넘으면_예외를_던진다() {
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
    void 참가자들의_이름을_가져온다() {
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
    void defender를_찾는다() {
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
