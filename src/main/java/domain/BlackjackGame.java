package domain;

public class BlackjackGame {
    public static final int STANDARD_OF_HIT = 17;
    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(Players players) {
        this.deck = new Deck();
        this.dealer = new Dealer();

        this.players = players;
    }

    public void giveInitCards() {
        giveCardsToDealer();
        giveCardsToPlayers();
    }
    private void giveCardsToDealer() {
        giveCardTo(dealer);
        giveCardTo(dealer);
    }

    private void giveCardsToPlayers() {
        for (Player player : players.getPlayers()) {
            giveCardTo(player);
            giveCardTo(player);
        }
    }

    public void giveCardTo(Participant participant) {
        Card card = deck.drawCard();
        participant.receiveCard(card);
    }

    public void giveAdditionalCardToDealer() {
        while (dealer.calculateScore() < STANDARD_OF_HIT) {
            giveCardTo(dealer);
        }
    }

    public void result() {
        dealer.decideResults(players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
