import java.util.List;

public class BlackjackGame {
    private final Deck deck;
    private final Dealer dealer;
    private final List<Player> players;

    public BlackjackGame(List<Player> players){
        this.deck = new Deck();
        this.dealer = new Dealer();

        this.players = players;
    }

    public void giveInitCards() {
        giveCardsToDealer();
        giveCardsToPlayers();
    }

    private void giveCardsToPlayers() {
        for (Player player : players) {
            giveCardTo(player);
            giveCardTo(player);
        }
    }

    private void giveCardsToDealer() {
        giveCardTo(dealer);
        giveCardTo(dealer);
    }

    public void giveCardTo(Participant participant) {
        Card card = deck.drawCard();
        participant.receiveCard(card);
    }

    public void giveAdditionalCardToDealer(){
        while(dealer.calculateScore() < 17){
            giveCardTo(dealer);
        }
    }

    public Participant getDealer(){
        return dealer;
    }
}
