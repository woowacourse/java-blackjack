package dto.response;

import domain.card.Card;
import domain.participant.Participant;
import java.util.ArrayList;
import java.util.List;
import view.CardNameRender;

public class DrawnCardsInfo {

    private final String name;
    private final List<String> drawnCards;

    private DrawnCardsInfo(final String name, final List<String> drawnCards) {
        this.name = name;
        this.drawnCards = drawnCards;
    }

    public static DrawnCardsInfo toDto(final Participant participant, List<Card> drawnCards) {
        List<String> cardInfos = new ArrayList<>();

        for (Card drawnCard : drawnCards) {
            cardInfos.add(renderCardName(drawnCard));
        }

        return new DrawnCardsInfo(participant.getName(), cardInfos);
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
}
