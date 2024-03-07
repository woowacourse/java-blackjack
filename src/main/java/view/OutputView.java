package view;

import cardGame.dto.BlackJackGameStatus;
import cardGame.dto.ParticipantsTotalGameResult;
import controller.dto.SingleWinOrNotResult;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import player.Name;
import player.dto.PlayersCardStatusDto;
import player.dto.SinglePlayerStatusDto;

public class OutputView {

    public void printInitCardStatus(BlackJackGameStatus blackJackGameStatus) {
        PlayersCardStatusDto playersCardStatus = blackJackGameStatus.playerCardStatus();

        String playersNames = changeNameFormat(playersCardStatus.getNames());
        System.out.println("\n딜러와 " + playersNames + "에게 2장을 나누었습니다.");

        System.out.println("딜러: " + blackJackGameStatus.dealerCards().getCardsStatus());

        for (int i = 0; i < playersCardStatus.size(); i++) {
            SinglePlayerStatusDto singlePlayerStatus = playersCardStatus.multiPlayersStatus().get(i);
            System.out.println(
                    singlePlayerStatus.name().getValue() + "카드: " + singlePlayerStatus.cards().getCardsStatus());
        }

        System.out.println();
    }

    public void printInfo(int dealerExtraCardCount) {
        System.out.println();
        for (int i = 0; i < dealerExtraCardCount; i++) {
            System.out.println("딜러가 16이하라 한장의 카드를 더 받았습니다.\n");
        }
    }

    public void printPariticipantsScore(ParticipantsTotalGameResult participantsTotalGameResult) {
        Map<SinglePlayerStatusDto, Integer> participantsResult = participantsTotalGameResult.totalGameResult();

        for (SinglePlayerStatusDto singlePlayerStatusDto : participantsResult.keySet()) {
            System.out.println(
                    makeCardsStatus(singlePlayerStatusDto) + " - 결과: " + participantsResult.get(singlePlayerStatusDto));
        }
    }

    public void printCardsStatus(SinglePlayerStatusDto singlePlayerStatus) {
        System.out.println(makeCardsStatus(singlePlayerStatus));
    }

    private String makeCardsStatus(SinglePlayerStatusDto singlePlayerStatus) {
        return singlePlayerStatus.name().getValue() + "카드: " + singlePlayerStatus.cards().getCardsStatus();
    }

    private String changeNameFormat(List<Name> names) {
        return names.stream()
                .map(Name::getValue)
                .collect(Collectors.joining(", "));
    }

    public void printDealerResult(int dealerWinningCount, int size) {
        System.out.println("\n## 최종 승패");
        System.out.println(
                "딜러: " + dealerWinningCount + GameResultSymbol.WINNING_SYMBOL.symbolName + " " + (size
                        - dealerWinningCount)
                        + GameResultSymbol.LOSE_SYMBOL.symbolName);
    }

    public void printPlayerResult(List<SingleWinOrNotResult> winOrNotResults) {
        for (SingleWinOrNotResult singleWinOrNotResult : winOrNotResults) {
            System.out.println(singleWinOrNotResult.name().getValue() + " : " + GameResultSymbol.changeToSymbol(
                    singleWinOrNotResult.isWinner()).symbolName);
        }
    }
}
