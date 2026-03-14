package domain.game;

import static util.Constants.REVERSE_SIGN;

import domain.player.Gambler;
import domain.player.Gamblers;
import domain.player.Participant;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GamblersGameResult {

    private Map<String, Profit> participantProfits;

    public GamblersGameResult(Participant dealer, Gamblers gamblers) {
        this.participantProfits = new HashMap<>();
        calculateProfits(dealer, gamblers);
    }
}
