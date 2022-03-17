package blackjack.domain;

import static blackjack.view.OutputView.*;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Deck;
import blackjack.domain.card.HoldCards;
import blackjack.domain.player.BettingAmount;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Name;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.view.InputView;

public class BlackjackGame {

    private final Deck deck;
    private final Players players;
    private final Dealer dealer;

    private BlackjackGame(Deck deck, Players players, Dealer dealer) {
        this.deck = deck;
        this.players = players;
        this.dealer = dealer;
    }

    public static BlackjackGame init() {
        Deck deck = Deck.create();
        Players players = requestPlayersBettingAmount(requestNames(), deck);
        Dealer dealer = new Dealer(deck);
        return new BlackjackGame(deck, players, dealer);
    }

    public void run() {
        printPlayersCard(players.copy(), dealer.copy());

        takeTurnsPlayers();
        takeTurnDealer();

        printPlayersResult(players.copy(), dealer.copy());
        printScoreResult(players.compete(dealer));
    }

    private static Players requestPlayersBettingAmount(List<Name> names, Deck deck) {
        try {
            List<Player> players = names.stream()
                .map(name -> new Player(name, HoldCards.drawTwoCards(deck),
                    new BettingAmount(InputView.requestBettingAmount(name))))
                .collect(Collectors.toList());
            return new Players(players);
        } catch (IllegalArgumentException exception) {
            printException(exception);
            return requestPlayersBettingAmount(names, deck);
        }
    }

    private static List<Name> requestNames() {
        try {
            return InputView.requestNames()
                .stream()
                .map(Name::new)
                .collect(Collectors.toList());
        } catch (IllegalArgumentException exception) {
            printException(exception);
            return requestNames();
        }
    }

    private void takeTurnsPlayers() {
        for (Player player : players.getValue()) {
            takeTurn(player);
        }
    }

    private void takeTurn(Player player) {
        if (player.isBust()) {
            return;
        }
        if (Selection.NO == requestSelection(player)) {
            return;
        }
        player.drawCard(deck);
        printPlayerCards(player);
        takeTurn(player);
    }

    private void takeTurnDealer() {
        while (dealer.isDrawable()) {
            printDealerDrawMessage();
            dealer.drawCard(deck);
        }
    }

    private Selection requestSelection(Player player) {
        try {
            return Selection.from(InputView.requestDrawCommand(player));
        } catch (IllegalArgumentException exception) {
            printException(exception);
            return requestSelection(player);
        }
    }
}
