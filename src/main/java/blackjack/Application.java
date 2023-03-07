package blackjack;

import blackjack.controller.Controller;
import blackjack.domain.deck.CardsGenerator;
import blackjack.domain.deck.Deck;
import blackjack.domain.deck.ShuffledCardsGenerator;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.participant.dealer.Dealer;
import blackjack.domain.participant.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {
    public static void main(String[] args) {

        BlackjackGame blackjackGame = createGame();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        run(blackjackGame, inputView, outputView);
    }

    private static BlackjackGame createGame() {
        CardsGenerator shuffledCardsGenerator = new ShuffledCardsGenerator();

        Players players = new Players();
        Dealer dealer = new Dealer();
        Deck deck = new Deck(shuffledCardsGenerator);

        BlackjackGame blackjackGame = new BlackjackGame(players, dealer, deck);
        return blackjackGame;
    }

    private static void run(BlackjackGame blackjackGame, InputView inputView, OutputView outputView) {
        try {
            Controller controller = new Controller(inputView, outputView, blackjackGame);
            controller.run();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
