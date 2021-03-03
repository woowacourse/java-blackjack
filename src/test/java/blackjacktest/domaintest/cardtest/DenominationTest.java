package blackjacktest.domaintest.cardtest;

import blackjack.domain.card.Denomination;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DenominationTest {

    @Test
    @DisplayName("카드 숫자 생성 성공")
    void createDenomination() {
        Denomination denomination = Denomination.valueOf("ACE");
        assertThat(denomination).isNotNull();
    }
}
