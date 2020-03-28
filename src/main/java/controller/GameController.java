package controller;

import domain.card.CardsFactory;
import domain.card.Deck;
import domain.gamer.*;
import utils.InputUtils;
import view.InputView;
import view.OutputView;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class GameController {
    public void run() {
        Gamers gamers = generateGamers();
        Deck deck = new Deck(CardsFactory.getCards());

        gamers.initCard(deck);
        OutputView.printInitCardGuide(gamers);
        OutputView.printGamersCard(gamers);
        gamers.addCardAtGamers(deck);
        OutputView.printAddCardAtDealer();
        OutputView.printCardsResultAndScore(new CardsResult(gamers));

        GameResult gameResult = new GameResult(gamers);
        OutputView.printTotalEarningResult(gameResult);
    }

    private Gamers generateGamers() {
        return InputUtils.splitAsDelimiter(InputView.inputAsPlayerName())
                .stream()
                .map(name -> new Player(name, InputView.inputAsBettingMoney(name)))
                .collect(collectingAndThen(toList(), players -> new Gamers(players, new Dealer())));
    }
}
