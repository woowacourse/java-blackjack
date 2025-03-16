package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.fixture.BlackjackDeckTestFixture;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParticipantsTest {

    @Test
    void 플레이어의_이름이_딜러의_이름과_같으면_예외가_발생한다() {
        // given
        List<ParticipantName> names = List.of(new ParticipantName("딜러"));
        Map<ParticipantName, Bet> playerBets = new HashMap<>();
        names.forEach(playerName -> playerBets.put(playerName, new Bet(10_000)));

        // when & then
        assertThatThrownBy(() -> new Participants(names, playerBets, BlackjackDeckTestFixture.createRandomDeck()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 딜러일 수 없습니다.");
    }
}
