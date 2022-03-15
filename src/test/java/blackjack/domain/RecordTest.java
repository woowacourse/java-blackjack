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

    @ParameterizedTest
    @DisplayName("isWin 테스트")
    @CsvSource(value = {"19:18:false", "19:19:false", "19:20:true", "19:22:false", "22:21:true",
            "22:22:false"}, delimiter = ':')
    void isWin(int dealerScore, int playerScore, boolean expected) {
        // when
        final boolean actual = Record.isWin(dealerScore, playerScore);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("isPush() 테스트")
    @CsvSource(value = {"19:18:false", "19:19:true", "19:20:false", "19:22:false", "22:21:false",
            "22:22:false"}, delimiter = ':')
    void isPush(int dealerScore, int playerScore, boolean expected) {
        // when
        final boolean actual = Record.isPush(dealerScore, playerScore);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("isLoss()() 테스트")
    @CsvSource(value = {"19:18:true", "19:19:false", "19:20:false", "19:22:true", "22:21:false",
            "22:22:true"}, delimiter = ':')
    void isLoss(int dealerScore, int playerScore, boolean expected) {
        // when
        final boolean actual = Record.isLoss(dealerScore, playerScore);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}