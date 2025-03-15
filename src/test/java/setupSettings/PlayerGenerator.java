package setupSettings;

import model.Money;
import model.participant.Nickname;
import model.participant.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerGenerator {

    private static final Money money = new Money(1000);
    private static List<Nickname> cacheNicknames = NicknameGenerator.generateNicknames(3);

    public static Map<Nickname, Money> generatePlayerDatas(int count) {
        Map<Nickname, Money> playerDatas = new HashMap<>();
        List<Nickname> nicknames = getNicknames(count);
        for (int i = 0; i < count; i++) {
            playerDatas.put(nicknames.get(i), money);
        }
        return playerDatas;
    }

    public static Map<Nickname, Money> generatePlayerData() {
        return Map.of(
                cacheNicknames.getFirst(), money
        );
    }

    public static Player generatePlayer() {
        return generatePlayers(1).getFirst();
    }

    public static List<Player> generatePlayers(int count) {
        List<Nickname> nicknames = new ArrayList<>(getNicknames(count));
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            players.add(new Player(nicknames.get(i), money));
        }
        return players;
    }

    public static List<Player> generateCanModifyPlayers(int count) {
        List<Nickname> nicknames = new ArrayList<>(getNicknames(count));
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            players.add(new Player(nicknames.get(i), money));
        }
        return players;
    }

    public static Player generatePlayerBy(Nickname nickname) {
        return new Player(nickname, money);
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
