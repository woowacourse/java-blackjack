package blackjack.domain;

import blackjack.domain.card.RandomDeck;
import blackjack.domain.player.Command;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class Game {

    private final RandomDeck deck;
    private final Players players;
    private final Dealer dealer;

    public Game(List<String> names) {
        deck = new RandomDeck();
        players = generatePlayers(names);
        dealer = new Dealer(deck.initialDraw());
    }

    private Players generatePlayers(List<String> names) {
        return new Players(names.stream()
                .map(name -> new Player(name, deck.initialDraw()))
                .collect(toList()));
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
        return players.isPossibleToPlay();
    }

    public String getCurrentHittablePlayerName() {
        players.passTurnUntilHittable();
        return players.getCurrentTurn().getName();
    }

    public boolean isBettablePlayerRemains() {
        return players.isBettablePlayerRemains();
    }

    public String getCurrentBettablePlayerName() {
        players.passTurnUntilBettable();
        return players.getCurrentTurn().getName();
    }

    public void doBetting(long bettingMoney) {
        players.bettingCurrentPlayer(bettingMoney);
    }

    public boolean dealerCanDraw() {
        return dealer.canDraw();
    }

    public void doDealerDraw() {
        dealer.addCard(deck.draw());
    }

    public Dealer getDealer() {
        return dealer;
    }


    public Map<String, Long> getPlayerResults() {
        return players.calculateResult(dealer);
    }

    public long getDealerResult() {
        long result = 0L;
        Map<String, Long> playerResults1 = getPlayerResults();
        Collection<Long> values = playerResults1.values();
        for (Long value : values) {
            result -= value;
        }
        return result;
    }

    public List<Player> getPlayers() {
        return players.toList();
    }

    public Player getCurrentPlayer() {
        return players.getCurrentTurn();
    }
}
