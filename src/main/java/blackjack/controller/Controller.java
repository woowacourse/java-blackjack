package blackjack.controller;

import blackjack.exception.ErrorMessage;
import blackjack.model.*;
import blackjack.util.NameSplitter;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.HashMap;
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

    public void run() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer();
        ScoreCalculator scoreCalculator = new ScoreCalculator();

        Players players = getPlayers();
        List<Player> participants = players.getPlayers();

        deck.shuffle();
        initializeDealToParticipants(dealer, players, deck);
        outputView.printFirstCardStatus(dealer, players);

        turnToPlayers(participants, scoreCalculator, deck);
        turnToDealer(dealer, scoreCalculator, deck);
        outputView.printScoreResult(dealer, players);

        outputView.printGameResult(getPlayerGameResult(participants, dealer));
    }

    private static Map<Player, GameResult> getPlayerGameResult(List<Player> participants, Dealer dealer) {
        Map<Player, GameResult> gameResult = new LinkedHashMap<>();
        for (Player player : participants) {
            gameResult.put(player ,GameResult.getResult(player.getScore(), dealer.getScore()));
        }
        return gameResult;
    }

    private void turnToDealer(Dealer dealer, ScoreCalculator scoreCalculator, Deck deck) {
        calculateParticipantCardScore(scoreCalculator, dealer);

        while (dealer.canReceive(dealer.getScore())) {
            outputView.printDealerReceiveCard();
            receiveCardToParticipant(dealer, deck, ONE_REPEAT);
            dealer.addScore(scoreCalculator.getReceivedOneCardScore(dealer));
            if (scoreCalculator.isBurst(dealer.getScore())) {
                outputView.printBurst("딜러");
                dealer.burstScore();
                return;
            }
        }
    }

    private void turnToPlayers(List<Player> participants, ScoreCalculator scoreCalculator, Deck deck) {
        for (Player player : participants) {
            turnToOnePlayer(scoreCalculator, deck, player);
        }
    }

    // TODO: 10줄 초과, depth 2 초과 (2026. 3. 6.)
    private void turnToOnePlayer(ScoreCalculator scoreCalculator, Deck deck, Player player) {
        calculateParticipantCardScore(scoreCalculator, player);

        while (player.canReceive(player.getScore())) {
            String receiveCard = inputView.getReceiveCard(player);
            if (receiveCard.equals("n")) {
                return;
            }
            if (!receiveCard.equals("y")) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
            }
            receiveCardToParticipant(player, deck, ONE_REPEAT);
            player.addScore(scoreCalculator.getReceivedOneCardScore(player));
            if (scoreCalculator.isBurst(player.getScore())) {
                outputView.printBurst(player.getName());
                player.burstScore();
                return;
            }
            outputView.printPlayerCardStatus(player, player.getCards());
        }
    }

    private static void calculateParticipantCardScore(ScoreCalculator scoreCalculator, Participant participant) {
        participant.addScore(scoreCalculator.getCardScore(participant.getCards()));
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
