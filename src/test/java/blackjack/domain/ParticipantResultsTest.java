package blackjack.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParticipantResultsTest {
    private static ParticipantResults participantResults;

    @BeforeAll
    static void setParticipantResults() {
        participantResults = new ParticipantResults();
        participantResults.winPlayer("디디");
        participantResults.losePlayer("pobi");
        participantResults.tiePlayer("완태");
    }

    @ParameterizedTest
    @CsvSource(value = {"디디,WIN", "완태,TIE", "pobi,LOSE"})
    void 주어진_대로_결과가_나옵니다(final String playerName, final ResultType resultType) {
        final Map<String, ResultType> result = participantResults.getPlayerNameToResultType();
        assertThat(result.get(playerName)).isEqualTo(resultType);
    }
}
