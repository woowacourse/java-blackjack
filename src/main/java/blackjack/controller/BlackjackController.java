package blackjack.controller;

import blackjack.domain.Blackjack;
import blackjack.domain.Command;
import blackjack.domain.PlayerOutcome;
import blackjack.domain.entry.Participant;
import blackjack.domain.entry.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    public static Blackjack createBlackjack(List<String> inputNames) {
        return new Blackjack(inputNames);
    }

    public static List<Participant> getParticipants(Blackjack blackjack) {
        return blackjack.getParticipant();
    }

    public static List<Player> getPlayers(Blackjack blackjack) {
        return blackjack.getPlayers();
    }

    public static void hitPlayer(Blackjack blackjack, Player player, Command command) {
        if (blackjack.canHit(player, command)) {
            blackjack.hit(player);
        }
    }

    public static boolean canHitDealer(Blackjack blackjack) {
        return blackjack.needMoreCardByDealer();
    }

    public static Map<Participant, Integer> getCardResult(Blackjack blackjack) {
        List<Participant> participants = blackjack.getParticipant();
        return participants.stream()
            .collect(Collectors.toMap(participant -> participant, Participant::countCards));
    }

    public static Map<PlayerOutcome, List<Player>> getGameResult(Blackjack blackjack) {
        return blackjack.getGameResult();
    }
}
