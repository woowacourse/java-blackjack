package controller;

import domain.Answer;
import domain.Betting;
import domain.Card;
import domain.CardDistributor;
import domain.Dealer;
import domain.Money;
import domain.Name;
import domain.Participant;
import domain.Player;
import domain.Players;
import domain.Result;
import domain.ResultCalculator;
import dto.BettingDto;
import dto.CardStatusDto;
import util.CardsMaker;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void run() {
        List<String> playerNames = inputView.requestPlayerName();
        CardDistributor cardDistributor = new CardDistributor(CardsMaker.generate());
        Players players = Players.of(playerNames, cardDistributor);
        Betting betting = getBetting(players);
        Dealer dealer = new Dealer(cardDistributor.distributeInitialCard());
        printInitialDistribution(players, dealer);
        progress(players, cardDistributor, dealer);
        end(players, dealer, betting);
    }

    private Betting getBetting(Players players) {
        BettingDto bettingDto = new BettingDto();
        for (Player player : players.getPlayers()) {
            bettingDto.putPlayerAndMoney(player, new Money(inputView.requestBettingMoney(player.getNameValue())));
        }
        return new Betting(bettingDto.getBetting());
    }

    private void progress(Players players, CardDistributor cardDistributor, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            requestPlayerMoreCard(cardDistributor, player);
        }
        int dealerMoreCardCount = 0;
        while (dealer.isMoreCardAble()) {
            dealer.pick(cardDistributor.distribute());
            dealerMoreCardCount++;
        }
        outputView.printDealerMoreCard(dealer.getNameValue(), dealerMoreCardCount);
    }

    private void end(Players players, Dealer dealer, Betting betting) {
        printFinalCard(dealer);
        players.getPlayers().forEach(this::printFinalCard);
        outputView.printFinalResult(new ResultCalculator(betting, new Result(dealer, players)
                .getResult())
                .getResultOfBetting());
    }

    private void printFinalCard(Participant participant) {
        outputView.printCardAndScore(participant.getNameValue(), getCardStatus(participant.getCards()), participant.getTotalScoreValue());
    }

    private List<CardStatusDto> getCardStatus(List<Card> cards) {
        return cards.stream()
                .map(CardStatusDto::from)
                .collect(Collectors.toList());
    }

    private void printInitialDistribution(Players players, Dealer dealer) {
        outputView.printFirstCardDistribution(dealer.getNameValue(), getPlayerNames(players));
        outputView.printCardStatus(dealer.getNameValue(), getCardStatus(List.of(dealer.showOneCard())));
        for (Participant player : players.getPlayers()) {
            outputView.printCardStatus(player.getNameValue(), getCardStatus(player.getCards()));
        }
    }

    private List<String> getPlayerNames(Players players) {
        List<String> playerNames;
        playerNames = players.getPlayers()
                .stream()
                .map(Participant::getName)
                .map(Name::getValue)
                .collect(Collectors.toList());
        return playerNames;
    }

    private void requestPlayerMoreCard(CardDistributor cardDistributor, Player player) {
         Answer isCardRequested = Answer.MORE_CARD;

        while (player.isMoreCardAble() && isCardRequested.isMoreCard()) {
            isCardRequested = Answer.from(inputView.askMoreCard(player.getNameValue()));
            proceedOnce(cardDistributor, player, isCardRequested);
        }
    }

    private void proceedOnce(CardDistributor cardDistributor, Player player, Answer answer) {
        if (answer.isMoreCard()) {
            player.pick(cardDistributor.distribute());
        }

        outputView.printCardStatus(player.getNameValue(), getCardStatus(player.getCards()));
    }

}
