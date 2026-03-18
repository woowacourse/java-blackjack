package domain;

import java.util.List;

public class BlackJackGame {
    private static final int INITIAL_CARD_COUNT = 2;

    private final Deck deck;
    private final List<Player> players;
    private final Dealer dealer;
    private int currentPlayerIndex;

    public BlackJackGame(Deck deck, List<Player> players, Dealer dealer) {
        this.deck = deck;
        this.players = players;
        this.dealer = dealer;
        currentPlayerIndex = 0;
    }

    public void moveToNextPlayer() {
        currentPlayerIndex++;
    }

    public boolean hasCurrentPlayer() {
        return players.size() > currentPlayerIndex;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public String getCurrentPlayerName() {
        return getCurrentPlayer().getName();
    }

    public void distributeInitialCards() {
        distributeInitialCard(dealer);
        players.forEach(this::distributeInitialCard);
    }

    public void playGameWithDealer() {
        distributeCard(dealer);
    }

    public void hitCurrentPlayer() {
        distributeCard(getCurrentPlayer());
    }

    public boolean canCurrentPlayerReceiveCard() {
        return getCurrentPlayer().canReceiveCard();
    }

    public boolean canDealerReceiveCard() {
        return dealer.canReceiveCard();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public Result getGameResult() {
        return new Referee().judge(dealer, players);
    }

    private void distributeInitialCard(Participant participant) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            distributeCard(participant);
        }
    }

    private void distributeCard(Participant participant) {
        Card card = deck.distributeCard();
        participant.receiveCard(card);
    }
}
