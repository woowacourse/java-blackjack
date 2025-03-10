package domain.gamer;

import static controller.BlackJackController.BUST_NUMBER;

import domain.deck.Card;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Player extends Gamer {

    private static final int ACE_DEFAULT_NUMBER = 1;
    private static final int ACE_ADDITIONAL_NUMBER = 11;

    public Player(final Nickname nickname) {
        super(nickname);
    }

    @Override
    public int calculateSumOfRank() {
        if (hasAce()) {
            return calculateOptimalSumWithAce();
        }
        return getSumOfRank();
    }

    private int calculateOptimalSumWithAce() {
        final List<Card> cards = getCards();
        final List<Integer> sumOfRanks = calculateAllSums(cards);
        return findMaxSum(sumOfRanks);
    }

    private List<Integer> calculateAllSums(final List<Card> cards) {
        final Set<Integer> resultSet = new HashSet<>();
        calculateAllSums(cards, 0, 0, resultSet);
        return new ArrayList<>(resultSet);
    }

    private void calculateAllSums(final List<Card> cards,
                                  final int index,
                                  final int currentSum,
                                  final Set<Integer> resultSet
    ) {
        if (index == cards.size()) {
            resultSet.add(currentSum);
            return;
        }
        final Card card = cards.get(index);
        if (card.isAce()) {
            calculateAllSums(cards, index + 1, currentSum + ACE_DEFAULT_NUMBER, resultSet);
            calculateAllSums(cards, index + 1, currentSum + ACE_ADDITIONAL_NUMBER, resultSet);
            return;
        }
        calculateAllSums(cards, index + 1, currentSum + card.getScore(), resultSet);
    }

    private int findMaxSum(List<Integer> sums) {
        return sums.stream()
                .filter(sum -> sum <= BUST_NUMBER)
                .max(Integer::compareTo)
                .orElseGet(() -> findMinSum(sums));
    }

    private int findMinSum(List<Integer> sums) {
        return sums.stream().min(Integer::compareTo).orElse(0);
    }
}
