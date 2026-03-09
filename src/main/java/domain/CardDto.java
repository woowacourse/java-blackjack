package domain;

import java.util.List;

public record CardDto(List<Card> cards) {

    public int size() {
        return cards().size();
    }
}
