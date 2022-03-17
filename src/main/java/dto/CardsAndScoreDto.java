package dto;

import domain.player.Player;
import java.util.List;

public class CardsAndScoreDto {
    private final CardsDto cardsDto;
    private final int score;

    private CardsAndScoreDto(CardsDto cardsDto, int score) {
        this.cardsDto = cardsDto;
        this.score = score;
    }

    public static CardsAndScoreDto from(Player player) {
        return new CardsAndScoreDto(CardsDto.forResult(player), player.getScore());
    }

    public String getName() {
        return cardsDto.getName();
    }

    public List<String> getCards() {
        return cardsDto.getCards();
    }

    public int getScore() {
        return score;
    }
}
