package blackjack.view;

import blackjack.dto.CardDto;
import blackjack.dto.DealerInitialHandDto;
import blackjack.dto.HandDto;
import blackjack.dto.PlayerGameResultsDto;
import blackjack.dto.PlayerHandDto;
import blackjack.dto.PlayersHandDto;
import blackjack.view.object.CardNumberOutput;
import blackjack.view.object.CardShapeOutput;
import java.util.List;

public class OutputView {

    private static final String DEALER_DEFAULT_NAME = "딜러";

    public void printInitialHands(DealerInitialHandDto dealerInitialHandDto, PlayersHandDto playersInitialHandDto) {
        System.out.printf("\n%s와 %s에게 2장을 나누었습니다.\n", DEALER_DEFAULT_NAME,
                joinPlayerNames(playersInitialHandDto));

        System.out.printf("%s 카드: %s\n", DEALER_DEFAULT_NAME, formatCardName(dealerInitialHandDto.firstCard()));
        printAllPlayerHands(playersInitialHandDto);
    }

    private String joinPlayerNames(PlayersHandDto playersInitialHandDto) {
        return String.join(", ", getPlayerNames(playersInitialHandDto));
    }

    private List<String> getPlayerNames(PlayersHandDto playersInitialHandDto) {
        return playersInitialHandDto.playersHandDto().stream().map(PlayerHandDto::name).toList();
    }

    private String formatCardName(CardDto cardDto) {
        return CardNumberOutput.convertNumberToOutput(cardDto.cardNumber()) + CardShapeOutput.convertShapeToOutput(
                cardDto.cardShape());
    }

    private void printAllPlayerHands(PlayersHandDto playersInitialHandDto) {
        playersInitialHandDto.playersHandDto().forEach(this::printPlayerHand);
        System.out.println();
    }

    public void printPlayerHand(PlayerHandDto playerHandDto) {
        System.out.println(getPlayerNameOutputFormat(playerHandDto));
    }

    public void printDealerHand(HandDto dealerHandDto) {
        System.out.println(getDealerNameOutputFormat(dealerHandDto));
    }

    private StringBuilder getPlayerNameOutputFormat(PlayerHandDto playerHandDto) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder
                .append(playerHandDto.name())
                .append("카드: ")
                .append(joinCardNames(playerHandDto.playerHand()));
    }

    private StringBuilder getDealerNameOutputFormat(HandDto dealerHandDto) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder
                .append(DEALER_DEFAULT_NAME)
                .append("카드: ")
                .append(joinCardNames(dealerHandDto));
    }

    private String joinCardNames(HandDto handDto) {
        return String.join(", ", getCardNames(handDto));
    }

    private List<String> getCardNames(HandDto handDto) {
        return handDto.cardDtos().stream().map(this::formatCardName).toList();
    }

    public void printPlayersHandScore(PlayersHandDto playersHandDto) {
        for (PlayerHandDto playerHandDto : playersHandDto.playersHandDto()) {
            System.out.println(getPlayerNameOutputFormat(playerHandDto)
                    .append(" - 결과: ")
                    .append(playerHandDto.playerHand().handScore()));
        }
    }

    public void printDealerHandScore(HandDto dealerHandDto) {
        System.out.println();
        System.out.println(getDealerNameOutputFormat(dealerHandDto)
                .append(" - 결과: ")
                .append(dealerHandDto.handScore()));
    }

    public void printDealerMessage() {
        System.out.printf("\n%s는 16이하라 한장의 카드를 더 받았습니다.\n", DEALER_DEFAULT_NAME);
    }

    public void printResult(int dealerIncome, PlayerGameResultsDto playerGameResultsDto) {
        System.out.println("\n## 최종 수익");
        printDealerResult(dealerIncome);
        printPlayerResults(playerGameResultsDto);
    }

    private void printDealerResult(int dealerIncome) {
        String stringBuilder = DEALER_DEFAULT_NAME + ": " + dealerIncome;
        System.out.println(stringBuilder);
    }

    private void printPlayerResults(PlayerGameResultsDto playerGameResultsDto) {
        playerGameResultsDto.playerIncomeResults().forEach((name, money) ->
                System.out.println(name + ": " + money)
        );
    }

    public void printEmptyLine() {
        System.out.println();
    }
}
