package team.blackjack.controler;

import java.util.List;
import java.util.Map;
import team.blackjack.control.dto.DrawResult;
import team.blackjack.control.dto.ScoreResult;
import team.blackjack.domain.BlackjackGame;
import team.blackjack.domain.Card;
import team.blackjack.domain.Player;
import team.blackjack.domain.rule.DefaultBlackjackRule;
import team.blackjack.service.BlackJackService;
import team.blackjack.view.InputView;
import team.blackjack.view.OutputView;

public class BlackJackController {
    private final BlackJackService blackJackService;

    public BlackJackController(BlackJackService blackJackService) {
        this.blackJackService = blackJackService;
    }

    public void run() {
        OutputView.printPlayerNameRequest();
        List<String> playerNames = InputView.readPlayerNames();

        blackJackService.initGame(playerNames);
        final BlackjackGame blackjackGame = blackJackService.getBlackjackGame();

        blackJackService.drawInitialCards();
        OutputView.printDrawResult(convert(blackjackGame));

        readHitDecision(blackjackGame.getPlayers());

        while (blackJackService.shouldDealerHit()) {
            OutputView.printDealerHitMessage();
            blackJackService.hitDealer();
        }

        ScoreResult scoreResult = blackJackService.calculateAllParticipantScore();
        OutputView.printParticipantScoreResult(scoreResult);

        GameResult gameResult = blackJackService.getGameResult();
        OutputView.printGameResult(gameResult);
    }


    private void readHitDecision(List<Player> players) {
        players.forEach(this::processHit);
    }

    private void processHit(Player player) {
        while (!DefaultBlackjackRule.isBust(player.getScore())) {
            OutputView.printAskDrawCard(player.getName());

            if (!InputView.readHitDecision()) {
                return;
            }

            blackJackService.hitPlayer(player);
            OutputView.printPlayerCards(player.getName(), player.getHands().getFirst().getCardNames());
        }
    }

    private DrawResult convert(BlackjackGame game) {
        final List<String> playerNames = game.getPlayers().stream()
                .map(Player::getName)
                .toList();
        final List<Card> cards = game.getDealer().getHand().getCards();
        final Map<String, List<String>> playerCards = game.getAllPlayerCards();

        return new DrawResult(playerNames, cards.getFirst().getCardName(), playerCards);
    }
}
