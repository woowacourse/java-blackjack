package dto;

import domain.participant.Player;
import java.util.List;

public class PlayerDto extends ParticipantDto {
    public PlayerDto(String name, List<String> cardNames, int totalSum) {
        super(name, cardNames, totalSum);
    }

    public static PlayerDto from(Player player) {
        return new PlayerDto(
                player.getName().toString(),
                player.getCardNames(),
                player.getTotalSum()
        );
    }
}
