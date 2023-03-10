package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserInformationTest {

    @DisplayName("User의 이름을 반환한다.")
    @Test
    void 이름_반환() {
        String nameInput = "test";
        UserInformation userInformation = UserInformation.from(new PlayerName(nameInput), 1_000);
        assertThat(userInformation.getNameValue()).isEqualTo(nameInput);
    }

    @DisplayName("User의 베팅 금액을 반환한다.")
    @Test
    void 베팅_금액_반환() {
        int bettingAmountValue = 7_000;
        UserInformation userInformation = UserInformation.from(new PlayerName("test"), bettingAmountValue);
        assertThat(userInformation.getBettingAmountValue()).isEqualTo(bettingAmountValue);
    }
}