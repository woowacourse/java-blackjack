package domain;

public class BlackjackGame {
    private static final int INITIAL_CARD_AMOUNT = 2;
    private static final String DEALER_NAME_VALUE = "딜러";
    private static final PlayerName DEALER_NAME = new PlayerName(DEALER_NAME_VALUE);

    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(Players players) {
        this.deck = new Deck();
        this.dealer = new Dealer(DEALER_NAME);

        this.players = players;
    }

    public void handOutInitialCards() {
        handOutCardTo(dealer, INITIAL_CARD_AMOUNT);

        for (Player player : players.getPlayers()) {
            handOutCardTo(player, INITIAL_CARD_AMOUNT);
        }
    }

    public void handOutCardTo(Participant participant) {
        Card card = deck.drawCard();
        participant.receive(card);
    }

    public void handOutCardTo(Participant participant, int amount) {
        for (int i = 0; i < amount; i++) {
            handOutCardTo(participant);
        }
    }

    public void handOutAdditionalCardToDealer() {
        while (dealer.isAbleToReceiveCard()) {
            handOutCardTo(dealer);
        }
    }

    public void decideBlackjackGameResults() {
        dealer.decideDealerResultsAgainst(players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
