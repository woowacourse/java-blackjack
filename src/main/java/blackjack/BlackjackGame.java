package blackjack;

import blackjack.domain.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;
import blackjack.strategy.RandomShuffleStrategy;
import blackjack.view.InputView;
import java.util.List;

public class BlackjackGame {

    private static final String DEALER_NAME = "딜러";

    private final InputView inputView;

    public BlackjackGame() {
        this.inputView = InputView.getInstance();
    }

    public void start() {
        Deck deck = new Deck(new RandomShuffleStrategy());

        List<String> names = inputView.readPlayersName();
        Players players = Players.of(names, deck);
        Dealer dealer = new Dealer(DEALER_NAME, deck);

    }
}
