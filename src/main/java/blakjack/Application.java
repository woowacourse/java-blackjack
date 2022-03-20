package blakjack;

import blakjack.domain.Chip;
import blakjack.domain.PlayerName;
import blakjack.domain.card.CardDeck;
import blakjack.domain.participant.Dealer;
import blakjack.domain.participant.Participant;
import blakjack.domain.participant.Player;
import blakjack.view.InputView;

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
        for (final PlayerName playerName : playerNames) {
            generatePlayers(players, playerName, chips);
        }
        return players;
    }

    private static void generatePlayers(final List<Participant> players, final PlayerName playerName, final List<Chip> chips) {
        for (final Chip chip : chips) {
            players.add(new Player(playerName, chip));
        }
    }

    private static void initCards(final CardDeck cardDeck, final Participant dealer, final List<Participant> players) {
        dealer.initCards(cardDeck);
        for (final Participant player : players) {
            player.initCards(cardDeck);
        }
    }
}
