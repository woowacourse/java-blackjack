package blackjack.model;

import blackjack.model.card.CardDeck;
import blackjack.model.player.Gamers;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import java.util.List;

public class BlackJackGame {
    private static final int START_CARD_COUNT = 2;

    private final Player dealer;
    private final Gamers gamers;

    public BlackJackGame(List<String> names) {
        this.dealer = new Dealer();
        this.gamers = Gamers.from(names);
    }

    public void giveStartingCardsBy(CardDeck cardDeck) {
        giveCardsToDealerBy(cardDeck);
        for (Player gamer : gamers.getValues()) {
            giveCardsTo(gamer, cardDeck);
        }
    }

    private void giveCardsToDealerBy(CardDeck cardDeck) {
        for (int i = 0; i < START_CARD_COUNT; i++) {
            dealer.receive(cardDeck.draw());
        }
    }

    private void giveCardsTo(Player gamer, CardDeck cardDeck) {
        for (int i = 0; i < START_CARD_COUNT; i++) {
            gamer.receive(cardDeck.draw());
        }
    }

    public boolean isDrawPossible() {
        return gamers.hasNextGamer();
    }

    public boolean isBustOnNowTurn() {
        return gamers.isCurrentGamerCanReceive();
    }

    public void drawCardFrom(CardDeck cardDeck) {
        gamers.giveCurrentGamer(cardDeck.draw());
    }

    public void nextDrawTurn() {
        gamers.nextGamer();
    }

    public Player getDealer() {
        return dealer;
    }

    public List<Player> getGamers() {
        return gamers.getValues();
    }

    public Player getCurrentPlayer() {
        return gamers.getCurrentValue();
    }

    public void giveCardBy(CardDeck cardDeck) {
        while (dealer.canReceive()) {
            dealer.receive(cardDeck.draw());
        }
    }
}
