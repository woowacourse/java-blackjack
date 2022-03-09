package blackjack.domain;

import java.util.List;

public class ResultDTO {

    private final String name;
    private final List<Card> cards;
    private final int score;

    public ResultDTO(Participant participant) {
        name = participant.getName();
        cards = participant.getCards();
        score = participant.getScore();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}
