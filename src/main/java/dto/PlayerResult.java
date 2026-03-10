package dto;

import java.util.List;

public record PlayerResult(
        String name,
        List<String> cardList
) {

}
