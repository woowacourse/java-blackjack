package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dealer {

    private final String name;
    private final List<String> cards;

    public Dealer(String name, List<String> cards) {
        cards = new ArrayList<>(cards);
        validate(cards);

        this.name = name;
        this.cards = cards;
    }

    private void validate(List<String> cards) {
        Objects.requireNonNull(cards, "[ERROR] 카드는 null일 수 없습니다.");
        validateSize(cards);
        validateDistinct(cards);
    }

    private void validateSize(List<String> cards) {
        if (cards.size() != 2) {
            throw new IllegalArgumentException("[ERROR] 카드를 두 장 받고 시작해야 합니다.");
        }
    }

    private void validateDistinct(List<String> cards) {
        if (cards.stream().distinct().count() != cards.size()) {
            throw new IllegalArgumentException("[ERROR] 카드는 중복될 수 없습니다.");
        }
    }

    public List<String> getCards() {
        return cards;
    }
}
