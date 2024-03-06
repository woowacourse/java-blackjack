package blackjack.controller;

import blackjack.domain.Deck;
import blackjack.domain.HandGenerator;
import blackjack.domain.Name;
import blackjack.domain.RandomDeck;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class BlackjackGame {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        Players players = createPlayers();
    }

    private Players createPlayers() {
        Deck deck = RandomDeck.getInstance();
        HandGenerator handGenerator = new HandGenerator(deck);
        return retryOnException(() -> createPlayersWithName(handGenerator));
    }

    private Players createPlayersWithName(HandGenerator handGenerator) {
        List<Name> playersName = readPlayersName();
        return new Players(playersName, handGenerator);
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
