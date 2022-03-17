package blackjack.domain;

import blackjack.domain.card.group.CardDeck;
import blackjack.domain.human.Dealer;
import blackjack.domain.human.group.Players;

public final class BlackjackRepository {
    private static final int INIT_CARD_NUMBER = 2;
    private static final CardDeck cardDeck = CardDeck.newInstance();

    private final Dealer dealer;
    private final Players players;

    private BlackjackRepository(Players players) {
        this.dealer = Dealer.newInstance();
        this.players = players;
    }

    public static BlackjackRepository from(Players players) {
        return new BlackjackRepository(players);
    }

    public void initCard() {
        for (int i = 0; i < INIT_CARD_NUMBER; i++) {
            dealer.addCard(cardDeck.pop());
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
