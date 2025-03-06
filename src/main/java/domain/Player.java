package domain;

import java.util.List;

public class Player extends Gamer {
    private int bestSum;

    public Player(final Nickname nickname) {
        super(nickname);
    }

    @Override
    public int calculateSumOfRank() {
        final List<Card> cards = this.hand.getCards();
        bestSum = 0;
        dfs(cards, 0, 0);
        return bestSum;
    }

    private void dfs(List<Card> cards, int index, int currentSum) {
        if (currentSum > 21) {
            return;
        }
        if (currentSum > bestSum) {
            bestSum = currentSum;
        }
        if (index >= cards.size()) {
            return;
        }

        dfs(cards, index + 1, currentSum);

        Card card = cards.get(index);
        if (card.isAce()) {
            dfs(cards, index + 1, currentSum + 1);
            dfs(cards, index + 1, currentSum + 11);

        } else {
            dfs(cards, index + 1, currentSum + card.getScore());
        }
    }
}
