package domain;

public class BlackjackGame {
    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void initGame() {
        dealer.shuffle();

        for (int count = 0; count < 2; count++) {
            dealAllParticipants();
        }
    }

    private void dealAllParticipants() {
        dealToDealer();

        for (int playerIndex = 0; playerIndex < players.count(); playerIndex++) {
            dealToPlayer(playerIndex);
        }
    }

    private void dealToDealer() {
        Card dealerCard = dealer.pickCard();
        dealer.add(dealerCard);
    }

    public void dealToPlayer(int playerIndex) {
        Card playerCard = dealer.pickCard();
        players.giveCardToPlayer(playerIndex, playerCard);
    }
}
