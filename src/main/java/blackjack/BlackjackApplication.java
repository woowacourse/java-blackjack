package blackjack;

import static blackjack.view.OutputView.*;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.Selection;
import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.dto.PlayerDto;
import blackjack.view.InputView;

public class BlackjackApplication {

    public static void main(String[] args) {
        Deck deck = Deck.create();
        Players players = requestPlayers(deck);
        Dealer dealer = new Dealer(deck);

        printPlayersCard(toDto(players), PlayerDto.from(dealer));

        takeTurnsPlayers(players, deck);
        takeTurnDealer(dealer, deck);

        printPlayersResult(toDto(players), PlayerDto.from(dealer));
        printScoreResult(players.compete(dealer));
    }

    private static void takeTurnsPlayers(Players players, Deck deck) {
        for (Player player : players.getValue()) {
            takeTurn(player, deck);
        }
    }

    private static void takeTurn(Player player, Deck deck) {
        Selection selection = Selection.YES;
        while (selection == Selection.YES && !player.isBust()) {
            selection = requestSelection(player);
            draw(player, deck, selection);
        }
    }

    private static void takeTurnDealer(Dealer dealer, Deck deck) {
        while (dealer.isDrawable()) {
            printDealerDrawMessage();
            dealer.drawCard(deck);
        }
    }

    private static Selection requestSelection(Player player) {
        try {
            return Selection.from(InputView.requestDrawCommand(PlayerDto.from(player)));
        } catch (IllegalArgumentException exception) {
            printException(exception);
            return requestSelection(player);
        }
    }

    private static void draw(Player player, Deck deck, Selection selection) {
        if (selection == Selection.YES) {
            player.drawCard(deck);
        }
        printPlayerCards(PlayerDto.from(player));
    }

    private static List<PlayerDto> toDto(Players players) {
        return players.getValue()
            .stream()
            .map(PlayerDto::from)
            .collect(Collectors.toList());
    }

    private static Players requestPlayers(Deck deck) {
        List<String> inputNames = InputView.requestNames();

        try {
            List<Player> players = inputNames.stream()
                .map(String::trim)
                .map(input -> new Player(input, deck))
                .collect(Collectors.toList());
            return new Players(players);
        } catch (IllegalArgumentException exception) {
            printException(exception);
            return requestPlayers(deck);
        }
    }
}
