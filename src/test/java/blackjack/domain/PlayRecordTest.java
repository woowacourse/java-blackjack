package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PlayRecordTest {

    @ParameterizedTest
    @CsvSource(value = {"WIN:LOSS", "LOSS:WIN", "PUSH:PUSH"}, delimiter = ':')
    @DisplayName("반대 전적을 반환한다.")
    void getOpposite(PlayRecord record, PlayRecord expected) {
        assertThat(record.getOpposite()).isEqualTo(expected);
    }
}
