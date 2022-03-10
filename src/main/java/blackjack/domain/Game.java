package blackjack.domain;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.player.Command;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

import java.util.List;

public class Game {

    private final Players players;
    private final Deck deck;
    private final Dealer dealer;

    public Game(List<Player> players, Deck deck) {
        this.players = new Players(players);
        this.deck = deck;
        dealer = new Dealer(Cards.of(deck.initialDraw()));
    }

    public void playTurn(Command command) {
        Player currentTurn = players.getCurrentTurn();
        if (command.equals(Command.HIT)) {
            currentTurn.addCard(deck.draw());
        }
        if (command.equals(Command.STAY)) {
            currentTurn.stay();
        }
    }

    public boolean isPossibleToPlay() {
        boolean isPossibleToPlay = false;
        for (Player player : players.getPlayers()) {
            isPossibleToPlay |= player.isAbleToHit();
        }
        return isPossibleToPlay;
    }

    public String getCurrentHitablePlayerName() {
        players.passTurnUntilHitable();
        return players.getCurrentTurn().getName();
    }

    public boolean doesNeedToDraw() {
        return dealer.doesNeedToDraw();
    }

    public void doDealerDraw() {
        dealer.addCard(deck.draw());
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayersToList() {
        return players.toList();
    }

    public Player getCurrentPlayer() {
        return players.getCurrentTurn();
    }
}
