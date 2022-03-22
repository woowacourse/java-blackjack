package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("플레이어가 버스트 상태인지 알려준다")
    void isPlayerBust() {
        Player player = new Player("pobi", new BetMoney(1000));
        player.receiveCards(List.of(
                Card.valueOf(CardNumber.JACK, CardSymbol.SPADES), Card.valueOf(CardNumber.QUEEN, CardSymbol.SPADES),
                Card.valueOf(CardNumber.KING, CardSymbol.SPADES)));
        Assertions.assertThat(player.isFinished()).isTrue();
    }
}
