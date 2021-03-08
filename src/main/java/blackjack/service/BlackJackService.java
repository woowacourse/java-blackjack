package blackjack.service;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;

public class BlackJackService {
    private static final int INIT_DRAW_COUNT = 2;

    public CardDeck cardDeckSetting() {
        Cards deck = new Cards(Card.values());
        CardDeck cardDeck = new CardDeck(deck);
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
