package dto.response;

import static java.util.stream.Collectors.toList;

import domain.card.Card;
import java.util.List;
import view.CardNameRender;

public class DrawnCardsInfo {

    private final String name;
    private final List<String> drawnCards;

    private DrawnCardsInfo(final String name, final List<String> drawnCards) {
        this.name = name;
        this.drawnCards = drawnCards;
    }

    public static DrawnCardsInfo toDto(final String playerName, List<Card> drawnCards) {
        List<String> cardsInfo = drawnCards.stream()
                .map(CardNameRender::render)
                .collect(toList());

        return new DrawnCardsInfo(playerName, cardsInfo);
    }

    public String getName() {
        return name;
    }

    public List<String> getDrawnCards() {
        return drawnCards;
    }
}
