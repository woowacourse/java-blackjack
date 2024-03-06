public class BlackJack {
    private Deck deck;
    private Dealer dealer;
    private Participants participants;

    public BlackJack(Deck deck, Dealer dealer, Participants participants) {
        this.deck = deck;
        this.dealer = dealer;
        this.participants = participants;
    }

    public void beginDealing() {
        dealer.receiveCard(deck.draw());
        dealer.receiveCard(deck.draw());
        for (int i = 0; i < participants.getValue().size(); i++) {
            participants.getValue().get(i).receiveCard(deck.draw());
            participants.getValue().get(i).receiveCard(deck.draw());
        }
    }

    public boolean isWinner(Participant participant) {
        int participantScore = participant.calculateScore();
        int dealerScore = dealer.calculateScore();

        if (participantScore == dealerScore) {
            return isWinnerByCardCount(participant);
        }
        return participantScore > dealerScore;
    }

    private boolean isWinnerByCardCount(Participant participant) {
        int participantCardCount = participant.getCardCount();
        int dealerCardCount = dealer.getCardCount();

        return participantCardCount < dealerCardCount;
    }
}
