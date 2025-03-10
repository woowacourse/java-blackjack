package controller;

import controller.dto.DealerMatchResultCount;
import controller.dto.NameAndSum;
import controller.dto.ParticipantsMatchResult;
import domain.Dealer;
import domain.MatchResult;
import domain.Player;
import domain.Players;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DtoConverter {

    public static List<NameAndSum> getNameAndSumOfAllPlayers(Players players) {
        List<NameAndSum> nameAndSums = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            nameAndSums.add(new NameAndSum(player.getName(), player.computeOptimalSum()));
        }
        return nameAndSums;
    }

    public static DealerMatchResultCount computeDealerMatchResultCount(
            Map<Player, MatchResult> participantNameAndMatchResult) {
        Map<MatchResult, Integer> matchResultCount = new LinkedHashMap<>();
        MatchResult.sortedValues().forEach(matchResult -> matchResultCount.put(matchResult, 0));

        participantNameAndMatchResult.forEach((key, value) -> matchResultCount.put(MatchResult.inverse(value),
                matchResultCount.getOrDefault(MatchResult.inverse(value), 0) + 1));
        return new DealerMatchResultCount(matchResultCount);
    }

    public static ParticipantsMatchResult computeParticipantsMatchResult(Dealer dealer,
                                                                         List<Player> participants) {
        Map<Player, MatchResult> participantNameAndMatchResult = new LinkedHashMap<>();
        for (Player participant : participants) {
            MatchResult matchResult = computeParticipantMatchResult(dealer, participant);
            participantNameAndMatchResult.put(participant, matchResult);
        }
        return new ParticipantsMatchResult(participantNameAndMatchResult);
    }

    private static MatchResult computeParticipantMatchResult(Dealer dealer, Player participant) {
        if (participant.isBust()) {
            return MatchResult.LOSE;
        }
        if (dealer.isBust()) {
            return MatchResult.WIN;
        }
        return MatchResult.compareBySum(participant.computeOptimalSum(),
                dealer.computeOptimalSum());
    }
}
