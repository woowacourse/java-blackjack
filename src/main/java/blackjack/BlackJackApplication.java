package blackjack;

import blackjack.domain.game.BlackJackGame;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.game.BlackJackReferee;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackApplication {

    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame(
            InputView.askNames(),
            InputView::askBet,
            new Deck(Card.getCards()));

        Gamers gamers = blackJackGame.getGamers();
        OutputView.printGamers(gamers.getDealer(), gamers.getPlayers());

        BlackJackReferee result = blackJackGame.play(InputView::askHitOrStay, OutputView::checkHoldingCards);

        OutputView.printAdditionalDrawDealer(result.getDealerDrawCount());
        OutputView.printFinalCards(gamers.getDealer(), gamers.getPlayers());
        OutputView.printFinalResult(result);
    }
}
