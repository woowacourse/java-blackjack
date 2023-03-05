package blackjack;

import blackjack.domain.game.BlackjackGame;
import blackjack.controller.Controller;
import blackjack.domain.deck.CardsGenerator;
import blackjack.domain.deck.Deck;
import blackjack.domain.deck.ShuffledCardsGenerator;
import blackjack.domain.participant.dealer.Dealer;
import blackjack.domain.participant.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {
    public static void main(String[] args) {

        CardsGenerator shuffledCardsGenerator = new ShuffledCardsGenerator();

        Players players = new Players();
        Dealer dealer = new Dealer();
        Deck deck = new Deck(shuffledCardsGenerator);

        BlackjackGame blackjackGame = new BlackjackGame(players, dealer, deck);
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        Controller controller = new Controller(inputView, outputView, blackjackGame);

        controller.run();
    }
}
