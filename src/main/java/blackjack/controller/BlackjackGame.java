package blackjack.controller;

import blackjack.domain.Deck;
import blackjack.domain.HandGenerator;
import blackjack.domain.Name;
import blackjack.domain.RandomDeck;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayerIterator;
import blackjack.domain.participant.Players;
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
    }

    private Participants createParticipants() {
        Deck deck = RandomDeck.getInstance();
        HandGenerator handGenerator = new HandGenerator(deck);
        return retryOnException(() -> createParticipantsWithNames(handGenerator));
    }

    private void participantsHitCard(Participants participants) {
        playersHitCard(participants.getPlayers());
        // TODO 딜러 Hit
    }

    private void playersHitCard(Players players) {
        PlayerIterator playerIterator = new PlayerIterator(players);
        while (playerIterator.hasNext()) {
            hitIfCurrentPlayerWantCard(playerIterator);
        }
    }

    private void hitIfCurrentPlayerWantCard(PlayerIterator playerIterator) {
        Player player = playerIterator.getPlayer();
        boolean isPlayerWantHit = retryOnException(() -> readIsPlayerWantHit(player));
        if (isPlayerWantHit) {
            player.addCard(RandomDeck.getInstance());
        }
        outputView.printPlayerHand(player);
        playerIterator.update(isPlayerWantHit);
    }

    private boolean readIsPlayerWantHit(Player player) {
        String playerName = player.getName();
        return inputView.readIsPlayerWantHit(playerName);
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
