package domain.player;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlayerInputInformation {
    Map<String,Double> playerInformation;

    public PlayerInputInformation(List<String> playerName, List<Double> battingMoney) {
        if(playerName == null || battingMoney == null){
            throw new NullPointerException("null 값 오류발생");
        }
        playerInformation = new LinkedHashMap<>();

        for(int i=0;i<playerName.size();i++){
            playerInformation.put(playerName.get(i),battingMoney.get(i));
        }
    }

    public Map<String, Double> getPlayerInformation() {
        return Collections.unmodifiableMap(playerInformation);
    }
}
