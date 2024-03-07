package blackjack;

import blackjack.domain.Dealer;
import blackjack.domain.GameBoard;
import blackjack.domain.Name;
import blackjack.domain.Outcome;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.Referee;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckShuffleFactory;
import blackjack.domain.dto.DealerDto;
import blackjack.domain.dto.OutcomeDto;
import blackjack.domain.dto.PlayersDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        Deck deck = new Deck(new DeckShuffleFactory());
        Dealer dealer = Dealer.create();
        Players players = Players.from(InputView.readPlayerNames());
        final GameBoard gameBoard = new GameBoard(deck, dealer, players);
        final DealerDto dealerDto = gameBoard.drawInitialDealerCards();
        final PlayersDto playersDto = gameBoard.drawInitialPlayersCards();

        OutputView.printInitialState(dealerDto, playersDto);

        hitPlayers(gameBoard, players);
        hitDealer(gameBoard, dealer);

        OutputView.printFinalState(dealer.allCardToDto(), players.toDto());

        Referee referee = new Referee(dealer.getCards());
        final List<Outcome> reversedOutcomes = Outcome.reverse(gameBoard.calculateOutcomes(referee));
        final List<OutcomeDto> playerOutcomes = new ArrayList<>();
        for (Name name : players.getNames()) {
            playerOutcomes.add(new OutcomeDto(name, gameBoard.calculateOutcome(referee, name)));
        }
        OutputView.printFinalOutcomes(Outcome.toDto(reversedOutcomes), playerOutcomes);
    }

    private static void hitPlayers(final GameBoard gameBoard, final Players players) {
        for (Player player : players.getPlayers()) {
            hitPlayer(gameBoard, player);
        }
    }

    private static void hitPlayer(final GameBoard gameBoard, final Player player) {
        while (gameBoard.isHit(player) && InputView.readDoesWantHit(player.getName())) {
            gameBoard.hit(player);
            OutputView.printCurrentState(player.toDto());
        }
    }

    private static void hitDealer(final GameBoard gameBoard, final Dealer dealer) {
        System.out.println();
        while (gameBoard.isHit(dealer)) {
            gameBoard.hit(dealer);
            OutputView.printDealerDrawMessage();
        }
    }
}
