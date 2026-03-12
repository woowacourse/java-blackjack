package blackjack;

import blackjack.domain.deck.Deck;
import blackjack.domain.deck.RandomCardShuffler;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.PlayerFactory;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import java.util.List;

public class BlackjackGameFactory {

    private BlackjackGameFactory() {}

    public static BlackjackGame create() {
        final Dealer dealer = new Dealer(new Deck(new RandomCardShuffler()));
        final Players players = createPlayers();
        return new BlackjackGame(dealer, players);
    }

    private static Players createPlayers() {
        final List<String> names = InputView.readPlayerNames();
        final List<Integer> amounts = InputView.readBettingAmounts(names);
        return PlayerFactory.createPlayers(names, amounts);
    }
}
