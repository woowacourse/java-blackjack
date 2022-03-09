package service.dto;

import java.util.List;

public class InitGameDto {
    private final List<String> names;
    private final List<List<String>> cards;

    public InitGameDto(List<String> names, List<List<String>> cards) {
        this.names = names;
        this.cards = cards;
    }

    public List<String> getNames() {
        return names;
    }

    public List<List<String>> getCards() {
        return cards;
    }
}
