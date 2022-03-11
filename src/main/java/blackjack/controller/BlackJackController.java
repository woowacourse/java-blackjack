package blackjack.controller;

import blackjack.domain.BlackJack;
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
    private PlayerGroup playerGroup;
    private BlackJack blackJack;

    public void run() {
        initialize();
        play();
        finish();
    }

    private void initialize() {
        playerGroup = initializePlayerGroup();
        blackJack = new BlackJack(playerGroup);
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
        List<Player> players = playerGroup.getPlayers();
        for (Player player : players) {
            requestCardAddition(player);
        }
        OutputView.printDealerCardMessage(blackJack.addCardToDealer());
    }

    private void requestCardAddition(Player player) {
        if (InputView.requestCardAddition(player.getName())) {
            blackJack.addCardTo(player);
            OutputView.printGamerCard(GamerCardsDto.of(player.getName(), player.getCardGroup()));
            requestCardAdditionToPlayer(player);
            return;
        }
        OutputView.printGamerCard(GamerCardsDto.of(player.getName(), player.getCardGroup()));
    }

    private void requestCardAdditionToPlayer(Player player) {
        if (player.isAddable()) {
            requestCardAddition(player);
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
