package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private static final int JUDGE_ACE_CARD_VALUE_ELEVEN_MAX_SUM = 11;
    private static final int CALIBRATED_ACE_CARD_ELEVEN_VALUE = 10;

    private final PlayerName playerName;
    private final List<Card> receivedCards = new ArrayList<>();

    public Player(PlayerName playerName) {
        this.playerName = playerName;
    }

    public void receiveCard(Card card) {
        receivedCards.add(card);
    }

    public List<Card> getReceivedCards() {
        return this.receivedCards;
    }

    public int calculateCardNumber() {
        int totalSumAceCardValueOne = calculateCardNumberAceCardValueOne();
        if (playerHasAceCard() && totalSumAceCardValueOne <= JUDGE_ACE_CARD_VALUE_ELEVEN_MAX_SUM) {
            return totalSumAceCardValueOne + CALIBRATED_ACE_CARD_ELEVEN_VALUE;
        }
        return totalSumAceCardValueOne;
    }

    private int calculateCardNumberAceCardValueOne() {
        return receivedCards.stream()
            .mapToInt(card -> card.getCardNumber().value)
            .sum();
    }

    private boolean playerHasAceCard() {
        return receivedCards.stream()
            .anyMatch(card -> card.getCardNumber().equals(CardNumber.ACE));
    }
}
