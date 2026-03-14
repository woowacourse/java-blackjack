package view;

import dto.BattingResultDto;
import dto.DealerHandScoreDto;
import dto.DealerInitialHandDto;
import dto.GameScoreDto;
import dto.GameStartDto;
import dto.PlayerHandDto;
import dto.PlayerHandScoreDto;
import dto.PlayerProfitDto;

import java.util.List;

public class OutputView {
    public void printStartGame(GameStartDto gameStartDto) {
        System.out.println("딜러와 " + convertListToString(gameStartDto.playerNames()) + "에게 2장을 나누었습니다.");
        printDealerInitialHandCard(gameStartDto.dealer());

        List<PlayerHandDto> players = gameStartDto.players();
        for (PlayerHandDto player : players) {
            printHandCard(player);
        }
        System.out.println();
    }

    private void printDealerInitialHandCard(DealerInitialHandDto dealerInitialHandDto) {
        System.out.println("딜러: " + dealerInitialHandDto.firstHandCard());
    }

    public void printHandCard(PlayerHandDto playerHandDto) {
        System.out.println(playerHandDto.name() + "카드: " + convertListToString(playerHandDto.handCards()));
    }

    private void printDealerHandCardWithScore(DealerHandScoreDto handScoreDto) {
        System.out.println("딜러 카드: " + convertListToString(handScoreDto.handCards()) + " - 결과: " + printScore(handScoreDto.isBlackJack(), handScoreDto.isBust(), handScoreDto.score()));
    }

    private void printPlayerHandCardWithScore(PlayerHandScoreDto handScoreDto) {
        System.out.println(handScoreDto.name() + "카드: " + convertListToString(handScoreDto.handCards()) + " - 결과: " + printScore(handScoreDto.isBlackJack(), handScoreDto.isBust(), handScoreDto.score()));
    }

    private String printScore(boolean isBlackJack, boolean isBust, int score) {
        if (isBlackJack) {
            return "블랙잭";
        }
        if (isBust) {
            return "버스트";
        }
        return String.valueOf(score);
    }

    public void printDealerReceiveCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printScore(GameScoreDto gameResultDto) {
        System.out.println();
        printDealerHandCardWithScore(gameResultDto.dealer());

        List<PlayerHandScoreDto> players = gameResultDto.players();
        for (PlayerHandScoreDto player : players) {
            printPlayerHandCardWithScore(player);
        }
    }

    public void printBattingResults(BattingResultDto battingResultDto) {
        long dealerProfit = battingResultDto.dealerProfit();

        List<PlayerProfitDto> playerProfitDtos = battingResultDto.playerProfitDtos();

        System.out.println();
        System.out.println("## 최종 수익");
        System.out.println("딜러: " + dealerProfit);

        for (PlayerProfitDto playerProfitDto : playerProfitDtos) {
            System.out.println(playerProfitDto.name() + ": " + playerProfitDto.profit());
        }
    }


    private String convertListToString(List<String> list) {
        return String.join(", ", list);
    }

    public void printInputErrorMessage(String errorMessage) {
        System.out.printf("%s 다시 입력해주세요\n", errorMessage);
    }
}
