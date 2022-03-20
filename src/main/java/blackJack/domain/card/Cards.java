package blackJack.domain.card;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Cards {

    private static final String ERROR_MESSAGE_HIT_DUPLICATED_CARD = "중복된 카드는 받을 수 없습니다.";

    private final Set<Card> cards;

    public Cards() {
        this.cards = new LinkedHashSet<>();
    }

    public void add(Card card) {
        validateHitDuplicatedCard(card);
        cards.add(card);
    }

    private void validateHitDuplicatedCard(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_HIT_DUPLICATED_CARD);
        }
    }

    public Score calculateScore() {
        Score score = initScore();
        if (containsAce() && score.isChangeAceScore()) {
            return score.changeAceScore();
        }
        return score;
    }

    private Score initScore() {
        return new Score(cards.stream()
                .mapToInt(Card::getPoint)
                .sum());
    }

    private boolean containsAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return cards.stream()
                .collect(Collectors.toUnmodifiableList());
    }
}
