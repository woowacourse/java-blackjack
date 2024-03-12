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
import blackjack.dto.OutcomeDto;
import blackjack.dto.OutcomesDto;
import blackjack.dto.PlayerDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        final GameBoard gameBoard = createGameBoard();
        drawInitialCards(gameBoard);

        hit(gameBoard);
        OutputView.printFinalState(
                createDealerDto(gameBoard.getDealer().getHand()), createPlayerDtos(gameBoard.getPlayers()));

        final OutcomesDto dealerOutcomeDto = createOutcomesDto(gameBoard.getDealerOutcomes());
        final List<OutcomeDto> playerOutcomeDtos = gameBoard.getPlayerOutcomeDtos();
        OutputView.printFinalOutcomes(dealerOutcomeDto, playerOutcomeDtos);
    }

    private static GameBoard createGameBoard() {
        Deck deck = new ShuffledDeckFactory().create();
        Dealer dealer = Dealer.of(deck);
        Players players = Players.from(InputView.readPlayerNames());
        return new GameBoard(dealer, players);
    }

    private static void drawInitialCards(GameBoard gameBoard) {
        final DealerDto dealerDto = createDealerDto(gameBoard.drawInitialDealerHand());
        final List<PlayerDto> playerDtos = createPlayerDtos(gameBoard.drawInitialPlayersHand());

        OutputView.printInitialState(dealerDto, playerDtos);
    }

    private static DealerDto createDealerDto(final Card card) {
        return createDealerDto(new Hand(List.of(card)));
    }

    private static DealerDto createDealerDto(final Hand hand) {
        return new DealerDto(hand.toList(), hand.calculateOptimalSum());
    }

    private static List<PlayerDto> createPlayerDtos(final Players players) {
        return players.getPlayers().stream()
                .map(Application::createPlayerDto)
                .toList();
    }

    private static PlayerDto createPlayerDto(final Player player) {
        return new PlayerDto(player.getName(), player.getHand().toList(), player.getHand().calculateOptimalSum());
    }

    private static void hit(GameBoard gameBoard) {
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
        while (gameBoard.isHit(player) && InputView.readDoesWantHit(player.getName())) {
            gameBoard.hit(player);
            OutputView.printCurrentState(createPlayerDto(player));
        }
    }

    private static void hitDealer(final GameBoard gameBoard) {
        final Dealer dealer = gameBoard.getDealer();
        while (gameBoard.isHit(dealer)) {
            gameBoard.hit(dealer);
            OutputView.printDealerDrawMessage();
        }
    }

    private static OutcomesDto createOutcomesDto(final List<Outcome> outcomes) {
        final Map<Outcome, Long> outcomeCounts = Outcome.countByKind(outcomes);
        return new OutcomesDto(
                outcomeCounts.getOrDefault(Outcome.WIN, 0L).intValue(),
                outcomeCounts.getOrDefault(Outcome.LOSE, 0L).intValue(),
                outcomeCounts.getOrDefault(Outcome.PUSH, 0L).intValue()
        );
    }
}
