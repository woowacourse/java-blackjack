package blackjackgame.view;

import blackjackgame.dto.GameProfitDTO;
import java.util.List;

public class GameProfitOutputView {

    private GameProfitOutputView() {
    }

    public static void print(GameProfitDTO gameProfitDTO) {
        System.out.printf("%s: %s%n", gameProfitDTO.getDealerName(), gameProfitDTO.getDealerProfit());
        List<String> playersName = gameProfitDTO.getPlayersName();
        for (int i = 0; i < playersName.size(); i++) {
            String playerName = playersName.get(i);
            Integer playerProfit = gameProfitDTO.getPlayersProfit().get(i);
            System.out.printf("%s: %s%n", playerName, playerProfit);
        }
    }
}
