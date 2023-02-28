package domain;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    @DisplayName("이름이 딜러인 딜러를 생성한다.")
    void createDealer() {
        Dealer dealer = new Dealer();
        assertThat(dealer.getName()).isEqualTo("딜러");
    }
}
