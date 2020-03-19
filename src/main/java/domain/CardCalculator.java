package domain;

import domain.card.Card;
import domain.card.Cards;

import java.util.List;

public class CardCalculator {
    private static final int MAX_CARDS_SUM = 21;
    private static final int SUM_CONTAIN_ACE = 10;
    private static final int DEALER_STANDARD_ADDITIONAL_CARD = 16;

    private CardCalculator() {
    }

    public static int calculateAceStrategy(Cards cards) {
        if (cards == null) {
            throw new NullPointerException("입력한 카드가 없습니다.");
        }

        int playerCardsSum = calculateCards(cards.getCards());
        if (cards.containAce() && isAceStrategy(playerCardsSum)) {
            playerCardsSum += SUM_CONTAIN_ACE;
        }
        return playerCardsSum;
    }

    private static boolean isAceStrategy(int playerCardsSum) {
        return playerCardsSum + SUM_CONTAIN_ACE <= MAX_CARDS_SUM;
    }

    private static int calculateCards(List<Card> cards) {
        return (int) cards.stream()
                .mapToLong(Card::getCardNumber)
                .sum();
    }

    public static boolean determineWinner(Cards playerCards, Cards dealerCards) {
        if (playerCards == null || dealerCards == null) {
            throw new NullPointerException("플레이어 또는 딜러의 카드를 입력하지 않았습니다.");
        }

        int playerCardsSum = calculateAceStrategy(playerCards);
        int dealerCardsSum = calculateAceStrategy(dealerCards);

        if (playerCardsSum <= MAX_CARDS_SUM && dealerCardsSum > MAX_CARDS_SUM) {
            return true;
        }
        if (playerCardsSum > MAX_CARDS_SUM) {
            return false;
        }
        return playerCardsSum >= dealerCardsSum;
    }

    public static boolean isMaxCardsSum(Cards cards) {
        return calculateAceStrategy(cards) == MAX_CARDS_SUM;
    }

    public static boolean isUnderMaxCardsSum(Cards playerCards) {
        return calculateAceStrategy(playerCards) < MAX_CARDS_SUM;
    }

    public static boolean isUnderDealerStandard(Cards dealerCards) {
        return calculateAceStrategy(dealerCards) <= DEALER_STANDARD_ADDITIONAL_CARD;
    }
}
