package blackjack.domain;

import blackjack.domain.card.group.CardDeck;
import blackjack.domain.human.Dealer;
import blackjack.domain.human.group.Players;
import blackjack.util.Constants;

public final class BlackjackTable {
    private static final CardDeck cardDeck = CardDeck.newInstance();
    
    private final Dealer dealer;
    private final Players players;
    
    private BlackjackTable(Players players) {
        this.dealer = Dealer.newInstance();
        this.players = players;
    }
    
    public static BlackjackTable from(Players players) {
        return new BlackjackTable(players);
    }
    
    public void initCard() {
        for (int i = 0; i < Constants.INIT_CARD_NUMBER; i++) {
            dealer.addCard(cardDeck.draw());
            players.giveCard(cardDeck);
        }
    }
    
    public CardDeck getCardDeck() {
        return cardDeck;
    }
    
    public Dealer getDealer() {
        return dealer;
    }
    
    public Players getPlayers() {
        return players;
    }
}
