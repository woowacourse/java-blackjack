package blackjack.domain.result;

import blackjack.domain.participant.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;

import static blackjack.domain.card.Card.NULL_ERR_MSG;
import static blackjack.domain.result.DealerResult.EMPTY_ERR_MSG;
import static blackjack.domain.result.ResultType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DealerResultTest {

    @DisplayName("예외 테스트: 생성자에 null 혹은 empty 값 전달된 경우 Exception 발생")
    @Test
    void test1() {
        assertThatThrownBy(() -> new DealerResult(null, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(NULL_ERR_MSG);

        assertThatThrownBy(() -> new DealerResult(new Name("쪼밀리"), null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(NULL_ERR_MSG);

        assertThatThrownBy(() -> new DealerResult(new Name("쪼밀리"), Collections.emptyMap()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(EMPTY_ERR_MSG);
    }

    @DisplayName("딜러 게임 결과 확인")
    @Test
    void test2() {
        HashMap<ResultType, Integer> result = new HashMap<>();
        result.put(WIN, 3);
        result.put(DRAW, 0);
        result.put(LOSE, 1);

        DealerResult dealerResult = new DealerResult(new Name("딜러"), result);

        String actualRecord = dealerResult.showDealerRecord();

        String expectedRecord = "3승 1패 0무";

        assertThat(actualRecord).isEqualTo(expectedRecord);
    }
}
