package dto.response;

import domain.card.Card;
import java.util.ArrayList;
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

        List<String> cardInfos = new ArrayList<>();
        for (Card drawnCard : drawnCards) {
            cardInfos.add(renderCardName(drawnCard));
        }

        return new ParticipantResult(playerName, cardInfos, score);
    }

    private static String renderCardName(Card drawnCard) {
        return CardNameRender.renderValue(drawnCard.getValue()) + CardNameRender.renderType(drawnCard.getType());
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
