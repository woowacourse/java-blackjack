package blackjack;

import blackjack.domain.BlackJackGame;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackApplication {

    public static void main(String[] args) {
        BlackJackGame blackJackGame = BlackJackGame.start(InputView.getNames(), new CardFactory(Card.getCards()));
        OutputView.printFirstCards(blackJackGame.getDealerDto(), blackJackGame.getPlayerDtos());

        blackJackGame.askPlayerHitOrStay(InputView::getAnswerOfAdditionalDraw, OutputView::printPlayerCard);
        OutputView.printAdditionalDrawDealer(blackJackGame.askDealerHitOrStay());

        OutputView.printFinalCards(blackJackGame.getDealerDto(), blackJackGame.getPlayerDtos());
        OutputView.printFinalResult(blackJackGame.createResult());
    }
}
