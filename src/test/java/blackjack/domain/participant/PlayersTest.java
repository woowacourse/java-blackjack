package blackjack.domain.participant;

import blackjack.utils.FixedCardDeck;
import blackjack.utils.IllegalNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {
    @Test
    @DisplayName("정상적인 입력이 생성되는지 확인")
    void create1() {
        assertThatCode(() -> new Players("boni,gram,kilo", new FixedCardDeck()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("콤마가 아닌 다른 구분자를 사용하면 예외 발생 확인")
    void create2() {
        assertThatThrownBy(() -> new Players("boni. gram, kilo", new FixedCardDeck()))
                .isInstanceOf(IllegalNameException.class)
                .hasMessage("이름은 알파벳 대소문자로 이루어져야 합니다.");
    }

    @Test
    @DisplayName("영어 대문자가 포함되어도 생성되는지 확인")
    void create3() {
        assertThatCode(() -> new Players("Boni,Gram,Kilo", new FixedCardDeck()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("이름과 이름사이 구분자외에 공백이 있어도 생성되는지 확인")
    void create4() {
        assertThatCode(() -> new Players("Boni, Gram,Kilo", new FixedCardDeck()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("이름 내에 숫자가 포함 되어 있으면 예외 발생 확인")
    void create5() {
        assertThatThrownBy(() -> new Players("boni1,gram2,kilo3", new FixedCardDeck()))
                .isInstanceOf(IllegalNameException.class)
                .hasMessage("이름은 알파벳 대소문자로 이루어져야 합니다.");
    }

    @Test
    @DisplayName("이름 내에 공백이 있을 때 예외 발생 확인")
    void create6() {
        assertThatThrownBy(() -> new Players("b oni,gr am,ki lo", new FixedCardDeck()))
                .isInstanceOf(IllegalNameException.class)
                .hasMessage("이름은 알파벳 대소문자로 이루어져야 합니다.");
    }

    @Test
    @DisplayName("빈 문자열일 때 예외 발생 확인")
    void create7() {
        assertThatThrownBy(() -> new Players("", new FixedCardDeck()))
                .isInstanceOf(IllegalNameException.class)
                .hasMessage("입력이 null이거나 빈 문자열일 수 없습니다.");
    }

    @Test
    @DisplayName("null일 때 예외 발생 확인")
    void create8() {
        assertThatThrownBy(() -> new Players(null, new FixedCardDeck()))
                .isInstanceOf(IllegalNameException.class)
                .hasMessage("입력이 null이거나 빈 문자열일 수 없습니다.");
    }

    @Test
    @DisplayName("한글일 때 예외 발생 확인")
    void create9() {
        assertThatThrownBy(() -> new Players("한글,boni,gram", new FixedCardDeck()))
                .isInstanceOf(IllegalNameException.class)
                .hasMessage("이름은 알파벳 대소문자로 이루어져야 합니다.");
    }

    @Test
    @DisplayName("숫자일 때 예외 발생 확인")
    void create10() {
        assertThatThrownBy(() -> new Players("111,boni,gram", new FixedCardDeck()))
                .isInstanceOf(IllegalNameException.class)
                .hasMessage("이름은 알파벳 대소문자로 이루어져야 합니다.");
    }

    @Test
    @DisplayName("중복된 이름일 때 예외 발생 확인")
    void create11() {
        assertThatThrownBy(() -> new Players("boni,boni,gram", new FixedCardDeck()))
                .isInstanceOf(IllegalNameException.class)
                .hasMessage("중복된 이름을 사용할 수 없습니다.");
    }
}
