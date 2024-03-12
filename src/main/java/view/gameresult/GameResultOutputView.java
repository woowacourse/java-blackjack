package view.gameresult;

import dto.DealerGameResultDTO;
import dto.PlayerGameResultDTO;
import java.util.List;

public class GameResultOutputView {

    public static void print(List<PlayerGameResultDTO> playerGameResultDTOs) {
        for (PlayerGameResultDTO playerGameResultDTO : playerGameResultDTOs) {
            print(playerGameResultDTO);
        }
    }

    public static void print(PlayerGameResultDTO playerGameResultDTO) {
        String gameResultOutput = PlayerGameResultOutputGenerator.generate(playerGameResultDTO);
        System.out.println(gameResultOutput);
    }

    public static void print(DealerGameResultDTO dealerGameResultDTO) {
        String gameResultOutput = DealerGameResultOutputGenerator.generate(dealerGameResultDTO);
        System.out.printf("딜러: %s%n", gameResultOutput);
    }

}
