package domain.gamer;

import domain.card.CardGenerator;
import domain.card.CardGroup;

import java.util.HashSet;
import java.util.List;

public class GamerGenerator {
    private static final int MAX_PLAYER = 7;

    private GamerGenerator() {
    }

    public static List<Player> generatePlayer(List<String> playerNames, CardGenerator cardGenerator) {
        validateDuplicate(playerNames);
        validatePLayerNumber(playerNames);
        return playerNames.stream()
                .map(name -> new Player(name, new CardGroup()))
                .toList();
    }

    public static Dealer generateDealer() {
        return new Dealer(new CardGroup());
    }

    private static void validateDuplicate(final List<String> playerNames) {
        if (isDuplicate(playerNames)) {
            throw new IllegalArgumentException("[ERROR] 플레어는 중복될 수 없습니다.");
        }
    }

    private static void validatePLayerNumber(final List<String> playerNames) {
        if (playerNames.size() > MAX_PLAYER) {
            throw new IllegalArgumentException("[ERROR] 플레이어 인원 수느 7명을 초과할 수 없습니다.");
        }
    }

    private static boolean isDuplicate(final List<String> playerNames) {
        return new HashSet<>(playerNames).size() != playerNames.size();
    }
}
