package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 외부에서 만들어서 넣어주자. 이것의 장점과 단점에 대해서 생각해보자.
public class Players {
    private static final String DUPLICATION_NAME_ERROR = "중복된 이름이 존재합니다.";

    private final List<Player> players = new ArrayList<>();

    public Players(List<String> names, List<Integer> bettingMoneys, Deck deck) {
        validateDuplicationNames(names);
        for (int i = 0; i < names.size(); i++) {
            List<Card> cards = List.of(deck.draw(), deck.draw());
            players.add(new Player(names.get(i), cards, bettingMoneys.get(i)));
        }
    }

    private void validateDuplicationNames(List<String> names) {
        Set<String> duplicationCheck = new HashSet<>(names);
        if (duplicationCheck.size() != names.size()) {
            throw new IllegalArgumentException(DUPLICATION_NAME_ERROR);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
