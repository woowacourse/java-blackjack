package blackjack.manager;

import blackjack.domain.Dealer;
import blackjack.domain.Hand;
import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import java.util.List;

public class BlackJackInitManager {
    public Participants saveParticipants(List<String> names) {
        List<Participant> participants = new java.util.ArrayList<>(names.stream()
                .map(name -> (Participant) new Player(name, new Hand()))
                .toList());
        participants.add(new Dealer(new Hand()));
        return new Participants(participants);
    }
}
