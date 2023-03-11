package dto.response;

import static java.util.stream.Collectors.toList;

import domain.card.Card;
import java.util.List;
import view.CardNameRender;

public class ParticipantResult {

    private final String name;
    private final List<String> drawnCards;
    private final int score;

    public ParticipantResult(final String name, final List<String> drawnCards, final int score) {
        this.name = name;
        this.drawnCards = drawnCards;
        this.score = score;
    }

    public static ParticipantResult toDto(final String playerName,
                                          final List<Card> drawnCards,
                                          final int score) {

        List<String> cardsInfo = drawnCards.stream()
                .map(CardNameRender::render)
                .collect(toList());

        return new ParticipantResult(playerName, cardsInfo, score);
    }

    public String getName() {
        return name;
    }

    public List<String> getDrawnCards() {
        return drawnCards;
    }

    public int getScore() {
        return score;
    }
}
