package domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class DealerTest {
    @Test
    void 이름_반환_확인_테스트() {
        Dealer dealer = new Dealer();

        Assertions.assertThat(dealer.getName()).isEqualTo("딜러");
    }
}
