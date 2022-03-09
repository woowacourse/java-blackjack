package blackjack.domain.player;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import fuelinjection.Car;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {

    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    @DisplayName("이름이 유효하지 않으면 예외를 던진다.")
    void emptyName(String name) {
        // then
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("공백은 허용되지 않습니다.");
    }

    @Test
    @DisplayName("이름이 제한된 길이를 초과하면 예외를 던진다.")
    void nameLength() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("1234567890".repeat(10));
        stringBuilder.append("1");

        // then
        assertThatThrownBy(() -> new Player(stringBuilder.toString()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("길이는 100자를 초과할 수 없습니다.");

    }

    @Test
    @DisplayName("카드를 받아 저장한다.")
    void hit() {
        // give
        final Player player = new Player("pobi");
        final Card card = new Card(CardSymbol.DIAMOND, CardNumber.JACK);

        // when
        player.hit(card);
        final int actual = player.getScore();

        // then
        assertThat(actual).isEqualTo(10);
    }
}
