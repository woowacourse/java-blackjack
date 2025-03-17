package setupSettings;

import model.Money;
import model.participant.Nickname;
import model.participant.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerGenerator {

    private static final Money money = new Money(1000);
    private static List<Nickname> cacheNicknames = NicknameGenerator.generateNicknames(3);

    public static Player generatePlayer() {
        return generatePlayers(1).getFirst();
    }

    public static List<Player> generatePlayers(int count) {
        List<Nickname> nicknames = new ArrayList<>(getNicknames(count));
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            players.add(generatePlayerBy(nicknames.get(i)));
        }
        return players;
    }

    public static Player generatePlayerBy(Nickname nickname) {
        return new Player(nickname, money, () -> true);
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
