package view;

import domain.gamer.Name;
import dto.AllPlayerResultDto;
import dto.DealerResultDto;
import dto.GameResultDto;
import dto.InitCardDto;
import dto.PlayerCardStateDto;
import java.util.List;

public class OutputView {

    private static final int INIT_CARD_COUNT = 2;
    private static final int MIN_DEALER_SCORE = 16;
    private static final String NAME_FORMAT_SYMBOL = ", ";

    public void printInitCard(InitCardDto initCardDto) {
        String playersNames = changeNameFormat(initCardDto.playerNames());
        System.out.println("\n딜러와 " + playersNames + "에게 " + INIT_CARD_COUNT + "장을 나누었습니다.");
        System.out.println("딜러: " + changeFormat(initCardDto.dealerCard()));

        for (int i = 0; i < initCardDto.playersCard().size(); i++) {
            System.out.println(
                    initCardDto.playerNames().get(i) + "카드: " + initCardDto.playersCard().get(i));
        }
        System.out.println();
    }

    private String changeNameFormat(List<String> names) {
        return String.join(NAME_FORMAT_SYMBOL, names);
    }

    private String changeFormat(List<String> names) {
        return String.join(NAME_FORMAT_SYMBOL, names);
    }

    public void printDealerAddCard() {
        System.out.println();
        System.out.println("딜러가 " + MIN_DEALER_SCORE + "이하라 한장의 카드를 더 받았습니다.\n");
    }

    public void printCardsStatus(PlayerCardStateDto playerCardStateDto) {
        System.out.println(playerCardStateDto.name() + "카드: " + changeFormat(playerCardStateDto.playerCards()));

    }

    public void printDealerScore(DealerResultDto dealerResult) {
        System.out.println("딜러: " + changeFormat(dealerResult.dealerCards()) + " - 결과: " + dealerResult.dealerScore());
    }

    public void printPlayersScore(AllPlayerResultDto allPlayerResult) {
        for (int i = 0; i < allPlayerResult.playersCardStateDto().size(); i++) {
            PlayerCardStateDto playerCardState = allPlayerResult.playersCardStateDto().get(i);
            System.out.println(
                    playerCardState.name() + "카드: " + changeFormat(playerCardState.playerCards()) + " - 결과: "
                            + allPlayerResult.playersScore().get(i));
        }

    }

    public void printResult(GameResultDto gameResult, List<Name> playerNames) {
        System.out.println("\n## 최종 승패");
        List<Boolean> result = gameResult.gameResult();
        System.out.printf("딜러: %d승 %d패\n", gameResult.dealerWinCount(),
                result.size() - gameResult.dealerWinCount());

        for (int i = 0; i < result.size(); i++) {
            String winOrLose = "패";
            if (result.get(i)) {
                winOrLose = "승";
            }
            System.out.println(playerNames.get(i).getValue() + ": " + winOrLose);
        }
    }
}
