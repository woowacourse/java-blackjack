package blackjack.service;

import blackjack.domain.card.CardDeck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Players;

public class BlackJackService {
    private static final int INIT_DRAW_COUNT = 2;

    public CardDeck cardDeckSetting() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.shuffleCard();
        return cardDeck;
    }

    public void eachDrawTwoCards(Players players, Dealer dealer, CardDeck cardDeck) {
        for (int i = 0; i < INIT_DRAW_COUNT; i++) {
            dealer.receiveCard(cardDeck.drawCard());
            players.eachPlayerDrawCard(cardDeck);
        }
    }
}
