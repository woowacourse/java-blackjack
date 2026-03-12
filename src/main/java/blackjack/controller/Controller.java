package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class Controller {
    private static final int INITIAL_DEAL_REPEAT = 2;
    private static final int ONE_REPEAT = 1;

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer();
        Players players = createPlayers();
        List<Player> participants = players.getPlayers();

        deck.shuffle();
        initializeDealToParticipants(dealer, players, deck);

        outputView.printFirstCardStatus(dealer, players);

        turnToPlayers(participants, deck);
        turnToDealer(dealer, deck);
        outputView.printScoreResult(dealer, players);

        GameResults gameResults = GameResults.of(dealer, players);

        outputView.printGameResultProfit(gameResults);
    }

    private Players createPlayers() {
        List<String> playerNames = inputView.readNames();
        List<Player> players = playerNames.stream()
                .map(name -> new Player(name, new BettingAmount(inputView.readBettingAmount(name))))
                .toList();
        return new Players(players);
    }

    private void turnToDealer(Dealer dealer, Deck deck) {
        while (dealer.canReceive()) {
            outputView.printDealerReceiveCard();
            receiveCardToParticipant(dealer, deck, ONE_REPEAT);
            if (dealer.isBurst()) {
                outputView.printBurst("딜러");
                return;
            }
        }
    }

    private void turnToPlayers(List<Player> participants, Deck deck) {
        for (Player player : participants) {
            turnToOnePlayer(deck, player);
        }
    }

    private void turnToOnePlayer(Deck deck, Player player) {
        while (player.canReceive()) {
            HitCommand command = HitCommand.from(inputView.readReceiveCard(player));
            if (!command.isHit()) {
                return;
            }

            receiveCardToParticipant(player, deck, ONE_REPEAT);
            if (player.isBurst()) {
                outputView.printBurst(player.getName());
                return;
            }
            outputView.printPlayerCardStatus(player, player.getCards());
        }
    }

    private void initializeDealToParticipants(Dealer dealer, Players players, Deck deck) {
        receiveCardToParticipant(dealer, deck, INITIAL_DEAL_REPEAT);

        for (Player player : players.getPlayers()) {
            receiveCardToParticipant(player, deck, INITIAL_DEAL_REPEAT);
        }
    }

    private void receiveCardToParticipant(Participant participant, Deck deck, int repeat) {
        for (int i = 0; i < repeat; i++) {
            participant.receiveCard(deck.hit());
        }
    }
}
