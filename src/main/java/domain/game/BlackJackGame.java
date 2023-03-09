package domain.game;

import domain.deck.Card;
import domain.deck.Deck;
import domain.player.Amount;
import domain.player.Dealer;
import domain.player.Name;
import domain.player.Player;
import domain.player.Players;
import java.util.List;

public class BlackJackGame {
    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public BlackJackGame(final Deck deck, final List<Name> names, final List<Amount> amounts) {
        this.deck = deck;
        this.dealer = new Dealer();
        this.players = new Players(names, amounts);
        distributeTwoCards();
    }

    private void distributeTwoCards() {
        dealer.drawCard(deck.popCard());
        dealer.drawCard(deck.popCard());

        for (final Player player : players.getPlayers()) {
            player.drawCard(deck.popCard());
            player.drawCard(deck.popCard());
        }
    }

    public void drawCardDealer() {
        dealer.drawCard(deck.popCard());
    }

    public void drawCardPlayer(final Name name) {
        players.getPlayer(name)
                .drawCard(deck.popCard());
    }

    public boolean isDealerDraw() {
        return dealer.isDealerDraw();
    }

    public List<Card> getPlayerCards(final Name name) {
        return players.getPlayer(name).cards();
    }

    public List<Card> getDealerCards() {
        return dealer.cards();
    }

    public int getPlayerScore(final Name name) {
        return players.getPlayer(name).score();
    }

    public int getDealerScore() {
        return dealer.score();
    }
}
