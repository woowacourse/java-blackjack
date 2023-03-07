package controller;

import domain.CardSelector;
import domain.CardBox;
import domain.CardNumberGenerator;
import domain.Cards;
import domain.Dealer;
import domain.Name;
import domain.Player;
import domain.Result;
import domain.ResultCalculator;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run(final CardNumberGenerator generator, final CardBox cardBox) {
        List<Name> names = inputNames(inputView.getPlayer());
        List<Cards> givenCards = CardSelector.pickCards(names, generator, cardBox);
        Dealer dealer = new Dealer(new Name("딜러"), givenCards.get(0));
        List<Player> players = CardSelector.giveCardsToPlayers(names, givenCards, dealer);
        printInitialCardResult(givenCards, players);
        printAllGameResult(generator, cardBox, dealer, players);
    }

    private void printInitialCardResult(final List<Cards> randomBoxedCards, final List<Player> players) {
        List<String> copiedNames = copyEachPlayersName(players);
        List<List<String>> copiedCards = copyCards(randomBoxedCards);
        printPlayerNamesAndCardsPerPlayer(copiedNames, copiedCards);
    }

    private void printPlayerNamesAndCardsPerPlayer(final List<String> boxedNames, final List<List<String>> boxedCards) {
        outputView.printPlayerNames(boxedNames);
        outputView.printCardsPerPlayer(boxedNames, boxedCards);
    }

    private void printAllGameResult(CardNumberGenerator generator, CardBox cardBox, Dealer dealer,
        List<Player> players) {
        eachPlayersTurn(players, cardBox, generator);
        dealerTurn(dealer, generator, cardBox);
        printFinalCardResult(players);
        printWinningResult(dealer, players, copyEachPlayersName(players));
    }

    private void eachPlayersTurn(final List<Player> players, final CardBox cardBox,
        final CardNumberGenerator generator) {
        for (int index = 1; index < players.size(); index++) {
            playerSelectToAddCard(players, index, cardBox, generator);
        }
    }

    private void playerSelectToAddCard(final List<Player> players, final int index, final CardBox cardBox,
        final CardNumberGenerator generator) {
        while (inputView.addOrStop(players.get(index).getName())) {
            int cardBoxIndex = generator.generateIndex();
            CardSelector.playerDrawIfSelectToAddCard(players, index, cardBox, cardBoxIndex);
            outputView.printCurrentPlayerResult(players.get(index).getName(), players.get(index).copyCards());
        }
        outputView.printCurrentPlayerResult(players.get(index).getName(), players.get(index).copyCards());
    }

    private void dealerTurn(final Dealer dealer, final CardNumberGenerator generator,
        final CardBox cardBox) {
        while (dealer.isSumUnderStandard()) {
            outputView.noticeDealerUnderStandard();
            CardSelector.dealerPickCard(dealer, generator.generateIndex(), cardBox);
        }
    }

    private void printFinalCardResult(final List<Player> players) {
        System.out.println();
        players.forEach(player ->
            outputView.printAllCardResult(player.getName(), player.copyCards(), player.sumOfPlayerCards())
        );
    }

    private void printWinningResult(final Dealer dealer, final List<Player> players, final List<String> copiedNames) {
        List<Result> winningResult = ResultCalculator.getWinningResult(dealer, players);
        outputView.printWinningResult(winningResult, copiedNames);
    }

    private List<String> copyEachPlayersName(final List<Player> players) {
        return players.stream()
            .map(Player::getName)
            .collect(Collectors.toList());
    }

    private List<List<String>> copyCards(final List<Cards> boxedCards) {
        return boxedCards.stream()
            .map(Cards::copyCards)
            .collect(Collectors.toList());
    }


    private List<Name> inputNames(final List<String> playerNames) {
        return playerNames.stream()
            .map(Name::new)
            .collect(Collectors.toList());
    }

}
