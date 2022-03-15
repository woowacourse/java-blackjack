package blackjack;

import blackjack.domain.BlackJackGame;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.dto.GameResultDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackApplication {

    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame(
            InputView.askNames(),
            InputView::askBet,
            new Deck(Card.getCards()));
        OutputView.printGamers(blackJackGame.getDealerDto(), blackJackGame.getPlayerDtos());

        blackJackGame.play(InputView::askHitOrStay, OutputView::checkHoldingCards);
        GameResultDto result = blackJackGame.createResult();

        OutputView.printAdditionalDrawDealer(result.getDealerDrawCount());
        OutputView.printFinalCards(blackJackGame.getDealerDto(), blackJackGame.getPlayerDtos());
        OutputView.printFinalResult(result);
    }
}
