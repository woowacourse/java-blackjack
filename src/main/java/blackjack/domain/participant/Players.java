package blackjack.domain.participant;

import blackjack.utils.CardDeck;
import blackjack.utils.IllegalNameException;

import java.util.*;

public class Players {
    private final List<Player> players;

    public Players(String namesInput, CardDeck cardDeck) {
        players = new ArrayList<>();
        validate(namesInput);
        String[] names = namesInput.split(",");
        validateDuplicate(names);
        for (String name : names) {
            players.add(new Player(name, cardDeck));
        }
    }

    private void validateDuplicate(String[] names) {
        Set<String> namesCopy = new HashSet<>(Arrays.asList(names));
        if (namesCopy.size() != names.length) {
            throw new IllegalNameException("중복된 이름을 사용할 수 없습니다.");
        }
    }

    private void validate(String namesInput) {
        if (namesInput == null || namesInput.isEmpty()) {
            throw new IllegalNameException("입력이 null이거나 빈 문자열일 수 없습니다.");
        }
    }

    public List<Player> values() {
        return Collections.unmodifiableList(players);
    }
}
