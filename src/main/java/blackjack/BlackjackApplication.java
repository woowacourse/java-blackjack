package blackjack;

import static blackjack.view.OutputView.*;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Name;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.ScoreResult;
import blackjack.domain.Selection;
import blackjack.dto.PlayerDto;
import blackjack.view.InputView;

public class BlackjackApplication {

    public static void main(String[] args) {
        Players players = requestPlayers();
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        drawCardTwice(players, dealer, deck);
        List<PlayerDto> playerDtos = toDto(players);
        PlayerDto dealerDto = toDto(dealer);

        printInitGameMessage(playerDtos, dealerDto);
        printOpenCard(playerDtos, dealerDto);

        takeTurnsPlayers(players, deck);
        takeTurnDealer(dealer, deck);

        printPlayersResult(toDto(players), toDto(dealer));
        ScoreResult result = players.compete(dealer);

        printResult(result);
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
            return Selection.from(InputView.requestDrawCommand(toDto(player)));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return requestSelection(player);
        }
    }

    private static void draw(Player player, Deck deck, Selection selection) {
        if (selection == Selection.YES) {
            player.drawCard(deck);
        }
        printPlayerCards(toDto(player));
    }

    private static void drawCardTwice(Players players, Dealer dealer, Deck deck) {
        for (int i = 0; i < 2; i++) {
            players.drawAll(deck);
            dealer.drawCard(deck);
        }
    }

    private static PlayerDto toDto(Player player) {
        return PlayerDto.from(player);
    }

    private static List<PlayerDto> toDto(Players players) {
        return players.getValue()
            .stream()
            .map(PlayerDto::from)
            .collect(Collectors.toList());
    }

    private static Players requestPlayers() {
        List<String> inputNames = InputView.requestNames();

        try {
            List<Player> players = inputNames.stream()
                .map(String::trim)
                .map(Name::new)
                .map(Player::new)
                .collect(Collectors.toList());
            return new Players(players);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return requestPlayers();
        }
    }
}
