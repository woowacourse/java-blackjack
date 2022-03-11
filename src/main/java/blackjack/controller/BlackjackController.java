package blackjack.controller;

import static blackjack.view.InputView.requestMoreCardInput;
import static blackjack.view.OutputView.printAllCardsAndScore;
import static blackjack.view.OutputView.printDealerExtraCardInfo;
import static blackjack.view.OutputView.printGameResult;
import static blackjack.view.OutputView.printPlayerBustInfo;
import static blackjack.view.OutputView.printPlayerCardsInfo;

import blackjack.domain.card.CardDeck;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.ResultReferee;
import blackjack.domain.participant.Player;
import blackjack.dto.InitialDistributionDto;
import blackjack.dto.ResultStatisticsDto;
import java.util.List;

public class BlackjackController {

    public BlackjackGame initializeGame(List<String> playerNames) {
        return new BlackjackGame(new CardDeck(), playerNames);
    }

    public InitialDistributionDto getInitialDistribution(BlackjackGame game) {
        return new InitialDistributionDto(game.getParticipants());
    }

    public void distributeAllCards(BlackjackGame game) {
        List<Player> players = game.getPlayers();
        for (Player player : players) {
            drawAllPlayerCards(player, game);
        }

        drawDealerCard(game);
    }

    private void drawAllPlayerCards(Player player, BlackjackGame game) {
        while (player.canReceive()) {
            if (!requestMoreCardInput(player.getName())) {
                return;
            }
            player.receiveCard(game.popCard());
            printPlayerCardsInfo(player);
        }

        printPlayerBustInfo();
    }

    private void drawDealerCard(BlackjackGame game) {
        if (game.giveCardToDealer()) {
            printDealerExtraCardInfo();
        }
    }

    public void getGameResult(BlackjackGame game) {
        ResultReferee referee = new ResultReferee(game.getDealer(), game.getPlayers());
        ResultStatisticsDto dto = new ResultStatisticsDto(referee.initAndGetGameResults());

        printAllCardsAndScore(dto);
        printGameResult(dto);
    }
}
