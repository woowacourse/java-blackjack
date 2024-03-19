package domain.dto;

import domain.Card;
import domain.Player;
import java.util.List;

public class PlayerDto extends DealerDto {
    private final String name;

    private PlayerDto(String name, List<Card> cards, int totalScore) {
        super(cards, totalScore);
        this.name = name;
    }

    public static PlayerDto from(Player player) {
        return new PlayerDto(
                player.getName(),
                player.getCards(),
                player.getTotalScore()
        );
    }

    public String getName() {
        return name;
    }
}
