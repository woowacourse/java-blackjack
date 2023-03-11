package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.bank.BettingMoney;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/11
 */
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BettingMoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 99, -100})
    void 백원이하를_넘겨주면_예외가_발생한다(int money) {
        assertThatThrownBy(() -> new BettingMoney(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최소 베팅 금액은 100원 입니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {101, 1001})
    void 백원단위가_아닐경우_예외가_발생한다(int money) {
        assertThatThrownBy(() -> new BettingMoney(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("100원 단위로 입력가능합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {100_000_100, 1_000_000_000})
    void 베팅_금액은_일억을_넘길경우_예외가_발생한다(int money) {
        assertThatThrownBy(() -> new BettingMoney(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최대 베팅 금액은 1억원 입니다.");
    }

}
