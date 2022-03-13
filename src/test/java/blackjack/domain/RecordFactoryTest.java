package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RecordFactoryTest {

    @ParameterizedTest
    @CsvSource(value = {"18:LOSS", "19:PUSH", "20:WIN", "22:LOSS"}, delimiter = ':')
    @DisplayName("딜러가 버스트 하지 않은 경우 플레이어의 승패 여부를 반환한다.")
    void getRecord_dealerNotBust(int playerScore, Record expected) {
        // give
        final int dealerScore = 19;

        // when
        Record actual = Record.of(dealerScore, playerScore);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"20:WIN", "22:LOSS"}, delimiter = ':')
    @DisplayName("딜러가 버스트한 경우 플레이어의 승패 여부를 반환한다.")
    void getRecord_dealerBust(int playerScore, Record expected) {
        // give
        final int dealerScore = 22;

        // when
        Record actual = Record.of(dealerScore, playerScore);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"19:18:WIN", "19:20:LOSS", "19:22:WIN", "19:19:PUSH",
            "22:18:LOSS", "22:20:LOSS", "22:22:WIN"}, delimiter = ':')
    @DisplayName("딜러의 전적을 반환한다.")
    void getDealerRecord(int dealerScore, int playerScore, Record expected) {
        // give
        final RecordFactory factory = new RecordFactory(dealerScore, new ArrayList<>());

        factory.getPlayerRecord(playerScore);

        // when
        final Map<Record, Integer> record = factory.getDealerRecord();
        final int actual = record.get(expected);

        // then
        assertThat(actual).isEqualTo(1);
    }
}
