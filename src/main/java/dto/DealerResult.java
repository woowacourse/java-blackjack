package dto;

import java.util.List;

public record DealerResult(
        String name,
        List<String> cardList,
        int score
) {

}

