package blackjack.view;

import blackjack.dto.GamersDto;
import blackjack.dto.GamersNetProfitDto;
import blackjack.dto.PlayerDto;
import java.util.Map;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();

    private OutputView() {
    }

    public static void printInitialDrawResult(GamersDto gamersDto) {
        String playerNames = Formatter.playerNamesFormat(gamersDto.playerDtos());
        printMessage(String.format(NEW_LINE + "딜러와 %s에게 2장을 나누었습니다.", playerNames));
        printGamerCardInfo(gamersDto);
    }

    public static void printPlayerCard(PlayerDto playerDto) {
        String playerCardInfo = Formatter.playerCardInfoFormat(playerDto);
        printMessage(playerCardInfo);
    }

    public static void printDealerHit() {
        printMessage(NEW_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printGamerCardAndScore(GamersDto gamersDto) {
        StringBuilder gamerCardAndScore = new StringBuilder();

        gamerCardAndScore.append(NEW_LINE).append(Formatter.dealerCardAndScoreInfoFormat(gamersDto.dealerDto()));
        for (PlayerDto playerDto : gamersDto.playerDtos()) {
            gamerCardAndScore.append(NEW_LINE).append(Formatter.playerCardAndScoreInfoFormat(playerDto));
        }

        printMessage(gamerCardAndScore.toString());
    }

    public static void printNetProfit(GamersNetProfitDto gamersNetProfitDto) {
        printMessage(NEW_LINE + "## 최종 수익");
        printDealerNetProfit(gamersNetProfitDto.dealerNetProfit());
        printPlayerNetProfit(gamersNetProfitDto.playersNetProfit());
    }

    private static void printGamerCardInfo(GamersDto gamersDto) {
        StringBuilder gamerCardInfo = new StringBuilder();

        gamerCardInfo.append(NEW_LINE).append(Formatter.dealerCardInfoFormat(gamersDto.dealerDto()));
        for (PlayerDto playerDto : gamersDto.playerDtos()) {
            gamerCardInfo.append(NEW_LINE).append(Formatter.playerCardInfoFormat(playerDto));
        }

        printMessage(gamerCardInfo.toString());
    }

    private static void printDealerNetProfit(int dealerNetProfit) {
        printMessage(Formatter.dealerNetProfitFormat(dealerNetProfit));
    }

    private static void printPlayerNetProfit(Map<String, Integer> playersNetProfit) {
        StringBuilder playersNetProfitInfo = new StringBuilder();

        for (String playerName : playersNetProfit.keySet()) {
            int netProfit = playersNetProfit.get(playerName);
            playersNetProfitInfo.append(Formatter.playerNetProfitFormat(playerName, netProfit)).append(NEW_LINE);
        }

        printMessage(playersNetProfitInfo.toString());
    }

    private static void printMessage(String message) {
        System.out.println(message);
    }
}
