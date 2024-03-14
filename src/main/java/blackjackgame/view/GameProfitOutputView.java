package blackjackgame.view;

import blackjackgame.dto.GameProfitDTO;
import java.util.List;

public class GameProfitOutputView {

    public static void print(GameProfitDTO gameProfitDTO) {
        System.out.println(gameProfitDTO.getDealerName() + ": " + gameProfitDTO.getDealerProfit());
        List<String> playersName = gameProfitDTO.getPlayersName();
        for(int i = 0; i < playersName.size(); i++) {
            String playerName = playersName.get(i);
            Integer playerProfit = gameProfitDTO.getPlayersProfit().get(i);
            System.out.println(playerName + ": " + playerProfit);
        }
    }
}
