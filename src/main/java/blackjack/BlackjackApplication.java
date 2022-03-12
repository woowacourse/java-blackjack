package blackjack;

import static blackjack.view.OutputView.printDealerDrawMessage;
import static blackjack.view.OutputView.printInitGameMessage;
import static blackjack.view.OutputView.printOpenCard;
import static blackjack.view.OutputView.printPlayerCards;
import static blackjack.view.OutputView.printPlayersResult;
import static blackjack.view.OutputView.printResult;

import blackjack.domain.BlackjackGame;
import blackjack.domain.Drawable;
import blackjack.domain.Name;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.ScoreResult;
import blackjack.domain.Selection;
import blackjack.dto.PlayerDto;
import blackjack.view.InputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackApplication {

    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame(requestPlayers());

        startSetting(blackjackGame);
        takeTurns(blackjackGame);
        endGame(blackjackGame);
    }

    private static void startSetting(BlackjackGame blackjackGame) {
        blackjackGame.drawCardTwice();

        List<PlayerDto> playerDtos = toDto(blackjackGame.getPlayers());
        PlayerDto dealerDto = toDto(blackjackGame.getDealer());
        printInitGameMessage(playerDtos, dealerDto);
        printOpenCard(playerDtos, dealerDto);
    }

    private static void takeTurns(BlackjackGame blackjackGame) {
        takeTurnsPlayers(blackjackGame.getPlayers(), blackjackGame.getDeck());
        takeTurnDealer(blackjackGame);
    }

    private static void endGame(BlackjackGame blackjackGame) {
        printPlayersResult(toDto(blackjackGame.getPlayers()), toDto(blackjackGame.getDealer()));
        ScoreResult result = blackjackGame.makeResults();
        printResult(result);
    }

    private static void takeTurnsPlayers(Players players, Drawable deck) {
        for (Player player : players.getValue()) {
            takeTurn(player, deck);
        }
    }

    private static void takeTurn(Player player, Drawable deck) {
        Selection selection = Selection.YES;
        while (selection == Selection.YES && !player.isBust()) {
            selection = requestSelection(player);
            draw(player, deck, selection);
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

    private static void takeTurnDealer(BlackjackGame blackjackGame) {
        while (blackjackGame.checkDealerDrawable()) {
            printDealerDrawMessage();
            blackjackGame.drawDealerCard();
        }
    }

    private static void draw(Player player, Drawable deck, Selection selection) {
        if (selection == Selection.YES) {
            player.drawCard(deck);
        }
        printPlayerCards(toDto(player));
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
