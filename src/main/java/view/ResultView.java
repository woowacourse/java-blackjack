package view;

import dto.view.ParticipantProfitDto;
import dto.view.ParticipantStatsDto;
import dto.view.PlayerCardsDto;
import dto.view.PlayerOutcomeDto;
import dto.view.PlayerProfitDto;
import dto.view.ResultDto;
import dto.view.StartBlackJackDto;
import java.util.List;

public class ResultView {
    private static final String DEALER_HIT_ONE_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String WINNING_STATISTICS = "## 최종 승패";
    private static final String FINAL_PROFIT = "## 최종 수익";
    private static final String DEALER_NAME = "딜러";

    public void printLineBreak() {
        System.out.println();
    }

    public void printPlayerCards(final PlayerCardsDto playerCardsDto) {
        System.out.println(playerCardsDto.name() + "카드: " + String.join(", ", playerCardsDto.cards()));
    }

    public void printPlayerBust(String playerName) {
        System.out.println(playerName + "는 버스트!");
    }

    public void printDealerDrawMessage() {
        System.out.println(DEALER_HIT_ONE_CARD);
    }

    public void printDealerBust() {
        System.out.println("딜러는 버스트!");
    }

    public void printGameStartMessage(StartBlackJackDto startBlackJackDto) {
        System.out.println();
        printGameStart(startBlackJackDto);
        System.out.println();
    }

    public void printGameStart(StartBlackJackDto startBlackJackDto) {
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", joinPlayerNames(startBlackJackDto.playerCards()));
        System.out.printf("딜러카드: %s%n", startBlackJackDto.dealerCard());
        startBlackJackDto.playerCards().forEach(player -> System.out.printf(
                "%s카드: %s%n",
                player.name(),
                String.join(", ", player.cards())
        ));
    }

    public void printResult(ResultDto resultDto) {
        System.out.println("딜러카드: " + resultDto.dealer().cards() + " - 결과: " + resultDto.dealer().score());
        resultDto.players().forEach(player -> System.out.printf(
                "%s카드: %s - 결과: %s%n",
                player.name(),
                player.cards(),
                player.score()
        ));
    }

    public void printWinner(ParticipantStatsDto participantStatsDto) {
        System.out.println();
        System.out.println(WINNING_STATISTICS);
        printDealerOutcome(participantStatsDto);
        printPlayerOutcomes(participantStatsDto);
    }

    public void printFinalProfit(ParticipantProfitDto participantProfitDto) {
        System.out.println();
        System.out.println(FINAL_PROFIT);
        System.out.printf("%s: %d%n", DEALER_NAME, participantProfitDto.dealerProfit());
        participantProfitDto.playerProfits().forEach(this::printPlayerProfit);
    }

    private String joinPlayerNames(List<PlayerCardsDto> playerCardsDtos) {
        final StringBuilder playerNames = new StringBuilder();
        playerCardsDtos.forEach(player -> {
            if (playerNames.length() > 0) {
                playerNames.append(", ");
            }
            playerNames.append(player.name());
        });
        return playerNames.toString();
    }

    private void printDealerOutcome(ParticipantStatsDto participantStatsDto) {
        System.out.printf(
                "딜러: %d승 %d패 %d무%n",
                participantStatsDto.dealerStat().win(),
                participantStatsDto.dealerStat().lose(),
                participantStatsDto.dealerStat().draw()
        );
    }

    private void printPlayerOutcomes(ParticipantStatsDto participantStatsDto) {
        participantStatsDto.playerStats().forEach(this::printPlayerOutcome);
    }

    private void printPlayerOutcome(PlayerOutcomeDto playerOutcomeDto) {
        System.out.println(playerOutcomeDto.name() + ": " + playerOutcomeDto.outcome());
    }

    private void printPlayerProfit(PlayerProfitDto playerProfitDto) {
        System.out.printf("%s: %d%n", playerProfitDto.name(), playerProfitDto.profit());
    }
}
