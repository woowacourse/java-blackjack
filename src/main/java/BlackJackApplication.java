import controller.BlackjackGame;
import view.InputView;
import view.OutputView;

public class BlackJackApplication {
    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame(InputView::scanGamblerInfos);

        blackjackGame.spreadCards(OutputView::printInitialInfo);
        blackjackGame.playForGamblers(InputView::scanIsHit, OutputView::printCards);
        blackjackGame.playForDealer(OutputView::breakLine, OutputView::printDealerAddCard);

        blackjackGame.printCardsAndScore(OutputView::printCardAndScoreDtos);
        blackjackGame.printRevenue(OutputView::printResult);
    }
}
