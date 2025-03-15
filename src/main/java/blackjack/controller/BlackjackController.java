package blackjack.controller;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.round.ProfitCalculator;
import blackjack.domain.round.Round;
import blackjack.dto.GamerDto;
import blackjack.dto.request.BetsRequestDto;
import blackjack.dto.request.NamesRequestDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {

    private final ProfitCalculator profitCalculator = new ProfitCalculator();
    private final Round round = new Round();

    public void run() {
        setRound();
        drawPlayerCards();
        drawDealerCards();
        printResult();
    }

    private void setRound() {
        NamesRequestDto namesRequestDto = InputView.readNames();
        getPlayerBets(namesRequestDto.names());
        round.initialize(namesRequestDto.names());
        OutputView.printStartingCards(round.getStartingCards());
    }

    private void getPlayerBets(List<String> playerNames) {
        for (String name : playerNames) {
            BetsRequestDto betsRequestDto = InputView.readBets(name);
            profitCalculator.addPlayerBet(name, betsRequestDto.amount());
        }
    }

    private void drawPlayerCards() {
        List<Player> players = round.getPlayers();
        for (Player player : players) {
            drawCardsForCurrentPlayer(player);
        }
    }

    private void drawCardsForCurrentPlayer(Player currentPlayer) {
        String currentPlayerName = currentPlayer.getName();
        while (InputView.readAdditionalCardSelection(currentPlayerName).selection()) {
            round.drawPlayerCard(currentPlayerName);
            OutputView.printAdditionalCard(GamerDto.from(currentPlayer));
            if (!currentPlayer.canReceiveAdditionalCards()) {
                OutputView.printBustNotice(currentPlayerName);
                break;
            }
        }
    }

    private void drawDealerCards() {
        Dealer dealer = round.getDealer();
        while (dealer.canReceiveAdditionalCards()) {
            round.drawDealerCard();
            OutputView.printDealerDrawNotice();
        }
    }

    private void printResult() {
        OutputView.printRoundResult(round.getRoundResults());
        OutputView.printFinalResult(profitCalculator.getProfits(round));
    }
}
