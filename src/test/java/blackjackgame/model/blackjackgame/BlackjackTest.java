package blackjackgame.model.blackjackgame;

import static blackjackgame.model.card.CardNumber.ACE;
import static blackjackgame.model.card.CardNumber.JACK;
import static blackjackgame.model.card.CardShape.CLOVER;
import static blackjackgame.model.card.CardShape.DIAMOND;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import blackjackgame.model.card.Card;
import blackjackgame.model.card.Cards;
import blackjackgame.model.participants.dealer.Dealer;
import blackjackgame.model.participants.player.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @DisplayName("딜러가 블랙잭인 경우 true")
    @Test
    void testTrueWhenDealerIsBlackjack() {
        Cards cards = new Cards(
                List.of(new Card(JACK, DIAMOND), new Card(ACE, CLOVER))
        );
        Dealer dealer = new Dealer(cards);
        Blackjack blackjack = new Blackjack(dealer, Players.from(List.of("lily")));
        assertTrue(blackjack.isDealerBlackjack());
    }
}
