package domain;

public class BlackjackGame {
    private static final int STANDARD_OF_HIT = 17;

    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(Players players) {
        this.deck = new Deck();
        this.dealer = new Dealer();

        this.players = players;
    }

    public void handOutInitialCards() {
        giveCardsToDealer();
        giveCardsToPlayers();
    }
    private void giveCardsToDealer() {
        handOutCardTo(dealer);
        handOutCardTo(dealer);
    }

    private void giveCardsToPlayers() {
        for (Player player : players.getPlayers()) {
            handOutCardTo(player);
            handOutCardTo(player);
        }
    }

    public void handOutCardTo(Participant participant) {
        Card card = deck.drawCard();
        participant.receiveCard(card);
    }

    public void handOutAdditionalCardToDealer() {
        while (dealer.calculateScore() < STANDARD_OF_HIT) {
            handOutCardTo(dealer);
        }
    }

    public void result() {
        dealer.decideDealerResultsAgainst(players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
