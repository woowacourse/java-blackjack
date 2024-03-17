package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.result.Score;
import java.util.List;
import java.util.Map.Entry;

public record ParticipantScoreDto(ParticipantCardsDto participantCards, int score) {
    public static ParticipantScoreDto of(final Entry<ParticipantName, Hands> entry, final Score score) {
        ParticipantName name = entry.getKey();
        List<Card> cards = entry.getValue().getCards();

        return new ParticipantScoreDto(ParticipantCardsDto.of(name, cards), score.getValue());
    }
}
