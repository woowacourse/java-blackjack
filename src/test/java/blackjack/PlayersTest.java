package blackjack;

import blackjack.domain.Players;
import blackjack.utils.FixedCardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {
    @Test
    void create() {
        assertThatCode(() -> new Players("boni,gram,kilo", new FixedCardDeck())).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패 - , 구분자 아닌경우")
    void create1() {
        assertThatThrownBy(() -> new Players("boni. gram, kilo", new FixedCardDeck())).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void create3() {
        assertThatCode(() -> new Players("Boni,Gram,Kilo", new FixedCardDeck())).doesNotThrowAnyException();
    }

    @Test
    void create4() {
        assertThatCode(() -> new Players("Boni, Gram,Kilo", new FixedCardDeck())).doesNotThrowAnyException();
    }

    @Test
    void create6() {
        assertThatThrownBy(() -> new Players("boni1,gram2,kilo3", new FixedCardDeck())).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void create7() {
        assertThatThrownBy(() -> new Players("b oni,gr am,ki lo", new FixedCardDeck())).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("빈 문자열일 때 예외 발생 확인")
    void create8() {
        assertThatThrownBy(() -> new Players("", new FixedCardDeck())).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("null일 때 예외 발생 확인")
    void create9() {
        assertThatThrownBy(() -> new Players(null, new FixedCardDeck())).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("한글일 때 예외 발생 확인")
    void create10() {
        assertThatThrownBy(() -> new Players("한글,boni,gram", new FixedCardDeck())).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("숫자일 때 예외 발생 확인")
    void create11() {
        assertThatThrownBy(() -> new Players("111,boni,gram", new FixedCardDeck())).isInstanceOf(IllegalArgumentException.class);
    }
}
