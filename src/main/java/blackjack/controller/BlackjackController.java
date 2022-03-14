package blackjack.controller;

import static blackjack.view.InputView.requestPlayerNamesInput;
import static blackjack.view.OutputView.printAllCardsAndScore;
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
        final CardStack cardDeck = new CardDeck();
        final List<String> playerNames = requestPlayerNamesInput();
        final CardBundleStrategy strategy = (cardStack) -> CardBundle.of(cardStack.pop(), cardStack.pop());

        return new BlackjackGame(cardDeck, playerNames, strategy);
    }

    public void playGame(final BlackjackGame game) {
        final InitialDistributionDto gameInfoDto = InitialDistributionDto.of(game);

        printInitialDistributionAnnouncement(gameInfoDto);
        if (!gameInfoDto.getIsGameOver()) {
            distributeAllCards(game);
        }
    }

    private void distributeAllCards(final BlackjackGame game) {
        game.distributeAllCards(
                InputView::requestMoreCardInput,
                OutputView::printPlayerCardDistributionInfo,
                OutputView::printDealerExtraCardInfo);
    }

    public void showGameResult(final BlackjackGame game) {
        final ResultReferee referee = new ResultReferee(game.getDealer(), game.getPlayers());
        final GameResultDto dto = new GameResultDto(referee.getResults());

        printAllCardsAndScore(dto);
        printGameResult(dto);
    }
}
