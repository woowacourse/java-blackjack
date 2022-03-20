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
        final List<PlayerName> playerNames = catchPlayerNameException();
        final List<Chip> chips = catchChipException(playerNames);
        final CardDeck cardDeck = new CardDeck();
        final Participant dealer = new Dealer();
        final List<Participant> players = generatePlayers(playerNames, chips);
        gameStart(cardDeck, dealer, players);
    }

    private static List<PlayerName> catchPlayerNameException() {
        try {
            return InputView.inputPlayerNames();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return catchPlayerNameException();
        }
    }

    private static List<Chip> catchChipException(final List<PlayerName> playerNames) {
        try {
            return InputView.inputBettingMoney(playerNames);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return catchChipException(playerNames);
        }
    }

    private static List<Participant> generatePlayers(final List<PlayerName> playerNames, final List<Chip> chips) {
        final List<Participant> players = new ArrayList<>();
        for (int i = 0; i < playerNames.size(); i++) {
            players.add(new Player(playerNames.get(i), chips.get(i)));
        }
        return players;
    }

    private static void gameStart(final CardDeck cardDeck, final Participant dealer, final List<Participant> players) {
        initCards(cardDeck, dealer, players);
        hit(cardDeck, dealer, players);
        showScore(dealer, players);
    }

    private static void initCards(final CardDeck cardDeck, final Participant dealer, final List<Participant> players) {
        dealer.initCards(cardDeck);
        for (final Participant player : players) {
            player.initCards(cardDeck);
        }

        OutputView.printInitCards(dealer, players);
    }

    private static void hit(final CardDeck cardDeck, final Participant dealer, final List<Participant> players) {
        for (final Participant player : players) {
            catchPlayerHitException(cardDeck, player);
        }
        catchDealerHitException(cardDeck, dealer);
    }

    private static void catchPlayerHitException(final CardDeck cardDeck, final Participant player) {
        try {
            hitPlayer(cardDeck, player);
        } catch (IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private static void hitPlayer(final CardDeck cardDeck, final Participant player) {
        while (InputView.inputHitRequest(player)) {
            player.hit(cardDeck.draw());
            OutputView.printDealerCards(player);
        }
        player.stay();
    }

    private static void catchDealerHitException(final CardDeck cardDeck, final Participant dealer) {
        try {
            dealer.hit(cardDeck.draw());
            OutputView.printDealerHit();
        } catch (IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private static void showScore(final Participant dealer, final List<Participant> players) {
        OutputView.printScore(dealer, players);
    }
}
