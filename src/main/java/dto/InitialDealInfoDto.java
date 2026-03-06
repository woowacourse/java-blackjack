package dto;

import domain.player.Player;
import java.util.List;
import java.util.Map;

public record InitialDealInfoDto(Map<String, List<String>> playerDealInfo) {

//    public InitialDealInfoDto(List<Player> players) {
//        //playerDealInfo =
//    }
}
