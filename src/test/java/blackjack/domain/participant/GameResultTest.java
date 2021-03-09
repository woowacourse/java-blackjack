package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {
    @Test
    @DisplayName("플레이어가 딜러를 이겼는지 테스트")
    void getPlayerGameResultAgainstDealerTest() {
        Player player = new Player("bepoz");
        Dealer dealer = new Dealer();
        player.addCard(new Card(CardNumber.KING, Shape.DIAMOND));
        dealer.addCard(new Card(CardNumber.TWO, Shape.DIAMOND));

        assertThat(GameResult.getPlayerGameResultAgainstDealer(player, dealer)).isEqualTo("승");
    }
}