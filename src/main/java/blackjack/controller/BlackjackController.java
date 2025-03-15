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
    private Round round;

    public void run() {
        setRound();
        drawPlayerCards();
        drawDealerCards();
        printResult();
    }

    private void setRound() {
        Dealer dealer = new Dealer();
        List<Player> players = setPlayers();
        getPlayerBets(players);
        round = new Round(dealer, players);
        OutputView.printStartingCards(round.initialize());
    }

    private List<Player> setPlayers() {
        NamesRequestDto namesRequestDto = InputView.readNames();
        return namesRequestDto.names().stream()
                .map(Player::new)
                .toList();
    }

    private void getPlayerBets(List<Player> players) {
        for (Player player : players) {
            BetsRequestDto betsRequestDto = InputView.readBets(player.getName());
            profitCalculator.addPlayerBet(player, betsRequestDto.amount());
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
