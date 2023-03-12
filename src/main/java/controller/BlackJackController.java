package controller;

import domain.Card;
import domain.Dealer;
import domain.GameResult;
import domain.GameResultAmount;
import domain.Name;
import domain.Participant;
import domain.Player;
import domain.Result;
import domain.BlackJacService;
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
        BlackJacService blackJacService = new BlackJacService(inputView.requestPlayerName());
        getBettingAmountOfPlayers(blackJacService);
        printInitialDistribution(blackJacService);
        progress(blackJacService);
        end(blackJacService);
    }

    private void getBettingAmountOfPlayers(BlackJacService blackJacService) {
        for (Player player : blackJacService.getPlayers()) {
            player.betAmount(getBettingAmount(player));
        }
    }

    private int getBettingAmount(Player player) {
        return inputView.requestBettingAmount(player.getNameValue());
    }

    private void printInitialDistribution(BlackJacService blackJacService) {
        outputView.printFirstCardDistribution(blackJacService.getDealerName(), blackJacService.getPlayerNames());
        outputView.printCardStatus(blackJacService.getDealerName(), getCardStatus(blackJacService.showOneCardOfDealerCards()));
        for (Player player : blackJacService.getPlayers()) {
            outputView.printCardStatus(player.getNameValue(), getCardStatus(player.getCards()));
        }
    }

    private void progress(BlackJacService blackJacService) {
        for (Player player : blackJacService.getPlayers()) {
            requestPlayerMoreCard(blackJacService, player);
        }

        blackJacService.drawUntilDealerNoMoreCard();
        outputView.printDealerMoreCard(blackJacService.getDealerName(), blackJacService.getDealerMoreCardCount());
    }

    private void requestPlayerMoreCard(BlackJacService blackJacService, Player player) {
        Answer isCardRequested = Answer.MORE_CARD;

        while (player.isMoreCardAble() && isCardRequested.isMoreCard()) {
            isCardRequested = Answer.from(inputView.askMoreCard(player.getNameValue()));
            proceedOnce(blackJacService, player, isCardRequested);
        }
    }

    private void proceedOnce(BlackJacService blackJacService, Player player, Answer answer) {
        if (answer.isMoreCard()) {
            blackJacService.distributeCardToPlayer(player);
        }

        outputView.printCardStatus(player.getNameValue(), getCardStatus(player.getCards()));
    }

    private void end(BlackJacService blackJacService) {
        printFinalCard(blackJacService.getDealer());
        blackJacService.getPlayers().forEach(this::printFinalCard);
        outputView.printFinalResult(getResultOfBetting(blackJacService));
    }

    private void printFinalCard(Participant participant) {
        outputView.printCardAndScore(participant.getNameValue(), getCardStatus(participant.getCards()), participant.getTotalScoreValue());
    }

    private Map<Name, Integer> getResultOfBetting(BlackJacService blackJacService) {
        return new GameResultAmount(getResult(blackJacService.getDealer(), blackJacService.getPlayers()))
                .getResultOfBetting();
    }

    private Map<Player, GameResult> getResult(Dealer dealer, List<Player> players) {
        return new Result(dealer, players)
                .getResult();
    }

    private List<CardStatusDto> getCardStatus(List<Card> cards) {
        return cards.stream()
                .map(CardStatusDto::new)
                .collect(Collectors.toList());
    }

}
