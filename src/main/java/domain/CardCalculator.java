package domain;

import domain.card.Card;
import domain.card.Cards;

import java.util.List;

public class CardCalculator {
    private static final int BLACK_JACK = 21;
    private static final int SUM_CONTAIN_ACE = 10;

    private CardCalculator() {
    }

    public static int calculateAceStrategy(Cards cards) {
        if (cards == null) {
            throw new NullPointerException("입력한 카드가 없습니다.");
        }

        int playerCardsSum = calculateCards(cards.getCards());
        if (cards.containAce() && playerCardsSum + SUM_CONTAIN_ACE <= BLACK_JACK) {
            playerCardsSum += SUM_CONTAIN_ACE;
        }
        return playerCardsSum;
    }

    private static int calculateCards(List<Card> cards) {
        return (int) cards.stream()
                .mapToLong(Card::getCardNumber)
                .sum();
    }

    public static boolean determineWinner(Cards userCards, Cards dealerCards) {
        if (userCards == null || dealerCards == null) {
            throw new NullPointerException("유저 또는 딜러의 카드를 입력하지 않았습니다.");
        }

        int userCardSum = calculateAceStrategy(userCards);
        int dealerCardSum = calculateAceStrategy(dealerCards);

        if (userCardSum <= BLACK_JACK && dealerCardSum > BLACK_JACK) {
            return true;
        }
        if (userCardSum > BLACK_JACK) {
            return false;
        }
        return userCardSum >= dealerCardSum;
    }

    public static boolean isCardSumBlackJack(Cards cards) {
        return calculateAceStrategy(cards) == BLACK_JACK;
    }
}
