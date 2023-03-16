package blackjack.dto;

import blackjack.domain.user.Dealer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

class DealerDtoTest {

    @Test
    void 딜러_DTO는_이름_점수_카드정보를_가진다() {
        String dealerName = "딜러";

        Dealer dealer = new Dealer(dealerName);
        DealerDto dealerDto = new DealerDto(dealer);

        assertAll(
                () -> Assertions.assertThat(dealerDto.getCards()).isEmpty(),
                () -> Assertions.assertThat(dealerDto.getName()).isEqualTo(dealerName),
                () -> Assertions.assertThat(dealerDto.getName()).isEqualTo(dealerName)
        );
    }
}
