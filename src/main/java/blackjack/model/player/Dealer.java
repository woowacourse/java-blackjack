package blackjack.model.player;

import blackjack.model.card.Card;
import blackjack.model.game.ParticipantResult;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class Dealer extends Player {

    public static final int RECEIVABLE_POINT = 16;

    public boolean isDrawable() {
        return calculatePoint() <= RECEIVABLE_POINT;
    }

    public Card getInitialCard() {
        return getReceivedCards().getFirstCard();
    }

    public Map<ParticipantResult, Integer> calculateResult(Participants participants) {
        Map<ParticipantResult, Integer> result = new EnumMap<>(ParticipantResult.class);
        Arrays.stream(ParticipantResult.values())
                .forEach(participantResult -> result.put(participantResult, 0));
        participants.stream().forEach(participant -> {
            ParticipantResult participantResult = participant.dueWith(this);
            result.merge(participantResult, 1, Integer::sum);
        });
        return result;
    }
}
