package util;

import domain.participant.Player;
import domain.participant.Players;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NameParser {
    public static Players makeNameList(String input) {
        List<Player> playerList = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Player::new)
                .collect(Collectors.toList());

        return new Players(playerList);
    }
}
