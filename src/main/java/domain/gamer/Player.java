package domain.gamer;

import domain.deck.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class Player extends Gamer {

    private static final int ACE_DEFAULT_NUMBER = 1;
    private static final int ACE_ADDITIONAL_NUMBER = 11;

    public Player(final Nickname nickname) {
        super(nickname);
    }

    @Override
    public int calculateSumOfRank() {
        final Hand hand = getHand();

        if (hand.hasAce()) {
            final List<Card> cards = hand.getCards();
            final List<Integer> sumOfRanks = calculateAllSums(cards);

            return sumOfRanks.stream()
                    .filter(sum -> sum <= Hand.BUST_NUMBER)
                    .max(Integer::compareTo)
                    .orElseGet(getMinSum(sumOfRanks));
        }
        return hand.calculateSumOfRank();
    }

    private static Supplier<Integer> getMinSum(final List<Integer> sumOfRanks) {
        return () -> sumOfRanks.stream().min(Integer::compareTo).orElse(0);
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
            calculateAllSums(cards, index + 1, currentSum + ACE_DEFAULT_NUMBER, resultSet);
            calculateAllSums(cards, index + 1, currentSum + ACE_ADDITIONAL_NUMBER, resultSet);
            return;
        }
        calculateAllSums(cards, index + 1, currentSum + card.getScore(), resultSet);
    }
}
