package blackjack.domain.fixture;

import static blackjack.domain.fixture.CardFixture.createCards;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardType;
import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Name;
import blackjack.domain.gambler.Player;

public class GamblerFixture {
    public static Dealer createDealer() {
        return new Dealer();
    }
    public static Dealer createDealerWithCards(CardType... cardTypes) {
        Dealer dealer = new Dealer();
        for (Card card : createCards(cardTypes)) {
            dealer.hit(card);
        }
        return dealer;
    }

    public static Player createPlayerWithCards(Name name, CardType... cardTypes) {
        Player player = new Player(name);
        for (Card card : createCards(cardTypes)) {
            player.hit(card);
        }
        return player;
    }
}
