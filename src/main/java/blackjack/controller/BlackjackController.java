package blackjack.controller;

import static blackjack.view.InputView.requestPlayerNamesInput;
import static blackjack.view.OutputView.printDealerBlackjackInfo;
import static blackjack.view.OutputView.printGameResult;
import static blackjack.view.OutputView.printInitialParticipantsCards;

import blackjack.domain.betting.PlayerBettings;
import blackjack.domain.card.CardBundle;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardStack;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.ResultReferee;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.GameParticipants;
import blackjack.domain.participant.Player;
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

    public PlayerBettings initializeBettings(final BlackjackGame game) {
        List<Player> players = game.getParticipants().getPlayers();

        return PlayerBettings.of(players, InputView::requestBettingAmountInput);
    }

    public void playGame(final BlackjackGame game) {
        final InitialDistributionDto dto = InitialDistributionDto.of(game);

        if (game.isBlackjackDealer()) {
            printDealerBlackjackInfo(dto);
            return;
        }

        printInitialParticipantsCards(dto);
        distributeAllCards(game);
    }

    private void distributeAllCards(final BlackjackGame game) {
        game.distributeAllCards(
                InputView::requestMoreCardInput,
                OutputView::printPlayerCardDistributionInfo,
                OutputView::printDealerExtraCardInfo);
    }

    public void showGameResult(final BlackjackGame game) {
        GameParticipants participants = game.getParticipants();
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();

        final ResultReferee referee = new ResultReferee(dealer, players);
        final GameResultDto dto = new GameResultDto(referee.getResults());

        printGameResult(dto);
    }
}
