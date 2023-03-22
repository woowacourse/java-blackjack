package controller;

import domain.generator.CardNumberGenerator;
import domain.CardSelector;
import domain.Dealer;
import domain.Name;
import domain.Player;
import domain.ResultCalculator;
import java.util.List;
import java.util.Map;
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

    public void run(final CardNumberGenerator generator) {
        List<Name> names = inputNames(inputView.getPlayer());
        Dealer dealer = Dealer.createInitialDealer(generator);
        List<Integer> bettingMoneys = inputView.inputBettingMoneys(names);
        List<Player> players = CardSelector.giveCardsToPlayers(names, generator, bettingMoneys);
        printInitialCardResult(dealer, players);
        startEachTurn(generator, dealer, players);
        printFinalCardResult(dealer, players);
        ResultCalculator.calculateBettingResultAfterGame(dealer, players);
        printWinningResult(players, ResultCalculator.sumDealerProfit(players));
    }

    private void printInitialCardResult(final Dealer dealer, final List<Player> players) {
        List<String> names = copyEachPlayersName(players);
        List<List<String>> copiedCards = copyCards(players);
        copiedCards.add(0, dealer.copyCards());
        printPlayerNamesAndCardsPerPlayer(names, copiedCards);
    }

    private void printPlayerNamesAndCardsPerPlayer(final List<String> names,
        final List<List<String>> boxedCards) {
        outputView.printPlayerNames(names);
        outputView.printCardsPerPlayer(names, boxedCards);
    }

    private void startEachTurn(CardNumberGenerator generator, Dealer dealer, List<Player> players) {
        eachPlayersTurn(players, generator);
        dealerTurn(dealer, generator);
    }

    private void eachPlayersTurn(final List<Player> players,
        final CardNumberGenerator generator) {
        for (Player player : players) {
            player.giveBonusIfInitialCardsAreBlackJack();
            playerSelectToAddCard(player, generator);
        }
    }

    private void playerSelectToAddCard(final Player player,
        final CardNumberGenerator generator) {
        selectToAddCard(player, generator);
        printIfPlayerCannotReceiveCard(player);
        printCurrentPlayerInfo(player);
    }

    private void printIfPlayerCannotReceiveCard(Player player) {
        outputView.noticePlayerCannotReceiveCard(player.getName(), player.sumOfParticipantCards(),
            player.isPlayerCanAddCard());
    }


    private void selectToAddCard(Player player, CardNumberGenerator generator) {
        while (player.isSelectToAddCard(inputView.addOrStop(player.getName()))) {
            CardSelector.playerDrawIfSelectToAddCard(player, generator);
            printCurrentPlayerInfo(player);
        }
    }
    private void printCurrentPlayerInfo(Player player) {
        outputView.printCurrentPlayerResult(player.getName(), player.copyCards());
    }

    private void dealerTurn(Dealer dealer, final CardNumberGenerator generator) {
        while (dealer.isSumUnderStandard()) {
            noticeIfDealerSumUnderStandard();
            CardSelector.dealerPickCard(dealer, generator.generateIndex());
        }
    }

    private void noticeIfDealerSumUnderStandard() {
        outputView.noticeDealerUnderStandard();
    }

    private void printFinalCardResult(final Dealer dealer, final List<Player> players) {
        System.out.println();
        outputView.printDealerCardResult(dealer.copyCards(), dealer.sumOfParticipantCards());
        players.forEach(player ->
            outputView.printAllCardResult(player.getName(), player.copyCards(), player.sumOfParticipantCards())
        );
    }

    private void printWinningResult(final List<Player> players, int dealerProfit) {
        outputView.printDealerProfit(dealerProfit);
        outputView.printEachPlayersProfit(copyResult(players));
    }

    private static Map<String, Integer> copyResult(List<Player> players) {
        return players.stream().collect(
            Collectors.toMap(Player::getName, Player::calculateFinalProfit)
        );
    }

    private List<String> copyEachPlayersName(final List<Player> players) {
        return players.stream()
            .map(Player::getName)
            .collect(Collectors.toList());
    }

    private List<List<String>> copyCards(final List<Player> players) {
        return players.stream()
            .map(Player::copyCards)
            .collect(Collectors.toList());
    }


    private List<Name> inputNames(final List<String> playerNames) {
        return playerNames.stream()
            .map(Name::new)
            .collect(Collectors.toList());
    }

}
