package blackjack.domain;

import blackjack.domain.card.CardDeck;
import blackjack.domain.human.Dealer;
import blackjack.domain.human.Players;
import blackjack.util.Constants;

public class Table {
    private final Players players;
    private final Dealer dealer;
    private final CardDeck cardDeck;

    private Table(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
        this.cardDeck = CardDeck.getInstance();
    }

    public static Table of(Players players, Dealer dealer) {
        return new Table(players, dealer);
    }

    public void initCard() {
        for (int i = 0; i < Constants.INIT_CARD_NUMBER; i++) {
            dealer.addCard(cardDeck.giveCard());
            players.giveCard(cardDeck);
        }
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }
}
