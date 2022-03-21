package blackjack.domain.machine;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Name;
import java.util.List;
import java.util.stream.Collectors;

public class GameResponse {

    private final String name;
    private final List<String> deck;
    private final int score;

    public GameResponse(Name name, Cards cards) {
        this.name = name.value();
        this.deck = cards.getCards()
                .stream()
                .map(Card::toString)
                .collect(Collectors.toList());
        this.score = cards.score().value();
    }

    public String getName() {
        return name;
    }

    public List<String> getDeck() {
        return deck;
    }

    public int getScore() {
        return score;
    }
}
