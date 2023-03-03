package domain;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {
    private final Deck deck;

    private final List<Player> players;

    public BlackJackGame(Deck deck, List<String> playerNames) {
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

    public void drawCard(String playerName) {
        Player player = findPlayer(playerName);
        player.drawCard(deck.popCard());
    }

    private Player findPlayer(String playerName) {
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

    public List<Card> getCards(String playerName) {
        return findPlayer(playerName).getCards();
    }

    public int getScore(String playerName) {
        return findPlayer(playerName).getScore();
    }
}
