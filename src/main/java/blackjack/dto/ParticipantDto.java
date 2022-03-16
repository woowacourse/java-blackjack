package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;
import java.util.List;
import java.util.stream.Collectors;

public class ParticipantDto {
    private final String name;
    private final List<String> cards;
    private final int score;

    private ParticipantDto(String name, List<String> cards, int score) {
        this.name = name;
        this.cards = cards;
        this.score = score;
    }

    public static ParticipantDto from(Participant participant) {
        String name = participant.getName();
        List<String> cards = getCardNames(participant);
        int score = participant.getCurrentScore().getValue();

        return new ParticipantDto(name, cards, score);
    }

    private static List<String> getCardNames(Participant participant) {
        return participant.getHand()
                .getCards()
                .stream()
                .map(Card::getName)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return List.copyOf(cards);
    }

    public String getFirstCard() {
        return cards.get(0);
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "ParticipantDto{" +
                "name='" + name + '\'' +
                ", cards=" + cards +
                ", score=" + score +
                '}';
    }
}
