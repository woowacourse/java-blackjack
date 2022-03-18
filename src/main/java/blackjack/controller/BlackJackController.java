package blackjack.controller;

import blackjack.domain.BlackJack;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.PlayerGroup;
import blackjack.domain.result.GameResult;
import blackjack.dto.DealerResultDto;
import blackjack.dto.GamerCardsDto;
import blackjack.dto.GamerCardsResultDto;
import blackjack.dto.PlayerResultDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {
    private BlackJack blackJack;

    public void run() {
        initialize();
        play();
        finish();
    }

    private void initialize() {
        blackJack = new BlackJack(initializePlayerGroup(), new Dealer());
        blackJack.divideCards();
        OutputView.printGamersCards(GamerCardsDto.of(blackJack.getGamers()));
    }

    private PlayerGroup initializePlayerGroup() {
        try {
            return new PlayerGroup(Player.of(InputView.requestPlayerNames()));
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return initializePlayerGroup();
        }
    }

    private void play() {
        List<String> playerNames = blackJack.getPlayerNames();
        for (String playerName : playerNames) {
            requestHitOrStand(playerName);
        }
        OutputView.printDealerCardMessage(blackJack.addCardToDealer());
    }

    private void requestHitOrStand(String playerName) {
        Player player = blackJack.findPlayerByName(playerName);
        if (InputView.requestHit(playerName)) {
            blackJack.addCardTo(player);
            OutputView.printGamerCard(GamerCardsDto.of(playerName, player.getCards()));
            requestStatus(player);
            return;
        }
        OutputView.printGamerCard(GamerCardsDto.of(playerName, player.getCards()));
    }

    private void requestStatus(Player player) {
        if (player.isNotBust()) {
            requestHitOrStand(player.getName());
        }
    }

    private void finish() {
        printCards();
        printResult();
    }

    private void printCards() {
        blackJack.openDealerCards();
        OutputView.printGamersCardAndSum(GamerCardsResultDto.of(blackJack.getGamers()));
    }

    private void printResult() {
        GameResult gameResult = blackJack.getGameResult();
        OutputView.printGameResult(DealerResultDto.of(gameResult.getDealerResult()),
                PlayerResultDto.of(gameResult.getPlayerResult()));
    }
}
