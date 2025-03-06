package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.manager.BlackJackInitManager;
import blackjack.manager.BlackjackProcessManager;
import blackjack.manager.CardsGenerator;
import blackjack.manager.GameRuleEvaluator;
import blackjack.view.Confirmation;
import java.util.List;

public class BlackjackController {

    private final GameRuleEvaluator gameRuleEvaluator;

    public BlackjackController(GameRuleEvaluator gameRuleEvaluator) {
        this.gameRuleEvaluator = gameRuleEvaluator;
    }

    public void run() {
        CardsGenerator cardsGenerator = new CardsGenerator();
        BlackJackInitManager blackJackInitManager = new BlackJackInitManager(cardsGenerator);

        Deck deck = blackJackInitManager.generateDeck();
        // 이름 입력 받은 것 넣기
        Players players = blackJackInitManager.savePlayers(List.of(), Hand::new);
        Dealer dealer = blackJackInitManager.saveDealer(Hand::new);

        BlackjackProcessManager blackjackProcessManager = new BlackjackProcessManager(deck);

        // 딜러 카드 분배
        for (Player player : players.getPlayers()) {
            blackjackProcessManager.giveStartingCards(player.getCardHolder());
        }
        blackjackProcessManager.giveStartingCards(dealer.getCardHolder());

        // TODO 카드를 출력하는 것을 만들어야함
        for (Player player : players.getPlayers()) {
            while (gameRuleEvaluator.canTakeCardFor(player)) {
                Confirmation confirmation = Confirmation.Y;
                // 플레이어에게 카드 줘야하는 지 확인
                //  TODO inputViewer한테 물어봄
                if (confirmation.equals(Confirmation.N)) {
                    break;
                }
                blackjackProcessManager.giveCard(player.getCardHolder());

                // TODO BUSTED
            }
        }

        while (gameRuleEvaluator.canTakeCardFor(dealer)) {
            // TODO 16이하라 출력해야 한다는 것을 표시
            blackjackProcessManager.giveCard(dealer.getCardHolder());
        }

        boolean isBustedForDealer = gameRuleEvaluator.isBustedFor(dealer);

        // 비교하는 작업이 시작됨
        for (Player player : players.getPlayers()) {
            boolean isBustedForPlayer = gameRuleEvaluator.isBustedFor(player);

            // 딜러가 bust 됐다면
            if (isBustedForDealer) {
                if (isBustedForPlayer) {
                    // 무로 처리한다.
                }

                // 플레이어 승리
            }

            // 딜러가 bust 안됐다면
            if (isBustedForPlayer) {
                // 딜러 승리
            }

            // 값 비교

        }

        // 사람 이름 입력을 받는다

        // players 저장

        // 카드 두 장 분배

        // 딜러 카드 한 장
        // 플레이어들 카드 두장 출력

        // 플레이어 한명씩 카드 받는지 여부 (이 때, Busted되거나 N을 입력하면 기회 뺏김)

        // 딜러가 더 받는지 여부  (더 받았으면 출력)

        // 딜러와 플레이어들의 카드들과 결과 값 출력 (이때 결과는 21에 가까운 최적의 값으로 선택되어야함)

        // 승패 출력

    }
}
