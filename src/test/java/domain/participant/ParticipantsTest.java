package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.fixture.BlackjackDeckTestFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParticipantsTest {

    @Test
    void 플레이어의_이름이_딜러의_이름과_같으면_예외가_발생한다() {
        // given
        List<String> names = List.of("딜러");
        List<Integer> betAmounts = List.of(1_000);

        // when & then
        assertThatThrownBy(() -> new Participants(names, betAmounts, BlackjackDeckTestFixture.createRandomDeck()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 딜러일 수 없습니다.");
    }
}
