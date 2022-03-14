package blackjack.controller;

import static blackjack.view.InputView.requestPlayerNamesInput;
import static blackjack.view.OutputView.printAllCardsAndScore;
import static blackjack.view.OutputView.printDealerExtraCardInfo;
import static blackjack.view.OutputView.printGameResult;
import static blackjack.view.OutputView.printInitialDistributionAnnouncement;

import blackjack.domain.card.CardBundle;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardStack;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.ResultReferee;
import blackjack.dto.GameResultDto;
import blackjack.dto.InitialDistributionDto;
import blackjack.strategy.CardBundleStrategy;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    public BlackjackGame initializeGame() {
        CardStack cardDeck = new CardDeck();
        List<String> playerNames = requestPlayerNamesInput();
        CardBundleStrategy strategy = (cardStack) -> CardBundle.of(cardStack.pop(), cardStack.pop());

        return new BlackjackGame(cardDeck, playerNames, strategy);
    }

    public void playGame(BlackjackGame game) {
        InitialDistributionDto gameInfoDto = InitialDistributionDto.of(game);

        printInitialDistributionAnnouncement(gameInfoDto);
        if (!gameInfoDto.getIsGameOver()) {
            distributeAllCards(game);
        }
    }

    private void distributeAllCards(BlackjackGame game) {
        game.distributeAllPlayerCards(
                InputView::requestMoreCardInput,
                OutputView::printPlayerCardDistributionInfo);
        drawDealerCards(game);
    }

    private void drawDealerCards(BlackjackGame game) {
        while (game.dealerCanDraw()) {
            game.drawDealerCard();
            printDealerExtraCardInfo();
        }
    }

    public void showGameResult(BlackjackGame game) {
        ResultReferee referee = new ResultReferee(game.getDealer(), game.getPlayers());
        GameResultDto dto = new GameResultDto(referee.getResults());

        printAllCardsAndScore(dto);
        printGameResult(dto);
    }
}
