package blackjack.domain;

public class BlackjackGame {

    private final Players players;
    private final Dealer dealer = new Dealer();
    private final Drawable deck = new Deck();

    public BlackjackGame(Players players) {
        this.players = players;
    }

    public void drawCardTwice() {
        for (int i = 0; i < 2; i++) {
            players.drawAll(deck);
            dealer.drawCard(deck);
        }
    }

    public boolean checkDealerDrawable() {
        return dealer.isDrawable();
    }

    public void drawDealerCard() {
        dealer.drawCard(deck);
    }

    public ScoreResult makeResults() {
        return players.compete(dealer);
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Drawable getDeck() {
        return deck;
    }
}
