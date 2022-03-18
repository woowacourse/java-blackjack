package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class PlayersTest {
    @Test
    @DisplayName("플레이어의 이름이 중복되면 예외가 발생한다.")
    void validateDuplicationNames() {
        Card card = new Card(CardShape.CLOVER, CardNumber.FIVE);
        Player player1 = new Player("범고래", List.of(card), 1000);
        Player player2 = new Player("범고래", List.of(card), 2000);
        Assertions.assertThatThrownBy(() -> {
            new Players(List.of(player1, player2));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 이름이 존재합니다.");
    }
}