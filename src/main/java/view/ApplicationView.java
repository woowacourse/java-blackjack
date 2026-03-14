package view;

import domain.answer.DrawDecision;
import domain.betting.BettingMoney;
import domain.betting.dto.GamerBettingProfitDto;
import domain.gamer.Players;
import exception.BlackjackGameException;
import domain.gamer.PlayerName;
import domain.gamer.dto.GamerHandDto;
import domain.gamer.dto.GamerResultDto;
import domain.gamer.dto.GamersNameDto;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ApplicationView {

    private static final String PLAYER_NAME_DELIMITER = ",";

    public InputReader reader;
    public OutputWriter writer;

    public ApplicationView(InputReader reader, OutputWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public Players enterPlayers() {
        return retry(() -> {
            writer.printInputNameGuideMessage();
            String names = readInput();
            List<PlayerName> playerNames = Arrays.stream(names.split(PLAYER_NAME_DELIMITER))
                    .map(String::trim)
                    .map(PlayerName::new)
                    .toList();
            return Players.enterByPlayerNames(playerNames);
        });
    }

    public BettingMoney askBettingMoney(String playerName) {
        return retry(() -> {
            writer.printBettingGuideMessage(playerName);
            String money = readInput();
            return BettingMoney.bet(money);
        });
    }

    public DrawDecision askDrawCard(String playerName) {
        return retry(() -> {
            writer.printDrawCardGuideMessage(playerName);
            String userDecision = readInput();
            return DrawDecision.from(userDecision);
        });
    }

    public void printFirstHandOutResult(GamersNameDto playerNames) {
        String formattedNames = String.join(PLAYER_NAME_DELIMITER, playerNames.playerNames());
        writer.printDealInitialCardMessage(formattedNames);
    }

    public void printAllParticipantsHand(List<GamerHandDto> playerHands) {
        for (GamerHandDto playerHand : playerHands) {
            printParticipantHand(playerHand);
        }
    }

    public void printParticipantHand(GamerHandDto playerHand) {
        writer.printAllParticipantsHand(playerHand.playerName(), playerHand.handOnCards());
    }

    public void printDealerAdditionalDrawCardMessage() {
        writer.printDealerAdditionalDrawCardMessage();
    }

    public void printFinalResultMessage(GamerResultDto playerResult) {
        GamerHandDto playerHand = playerResult.playerHand();
        writer.printFinalResultMessage(playerHand.playerName(), playerHand.handOnCards(), playerResult.resultScore());
    }

    public void printGamerProfit(GamerBettingProfitDto dealerProfit, List<GamerBettingProfitDto> playersProfit) {
        writer.printProfitTitleMessage();
        writer.printGamerProfit(dealerProfit.gamerName(), dealerProfit.bettingProfit());
        printAllPlayerProfit(playersProfit);
    }

    private void printAllPlayerProfit(List<GamerBettingProfitDto> playersProfit) {
        playersProfit.forEach(profit ->
                writer.printGamerProfit(profit.gamerName(), profit.bettingProfit())
        );
    }

    private <T> T retry(Supplier<T> task) {
        while (true) {
            try {
                return task.get();
            } catch (BlackjackGameException e) {
                writer.printErrorMessage(e.getMessage());
            }
        }
    }

    private String readInput() {
        return reader.readInput();
    }

}
