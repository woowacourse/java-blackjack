package blackjack;

import blackjack.domain.Players;
import blackjack.utils.FixedCardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {
    @Test
    @DisplayName("정상 - 소문자로 공백없이 구")
    void create1() {
        assertThatCode(() -> new Players("boni,gram,kilo", new FixedCardDeck())).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패 - , 구분자 아닌 경우")
    void create2() {
        assertThatThrownBy(() -> new Players("boni. gram, kilo", new FixedCardDeck())).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("정상 - 대문자 포함")
    void create3() {
        assertThatCode(() -> new Players("Boni,Gram,Kilo", new FixedCardDeck())).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("정상 - 대문자 포함 문자~구분자사이 공백 포함")
    void create4() {
        assertThatCode(() -> new Players("Boni, Gram,Kilo", new FixedCardDeck())).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패 - 이름에 숫자 포함")
    void create5() {
        assertThatThrownBy(() -> new Players("boni1,gram2,kilo3", new FixedCardDeck())).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("실패 - 이름에 공백이 포함")
    void create6() {
        assertThatThrownBy(() -> new Players("b oni,gr am,ki lo", new FixedCardDeck())).isInstanceOf(IllegalArgumentException.class);
    }
}
