package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.card.CardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @DisplayName("이름이 공백이면 예외가 발생한다")
    @Test
    void construct_exception_blank() {
        assertThatThrownBy(() -> new Player(" ", 3000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 공백이거나 없을 수 없습니다.");
    }

    @DisplayName("이름이 null값 이면 예외가 발생한다")
    @Test
    void construct_exception_null() {
        assertThatThrownBy(() -> new Player(null, 3000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 공백이거나 없을 수 없습니다.");
    }

    @DisplayName("베팅 금액이 음수이면 예외가 발생한다.")
    @Test
    void construct_exception_minus() {
        assertThatThrownBy(() -> new Player("리버", -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 베팅 금액은 0보다 큰 금액이여야 합니다.");
    }

    @DisplayName("베팅 금액이 0이면 예외가 발생한다.")
    @Test
    void construct_exception_zero() {
        assertThatThrownBy(() -> new Player("리버", 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 베팅 금액은 0보다 큰 금액이여야 합니다.");
    }

    @DisplayName("카드를 추가로 분배 받을 때, y나 n 이 아닌 다른 문자가 입력되면 예외를 발생한다.")
    @Test
    void hit_exception() {
        CardDeck cardDeck = new CardDeck();
        Participant leaver = new Player("리버", 100);
        leaver.drawFrom(cardDeck);

        assertThatThrownBy(() -> leaver.hitFrom(cardDeck, (participantName) -> "rdt"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] y 또는 n만 입력해주세요");
    }
}
