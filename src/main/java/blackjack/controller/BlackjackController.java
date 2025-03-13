package blackjack.controller;

import java.util.List;

import blackjack.domain.GameManager;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.gamer.Player;
import blackjack.dto.GamerDto;
import blackjack.dto.response.FinalResultResponseDto;
import blackjack.dto.response.RoundResultsResponseDto;
import blackjack.dto.response.StartingCardsResponseDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    private Gamers gamers;
    private GameManager gameManager;

    public void run() {
        setPlayers();
        drawPlayerCards();
        drawDealerCards();
        printResult();
    }

    private void setPlayers() {
        List<String> names = InputView.readNames().names();
        Dealer dealer = new Dealer();
        gameManager = dealer;
        gamers = Gamers.of(dealer,
            names.stream()
                .map(Player::new)
                .toList());
        OutputView.printStartingCards(drawStartingCards());
    }

    public StartingCardsResponseDto drawStartingCards() {
        gameManager.drawStartingCards(gamers.getDealer());
        for (var player : gamers.getPlayers()) {
            gameManager.drawStartingCards(player);
        }
        return StartingCardsResponseDto.of(gamers.getDealer(), gamers.getPlayers());
    }

    private void drawPlayerCards() {
        for (var player : gamers.getPlayers()) {
            drawCardsFor(player);
        }
    }

    private void drawCardsFor(Player player) {
        while (player.canReceiveAdditionalCards()
            && InputView.readAdditionalCardSelection(player.getName()).selection()) {
            gameManager.drawCard(player);
            OutputView.printAdditionalCard(GamerDto.from(player));
        }
        if (!player.canReceiveAdditionalCards()) {
            OutputView.printBustNotice(player.getName());
        }
    }

    private void drawDealerCards() {
        Dealer dealer = gamers.getDealer();
        while (dealer.canReceiveAdditionalCards()) {
            gameManager.drawCard(dealer);
            OutputView.printDealerDrawNotice();
        }
    }

    private void printResult() {
        OutputView.printRoundResult(RoundResultsResponseDto.of(gamers.getDealer(), gamers.getPlayers()));
        OutputView.printFinalResult(FinalResultResponseDto.of(gamers.getDealer(), gamers.getPlayers()));
    }
}
