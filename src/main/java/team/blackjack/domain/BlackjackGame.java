package team.blackjack.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {
    private static final int INITIAL_CARD_COUNT = 2;
    private final Dealer dealer;
    private final List<Player> players;
    private final Deck deck;

    public BlackjackGame(List<String> playerNames) {
        this.dealer = new Dealer();
        this.players = createPlayers(playerNames);
        this.deck = new Deck();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void dealInitialCardsTo(Participant participant) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            participant.hit(deck.draw());
        }
    }

    public void dealCardTo(Participant participant) {
        participant.hit(deck.draw());
    }

    public List<String> getPlayerNames() {
        return this.players.stream()
                .map(Player::getName)
                .toList();
    }

    public Map<String, List<String>> getAllPlayerCards() {
        final HashMap<String, List<String>> result = new HashMap<>();
        for (Player player : players) {
            result.put(player.getName(), getPlayerCardInAllHand(player));
        }

        return result;
    }

    public Map<String, Integer> getAllPlayerScores(){
        return this.players.stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        Player::getScore)
                );
    }

    public List<String> getDealerCards(){
        return dealer.getAllCards();
    }

    public int getDealerScore(){
        return this.dealer.getScore();
    }

    private List<String> getPlayerCardInAllHand(Player player) {
        return player.getCardInAllHands();
    }

    private List<Player> createPlayers(List<String> playerNames) {
        return playerNames.stream()
                .map(Player::new)
                .toList();
    }
}
