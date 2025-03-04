import blackjack.domain.Card;
import blackjack.domain.CardNumber;
import blackjack.domain.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class BlackjackTest {
//    @DisplayName("각 플레이어 마다 기본 카드 2장을 발급한다")
//    @Test
//    void give_two_cards() {
//        blackjack.domain.Player player = new blackjack.domain.Player();
//        Assertions.assertThat(player.getCards().size()).isEqualTo(2);
//    }

    @DisplayName("카드는 숫자와 모양을 가진다.")
    @Test
    void card() {
        Card card = new Card(CardNumber.EIGHT, CardShape.CLOVER);
        assertAll(
                () -> assertThat(card.getNumber()).isEqualTo(8),
                () -> assertThat(card.getShape()).isEqualTo(CardShape.CLOVER)
        );
    }
}
