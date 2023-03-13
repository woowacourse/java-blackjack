package controller;

import domain.Amount;
import domain.BettingAmount;
import domain.Card;
import domain.GameManager;
import domain.Participant;
import domain.Player;
import dto.CardStatusDto;
import view.Answer;
import view.InputView;
import view.OutputView;

import java.util.HashMap;
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
        BettingAmount bettingAmount = getBettingAmountOfPlayers(gameManager);
        printInitialDistribution(gameManager);
        progress(gameManager);
        end(gameManager, bettingAmount);
    }

    private BettingAmount getBettingAmountOfPlayers(GameManager gameManager) {
        Map<Player, Amount> bettingAmountOfPlayers = new HashMap<>();

        for (Player player : gameManager.getPlayers()) {
            bettingAmountOfPlayers.put(player, getBettingAmount(player));
        }

        return new BettingAmount(bettingAmountOfPlayers);
    }

    private Amount getBettingAmount(Player player) {
        return new Amount(inputView.requestBettingAmount(player.getNameValue()));
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

    private void end(GameManager gameManager, BettingAmount bettingAmount) {
        printFinalCard(gameManager.getDealer());
        gameManager.getPlayers().forEach(this::printFinalCard);
        outputView.printFinalResult(gameManager.getCameResultAmount(bettingAmount));
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
