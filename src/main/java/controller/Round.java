package controller;

import static controller.GameCommand.HIT;

import controller.dto.gamer.GamerHandStatus;
import domain.Dealer;
import domain.Deck;
import domain.Gamer;
import domain.Gamers;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Round {
    private final Dealer dealer;
    private final Gamers gamers;

    public Round(final Dealer dealer, final List<String> playerNames) {
        this.dealer = dealer;
        this.gamers = new Gamers(playerNames.stream()
                .map(Gamer::new)
                .toList());
    }

    public void initiateGameCondition() {
        Deck.shuffle();

        dealer.pickTwoCards();
        for (Gamer gamer : gamers.listOf()) {
            gamer.pickTwoCards();
        }
    }

    public void giveCardToGamer(final String name, final OutputView outputView, final InputView inputView) {
        Gamer gamer = getGamer(name);
        GamerHandStatus currentHand = new GamerHandStatus(name, gamer.getHandStatusAsString());
        GameCommand command = inputCommand(name, inputView);

        while (HIT.equals(command)) {
            currentHand = new GamerHandStatus(gamer.getName(), gamer.getHandStatusAsString());
            if (!gamer.isAbleToDrawCard()) {
                break;
            }
            outputView.printCardStatus(name, currentHand);
            command = inputCommand(name, inputView);
        }
        outputView.printCardStatus(name, currentHand);
    }

    private GameCommand inputCommand(final String name, final InputView inputView) {
        return GameCommand.valueOf(inputView.decideToGetMoreCard(name));
    }

    public int giveCardsToDealer() {
        int count = 0;
        while (!dealer.isUpToThreshold()) {
            dealer.pickOneCard();
            count++;
        }
        return count;
    }

    public Gamer getGamer(final String name) {
        return gamers.getGamerByName(name);
    }

    public Gamers getGamers() {
        return gamers;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<String> getGamerNames() {
        return gamers.getGamerNames();
    }
}
