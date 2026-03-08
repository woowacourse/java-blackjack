package blackjack.controller;

import blackjack.exception.ErrorMessage;
import blackjack.model.*;
import blackjack.util.NameSplitter;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

        Players players = getPlayers();
        List<Player> participants = players.getPlayers();

        deck.shuffle();
        initializeDealToParticipants(dealer, players, deck);
        outputView.printFirstCardStatus(dealer, players);

        turnToPlayers(participants, deck);
        turnToDealer(dealer, deck);
        outputView.printScoreResult(dealer, players);

        outputView.printGameResult(getPlayerGameResult(participants, dealer));
    }

    private static Map<Player, GameResult> getPlayerGameResult(List<Player> participants, Dealer dealer) {
        Map<Player, GameResult> gameResult = new LinkedHashMap<>();
        for (Player player : participants) {
            gameResult.put(player ,GameResult.getResult(player, dealer));
        }
        return gameResult;
    }

    private void turnToDealer(Dealer dealer, Deck deck) {
        dealer.updateScore();

        while (dealer.canReceive(dealer.getScore())) {
            outputView.printDealerReceiveCard();
            receiveCardToParticipant(dealer, deck, ONE_REPEAT);
            dealer.updateScore();
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

    // TODO: 10줄 초과, depth 2 초과 (2026. 3. 6.)
    private void turnToOnePlayer(Deck deck, Player player) {
        player.updateScore();

        while (player.canReceive(player.getScore())) {
            String receiveCard = inputView.getReceiveCard(player);
            if (receiveCard.equals("n")) {
                return;
            }
            if (!receiveCard.equals("y")) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
            }
            receiveCardToParticipant(player, deck, ONE_REPEAT);
            player.updateScore();
            if (player.isBurst()) {
                outputView.printBurst(player.getName());
                return;
            }
            outputView.printPlayerCardStatus(player, player.getCards());
        }
    }

    private Players getPlayers() {
        List<Player> players = NameSplitter.split(inputView.getName())
                .stream()
                .map(Player::new)
                .toList();
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
