package dto;

import domain.Card;
import domain.Participant;
import java.util.ArrayList;
import java.util.List;

public class GameResult {

    private final String name;
    private final List<String> drawnCards;
    private final int score;

    public GameResult(final String name, final List<String> drawnCards, final int score) {
        this.name = name;
        this.drawnCards = drawnCards;
        this.score = score;
    }

    public static GameResult toDto(final Participant participant) {
        List<String> cardInfos = new ArrayList<>();
        List<Card> cards = participant.getDrawnCards();
        for (Card drawnCard : cards) {
            cardInfos.add(drawnCard.getValue().getName() + drawnCard.getType().getName());
        }

        return new GameResult(participant.getName(), cardInfos, participant.calculateCardScore());
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
