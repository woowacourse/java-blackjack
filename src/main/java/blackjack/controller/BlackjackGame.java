package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.card.HandGenerator;
import blackjack.domain.card.RandomDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayerIterator;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.PlayerAction;
import blackjack.domain.result.BlackjackResult;
import blackjack.domain.result.Referee;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class BlackjackGame {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        Participants participants = createParticipants();
        outputView.printInitialHand(participants);
        participantsHitCard(participants);
        outputView.printParticipantsHandWithScore(participants);
        printBlackjackResult(participants);
    }

    private Participants createParticipants() {
        Deck deck = RandomDeck.getInstance();
        HandGenerator handGenerator = new HandGenerator(deck);
        return retryOnException(() -> createParticipantsWithNames(handGenerator));
    }

    private void participantsHitCard(Participants participants) {
        playersHitCard(participants.getPlayers());
        dealerHitCard(participants.getDealer());
    }

    private void printBlackjackResult(Participants participants) {
        Referee referee = Referee.getInstance();
        BlackjackResult blackjackResult = referee.generateResult(participants);
        outputView.printBlackjackResult(blackjackResult);
    }

    private void playersHitCard(Players players) {
        PlayerIterator playerIterator = new PlayerIterator(players);
        while (playerIterator.hasNext()) {
            hitIfCurrentPlayerWantCard(playerIterator);
        }
    }

    private void dealerHitCard(Dealer dealer) {
        Deck deck = RandomDeck.getInstance();
        int hitCount = 0;
        while (dealer.canHit()) {
            dealer.addCard(deck);
            hitCount++;
        }
        outputView.printDealerHitCount(hitCount);
    }

    private void hitIfCurrentPlayerWantCard(PlayerIterator playerIterator) {
        Player player = playerIterator.getPlayer();
        PlayerAction playerAction = retryOnException(() -> getPlayerAction(player));
        if (playerAction.equals(PlayerAction.HIT)) {
            player.addCard(RandomDeck.getInstance());
        }
        outputView.printPlayerHand(player);
        playerIterator.increaseOrder(playerAction);
    }

    private PlayerAction getPlayerAction(Player player) {
        String playerName = player.getName();
        String command = inputView.readPlayerActionCommand(playerName);
        return PlayerAction.getAction(command);
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

    private <T> T retryOnException(Supplier<T> operation) {
        try {
            return operation.get();
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return retryOnException(operation);
        }
    }
}
