package setupSettings;

import model.bating.Money;
import model.participant.Nickname;
import model.participant.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerGenerator {

    private static final Money money = new Money(1000);
    private static List<Nickname> cacheNicknames = NicknameGenerator.generateNicknames(3);

    public static Player generatePlayer() {
        return generatePlayers(1).keySet().stream().findFirst().get();
    }

    public static Map<Player, Money> generatePlayers(int count) {
        List<Nickname> nicknames = new ArrayList<>(getNicknames(count));
        Map<Player, Money> players = new HashMap<>();
        for (int i = 0; i < count; i++) {
            players.put(generatePlayerBy(nicknames.get(i)), money);
        }
        return players;
    }

    public static Player generatePlayerBy(Nickname nickname) {
        return new Player(nickname, () -> true);
    }

    private static List<Nickname> getNicknames(int count) {
        int size = cacheNicknames.size();
        if (size >= count) {
            return cacheNicknames.subList(0, count);
        }
        cacheNicknames = NicknameGenerator.generateNicknames(count);
        return cacheNicknames;
    }
}
