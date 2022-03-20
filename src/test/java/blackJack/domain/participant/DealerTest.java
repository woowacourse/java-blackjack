package blackJack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("Dealer 생성 테스트")
    void createDealer() {
        assertThat(new Dealer()).isNotNull();
    }
}
