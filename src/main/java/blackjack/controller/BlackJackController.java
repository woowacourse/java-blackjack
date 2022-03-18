package blackjack.controller;

import java.util.List;

import blackjack.domain.BlackJack;
import blackjack.domain.ProfitResult;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.PlayerGroup;
import blackjack.domain.result.GameResult;
import blackjack.dto.DealerResultDto;
import blackjack.dto.GamerCardsDto;
import blackjack.dto.GamerCardsResultDto;
import blackjack.dto.PlayerResultDto;
import blackjack.dto.ProfitResultsDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {
    private PlayerGroup playerGroup;
    private BlackJack blackJack;

    public void run() {
        initialize();
        play();
        finish();
    }

    private void initialize() {
        initializePlayerGroup();
        addInputMoney();
        initializeBlackJack();
        OutputView.printGamersCards(GamerCardsDto.of(blackJack.getGamers()));
    }

    private void initializeBlackJack() {
        blackJack = new BlackJack(playerGroup);
        blackJack.divideCards();
    }

    private void addInputMoney() {
        try {
            requestAndAddInputMoney();
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            addInputMoney();
        }
    }

    private void requestAndAddInputMoney() {
        List<Player> players = playerGroup.getPlayers();
        for (Player player : players) {
            player.addMoney(InputView.requestInputMoney(player.getName()));
        }
    }

    private void initializePlayerGroup() {
        try {
            playerGroup =  new PlayerGroup(Player.of(InputView.requestPlayerNames()));
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            initializePlayerGroup();
        }
    }

    private void play() {
        List<Player> players = playerGroup.getPlayers();
        for (Player player : players) {
            requestCardAddition(player);
        }
        OutputView.printDealerCardMessage(blackJack.playDealer());
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
        printProfit();
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

    private void printProfit() {
        ProfitResult profitResult = blackJack.getProfitResult();
        OutputView.printProfitResult(ProfitResultsDto.of(profitResult.get()));
    }
}
