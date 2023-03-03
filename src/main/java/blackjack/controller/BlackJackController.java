package blackjack.controller;

import blackjack.domain.BlackJackDeckGenerator;
import blackjack.domain.BlackJackGame;
import blackjack.domain.Card;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;

public class BlackJackController {

    private BlackJackGame blackJackGame;

    public void run() {
        List<String> playerNames = InputView.askPlayerNames();
        blackJackGame = new BlackJackGame(new BlackJackDeckGenerator(), playerNames);

        blackJackGame.handOut();

        Card dealerFirstCard = blackJackGame.openDealerFirstCard();
        Map<String, List<Card>> playersCards = blackJackGame.openPlayersCards();
        OutputView.showOpenCards(dealerFirstCard, playersCards);
        // TODO 각각 플레이어 처리
    }

    private void hitOrStay(String playerName) {
        InputView.askToTake(playerName);
        // TODO blackjackgame hit or stay
        // TODO outputview 플레이어 보유 카드 출력
    }
}
