package blackjack.controller;

import blackjack.domain.BlackjackTable;
import blackjack.domain.Command;
import blackjack.domain.PlayerOutcome;
import blackjack.domain.entry.Participant;
import blackjack.domain.entry.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    public static BlackjackTable createBlackjack(List<String> inputNames) {
        return new BlackjackTable(inputNames);
    }

    public static List<Participant> getParticipants(BlackjackTable blackjackTable) {
        return blackjackTable.getParticipant();
    }

    public static List<Player> getPlayers(BlackjackTable blackjackTable) {
        return blackjackTable.getPlayers();
    }

    public static void hitPlayer(BlackjackTable blackjackTable, Player player, Command command) {
        if (blackjackTable.canHit(player, command)) {
            blackjackTable.hit(player);
        }
    }

    public static Map<Participant, Integer> getCardResult(BlackjackTable blackjackTable) {
        List<Participant> participants = blackjackTable.getParticipant();
        return participants.stream()
            .collect(Collectors.toMap(participant -> participant, Participant::countCards));
    }

    public static Map<PlayerOutcome, List<Player>> getGameResult(BlackjackTable blackjackTable) {
        return blackjackTable.getGameResult();
    }

    public static void hitDealer(BlackjackTable blackjackTable) {
        while (blackjackTable.needMoreCardByDealer()) {
            blackjackTable.hitDealer();
        }
    }
}
