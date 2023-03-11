package controller;

import domain.Cards;
import domain.PlayerState;
import domain.generator.CardNumberGenerator;
import domain.CardSelector;
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

    public void run(final CardNumberGenerator generator) {
        List<Name> names = inputNames(inputView.getPlayer());
        Dealer dealer = new Dealer(Cards.pickInitialCards(generator));
        List<Integer> bettingMoneys = inputView.inputBettingMoneys(names);
        List<Player> players = CardSelector.giveCardsToPlayers(names, generator, bettingMoneys);
        printInitialCardResult(dealer, players);
        printAllGameResult(generator, dealer, players);
    }

    private void printInitialCardResult(final Dealer dealer, final List<Player> players) {
        List<String> copiedNames = copyEachPlayersName(players);
        List<List<String>> copiedCards = copyCards(players);
        copiedCards.add(0, dealer.copyCards());
        printPlayerNamesAndCardsPerPlayer(copiedNames, copiedCards);
    }

    private void printPlayerNamesAndCardsPerPlayer(final List<String> copiedNames,
        final List<List<String>> boxedCards) {
        outputView.printPlayerNames(copiedNames);
        outputView.printCardsPerPlayer(copiedNames, boxedCards);
    }

    private void printAllGameResult(CardNumberGenerator generator, Dealer dealer, List<Player> players) {
        eachPlayersTurn(players, generator);
        dealerTurn(dealer, generator);

        printFinalCardResult(dealer, players);
        List<Result> winningResult = ResultCalculator.getWinningResult(dealer, players);
        for (int i =0 ; i<players.size();i++){
            players.get(i).calculateFinalBettingResult(winningResult.get(i));
        }
        printWinningResult(players);
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
        addCardByPlayer(player, generator);
        if (player.askPlayerState().equals(PlayerState.MORE) || player.askPlayerState().equals(PlayerState.FLAT)) {
            outputView.noticePlayerCannotReceiveCard(player);
        }
        outputView.printCurrentPlayerResult(player.getName(), player.copyCards());
    }

    private void addCardByPlayer(Player player, CardNumberGenerator generator) {
        while (inputView.addOrStop(player.getName()) && checkPlayerCanReceiveCard(player)) {
            int cardBoxIndex = generator.generateIndex();
            CardSelector.playerDrawIfSelectToAddCard(player, cardBoxIndex);
            outputView.printCurrentPlayerResult(player.getName(), player.copyCards());
        }
    }

    private boolean checkPlayerCanReceiveCard(Player player) {
        return player.askPlayerState().equals(PlayerState.LESS);
    }

    private void dealerTurn(Dealer dealer, final CardNumberGenerator generator) {
        while (dealer.isSumUnderStandard()) {
            outputView.noticeDealerUnderStandard();
            CardSelector.dealerPickCard(dealer, generator.generateIndex());
        }
    }

    private void printFinalCardResult(final Dealer dealer, final List<Player> players) {
        System.out.println();
        outputView.printDealerCardResult(dealer.copyCards(), dealer.sumOfParticipantCards());
        players.forEach(player ->
            outputView.printAllCardResult(player.getName(), player.copyCards(), player.sumOfParticipantCards())
        );
    }

    private void printWinningResult(final List<Player> players) {
        outputView.printEachPlayersProfit(players);
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
