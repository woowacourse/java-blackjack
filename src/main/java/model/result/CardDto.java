package model.result;

import java.util.List;

public record CardDto(String name, List<String> cards) {

    public CardDto(String name, String card) {
        this(name, List.of(card));
    }
}
