package controller;

import static domain.constants.CardCommand.HIT;

import controller.dto.dealer.DealerHandStatus;
import controller.dto.gamer.GamerHandStatus;
import domain.Card;
import domain.Dealer;
import domain.Deck;
import domain.Gamer;
import domain.Hand;
import domain.Participant;
import domain.Player;
import domain.constants.CardCommand;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class Round {
    private final Participant participant;
    private final Deck deck;

    public Round(final Dealer dealer, final List<String> playerNames) {
        List<Gamer> gamers = playerNames.stream()
                .map(Gamer::new)
                .toList();
        this.participant = new Participant(dealer, gamers);
        deck = new Deck();
    }

    public Round(final Participant participant, final Deck deck) {
        this.participant = participant;
        this.deck = deck;
    }

    public void initiateGameCondition() {
        Dealer dealer = participant.dealer();
        dealer.pickTwoCards(deck);

        for (Gamer gamer : participant.gamers()) {
            gamer.pickTwoCards(deck);
        }
    }

    public DealerHandStatus getDealerStatusAfterStartGame() {
        Dealer dealer = participant.dealer();
        return new DealerHandStatus(getDealerHandAfterStartGame(dealer));
    }

    public List<GamerHandStatus> getGamerStatusAfterStartGame() {
        return participant.gamers().stream()
                .map(gamer -> new GamerHandStatus(gamer.getName(), getHandToString(gamer)))
                .toList();
    }

    private String getDealerHandAfterStartGame(final Dealer dealer) {
        Hand hand = dealer.getHand();
        List<Card> cardsInHand = hand.getCards();
        return cardsInHand.get(0).getName() + cardsInHand.get(0).getShape();
    }

    public String getHandToString(final Player player) {
        Hand hand = player.getHand();
        List<Card> cardsInHand = hand.getCards();

        return cardsInHand.stream()
                .map(card -> card.getName() + card.getShape())
                .collect(Collectors.joining(", "));
    }

    public void giveCardToGamer(final String name, final OutputView outputView, final InputView inputView) {
        Gamer gamer = getGamer(name);
        GamerHandStatus currentHand = new GamerHandStatus(name, getHandToString(gamer));
        CardCommand command = inputCommand(name, inputView);

        while (HIT.equals(command)) {
            currentHand = createHandStatusAfterPick(gamer);
            if (!gamer.isAbleToDrawCard()) {
                break;
            }
            outputView.printCardStatus(name, currentHand);
            command = inputCommand(name, inputView);
        }
        outputView.printCardStatus(name, currentHand);
    }

    private GamerHandStatus createHandStatusAfterPick(final Gamer gamer) {
        gamer.pickOneCard(deck);
        return new GamerHandStatus(gamer.getName(), getHandToString(gamer));
    }

    private CardCommand inputCommand(final String name, final InputView inputView) {
        return CardCommand.from(inputView.decideToGetMoreCard(name));
    }

    public List<String> getGamerNames() {
        List<Gamer> gamers = participant.gamers();
        return gamers.stream()
                .map(Gamer::getName)
                .toList();
    }

    public int giveCardsToDealer() {
        Dealer dealer = participant.dealer();

        int count = 0;
        while (!dealer.isUpToThreshold()) {
            dealer.pickOneCard(deck);
            count++;
        }
        return count;
    }

    public Dealer getDealer() {
        return participant.dealer();
    }

    public List<Gamer> getGamers() {
        return participant.gamers();
    }

    public Gamer getGamer(final String name) {
        return participant.gamers()
                .stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}
