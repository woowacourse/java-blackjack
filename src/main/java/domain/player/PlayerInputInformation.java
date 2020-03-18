package domain.player;

import java.util.Collections;
import java.util.Map;

public class PlayerInputInformation {

    private Map<String, Double> playerInformation;

    public PlayerInputInformation(Map<String, Double> playersInformation) {
        validateNull(playersInformation);

        this.playerInformation = playersInformation;
    }

    private void validateNull(Map<String, Double> playersInformation) {
        if (playersInformation == null) {
            throw new NullPointerException("null 값 오류발생");
        }
    }

    public Map<String, Double> getPlayerInformation() {
        return Collections.unmodifiableMap(playerInformation);
    }
}
