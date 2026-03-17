package model.factory;

import java.util.ArrayList;
import java.util.List;
import model.Participants;
import model.participant.Dealer;
import model.participant.Participant;
import model.participant.Player;
import model.vo.BetAmount;

public final class ParticipantsFactory {

    private ParticipantsFactory() {
    }

    public static Participants create(String[] names, List<BetAmount> betAmounts) {
        List<Participant> participants = new ArrayList<>();

        Dealer dealer = Dealer.of();

        for (int i = 0; i < names.length; i++) {
            Player player = Player.of(names[i], betAmounts.get(i));
            participants.add(player);
        }

        return Participants.of(participants, dealer);
    }
}
