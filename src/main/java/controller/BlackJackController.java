package controller;

import view.Answer;
import domain.Betting;
import domain.Card;
import domain.Dealer;
import domain.GameResult;
import domain.Amount;
import domain.Name;
import domain.Participant;
import domain.Player;
import domain.Result;
import domain.GameResultAmount;
import domain.service.BlackJackService;
import dto.BettingDto;
import dto.CardStatusDto;
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
        BlackJackService blackJackService = new BlackJackService(inputView.requestPlayerName());
        Betting betting = getBetting(blackJackService.getPlayers());
        printInitialDistribution(blackJackService);
        progress(blackJackService);
        end(blackJackService, betting);
    }

    private Betting getBetting(List<Player> players) {
        BettingDto bettingDto = new BettingDto();
        for (Player player : players) {
            bettingDto.putPlayerAndAmount(player, new Amount(getBettingAmount(player)));
        }
        return new Betting(bettingDto.getBetting());
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

    private void end(BlackJackService blackJackService, Betting betting) {
        printFinalCard(blackJackService.getDealer());
        blackJackService.getPlayers().forEach(this::printFinalCard);
        outputView.printFinalResult(getResultOfBetting(blackJackService, betting));
    }

    private void printFinalCard(Participant participant) {
        outputView.printCardAndScore(participant.getNameValue(), getCardStatus(participant.getCards()), participant.getTotalScoreValue());
    }

    private Map<Name, Integer> getResultOfBetting(BlackJackService blackJackService, Betting betting) {
        return new GameResultAmount(betting, getResult(blackJackService.getDealer(), blackJackService.getPlayers()))
                .getResultOfBetting();
    }

    private Map<Name, GameResult> getResult(Dealer dealer, List<Player> players) {
        return new Result(dealer, players).getResult();
    }

    private List<CardStatusDto> getCardStatus(List<Card> cards) {
        return cards.stream()
                .map(CardStatusDto::from)
                .collect(Collectors.toList());
    }

}
