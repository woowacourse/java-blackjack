package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.card.HandGenerator;
import blackjack.domain.card.RandomDeck;
import blackjack.domain.participant.*;
import blackjack.domain.result.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BlackjackGame {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        Participants participants = createParticipants();
        PlayerBets playerBets = createPlayerBets(participants.getPlayers());
        printInitialHandsOf(participants);
        participantsHitCard(participants);
        outputView.printParticipantsHandWithScore(participants);
        printBlackjackResult(participants, playerBets);
    }

    private Participants createParticipants() {
        Deck deck = RandomDeck.getInstance();
        HandGenerator handGenerator = new HandGenerator(deck);
        return retryOnException(() -> createParticipantsWithNames(handGenerator));
    }

    private Participants createParticipantsWithNames(HandGenerator handGenerator) {
        List<Name> playersName = readPlayersName();
        return new Participants(playersName, handGenerator);
    }

    private List<Name> readPlayersName() {
        List<String> playersName = inputView.readPlayersName();
        return playersName.stream()
                .map(Name::new)
                .toList();
    }

    private PlayerBets createPlayerBets(Players players) {
        return players.getValues()
                .stream()
                .map(player -> retryOnException(() -> createPlayerBet(player)))
                .collect(Collectors.collectingAndThen(Collectors.toList(), PlayerBets::new));
    }

    private PlayerBet createPlayerBet(Player player) {
        Name playerName = player.getName();
        int bettingPrice = inputView.readPlayerBettingPrice(playerName.getValue());
        return new PlayerBet(player, bettingPrice);
    }

    private void printInitialHandsOf(Participants participants) {
        InitialHands initialHands = participants.getParticipantsInitialHand();
        outputView.printInitialHands(initialHands);
    }

    private void participantsHitCard(Participants participants) {
        PlayerTurnSelector playerTurnSelector = participants.createPlayerTurnSelector();
        while (playerTurnSelector.hasNext()) {
            hitIfCurrentPlayerWants(playerTurnSelector);
        }

        int dealerHitCount = participants.executeAndCountDealerHit(RandomDeck.getInstance());
        outputView.printDealerHitCount(dealerHitCount);
    }

    private void hitIfCurrentPlayerWants(PlayerTurnSelector playerTurnSelector) {
        Player player = playerTurnSelector.getPlayer();
        PlayerAction playerAction = retryOnException(() -> getPlayerAction(player));
        if (playerAction.equals(PlayerAction.HIT)) {
            player.addCard(RandomDeck.getInstance());
        }
        outputView.printPlayerHand(player);
        playerTurnSelector.updateTurnByActionAndHand(playerAction);
    }

    private PlayerAction getPlayerAction(Player player) {
        Name playerName = player.getName();
        boolean dosePlayerWantHit = inputView.dosePlayerWantHit(playerName.getValue());
        return PlayerAction.getAction(dosePlayerWantHit);
    }

    private void printBlackjackResult(Participants participants, PlayerBets playerBets) {
        BetResultGenerator betResultGenerator = participants.createBetResultGenerator();
        BlackjackResult blackjackResult = betResultGenerator.generateBetResultOf(playerBets);
        outputView.printBlackjackResult(blackjackResult);
    }

    private <T> T retryOnException(Supplier<T> operation) {
        try {
            return operation.get();
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return retryOnException(operation);
        }
    }
}
