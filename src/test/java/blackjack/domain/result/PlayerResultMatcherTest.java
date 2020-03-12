package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardType;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerResultMatcherTest {

    @Test
    void name() {
        Dealer dealer = new Dealer();
        dealer.draw(new Card(CardSymbol.SIX, CardType.SPADE));
        dealer.draw(new Card(CardSymbol.TEN, CardType.CLOVER));
        dealer.draw(new Card(CardSymbol.THREE, CardType.DIAMOND));

        Player player = new Player("b");
        player.draw(new Card(CardSymbol.FIVE, CardType.DIAMOND));
        player.draw(new Card(CardSymbol.FOUR, CardType.HEART));

        BlackJackResult blackJackResult = PlayerResultMatcher.match(dealer, player);
        assertThat(blackJackResult).isEqualTo(BlackJackResult.LOSE);
    }
}