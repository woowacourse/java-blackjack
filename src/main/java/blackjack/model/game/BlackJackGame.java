package blackjack.model.game;

import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.Players;

public class BlackJackGame {
    private static final int ACE_THRESHOLD = 16;

    private final Deck deck;

    public BlackJackGame(Players players, Deck deck, Dealer dealer) {
        this.deck = deck;
    }


    public void initializeGame(Players players, Dealer dealer) {
        players.getParticipants().forEach(p -> p.initialCard(deck.drawCard(), deck.drawCard()));
        dealer.initialCard(deck.drawCard(), deck.drawCard());
    }


    public boolean isBustAfterDraw(Player player) {
        player.putCard(deck.drawCard());
        return player.isBust();
    }


    public boolean isDealerUnderNumber(Dealer dealer) {
        return dealer.calculatePoint() <= ACE_THRESHOLD;
    }

    public void giveDealerCard(Dealer dealer, Deck deck) {
        dealer.putCard(deck.drawCard());
    }


}