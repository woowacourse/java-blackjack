package domain.player;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlayerRoleTest {

    @ParameterizedTest(name = "isDealer()는 플레이어 역할에 따라 딜러인지 유무를 반환한다")
    @CsvSource(value = {"NORMAL:false", "DEALER:true"}, delimiter = ':')
    void isDealer_givenPlayerRole_thenReturnIsDealer(final PlayerRole role, final boolean expected) {
        // when
        boolean actual = role.isDealer();

        // then
        assertThat(actual).isSameAs(expected);
    }
}
