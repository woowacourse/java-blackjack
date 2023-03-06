package domain.game;

import domain.deck.Card;
import domain.deck.Deck;
import domain.player.Dealer;
import domain.player.Name;
import domain.player.Player;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {
    private static final String DEALER_NAME = "딜러";
    private static final int BLACK_JACK_NUMBER = 21;

    private final Deck deck;
    private final Dealer dealer;
    private final List<Player> players;

    public BlackJackGame(final Deck deck, final List<String> playerNames) {
        this.deck = deck;
        this.dealer = new Dealer();
        this.players = generatePlayers(playerNames);
        distributeTwoCards();
    }

    private List<Player> generatePlayers(final List<String> playerNames) {
        List<Player> players = new ArrayList<>();

        playerNames.forEach(
                name -> players.add(new Player(name))
        );

        return players;
    }

    private void distributeTwoCards() {
        dealer.drawCard(deck.popCard());
        dealer.drawCard(deck.popCard());

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
        if (isDealer(playerName)) {
            return dealer;
        }
        return players.stream()
                .filter(player -> player.getName().equals(new Name(playerName)))
                .findFirst()
                .orElseThrow();
    }

    public boolean isDealerDraw() {
        return dealer.isDealerDraw();
    }

    public Map<Name, Outcome> decidePlayersOutcome() {
        Map<Name, Outcome> result = new LinkedHashMap<>();

        players.forEach(player ->
                result.put(player.getName(), decideOutcome(dealer.getScore(), player.getScore()))
        );

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
        if (isBurst(dealerScore) && !isBurst(playerScore)) {
            return true;
        }
        return !isBurst(playerScore) && playerScore > dealerScore;
    }

    private boolean isPlayerDraw(final int dealerScore, final int playerScore) {
        if (isBurst(dealerScore) && isBurst(playerScore)) {
            return true;
        }
        return dealerScore == playerScore;
    }

    private boolean isBurst(int score) {
        return score > BLACK_JACK_NUMBER;
    }

    public List<Card> getCards(final String playerName) {
        if (isDealer(playerName)) {
            return dealer.getCards();
        }
        return findPlayer(playerName).getCards();
    }

    private boolean isDealer(final String name) {
        return name.equals(DEALER_NAME);
    }

    public int getScore(final String playerName) {
        if (isDealer(playerName)) {
            return dealer.getScore();
        }
        return findPlayer(playerName).getScore();
    }
}
