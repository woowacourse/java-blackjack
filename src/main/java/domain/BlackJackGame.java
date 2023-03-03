package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {
    private static final int BLACK_JACK_NUMBER = 21;

    private final Deck deck;
    private final List<Player> players;

    public BlackJackGame(final Deck deck, final List<String> playerNames) {
        this.deck = deck;
        this.players = new ArrayList<>();
        addPlayers(playerNames);
        distributeTwoCards();
    }

    private void addPlayers(final List<String> playerNames) {
        this.players.add(new Dealer());
        playerNames.forEach(name ->
                this.players.add(new Player(name))
        );
    }

    private void distributeTwoCards() {
        for (Player player : players) {
            player.drawCard(deck.popCard());
            player.drawCard(deck.popCard());
        }
    }

    public void drawCard(final String playerName) {
        Player player = findPlayer(playerName);
        player.drawCard(deck.popCard());
    }

    private Player findPlayer(final String playerName) {
        return players.stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElseThrow();
    }

    public boolean isDealerDraw() {
        return findDealer().isDealerDraw();
    }

    private Dealer findDealer() {
        return (Dealer) players.get(0);
    }

    public Map<String, Outcome> decidePlayersOutcome() {
        Map<String, Outcome> result = new HashMap<>();
        final int dealerScore = findDealer().getScore();
        final List<Player> players = this.players.subList(1, this.players.size());

        players.forEach((player ->
                result.put(player.getName(), decideOutcome(dealerScore, player.getScore()))
        ));

        return result;
    }

    private Outcome decideOutcome(final int dealerScore, final int playerScore) {
        if (isPlayerWin(dealerScore, playerScore)) {
            return Outcome.WIN;
        }
        if (isPlayerDraw(dealerScore, playerScore)) {
            return Outcome.DRAW;
        }
        return Outcome.LOSE;
    }

    private boolean isPlayerWin(final int dealerScore, final int playerScore) {
        if (dealerScore > BLACK_JACK_NUMBER && playerScore <= BLACK_JACK_NUMBER) {
            return true;
        }
        if (playerScore <= BLACK_JACK_NUMBER && playerScore > dealerScore) {
            return true;
        }
        return false;
    }

    private boolean isPlayerDraw(final int dealerScore, final int playerScore) {
        if (dealerScore > BLACK_JACK_NUMBER && playerScore > BLACK_JACK_NUMBER) {
            return true;
        }
        if (dealerScore == playerScore) {
            return true;
        }
        return false;
    }

    public List<Card> getCards(final String playerName) {
        return findPlayer(playerName).getCards();
    }

    public int getScore(final String playerName) {
        return findPlayer(playerName).getScore();
    }
}
