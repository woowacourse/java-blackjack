package blackjack.domain;

import blackjack.domain.Player;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("플레이어가 버스트 상태인지 알려준다")
    void isPlayerBust() {
        Player player = new Player("pobi",
                List.of(new Card(CardNumber.JACK, Symbol.SPADE), new Card(CardNumber.QUEEN, Symbol.SPADE),
                        new Card(CardNumber.KING, Symbol.SPADE)));
        Assertions.assertThat(player.isFinished()).isTrue();
    }
}
