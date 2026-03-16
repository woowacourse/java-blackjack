package blackjack;

import blackjack.domain.deck.Deck;
import blackjack.domain.deck.shuffler.RandomCardShuffler;
import blackjack.domain.hand.Hand;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.PlayerFactory;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import java.util.List;

public class BlackjackBoardFactory {

    private BlackjackBoardFactory() {}

    public static BlackjackBoard create() {
        final Deck deck = new Deck(new RandomCardShuffler());
        final Dealer dealer = new Dealer(new Hand());
        final Players players = createPlayers();
        return new BlackjackBoard(deck, dealer, players);
    }

    private static Players createPlayers() {
        final List<String> names = InputView.readPlayerNames();
        final List<Integer> amounts = InputView.readBettingAmounts(names);
        return PlayerFactory.createPlayers(names, amounts);
    }
}
