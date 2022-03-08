package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("플레이어는 생성될 때 두 장의 카드를 받는다.")
    void startWithDraw() {
        // given
        String name = "ohzzi";
        List<String> cards = List.of("3다이아몬드", "9클로버");

        // when
        Player player = new Player(name, cards);

        // then
        assertThat(player.getCards()).containsOnly("3다이아몬드", "9클로버");
    }

    @Test
    @DisplayName("플레이어를 생성할 때 카드는 null일 수 없다.")
    void cardsNotNull() {
        // given
        String name = "ohzzi";

        // then
        assertThatThrownBy(() -> new Player(name, null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("플레이어를 생성할 때 카드가 두 장이 아니면 예외가 발생한다.")
    void cardsSizeNotTwo() {
        // given
        String name = "ohzzi";
        List<String> cards = List.of("3다이아몬드", "9클로버", "10하트");

        // then
        assertThatThrownBy(() -> new Player(name, cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 카드를 두 장 받고 시작해야 합니다.");
    }

    @Test
    @DisplayName("플레이어를 생성할 때 카드가 중복되면 예외가 발생한다.")
    void duplicatedCards() {
        String name = "ohzzi";
        List<String> cards = List.of("3다이아몬드", "3다이아몬드");

        // then
        assertThatThrownBy(() -> new Player(name, cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 카드는 중복될 수 없습니다.");
    }
}
