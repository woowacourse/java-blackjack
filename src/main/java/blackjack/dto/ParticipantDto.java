package blackjack.dto;

import blackjack.domain.participant.Participant;
import java.util.List;
import java.util.stream.Collectors;

public class ParticipantDto {

    private final String name;
    private final List<CardDto> cardDtos;

    private ParticipantDto(final String name, final List<CardDto> cardDtos) {
        this.name = name;
        this.cardDtos = cardDtos;
    }

    public static ParticipantDto of(Participant participant) {
        final List<CardDto> cardDtos = participant.getCards().getValue().stream()
                .map(CardDto::of)
                .collect(Collectors.toList());
        return new ParticipantDto(participant.getName(), cardDtos);
    }

    public String getName() {
        return name;
    }

    public List<CardDto> getCardDtos() {
        return cardDtos;
    }
}
