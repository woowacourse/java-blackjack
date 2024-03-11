package blackjack.dto;

import blackjack.domain.participant.ParticipantName;

public record DealerMoreCardDto(String name, int count, int limitScore) {

    public static DealerMoreCardDto of(final ParticipantName name, final int count, final int limitScore) {
        return new DealerMoreCardDto(name.getName(), count, limitScore);
    }
}
