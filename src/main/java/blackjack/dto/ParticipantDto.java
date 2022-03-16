package blackjack.dto;

import blackjack.domain.Participant;
import java.util.List;
import java.util.stream.Collectors;

public class ParticipantDto {

    private final List<CardDto> cards;
    private final String name;
    private final int totalNumber;

    private ParticipantDto(List<CardDto> cards, String name, int totalNumber) {
        this.cards = cards;
        this.name = name;
        this.totalNumber = totalNumber;
    }

    public static ParticipantDto from(Participant player) {
        List<CardDto> cardDtos = player.getCards()
                .stream()
                .map(CardDto::from)
                .collect(Collectors.toList());
        return new ParticipantDto(cardDtos, player.getName(), player.getTotalNumber());
    }

    public String getName() {
        return name;
    }

    public List<CardDto> getCards() {
        return cards;
    }

    public int getTotalNumber() {
        return totalNumber;
    }
}
