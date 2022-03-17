package blackjack;

import java.util.List;

import blackjack.domain.game.BlackJackGame;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.game.BlackJackReferee;
import blackjack.domain.gamer.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackApplication {

    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame(InputView.askNames(), InputView::askBet,
            new Deck(Card.getCards()));

        Gamers gamers = blackJackGame.getGamers();
        Dealer dealer = gamers.getDealer();
        List<Player> players = gamers.getPlayers();

        OutputView.printGamers(dealer, players);

        BlackJackReferee result = blackJackGame.play(InputView::askHitOrStay, OutputView::checkHoldingCards);

        OutputView.printAdditionalDrawDealer(dealer.findHitCount());
        OutputView.printFinalCards(gamers.getDealer(), gamers.getPlayers());
        OutputView.printFinalResult(result);
    }
}
