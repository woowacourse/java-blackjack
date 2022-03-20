package blackjack;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardSymbol.*;
import static camp.nextstep.edu.missionutils.test.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import camp.nextstep.edu.missionutils.test.NsTest;

@SuppressWarnings("unchecked")
public class ApplicationInputTest extends NsTest {

    @Test
    @DisplayName("이름에 내용 없는 문자열이 들어오면 예외를 던진다.")
    void emptyName() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("공백은 허용되지 않습니다.")
        );
    }

    @Test
    @DisplayName("이름이 제한된 길이를 초과하면 예외를 던진다.")
    void nameLength() {
        String input = "1234567890".repeat(10)
            + "1";
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("길이는 100자를 초과할 수 없습니다.")
        );
    }

    @Test
    @DisplayName("배팅 금액에 0이 들어오면 예외 발생")
    void validateBettingMoney_0() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi, jason", "0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("양수")
        );
    }

    @Test
    @DisplayName("배팅 금액에 음수 값이 들어오면 예외 발생")
    void validateBettingMoney_negative() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi, jason", "-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("양수")
        );
    }

    @Test
    @DisplayName("배팅 금액에 숫자 아닌 값이 들어오면 예외 발생")
    void validateBettingMoney_notNumber() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi, jason", "a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("정수")
        );
    }

    @Test
    @DisplayName("HitorStay에 y 이외의 값을 넣으면 예외를 던진다.")
    void hitOrStay() {
        assertShuffleTest(() ->
                assertThatThrownBy(() -> runException("pobi", "100", "c"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("y, n 이외"),
            List.of(new Card(HEART, TWO),
                new Card(HEART, KING), new Card(HEART, SIX),
                new Card(SPADE, SEVEN), new Card(HEART, TEN))
        );
    }

    @Override
    protected void runMain() {
        Application.main(new String[] {});
    }
}
