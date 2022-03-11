package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.CardFactory;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    @DisplayName("참가자의 이름이 중복되면 예외가 발생한다.")
    void nameDuplicate() {
        // give
        String name = "rick";

        // when
        final List<String> names = List.of(name, name);

        // then
        assertThatThrownBy(() -> new Game(CardFactory.createBy(new ArrayList<>()), names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 중복을 허용하지 않습니다.");

    }
}
