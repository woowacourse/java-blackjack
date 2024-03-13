package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.List;

public class InitialHands {
    private final InitialHand dealerHand;
    private final List<InitialHand> playersHand;

    public InitialHands(InitialHand dealerHand, List<InitialHand> playersHand) {
        this.dealerHand = dealerHand;
        this.playersHand = playersHand;
    }

    public List<InitialHand> getValues() {
        List<InitialHand> participantsHand = new ArrayList<>();
        participantsHand.add(dealerHand);
        participantsHand.addAll(playersHand);
        return participantsHand;
    }

    public List<String> getPlayersName() {
        return playersHand.stream()
                .map(InitialHand::getName)
                .toList();
    }

    public String getDealerName() {
        return dealerHand.getName();
    }
}
