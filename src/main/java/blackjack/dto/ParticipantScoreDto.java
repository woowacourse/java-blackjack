package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.Name;
import blackjack.domain.Score;
import java.util.List;
import java.util.Map.Entry;

public record ParticipantScoreDto(ParticipantCardsDto participantCards, int score) {
    public static ParticipantScoreDto of(final Entry<Name, Hands> entry, final Score score) {
        Name name = entry.getKey();
        List<Card> cards = entry.getValue().getCards();

        return new ParticipantScoreDto(ParticipantCardsDto.of(name, cards), score.getValue());
    }
}
