package blackjack;

import blackjack.domain.BlackJackGame;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackApplication {

    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame(InputView.askNames(), new Deck(Card.getCards()));

        blackJackGame.start(OutputView::printFirstCards);
        blackJackGame.askPlayerHitOrStay(InputView::askHitOrStay, OutputView::printPlayerCard);
        OutputView.printAdditionalDrawDealer(blackJackGame.askDealerHitOrStay());

        OutputView.printFinalCards(blackJackGame.getDealerDto(), blackJackGame.getPlayerDtos());
        OutputView.printFinalResult(blackJackGame.createResult());
    }
}
