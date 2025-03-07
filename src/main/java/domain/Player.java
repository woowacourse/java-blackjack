package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Player extends Gamer {

    public Player(final Nickname nickname) {
        super(nickname);
    }

    @Override
    public int calculateSumOfRank() {

        if (hand.hasAce()) {
            final List<Card> cards = this.hand.getCards();
            final List<Integer> sumOfRanks = calculateAllSums(cards);

            return sumOfRanks.stream()
                    .filter(sum -> sum <= 21)
                    .max(Integer::compareTo)
                    .orElseGet(() -> sumOfRanks.stream().min(Integer::compareTo).orElse(0));
        }

        return hand.getSumOfRank();
    }

    private List<Integer> calculateAllSums(final List<Card> cards) {
        final Set<Integer> resultSet = new HashSet<>();
        calculateAllSums(cards, 0, 0, resultSet);
        final List<Integer> resultList = new ArrayList<>(resultSet);
        Collections.sort(resultList);
        return resultList;
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
            calculateAllSums(cards, index + 1, currentSum + 1, resultSet);
            calculateAllSums(cards, index + 1, currentSum + 11, resultSet);
            return;
        }
        calculateAllSums(cards, index + 1, currentSum + card.getScore(), resultSet);
    }
}
