package blackjack.domain.card;

import blackjack.domain.Outcome;
import blackjack.util.BlackJackRule;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        return addAceWeight(score);
    }

    private int addAceWeight(int score) {
        Optional<Card> aceCard = cards.stream().filter(Card::isAce).findFirst();
        if (aceCard.isPresent() && !BlackJackRule.isBust(score + Symbol.getAceWeight())) {
            score += Symbol.getAceWeight();
        }
        return score;
    }

    public boolean isBust() {
        return BlackJackRule.isBust(getScore());
    }

    public boolean isBlackJack() {
        return BlackJackRule.isBlackJack(getScore());
    }

    public List<String> getInfos() {
        return cards.stream()
            .map(Card::getInfo)
            .collect(Collectors.toList());
    }

    public Outcome calculateOutcome(Cards comparisonCards) {
        if (isBust()) {
            return Outcome.LOSE;
        }
        if (comparisonCards.isBust()) {
            return Outcome.WIN;
        }
        return Outcome.calculate(getScore(), comparisonCards.getScore());
    }
}
