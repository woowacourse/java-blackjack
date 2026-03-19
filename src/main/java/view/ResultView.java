package view;

import domain.card.Card;
import dto.view.ParticipantProfitDto;
import dto.view.ParticipantStatsDto;
import dto.view.PlayerCardsDto;
import dto.view.PlayerOutcomeDto;
import dto.view.PlayerProfitDto;
import dto.view.ResultDto;
import dto.view.StartBlackJackDto;
import java.util.List;
import java.util.stream.Collectors;
import message.IOMessage;

public class ResultView {
    public void printLineBreak() {
        System.out.println();
    }

    public void printPlayerCards(String playerName, String cardNames) {
        System.out.println(playerName + "카드: " + cardNames);
    }

    public void printPlayerBust(String playerName) {
        System.out.println(playerName + "는 버스트!");
    }

    public void printDealerDrawMessage() {
        System.out.println(IOMessage.DEALER_HIT_ONE_CARD.message());
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
        System.out.println(IOMessage.WINNING_STATISTICS.message());
        printDealerOutcome(participantStatsDto);
        printPlayerOutcomes(participantStatsDto);
    }

    public void printFinalProfit(ParticipantProfitDto participantProfitDto) {
        System.out.println();
        System.out.println(IOMessage.FINAL_PROFIT.message());
        System.out.printf("%s: %d%n", IOMessage.DEALER_NAME.message(), participantProfitDto.dealerProfit());
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

    public String joinCardNames(final List<Card> cards) {
        return cards.stream()
                .map(this::formatCardName)
                .collect(Collectors.joining(", "));
    }

    private String formatCardName(final Card card) {
        return card.getRank().symbol() + card.getSuit().suit();
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
