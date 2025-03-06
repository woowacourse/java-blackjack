package blackjack.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ParticipantsTest {

    @Test
    void 참여자가_최소_2명_최대_8명이_아닌_경우_예외가_발생한다() {
        // given

        // when & then
        assertThatThrownBy(() -> new Participants(List.of(
                new Participant("한스"),
                new Participant("한스"),
                new Participant("한스"),
                new Participant("한스"),
                new Participant("한스"),
                new Participant("한스"),
                new Participant("한스"),
                new Participant("한스"),
                new Participant("한스")
        )));
    }
}
