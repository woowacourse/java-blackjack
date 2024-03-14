package blackjack.view;

import blackjack.dto.DealerDto;
import blackjack.dto.DealerNetProfitDto;
import blackjack.dto.PlayerDto;
import blackjack.dto.PlayersNetProfitDto;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();

    private OutputView() {
    }

    public static void printInitialDrawResult(DealerDto dealerDto, List<PlayerDto> playerDtos) {
        String playerNames = Formatter.playerNamesFormat(playerDtos);
        printMessage(String.format(NEW_LINE + "딜러와 %s에게 2장을 나누었습니다.", playerNames));
        printGamerCardInfo(dealerDto, playerDtos);
    }

    public static void printPlayerCard(PlayerDto playerDto) {
        String playerCardInfo = Formatter.playerCardInfoFormat(playerDto);
        printMessage(playerCardInfo);
    }

    public static void printDealerHit() {
        printMessage(NEW_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printGamerCardAndScore(DealerDto dealerDto, List<PlayerDto> playerDtos) {
        StringBuilder gamerCardAndScore = new StringBuilder();

        gamerCardAndScore.append(NEW_LINE).append(Formatter.dealerCardAndScoreInfoFormat(dealerDto));
        for (PlayerDto playerDto : playerDtos) {
            gamerCardAndScore.append(NEW_LINE).append(Formatter.playerCardAndScoreInfoFormat(playerDto));
        }

        printMessage(gamerCardAndScore.toString());
    }

    public static void printNetProfit(DealerNetProfitDto dealerNetProfitDto, PlayersNetProfitDto playersNetProfitDto) {
        printMessage(NEW_LINE + "## 최종 수익");
        printDealerNetProfit(dealerNetProfitDto);
        printPlayerNetProfit(playersNetProfitDto);
    }

    private static void printGamerCardInfo(DealerDto dealerDto, List<PlayerDto> playerDtos) {
        StringBuilder gamerCardInfo = new StringBuilder();

        gamerCardInfo.append(NEW_LINE).append(Formatter.dealerCardInfoFormat(dealerDto));
        for (PlayerDto playerDto : playerDtos) {
            gamerCardInfo.append(NEW_LINE).append(Formatter.playerCardInfoFormat(playerDto));
        }

        printMessage(gamerCardInfo.toString());
    }

    private static void printDealerNetProfit(DealerNetProfitDto dealerNetProfitDto) {
        printMessage(Formatter.dealerNetProfitFormat(dealerNetProfitDto));
    }

    private static void printPlayerNetProfit(PlayersNetProfitDto playersNetProfitDto) {
        Map<String, Integer> playersNetProfit = playersNetProfitDto.playersNetProfit();
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
