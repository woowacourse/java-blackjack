package team.blackjack.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Deck getDeck() {
        return deck;
    }

    public void dealInitialCardsTo(Participant participant) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            participant.hit(deck.draw());
        }
    }

    public void dealCardTo(Participant participant){
        participant.hit(deck.draw());
    }

    public Map<String, List<String>> getAllPlayerCards() {
        final HashMap<String, List<String>> result = new HashMap<>();
        for (Player player : players) {
            result.put(player.getName(), getPlayerCardInAllHand(player));
        }

        return result;
    }

    private List<String> getPlayerCardInAllHand(Player player) {
        return player.getHands().stream()
                .map(Hand::getCards)
                .flatMap(List::stream)
                .map(Card::getCardName)
                .toList();
    }

    private List<Player> createPlayers(List<String> playerNames) {
        return playerNames.stream()
                .map(Player::new)
                .toList();
    }
}
