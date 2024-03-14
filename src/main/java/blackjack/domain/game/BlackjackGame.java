package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;
import blackjack.domain.strategy.RandomCardShuffleStrategy;

public class BlackjackGame {
    private static final int INITIAL_CARD_COUNT = 2;

    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void start() {
        dealer.shuffleCards(new RandomCardShuffleStrategy());

        distributeInitialCards();
    }

    private void distributeInitialCards() {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            distributeCardToDealer();
            distributeCardToPlayers();
        }
    }

    private void distributeCardToDealer() {
        final Card card = pickCard();
        dealer.receiveCard(card);
    }

    private void distributeCardToPlayers() {
        for (int playerIndex = 0; playerIndex < players.count(); playerIndex++) {
            final Card card = pickCard();
            players.distributeCardTo(playerIndex, card);
        }
    }

    private Card pickCard() {
        return dealer.pickCard();
    }
}
