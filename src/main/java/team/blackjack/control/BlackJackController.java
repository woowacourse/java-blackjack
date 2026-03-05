package team.blackjack.control;

import java.util.List;
import java.util.Map;
import team.blackjack.control.dto.DrawResult;
import team.blackjack.control.dto.ScoreResult;
import team.blackjack.domain.BlackjackGame;
import team.blackjack.domain.Card;
import team.blackjack.domain.Player;
import team.blackjack.service.BlackJackService;
import team.blackjack.view.InputView;
import team.blackjack.view.OutputView;

public class BlackJackController {
    private final BlackJackService blackJackService;

    public BlackJackController(BlackJackService blackJackService) {
        this.blackJackService = blackJackService;
    }

    public void run() {
        /**
         * TODO:
         * 1. 사용자 이름 입력(view함수 호출)
         * 2. 게임 초기화(service함수 호출)
         * 3. 각 플레이어, 딜러 카드 발급(service함수 호출)
         * 4. 플레이어,딜러 카드 출력(view함수 호출)
         * 5. 플레이어 행동 입력(view함수 호출)
         * 6. 플레이어 행동에 따른 카드 발급(service함수 호출)
         * 7. 딜러 카드 발급 혹은 스탠드(service함수 호출)
         * 8. 딜러 카드 발급 여부 출력 (view 함수 호출)
         * 9. 결과(발급 카드 및 점수) 계산 (service함수 호출)
         * 10. 결과 출력 (view함수 호출)
         * 11. 최종 승패 계산 (service함수 호출)
         * 12. 최종 승패 출력 (view함수 호출)
         */

        OutputView.printPlayerNameRequest();
        List<String> playerNames = InputView.readPlayerNames();

        blackJackService.initGame(playerNames);
        final BlackjackGame blackjackGame = blackJackService.getBlackjackGame();

        blackJackService.drawInitialCards();
        OutputView.printDrawResult(convert(blackjackGame));

        readHitDecision(blackjackGame, blackjackGame.getPlayers());

        while (blackJackService.shouldDealerHit()) {
            OutputView.printDealerHitMessage();
            blackJackService.hitDealer();
        }

        ScoreResult scoreResult = blackJackService.calculateAllParticipantScore();
        OutputView.printParticipantScoreResult(scoreResult);
    }


    private void readHitDecision(BlackjackGame game, List<Player> players) {
        players.forEach(player -> processHit(game, player));
    }

    private void processHit(BlackjackGame game, Player player) {
        while (true) {
            OutputView.printAskDrawCard(player.getName());
            if (!InputView.readHitDecision()) {
                return;
            }
            blackJackService.draw(game, player);
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
