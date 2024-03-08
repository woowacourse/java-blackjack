package view;

import cardGame.dto.BlackJackGameStatusDto;
import cardGame.dto.ParticipantsTotalGameResultDto;
import controller.dto.SinglePlayerResultDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import player.Name;
import player.dto.AllPlayerStatusDto;
import player.dto.SinglePlayerStatusDto;

public class OutputView {

    private static final int INIT_CARD_COUNT = 2;
    private static final int MIN_DEALER_SCORE = 16;
    private static final String PARTICIPANT_RESULT_SYMBOL = " : ";
    private static final String NAME_FORMAT_SYMBOL = ", ";

    public void printInitCardStatus(BlackJackGameStatusDto blackJackGameStatusDto) {
        AllPlayerStatusDto playersCardStatus = blackJackGameStatusDto.playerCardStatus();

        String playersNames = changeNameFormat(playersCardStatus.getNames());
        System.out.println("\n딜러와 " + playersNames + "에게 " + INIT_CARD_COUNT + "장을 나누었습니다.");

        System.out.println("딜러: " + blackJackGameStatusDto.dealerCards().getCardsStatus());

        for (int i = 0; i < playersCardStatus.size(); i++) {
            SinglePlayerStatusDto singlePlayerStatus = playersCardStatus.multiPlayersStatus().get(i);
            System.out.println(
                    singlePlayerStatus.name().getValue() + "카드: " + singlePlayerStatus.cards().getCardsStatus());
        }

        System.out.println();
    }

    public void printExtraCardInfo(int dealerExtraCardCount) {
        System.out.println();
        for (int i = 0; i < dealerExtraCardCount; i++) {
            System.out.println("딜러가 " + MIN_DEALER_SCORE + "이하라 한장의 카드를 더 받았습니다.\n");
        }
    }

    public void printPariticipantsScore(ParticipantsTotalGameResultDto participantsTotalGameResultDto) {
        Map<SinglePlayerStatusDto, Integer> participantsResult = participantsTotalGameResultDto.totalGameResult();

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
                .collect(Collectors.joining(NAME_FORMAT_SYMBOL));
    }

    public void printDealerResult(int dealerWinningCount, int dealerFailCount) {
        System.out.println("\n## 최종 승패");
        System.out.println(
                "딜러: " + dealerWinningCount + GameResultSymbol.WINNING_SYMBOL.symbolName + " " + dealerFailCount
                        + GameResultSymbol.LOSE_SYMBOL.symbolName);
    }

    public void printPlayerResult(List<SinglePlayerResultDto> winOrNotResults) {
        for (SinglePlayerResultDto singlePlayerResultDto : winOrNotResults) {
            System.out.println(singlePlayerResultDto.name().getValue() + PARTICIPANT_RESULT_SYMBOL
                    + GameResultSymbol.changeToSymbol(
                    singlePlayerResultDto.isWinner()).symbolName);
        }
    }
}
