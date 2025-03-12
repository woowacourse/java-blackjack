package blackjack.gamer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players() {
        this.players = new ArrayList<>();
    }

    public void addPlayersFrom(final String names) {
        final String[] parsedNames = names.replace(" ", "").split(",");
        if (parsedNames.length >= 6) {
            throw new IllegalArgumentException("적정 인원을 초과했습니다. 다시 입력해주세요");
        }
        Arrays.stream(parsedNames)
                .forEach(parsedName -> players.add(new Player(parsedName)));
    }
}
