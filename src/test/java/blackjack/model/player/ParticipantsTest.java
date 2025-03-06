package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ParticipantsTest {

    @Test
    void 참여자가_최소_2명_최대_8명이_아닌_경우_예외가_발생한다() {
        // given

        // when & then
        assertThatThrownBy(() -> new Participants(List.of(
                new Participant("한스1"),
                new Participant("한스2"),
                new Participant("한스3"),
                new Participant("한스4"),
                new Participant("한스5"),
                new Participant("한스6"),
                new Participant("한스7"),
                new Participant("한스8"),
                new Participant("한스9")
        ))).hasMessage("참여자는 2~8명 이여야 합니다.");
    }

    @Test
    void 중복된_이름이_있으면_예외가_발생한다() {
        // when & then
        assertThatThrownBy(() -> new Participants(List.of(
                new Participant("프리"),
                new Participant("프리")
        ))).hasMessage("중복된 이름은 사용할 수 없습니다.");
    }
}
