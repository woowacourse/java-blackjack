package blackjack.controller;

import static blackjack.view.InputView.requestMoreCardInput;
import static blackjack.view.InputView.requestPlayerNamesInput;
import static blackjack.view.OutputView.printAllCardsAndScore;
import static blackjack.view.OutputView.printDealerBlackjackInfo;
import static blackjack.view.OutputView.printDealerExtraCardInfo;
import static blackjack.view.OutputView.printGameResult;
import static blackjack.view.OutputView.printInitialParticipantsCards;
import static blackjack.view.OutputView.printPlayerBustInfo;
import static blackjack.view.OutputView.printPlayerCardsInfo;

import blackjack.domain.card.CardBundle;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardStack;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.ResultReferee;
import blackjack.domain.participant.Player;
import blackjack.dto.InitialDistributionDto;
import blackjack.dto.GameResultDto;
import blackjack.strategy.CardBundleStrategy;
import java.util.List;

public class BlackjackController {

    public BlackjackGame initializeGame() {
        CardStack cardDeck = new CardDeck();
        List<String> playerNames = requestPlayerNamesInput();
        CardBundleStrategy strategy = (cardStack) -> CardBundle.of(cardStack.pop(), cardStack.pop());

        return new BlackjackGame(cardDeck, playerNames, strategy);
    }

    public void playGame(BlackjackGame game) {
        InitialDistributionDto dto = new InitialDistributionDto(game.getParticipants());

        if (game.isBlackjackDealer()) {
            printDealerBlackjackInfo(dto);
            return;
        }

        printInitialParticipantsCards(dto);
        distributeAllCards(game);
    }

    public void distributeAllCards(BlackjackGame game) {
        List<Player> players = game.getPlayers();
        players.forEach(player -> drawAllPlayerCards(player, game));
        drawDealerCards(game);
    }

    private void drawAllPlayerCards(Player player, BlackjackGame game) {
        while (player.canDraw() && requestMoreCardInput(player.getName())) {
            player.receiveCard(game.popCard());
            printPlayerCardsInfo(player);
        }
        showPlayerBust(player);
    }

    private void showPlayerBust(Player player) {
        if (player.isBust()) {
            printPlayerBustInfo();
        }
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
