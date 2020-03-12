package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardType;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayerResultMatcherTest {

    @Test
    void name() {
        Dealer dealer = new Dealer();
        dealer.add(new Card(CardSymbol.SIX, CardType.SPADE));
        dealer.add(new Card(CardSymbol.TEN, CardType.CLOVER));
        dealer.add(new Card(CardSymbol.THREE, CardType.DIAMOND));

        Player player = new Player("b");
        player.add(new Card(CardSymbol.FIVE, CardType.DIAMOND));
        player.add(new Card(CardSymbol.FOUR, CardType.HEART));

        BlackJackResult blackJackResult = PlayerResultMatcher.match(dealer, player);
        assertThat(blackJackResult).isEqualTo(BlackJackResult.LOSE);
    }
}