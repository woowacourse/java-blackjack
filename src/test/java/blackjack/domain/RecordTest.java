package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RecordTest {

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
    @DisplayName("플레이어 Record의 이름이 주어지면 딜러의 Record를 반환한다.")
    @CsvSource(value = {"승:LOSS", "무:PUSH", "패:WIN"}, delimiter = ':')
    void fromOpposite(String playerRecordName, Record dealerRecord) {
        // when
        final Record actual = Record.fromOppositeName(playerRecordName);

        // then
        assertThat(actual).isEqualTo(dealerRecord);
    }
}