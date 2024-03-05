package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Player("atom"))
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어는 카드를 한 장 받을 수 있다.")
    @Test
    void drawOneCard() {
        Player player = new Player("atom");
        Card card = new Card(CardRank.EIGHT, CardShape.DIAMOND);

        player.draw(card);

        assertThat(player.getCards()).isEqualTo(List.of(card));
    }
}
