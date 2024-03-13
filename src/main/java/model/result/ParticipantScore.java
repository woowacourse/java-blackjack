package model.result;

import model.card.Card;
import model.card.Hand;
import model.dealer.Dealer;
import model.player.Player;

public class ParticipantScore {

    private static final int ACE_SCORE_HIGH = 11;
    private static final int ACE_SCORE_LOW = 1;
    private static final int MINIMUM_SCORE_FOR_ACE_HIGH = 12;
    private static final int BLACKJACK_SCORE = 21;

    private final String participantName;
    private final int score;

    public ParticipantScore(String participantName, int score) {
        this.participantName = participantName;
        this.score = score;
    }

    public static ParticipantScore from(Player player) {
        Hand cards = player.getHand();
        int totalScore = calculateTotalScore(cards);
        return new ParticipantScore(player.getName(), totalScore);
    }

    public static ParticipantScore from(Dealer dealer) {
        Hand cards = dealer.getHand();
        int totalScore = calculateTotalScore(cards);
        return new ParticipantScore(dealer.getName(), totalScore);
    }

    private static int calculateTotalScore(Hand hand) {
        int totalScore = hand.calculateTotalNumbers();
        while (totalScore < MINIMUM_SCORE_FOR_ACE_HIGH && hasAce(hand)) {
            totalScore += ACE_SCORE_HIGH - ACE_SCORE_LOW;
        }
        return totalScore;
    }

    private static boolean hasAce(Hand hand) {
        return hand.getCards()
            .stream()
            .anyMatch(Card::isAce);
    }

    public boolean isNotBurst() {
        return !isBurst();
    }

    public boolean isBurst() {
        return score > BLACKJACK_SCORE;
    }

    public String getParticipantName() {
        return participantName;
    }

    public int getScore() {
        return score;
    }
}
