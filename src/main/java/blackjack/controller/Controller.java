package blackjack.controller;

import blackjack.exception.ErrorMessage;
import blackjack.model.*;
import blackjack.util.Splitter;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Controller {

    public static final int INITIAL_DEAL_REPEAT = 2;
    public static final int ONE_REPEAT = 1;
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    // TODO: 전체 리팩토링 (2026. 3. 9.)
    public void run() {
        Deck deck = new Deck();
        Referee referee = new Referee();
        Dealer dealer = new Dealer();
        Players players = createPlayer();
        List<Player> participants = players.getPlayers();

        deck.shuffle();
        initializeDealToParticipants(dealer, players, deck);
        outputView.printFirstCardStatus(dealer, players);

        turnToPlayers(participants, deck);
        turnToDealer(dealer, deck);
        outputView.printScoreResult(dealer, players);

        outputView.printGameResult(getPlayerGameResult(participants, dealer, referee));
    }

    private static Map<Player, GameResult> getPlayerGameResult(List<Player> participants, Dealer dealer, Referee referee) {
        Map<Player, GameResult> gameResult = new LinkedHashMap<>();
        for (Player player : participants) {
            gameResult.put(player, referee.judge(player, dealer));
        }
        return gameResult;
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
            String receiveCard = inputView.getReceiveCard(player);
            if (receiveCard.equals("n")) {
                return;
            }
            if (!receiveCard.equals("y")) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
            }
            receiveCardToParticipant(player, deck, ONE_REPEAT);
            if (player.isBurst()) {
                outputView.printBurst(player.getName());
                return;
            }
            outputView.printPlayerCardStatus(player, player.getHandCards());
        }
    }

    private Players createPlayer() {
        List<Player> players = new ArrayList<>();
        List<String> playerNames = Splitter.split(inputView.getName()).stream().toList();
        for (String name : playerNames) {
            players.add(new Player(name, inputView.getBettingAmount(name)));
        }
        return new Players(players);
    }

    private void initializeDealToParticipants(Dealer dealer, Players players, Deck deck) {
        receiveCardToParticipant(dealer, deck, INITIAL_DEAL_REPEAT);

        for (Player player : players.getPlayers()) {
            receiveCardToParticipant(player, deck, INITIAL_DEAL_REPEAT);
        }
    }

    private void receiveCardToParticipant(Participant participant, Deck deck, int repeat) {
        for (int i = 0; i < repeat; i++) {
            participant.receiveCard(deck.giveCard());
        }
    }
}
