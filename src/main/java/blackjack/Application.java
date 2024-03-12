package blackjack;

import blackjack.domain.Dealer;
import blackjack.domain.GameBoard;
import blackjack.domain.Outcome;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.card.ShuffledDeckFactory;
import blackjack.dto.DealerDto;
import blackjack.dto.PlayerDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        final GameBoard gameBoard = createGameBoard();
        drawInitialHands(gameBoard);

        hitGamers(gameBoard);

        OutputView.printFinalState(
                createDealerDto(gameBoard.getDealer().getHand()), createPlayerDtos(gameBoard.getPlayers()));
        OutputView.printFinalOutcomes(Outcome.countByKind(gameBoard.getDealerOutcome()), gameBoard.getPlayerOutcomes());
    }

    private static GameBoard createGameBoard() {
        Deck deck = new ShuffledDeckFactory().create();
        Dealer dealer = Dealer.from(deck);
        Players players = Players.from(InputView.readPlayerNames());
        return new GameBoard(dealer, players);
    }

    private static void drawInitialHands(GameBoard gameBoard) {
        gameBoard.drawInitialPlayersHand();
        gameBoard.drawInitialDealerHand();
        final DealerDto dealerDto = createDealerDto(gameBoard.getDealerFirstCard());
        final List<PlayerDto> playerDtos = createPlayerDtos(gameBoard.getPlayers());

        OutputView.printInitialState(dealerDto, playerDtos);
    }

    private static DealerDto createDealerDto(final Card card) {
        return createDealerDto(new Hand(List.of(card)));
    }

    private static DealerDto createDealerDto(final Hand hand) {
        return new DealerDto(hand.getCards(), hand.calculateOptimalSum());
    }

    private static List<PlayerDto> createPlayerDtos(final Players players) {
        return players.getPlayers().stream()
                .map(Application::createPlayerDto)
                .toList();
    }

    private static PlayerDto createPlayerDto(final Player player) {
        return new PlayerDto(player.getName(), player.getHand().getCards(), player.calculateScore());
    }

    private static void hitGamers(GameBoard gameBoard) {
        hitPlayers(gameBoard);
        OutputView.printLineSeparator();
        hitDealer(gameBoard);
        OutputView.printLineSeparator();
    }

    private static void hitPlayers(final GameBoard gameBoard) {
        final Players players = gameBoard.getPlayers();
        for (Player player : players.getPlayers()) {
            hitPlayer(gameBoard, player);
        }
    }

    private static void hitPlayer(final GameBoard gameBoard, final Player player) {
        while (gameBoard.canPlayerHit(player) && InputView.readDoesWantHit(player.getName())) {
            gameBoard.hitPlayer(player);
            OutputView.printCurrentState(createPlayerDto(player));
        }
    }

    private static void hitDealer(final GameBoard gameBoard) {
        while (gameBoard.canDealerHit()) {
            gameBoard.hitDealer();
            OutputView.printDealerDrawMessage();
        }
    }
}
