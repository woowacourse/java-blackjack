package domain.gamer;

import domain.card.CardGenerator;
import domain.card.CardGroup;
import java.util.List;

public class GamerGenerator {

    private GamerGenerator() {

    }

    public static List<Player> generatePlayer(List<String> playerNames, CardGenerator cardGenerator) {
        validateDuplicate(playerNames);
        return playerNames.stream()
                .map(name -> new Player(name ,new CardGroup(), cardGenerator))
                .toList();
    }

    public static Dealer generateDealer(CardGenerator cardGenerator) {
        return new Dealer(new CardGroup(), cardGenerator);
    }

    private static void validateDuplicate(final List<String> playerNames) {
        if (isDuplicate(playerNames)) {
            throw new IllegalArgumentException("[ERROR] 플레어는 중복될 수 없습니다.");
        }
    }

    private static boolean isDuplicate(final List<String> playerNames) {
        return playerNames.stream()
                .distinct()
                .count() != playerNames.size();
    }
}
