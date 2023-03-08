package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ResultOfGameTest {

    private static final Map<String, ResultType> playerResult = Map.of("완태", ResultType.WIN, "ire", ResultType.WIN, "nono", ResultType.LOSE);
    private static final Map<ResultType, Integer> dealerResult = Map.of(ResultType.WIN, 1, ResultType.LOSE, 2);
    private static ResultOfGame result;

    @BeforeAll
    static void setParticipantResults() {
        result = new ResultOfGame(playerResult, dealerResult);
    }

    @ParameterizedTest
    @CsvSource(value = {"완태,WIN", "ire,WIN", "nono,LOSE"})
    void 플레이어의_결과가_저장한대로_나옵니다(final String playerName, final ResultType resultType) {
        assertThat(result.getPlayerResult()).containsEntry(playerName, resultType);
    }

    @ParameterizedTest
    @CsvSource(value = {"WIN,1", "LOSE,2"})
    void 딜러의_결과가_저장한대로_나옵니다(final ResultType resultType, final int resultTypeScore) {
        assertThat(result.getDealerResult()).containsEntry(resultType, resultTypeScore);
    }

}
