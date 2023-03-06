package domain.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackResultMaker {

    public Map<Participant, Result> makeParticipantsResult(final Dealer dealer, final List<Participant> participants) {
        Map<Participant, Result> results = new LinkedHashMap<>();
        for (Participant participant : participants) {
            results.put(participant, Result.decide(participant.getScore(), dealer.getScore()));
        }
        return results;
    }

    public Result makeDealerResult(final Dealer dealer, final List<Participant> participants) {
        final List<Score> scores = participants.stream()
            .map(Participant::getScore)
            .collect(Collectors.toList());
        return Result.decide(dealer.getScore(), scores);
    }
}
