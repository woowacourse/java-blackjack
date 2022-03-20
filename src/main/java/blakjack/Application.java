package blakjack;

import blakjack.domain.Chip;
import blakjack.domain.PlayerName;
import blakjack.domain.card.CardDeck;
import blakjack.domain.participant.Dealer;
import blakjack.domain.participant.Participant;
import blakjack.domain.participant.Player;
import blakjack.view.InputView;
import blakjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        final List<PlayerName> playerNames = InputView.inputPlayerNames();
        final List<Chip> chips = InputView.inputBettingMoney(playerNames);
        final CardDeck cardDeck = new CardDeck();
        final Participant dealer = new Dealer();
        final List<Participant> players = generatePlayers(playerNames, chips);

        initCards(cardDeck, dealer, players);
    }

    private static List<Participant> generatePlayers(final List<PlayerName> playerNames, final List<Chip> chips) {
        final List<Participant> players = new ArrayList<>();
        for (int i = 0; i < playerNames.size(); i++) {
            players.add(new Player(playerNames.get(i), chips.get(i)));
        }
        return players;
    }

    private static void initCards(final CardDeck cardDeck, final Participant dealer, final List<Participant> players) {
        dealer.initCards(cardDeck);
        for (final Participant player : players) {
            player.initCards(cardDeck);
        }

        OutputView.printInitCards(dealer, players);
    }
}
