package domain;

import static domain.fixture.MoneyFixture.만원;
import static domain.fixture.MoneyFixture.이만원;
import static domain.fixture.PlayerFixture.빙봉;
import static domain.fixture.PlayerFixture.우가;
import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/11
 */
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BankTest {

    @Test
    void 은행은_플레이어와_돈을_가진다() {
        assertDoesNotThrow(
                () -> new Bank(of(우가, 빙봉), of(만원, 이만원))
        );
    }

    @Test
    void 은행은_플레이어와_돈_갯수가_맞지_않으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Bank(of(우가, 빙봉), of(만원)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어와 돈 갯수가 맞지 않습니다.");
    }

    @Test
    void 은행은_해당_플레이어의_돈을_반환한다() {
        Bank bank = new Bank(of(우가, 빙봉), of(만원, 이만원));

        assertThat(bank.withdrawOfPlayer(우가)).isEqualTo(만원);
    }

    @Test
    void 은행은_해당_플레이어의_돈을_반환한다_없는_플레이어인경우_예외가_발생한다() {
        Bank bank = new Bank(of(우가), of(만원));

        assertThatThrownBy(() -> bank.withdrawOfPlayer(빙봉))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어를 확인해주세요.");
    }

    @Test
    void 은행은_수익률을_곱한_돈을_해당_플레이어에_저장한다() {
        Bank bank = new Bank(of(우가, 빙봉), of(만원, 이만원));

        double profit = 1.5;

        assertDoesNotThrow(
                () -> bank.multiplyInterestOfPlayer(우가, profit)
        );
    }

}
