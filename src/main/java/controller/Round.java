package controller;

import static domain.constants.CardCommand.HIT;

import controller.dto.HandStatus;
import domain.Dealer;
import domain.Deck;
import domain.Participant;
import domain.Player;
import domain.constants.CardCommand;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Round {
    private static final int THRESHOLD = 16;

    private final Participant participant;
    private final Deck deck;

    public Round(final Dealer dealer, final List<String> playerNames) {
        List<Player> players = playerNames.stream()
                .map(Player::new)
                .toList();
        this.participant = new Participant(dealer, players);
        deck = new Deck();
    }

    public Round(final Participant participant, final Deck deck) {
        this.participant = participant;
        this.deck = deck;
    }

    public List<HandStatus> initiateGameCondition() {
        List<HandStatus> status = new ArrayList<>();

        Dealer dealer = participant.dealer();
        dealer.pickTwoCards(deck);
        status.add(new HandStatus(dealer.getName(), dealer.getHand()));

        for (Player player : participant.players()) {
            player.pickTwoCards(deck);
            status.add(new HandStatus(player.getName(), player.getHand()));
        }
        return status;
    }

    public void giveCardToPlayer(final String name, final OutputView outputView, final InputView inputView) {
        Player player = getPlayer(name);
        HandStatus currentHand = new HandStatus(player.getName(), player.getHand());
        CardCommand command = inputCommand(name, inputView);

        while (HIT.equals(command)) {
            currentHand = createHandStatusAfterPick(player);
            if (player.isBusted()) {
                break;
            }
            outputView.printCardStatus(currentHand);
            command = inputCommand(name, inputView);
        }
        outputView.printCardStatus(currentHand);
    }

    private HandStatus createHandStatusAfterPick(final Player player) {
        player.pickOneCard(deck);
        return new HandStatus(player.getName(), player.getHand());
    }

    private CardCommand inputCommand(final String name, final InputView inputView) {
        return CardCommand.from(inputView.decideToGetMoreCard(name));
    }

    public List<String> getPlayerNames() {
        List<Player> players = participant.players();
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public int giveCardsToDealer() {
        Dealer dealer = participant.dealer();
        int currentScore = dealer.calculateResultScore();

        int count = 0;
        while (currentScore <= THRESHOLD) {
            dealer.pickOneCard(deck);
            currentScore = dealer.calculateResultScore();
            count++;
        }
        return count;
    }

    public Participant getParticipant() {
        return participant;
    }

    public Player getPlayer(final String name) {
        return participant.players()
                .stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}
