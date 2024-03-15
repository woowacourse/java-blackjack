package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BetDetailsTest {

    @DisplayName("배팅내역을 생성한다")
    @Test
    public void create() {
        assertThatCode(() -> new BetDetails(Map.of(new Name("이상"), new Money(1000))))
                .doesNotThrowAnyException();
    }
}
