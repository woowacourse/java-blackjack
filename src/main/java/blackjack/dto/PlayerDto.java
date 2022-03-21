package blackjack.dto;

import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerDto extends ParticipantDto {

    public PlayerDto(String name, List<String> cards, int score) {
        super(name, cards, score);
    }

    public static PlayerDto from(Player player) {
        return generatePlayerDto(player);
    }

    public static List<PlayerDto> from(Players players) {
        return players.getValue()
                .stream()
                .map(PlayerDto::generatePlayerDto)
                .collect(Collectors.toList());
    }

    private static PlayerDto generatePlayerDto(Player player) {
        String name = player.getName();
        List<String> cards = player.getHand().generateCardNames();
        int score = player.getCurrentScore().getValue();

        return new PlayerDto(name, cards, score);
    }
}
