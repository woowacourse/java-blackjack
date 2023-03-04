package service;

import domain.CardBox;
import domain.Cards;
import domain.Dealer;
import domain.Name;
import domain.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import view.InputView;
import view.OutputView;

public class BlackJackGame {
    private final InputView inputView;
    private final OutputView outputView;

    private List<Player> players;

    public BlackJackGame(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run(final CardBox cardBox) {
        List<Name> names = getNames();
        List<Cards> randomBoxedCars = getRandomCards(names, cardBox);
        Dealer dealer = readDealer(randomBoxedCars);
        readPlayers(names, randomBoxedCars, dealer);
        List<String> boxedNames = boxedNames(players);
        printInitialCardResult(randomBoxedCars, boxedNames);
        playersTurn(players, cardBox);
        dealerDrawIfUnderStandard(dealer, cardBox);
        printFinalCardResult(players);
        printWinningResult(dealer, players, boxedNames);
    }

    private void printInitialCardResult(final List<Cards> randomBoxedCars, final List<String> boxedNames) {
        List<List<String>> copiedBoxedCards = copiedBoxedCards(randomBoxedCars);
        printPlayerNamesAndCardsPerPlayer(boxedNames, copiedBoxedCards);
    }

    private List<Cards> getRandomCards(final List<Name> names,
                                       final CardBox cardBox) {
        return randomCards(names, cardBox);
    }

    private void printWinningResult(final Dealer dealer, final List<Player> players, final List<String> boxedNames) {
        List<Integer> winningResult = getWinningResult(dealer, players);
        outputView.printWinningResult(winningResult, boxedNames);
    }

    private List<Integer> getWinningResult(final Dealer dealer, final List<Player> players) {
        List<Integer> winningResult = new ArrayList<>();
        for (int index = 1; index < players.size(); index++) {
            winningResult.add(dealer.checkWinningResult(players.get(index)));
        }
        return winningResult;
    }

    private void printFinalCardResult(final List<Player> players) {
        System.out.println();
        players.forEach(player ->
                outputView.printAllCardResult(player.getName(), player.getCards(), player.sumOfPlayerCards())
        );
    }

    private void printPlayerNamesAndCardsPerPlayer(final List<String> boxedNames, final List<List<String>> boxedCards) {
        outputView.printPlayerNames(boxedNames);
        outputView.printCardsPerPlayer(boxedNames, boxedCards);
    }

    private List<Name> getNames() {
        List<String> playerNames = inputView.getPlayer();
        return readNames(playerNames);
    }

    private List<String> boxedNames(final List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    private List<List<String>> copiedBoxedCards(final List<Cards> cardsCards) {
        return cardsCards.stream()
                .map(Cards::getCards)
                .collect(Collectors.toList());
    }

    private void playersTurn(final List<Player> players, final CardBox cardBox) {
        for (int index = 1; index < players.size(); index++) {
            playerSelectAddCard(players, index, cardBox);
        }
    }

    private void playerSelectAddCard(final List<Player> players, final int index, final CardBox cardBox) {
        while (inputView.addOrStop(players.get(index).getName())) {
            playerDrawIfSelectToAddCard(players, index, cardBox);
        }
        outputView.printCurrentPlayerResult(players.get(index).getName(), players.get(index).getCards());
    }

    private void playerDrawIfSelectToAddCard(final List<Player> players, final int index,
                                             final CardBox cardBox) {
        drawCard(players.get(index), cardBox);
        outputView.printCurrentPlayerResult(players.get(index).getName(), players.get(index).getCards());
    }

    private static void drawCard(final Player players, final CardBox cardBox) {
        boolean flag = true;
        while (flag) {
            flag = !players.selectToPickOtherCard(cardBox);
        }
    }

    private void dealerDrawIfUnderStandard(final Dealer dealer,
                                           final CardBox cardBox) {
        while (dealer.isSumUnderStandard()) {
            outputView.noticeDealerUnderStandard();
            dealerDrawCard(dealer, cardBox);
        }
    }

    private void dealerDrawCard(final Dealer dealer, final CardBox cardBox) {
        drawCard(dealer, cardBox);
    }

    private void readPlayers(final List<Name> names, final List<Cards> cardsCards, final Dealer dealer) {
        List<Player> players = new ArrayList<>();
        players.add(dealer);
        for (int i = 0; i < names.size(); i++) {
            players.add(new Player(names.get(i), cardsCards.get(i + 1)));
        }
        this.players = players;
    }

    private Dealer readDealer(final List<Cards> cardsCards) {
        return new Dealer(new Name("딜러"), cardsCards.get(0));
    }

    private List<Name> readNames(final List<String> playerNames) {
        return playerNames.stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    private List<Cards> randomCards(final List<Name> names,
                                    final CardBox cardBox) {
        return IntStream.range(0, names.size() + 1)
                .mapToObj(i -> IntStream.range(0, 2)
                        .mapToObj(j -> cardBox.get())
                        .collect(Collectors.toList()))
                .map(Cards::new)
                .collect(Collectors.toList());
    }
}
