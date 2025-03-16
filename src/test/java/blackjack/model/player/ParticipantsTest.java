package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import blackjack.model.game.BettedMoney;
import org.junit.jupiter.api.Test;

public class ParticipantsTest {

    @Test
    void 참가자가_최소_2명_최대_8명이_아닌_경우_예외가_발생한다() {
        // given

        // when & then
        assertThatThrownBy(() -> new Participants(List.of(
                new Participant(new PlayerName("한스1"), new BettedMoney(10_000)),
                new Participant(new PlayerName("한스2"), new BettedMoney(10_000)),
                new Participant(new PlayerName("한스3"), new BettedMoney(10_000)),
                new Participant(new PlayerName("한스4"), new BettedMoney(10_000)),
                new Participant(new PlayerName("한스5"), new BettedMoney(10_000)),
                new Participant(new PlayerName("한스6"), new BettedMoney(10_000)),
                new Participant(new PlayerName("한스7"), new BettedMoney(10_000)),
                new Participant(new PlayerName("한스8"), new BettedMoney(10_000)),
                new Participant(new PlayerName("한스9"), new BettedMoney(10_000))
        ))).hasMessage("참가자는 2~8명 이여야 합니다.");
    }

    @Test
    void 중복된_이름이_있으면_예외가_발생한다() {
        // when & then
        assertThatThrownBy(() -> new Participants(List.of(
                new Participant(new PlayerName("프리"), new BettedMoney(10_000)),
                new Participant(new PlayerName("프리"), new BettedMoney(10_000))
        ))).hasMessage("중복된 이름은 사용할 수 없습니다.");
    }

    @Test
    void 블랙잭에_참가한_모든_참가자들의_이름을_가져온다() {
        // Given
        Participants participants = new Participants(List.of(
                new Participant(new PlayerName("프리"), new BettedMoney(10_000)),
                new Participant(new PlayerName("포비"), new BettedMoney(10_000)),
                new Participant(new PlayerName("제이슨"), new BettedMoney(10_000))
        ));

        // When
        List<String> participantNames = participants.getAllNames();

        // Then
        assertThat(participantNames).isEqualTo(List.of("프리", "포비", "제이슨"));
    }
}
