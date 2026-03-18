package domain;

import exception.ErrorMessage;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Game {
    private final List<Player> players;
    private final Dealer dealer;
    private final Deck deck;

    public Game(List<Name> playerNames, Deck deck) {
        validateDuplicate(playerNames);

        this.deck = deck;
        this.dealer = new Dealer();
        this.players = playerNames.stream()
                .map(Name::name)
                .map(Player::new)
                .toList();
        initCards();
    }

    private static void validateDuplicate(List<Name> playerNames) {
        Set<Name> set = new HashSet<>(playerNames);
        if (set.size() != playerNames.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_NAME.getMessage());
        }
    }

    private void initCards() {
        players.forEach(player -> {
            player.draw(deck.drawCard());
            player.draw(deck.drawCard());
        });
        dealer.draw(deck.drawCard());
        dealer.draw(deck.drawCard());
    }

    public Map<Player, WinningStatus> calculateAllResults() {
        Map<Player, WinningStatus> results = new LinkedHashMap<>();
        for (Player player : players) {
            results.put(player, player.calculateResult(dealer.state));
        }
        return results;
    }

    public void stayPlayer(Player player) {
        player.stay();
    }

    public void stayDealer() {
        dealer.stay();
    }

    public void hitPlayer(Player player) {
        player.draw(deck.drawCard());
    }

    public void hitDealer() {
        dealer.draw(deck.drawCard());
    }

    public boolean dealerCanHit() {
        return dealer.canHit();
    }

    public int getPlayerHandSize(Player player) {
        return player.getHandSize();
    }

    public int getDealerHandSize() {
        return dealer.getHandSize();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
