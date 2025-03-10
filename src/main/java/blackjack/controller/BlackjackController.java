package blackjack.controller;

import blackjack.domain.Round;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.dto.GamerDto;
import blackjack.dto.request.NamesRequestDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {

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
        round = new Round(dealer, players);
        OutputView.printStartingCards(round.initialize());
    }

    private List<Player> setPlayers() {
        NamesRequestDto namesRequestDto = InputView.readNames();
        return namesRequestDto.names().stream()
                .map(Player::new)
                .toList();
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
        OutputView.printFinalResult(round.getFinalResult());
    }
}
