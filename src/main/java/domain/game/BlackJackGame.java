package domain.game;

import java.util.List;
import java.util.stream.Collectors;

import domain.Number;
import domain.card.Deck;
import domain.card.ShuffleStrategy;
import domain.people.Dealer;
import domain.people.Player;
import domain.people.Players;

public final class BlackJackGame {

    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    private BlackJackGame(final List<String> names, final ShuffleStrategy shuffleStrategy) {
        this.deck = Deck.from(shuffleStrategy);
        this.dealer = new Dealer();
        this.players = createParticipants(names);
    }

    public static BlackJackGame from(final List<String> names, final ShuffleStrategy shuffleStrategy) {
        return new BlackJackGame(names, shuffleStrategy);
    }

    private Players createParticipants(final List<String> names) {
        return Players.from(names);
    }

    public void dealCardsToParticipants() {
        for (int i = 0; i < Number.INIT_HAND_COUNT.get(); i++) {
            deal();
        }
    }

    public void assignBetAmount(Player player, int betAmount) {
        player.assignBetAmount(betAmount);
    }

    private void deal() {
        dealer.receiveCard(deck.draw());
        for (Player player : players.getPlayers()) {
            player.receiveCard(deck.draw());
        }
    }

    public boolean hasBlackJack(Player player) {
        return player.hasBlackJack(Number.INIT_HAND_COUNT.get());
    }

    public void hitOrStay(final String hitRequest, final Player player) {
        if (isHit(hitRequest)) {
            player.receiveCard(deck.draw());
        }
    }

    public boolean isBust(Player player) {
        return player.isBust();
    }

    private boolean isHit(final String drawingInput) {
        return Requests.isHit(drawingInput);
    }

    public boolean isStay(final String drawingInput) {
        return Requests.isStay(drawingInput);
    }

    public boolean shouldDealerHit() {
        return dealer.shouldHit();
    }

    public void dealerHit() {
        dealer.receiveCard(deck.draw());
    }

    public List<Player> fetchNoBlackJackPlayers() {
        return fetchPlayers().stream()
            .filter(player -> !player.hasBlackJack(Number.INIT_HAND_COUNT.get()))
            .collect(Collectors.toList());
    }

    public List<Player> fetchPlayers() {
        return players.getPlayers();
    }

    public Integer fetchPlayerProfit(Player player) {
        return player.fetchProfit(dealer.fetchHandValue());
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

}
