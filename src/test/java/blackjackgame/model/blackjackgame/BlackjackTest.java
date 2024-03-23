package blackjackgame.model.blackjackgame;

import static org.junit.jupiter.api.Assertions.assertTrue;

import blackjackgame.model.card.Card;
import blackjackgame.model.card.CardNumber;
import blackjackgame.model.card.CardShape;
import blackjackgame.model.card.Cards;
import blackjackgame.model.card.StaticCardDispenser;
import blackjackgame.model.participants.dealer.Dealer;
import blackjackgame.model.participants.player.Players;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @DisplayName("딜러가 블랙잭인 경우 true")
    @Test
    void testTrueWhenDealerIsBlackjack() {
        Cards cards = createDealerrBlackjackTestCards();
        Dealer dealer = new Dealer(cards);
        Blackjack blackjack = new Blackjack(dealer, Players.from(List.of("lily")));
        assertTrue(blackjack.isDealerBlackjack());
    }

    private Cards createDealerrBlackjackTestCards() {
        StaticCardDispenser cardJackDia = new StaticCardDispenser(CardNumber.JACK, CardShape.DIAMOND);
        StaticCardDispenser cardAceClover = new StaticCardDispenser(CardNumber.ACE, CardShape.CLOVER);
        return new Cards(
                List.of(new Card(cardJackDia), new Card(cardAceClover))
        );
    }
}
