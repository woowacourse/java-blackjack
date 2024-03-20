package model.blackjackgame;

import static model.card.CardNumber.ACE;
import static model.card.CardNumber.JACK;
import static model.card.CardShape.CLOVER;
import static model.card.CardShape.DIAMOND;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import model.card.Card;
import model.card.Cards;
import model.participants.dealer.Dealer;
import model.participants.player.Players;
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
