package blackjack.domain.card;

import blackjack.domain.Outcome;
import blackjack.util.BlackJackRule;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private static final String DUPLICATE_CARD_EXCEPTION_MESSAGE = "카드가 중복되었습니다.";

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException(DUPLICATE_CARD_EXCEPTION_MESSAGE);
        }
        cards.add(card);
    }

    public int getScore() {
        int score = cards.stream()
            .mapToInt(Card::getValue)
            .sum();
        if (canAddAceWeight(score) && hasAce()) {
            score += Symbol.ACE_WEIGHT;
        }
        return score;
    }

    private boolean canAddAceWeight(int score) {
        return !BlackJackRule.isBust(score + Symbol.ACE_WEIGHT);
    }

    private boolean hasAce() {
        return cards.stream()
            .anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return BlackJackRule.isBust(getScore());
    }

    public boolean isBlackJack() {
        return BlackJackRule.isBlackJack(getScore());
    }

    public int getTotalScore() {
        if(isBust()) {
            return 0;
        }
        return getScore();
    }

    public List<String> getInfos() {
        return cards.stream()
            .map(Card::getInfo)
            .collect(Collectors.toList());
    }

    public Outcome calculateOutcome(Cards comparisonCards) {
        return Outcome.calculate(getTotalScore(), comparisonCards.getTotalScore());
    }
}
