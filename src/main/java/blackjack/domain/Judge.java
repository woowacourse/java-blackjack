package blackjack.domain;

public class Judge {

    private static final int BLACK_JACK = 21;
    private static final int ACE_WEIGHT = 10;

    //    private static final int DEA
    public Judge() {
    }

    public int calculateBestScore(Hand hand) {
        int aceCount = hand.countAce();
        int sum = hand.sum();
        while (aceCount > 0 && (sum + ACE_WEIGHT) < BLACK_JACK) {
            sum += ACE_WEIGHT;
            aceCount--;
        }
        return sum;
    }
}
