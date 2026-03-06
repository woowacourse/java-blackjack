package view;

import java.util.ArrayList;
import java.util.List;

public class InputParser {

    public static List<String> parsePlayers(String strPlayers) {

        String[] split = strPlayers.split(",", -1);

        List<String> players = new ArrayList<>();
        for (String name : split) {
            name = name.trim();

            if (name.length() < 2 || name.length() > 10) {
                throw new IllegalArgumentException("플레이어의 이름은 2글자 이상 10글자 이하여야 합니다.");
            }

            players.add(name);
        }

        return players;
    }
}
