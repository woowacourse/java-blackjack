package blackjack_statepattern;

import blackjack_statepattern.card.CardDeck;
import blackjack_statepattern.participant.Player;
import blackjack_statepattern.participant.Players;
import blackjack_statepattern.view.InputView;
import blackjack_statepattern.view.OutputView;
import java.util.stream.Collectors;

public final class BlackjackGame {
    private BlackjackBoard blackjackBoard;
    private CardDeck cardDeck;

    public void run() {
        Players players = initializePlayers();
        System.out.println(players);
    }

    private Players initializePlayers() {
        return new Players(InputView.askPlayerNames().stream()
                .map(String::trim)
                .map(name -> new Player(name, getBetMoney(name)))
                .collect(Collectors.toList()));
    }

    private int getBetMoney(String name) {
        try {
            return InputView.askBetMoney(name);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return getBetMoney(name);
        }
    }
}
