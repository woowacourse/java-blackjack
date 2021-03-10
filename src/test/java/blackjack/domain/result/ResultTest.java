package blackjack.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
    @DisplayName("결과를 뒤집는다. (ex. 승을 패로, 패를 승으로)")
    @Test
    public void reverse() {
        Result result = Result.WIN;

        Result reverseResult = result.reverse(result);

        assertThat(reverseResult).isEqualTo(Result.LOSE);
    }
}
