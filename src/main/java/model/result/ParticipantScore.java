package model.result;

import model.card.Card;
import model.card.Cards;
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
        Cards cards = player.getCards();
        int totalScore = calculateTotalScore(cards);
        return new ParticipantScore(player.getName(), totalScore);
    }

    public static ParticipantScore from(Dealer dealer) {
        Cards cards = dealer.getCards();
        int totalScore = calculateTotalScore(cards);
        return new ParticipantScore(dealer.getName(), totalScore);
    }

    private static int calculateTotalScore(Cards cards) {
        int totalScore = cards.calculateTotalNumbers();
        while (totalScore < MINIMUM_SCORE_FOR_ACE_HIGH && hasAce(cards)) {
            totalScore += ACE_SCORE_HIGH - ACE_SCORE_LOW;
        }
        return totalScore;
    }

    private static boolean hasAce(Cards cards) {
        return cards.getCards()
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
