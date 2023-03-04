package service;

import domain.CardBox;
import domain.Cards;
import domain.Dealer;
import domain.Name;
import domain.Player;
import domain.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import view.InputView;
import view.OutputView;

public class BlackJackGame {
    private static final String DEALER_NAME = "딜러";

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGame(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run(final CardBox cardBox) {
        List<Name> names = getNames();
        List<Cards> randomBoxedCars = getRandomCards(names, cardBox);
        Dealer dealer = readDealer(randomBoxedCars);
        Players players = readPlayers(names, randomBoxedCars, dealer);
        printInitialCardResult(randomBoxedCars, players);
        playersTurn(players, cardBox);
        dealerDrawIfUnderStandard(dealer, cardBox);
        printFinalCardResult(players);
        printWinningResult(dealer, players);
    }

    private List<Name> getNames() {
        List<String> playerNames = inputView.getPlayer();
        return readNames(playerNames);
    }

    private List<Name> readNames(final List<String> playerNames) {
        return playerNames.stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    private List<Cards> getRandomCards(final List<Name> names,
                                       final CardBox cardBox) {
        return IntStream.range(0, names.size() + 1)
                .mapToObj(i -> IntStream.range(0, 2)
                        .mapToObj(j -> cardBox.get())
                        .collect(Collectors.toList()))
                .map(Cards::new)
                .collect(Collectors.toList());
    }

    private Dealer readDealer(final List<Cards> cardsCards) {
        return new Dealer(new Name(DEALER_NAME), cardsCards.get(0));
    }

    private Players readPlayers(final List<Name> names, final List<Cards> cardsCards, final Dealer dealer) {
        List<Player> players = new ArrayList<>();
        players.add(dealer);
        for (int i = 0; i < names.size(); i++) {
            players.add(new Player(names.get(i), cardsCards.get(i + 1)));
        }
        return new Players(players);
    }

    private void printInitialCardResult(final List<Cards> randomBoxedCars, final Players players) {
        outputView.printPlayerNames(players.getNames());
        outputView.printCardsPerPlayer(players.getNames(), copiedBoxedCards(randomBoxedCars));
    }

    private List<List<String>> copiedBoxedCards(final List<Cards> cardsCards) {
        return cardsCards.stream()
                .map(Cards::getCards)
                .collect(Collectors.toList());
    }

    private void playersTurn(final Players players, final CardBox cardBox) {
        for (int index = 1; index < players.size(); index++) {
            playerSelectAddCard(players, index, cardBox);
        }
    }

    private void playerSelectAddCard(final Players players, final int index, final CardBox cardBox) {
        while (inputView.addOrStop(players.getNameOfPlayer(index))) {
            players.playerDrawAddCard(index, cardBox);
            outputView.printCurrentPlayerResult(players.getNameOfPlayer(index), players.getCardsOfPlayer(index));
        }
        outputView.printCurrentPlayerResult(players.getNameOfPlayer(index), players.getCardsOfPlayer(index));
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

    private void drawCard(final Player players, final CardBox cardBox) {
        boolean flag = true;
        while (flag) {
            flag = !players.selectToPickOtherCard(cardBox);
        }
    }

    private void printFinalCardResult(final Players players) {
        System.out.println();
        for (int index = 0; index < players.size(); index++) {
            outputView.printAllCardResult(players.getNameOfPlayer(index), players.getCardsOfPlayer(index),
                    players.getCardsSum(index));
        }
    }

    private void printWinningResult(final Dealer dealer, final Players players) {
        List<Integer> winningResult = getWinningResult(dealer, players);
        outputView.printWinningResult(winningResult, players.getNames());
    }

    private List<Integer> getWinningResult(final Dealer dealer, final Players players) {
        List<Integer> winningResult = new ArrayList<>();
        for (int index = 1; index < players.size(); index++) {
            winningResult.add(dealer.checkWinningResult(players.getPlayer(index)));
        }
        return winningResult;
    }
}
