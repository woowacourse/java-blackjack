package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class GameManger {
    private Map<User, CardDeck> userPick;


    public GameManger(List<String> names) {
        HashSet<String> distinctNames = new HashSet<>(names);
        if (names.isEmpty() || names.size() > 7) {
            throw new IllegalArgumentException("유저는 1명 이상 7명 이하로 등록해야 합니다.");
        }
        if (distinctNames.size() != names.size()) {
            throw new IllegalArgumentException("유저는 중복될 수 없습니다.");
        }
    }

    public void firstHandOutCard() {
    }

    public CardDeck findCardDeckByUsername(String name) {
        return null;
    }
}
