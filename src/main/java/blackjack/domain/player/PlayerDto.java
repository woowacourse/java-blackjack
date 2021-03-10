package blackjack.domain.player;

import blackjack.domain.card.CardsDto;

public class PlayerDto {

    private final CardsDto cardsDto;
    private final String name;
    private final int score;

    private PlayerDto(CardsDto cardsDto, String name, int score) {
        this.cardsDto = cardsDto;
        this.name = name;
        this.score = score;
    }

    public static PlayerDto from(Player player) {
        CardsDto cardsDto = CardsDto.from(player.getCards());
        String name = player.getName();
        int score = player.calculateScore();
        return new PlayerDto(cardsDto, name, score);
    }

    public CardsDto getCardsDto() {
        return cardsDto;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
