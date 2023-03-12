package controller;

import view.Answer;
import domain.Card;
import domain.Dealer;
import domain.GameResult;
import domain.Name;
import domain.Participant;
import domain.Player;
import domain.Result;
import domain.service.BlackJackService;
import dto.CardStatusDto;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
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
        BlackJackService blackJackService = new BlackJackService(inputView.requestPlayerName());
        blackJackService.betPlayers(getBettingAmountOfPlayers(blackJackService));
        printInitialDistribution(blackJackService);
        progress(blackJackService);
        end(blackJackService);
    }

    private List<Integer> getBettingAmountOfPlayers(BlackJackService blackJackService) {
        List<Integer> bettingAmount = new ArrayList<>();

        for (Player player : blackJackService.getPlayers()) {
            bettingAmount.add(getBettingAmount(player));
        }

        return bettingAmount;
    }

    private int getBettingAmount(Player player) {
        return inputView.requestBettingAmount(player.getNameValue());
    }

    private void printInitialDistribution(BlackJackService blackJackService) {
        outputView.printFirstCardDistribution(blackJackService.getDealerName(), blackJackService.getPlayerNames());
        outputView.printCardStatus(blackJackService.getDealerName(), getCardStatus(blackJackService.showOneCardOfDealerCards()));
        for (Player player : blackJackService.getPlayers()) {
            outputView.printCardStatus(player.getNameValue(), getCardStatus(player.getCards()));
        }
    }

    private void progress(BlackJackService blackJackService) {
        for (Player player : blackJackService.getPlayers()) {
            requestPlayerMoreCard(blackJackService, player);
        }

        blackJackService.drawUntilDealerNoMoreCard();
        outputView.printDealerMoreCard(blackJackService.getDealerName(), blackJackService.getDealerMoreCardCount());
    }

    private void requestPlayerMoreCard(BlackJackService blackJackService, Player player) {
        Answer isCardRequested = Answer.MORE_CARD;

        while (player.isMoreCardAble() && isCardRequested.isMoreCard()) {
            isCardRequested = Answer.from(inputView.askMoreCard(player.getNameValue()));
            proceedOnce(blackJackService, player, isCardRequested);
        }
    }

    private void proceedOnce(BlackJackService blackJackService, Player player, Answer answer) {
        if (answer.isMoreCard()) {
            player.pick(blackJackService.distributeCard());
        }

        outputView.printCardStatus(player.getNameValue(), getCardStatus(player.getCards()));
    }

    private void end(BlackJackService blackJackService) {
        printFinalCard(blackJackService.getDealer());
        blackJackService.getPlayers().forEach(this::printFinalCard);
//        outputView.printFinalResult(getResultOfBetting(blackJackService, bettingAmount));
    }

    private void printFinalCard(Participant participant) {
        outputView.printCardAndScore(participant.getNameValue(), getCardStatus(participant.getCards()), participant.getTotalScoreValue());
    }

//    private Map<Name, Integer> getResultOfBetting(BlackJackService blackJackService) {
//        return new GameResultAmount(bettingAmount, getResult(blackJackService.getDealer(), blackJackService.getPlayers()))
//                .getResultOfBetting();
//    }

    private Map<Player, GameResult> getResult(Dealer dealer, List<Player> players) {
        return new Result(dealer, players)
                .getResult();
    }

    private List<CardStatusDto> getCardStatus(List<Card> cards) {
        return cards.stream()
                .map(CardStatusDto::from)
                .collect(Collectors.toList());
    }

}
