package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Bet;
import blackjack.domain.player.Gamer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackResultTest {

    @Test
    @DisplayName("블랙잭 게임 룰 상 결과가 블랙잭이다.")
    void blackJackResultBlackJack() {
        Gamer gamer = new Gamer("huni", new Bet(10000));
        gamer.receiveCard(new Card(Suit.DIAMOND, Denomination.JACK));
        gamer.receiveCard(new Card(Suit.DIAMOND, Denomination.ACE));

        assertThat(BlackJackResult.findBlackJackResult(gamer)).isEqualTo(BlackJackResult.BLACK_JACK);
    }
}
