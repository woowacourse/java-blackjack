package dto;

import java.util.List;

public record DealerCardsDto(String name, String card) {

    public DealerCardsDto(String name, List<String> cards){
        this(name, cards.getFirst());
    }

}
