package controller;

import domain.Card;
import domain.Dealer;
import domain.GameManager;
import domain.GameResult;
import domain.GameResultAmount;
import domain.Name;
import domain.Participant;
import domain.Player;
import domain.Result;
import dto.CardStatusDto;
import view.Answer;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void run() {
        GameManager gameManager = new GameManager(inputView.requestPlayerName());
        getBettingAmountOfPlayers(gameManager);
        printInitialDistribution(gameManager);
        progress(gameManager);
        end(gameManager);
    }

    private void getBettingAmountOfPlayers(GameManager gameManager) {
        for (Player player : gameManager.getPlayers()) {
            player.betAmount(getBettingAmount(player));
        }
    }

    private int getBettingAmount(Player player) {
        return inputView.requestBettingAmount(player.getNameValue());
    }

    private void printInitialDistribution(GameManager gameManager) {
        outputView.printFirstCardDistribution(gameManager.getDealerName(), gameManager.getPlayerNames());
        outputView.printCardStatus(gameManager.getDealerName(), getCardStatus(gameManager.showOneCardOfDealerCards()));
        for (Player player : gameManager.getPlayers()) {
            outputView.printCardStatus(player.getNameValue(), getCardStatus(player.getCards()));
        }
    }

    private void progress(GameManager gameManager) {
        for (Player player : gameManager.getPlayers()) {
            requestPlayerMoreCard(gameManager, player);
        }

        gameManager.drawUntilDealerNoMoreCard();
        outputView.printDealerMoreCard(gameManager.getDealerName(), gameManager.getDealerMoreCardCount());
    }

    private void requestPlayerMoreCard(GameManager gameManager, Player player) {
        Answer isCardRequested = Answer.MORE_CARD;

        while (player.isMoreCardAble() && isCardRequested.isMoreCard()) {
            isCardRequested = inputView.askMoreCard(player.getNameValue());
            proceedOnce(gameManager, player, isCardRequested);
        }
    }

    private void proceedOnce(GameManager gameManager, Player player, Answer answer) {
        if (answer.isMoreCard()) {
            gameManager.distributeCardToPlayer(player);
        }

        outputView.printCardStatus(player.getNameValue(), getCardStatus(player.getCards()));
    }

    private void end(GameManager gameManager) {
        printFinalCard(gameManager.getDealer());
        gameManager.getPlayers().forEach(this::printFinalCard);
        outputView.printFinalResult(gameManager.getCameResultAmount());
    }

    private void printFinalCard(Participant participant) {
        outputView.printCardAndScore(participant.getNameValue(),
                getCardStatus(participant.getCards()),
                participant.getTotalScoreValue());
    }

    private List<CardStatusDto> getCardStatus(List<Card> cards) {
        return cards.stream()
                .map(CardStatusDto::new)
                .collect(Collectors.toList());
    }

}
