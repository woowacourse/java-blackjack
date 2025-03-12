package blackjack.domain;

import java.util.List;
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
}
