package cardGame;

import card.Card;
import card.CardNumber;
import card.CardPattern;
import dealer.Dealer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.Name;
import player.Player;

class SingleMatchTest {

    @DisplayName("Dealer의 승패 결과를 가져온다.")
    @Test
    void isDealerWins() {
        Dealer dealer = new Dealer();
        Player player = new Player(new Name("pola"));

        player.receiveCard(new Card(CardNumber.ACE, CardPattern.SPADE_PATTERN));
        player.receiveCard(new Card(CardNumber.ACE, CardPattern.DIA_PATTERN));

        dealer.receiveCard(new Card(CardNumber.JACK, CardPattern.CLOVER_PATTERN));
        dealer.receiveCard(new Card(CardNumber.QUEEN, CardPattern.CLOVER_PATTERN));

        SingleMatch singleMatch = new SingleMatch(player, dealer);

        Assertions.assertThat(singleMatch.isPlayerWins()).isTrue();
    }
}