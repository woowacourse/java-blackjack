package blackjack.domain.machine;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import java.util.List;
import java.util.stream.Collectors;

public class GameResponse {

    private final String name;
    private final List<String> deck;
    private final int totalPoint;

    public GameResponse(String name, Deck deck) {
        this.name = name;
        this.deck = deck.getCards()
                .stream()
                .map(Card::toString)
                .collect(Collectors.toList());
        this.totalPoint = deck.sumPoints();
    }

    public String getName() {
        return name;
    }

    public List<String> getDeck() {
        return deck;
    }

    public int getTotalPoint() {
        return totalPoint;
    }
}
