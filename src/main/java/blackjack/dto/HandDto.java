package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;
import java.util.List;
import java.util.stream.Collectors;

public class HandDto {
    private final List<String> cards;
    private final int score;

    private HandDto(List<String> cards, int score) {
        this.cards = cards;
        this.score = score;
    }

    public static HandDto of(Participant participant) {
        List<String> cards = getCards(participant);
        int score = participant.getCurrentScore().getValue();

        return new HandDto(cards, score);
    }

    private static List<String> getCards(Participant participant) {
        return participant.getHand()
                .getCards()
                .stream()
                .map(Card::getName)
                .collect(Collectors.toList());
    }

    public List<String> getCards() {
        return cards;
    }

    public String getFirstCard() {
        return cards.get(0);
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "HandDto{" +
                "cards=" + cards +
                ", score=" + score +
                '}';
    }
}
