package blackjack.controller;

import blackjack.domain.CardDeck;
import blackjack.domain.CardGenerator;
import blackjack.domain.Dealer;
import blackjack.domain.GameFinalResult;
import blackjack.domain.GameResult;
import blackjack.domain.GameRule;
import blackjack.domain.Player;
import blackjack.view.InputVIew;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackjackController {
    private InputVIew inputView;
    private OutputView outputView;
    private final CardGenerator cardGenerator;

    public BlackjackController() {
        this.inputView = new InputVIew();
        this.outputView = new OutputView();
        this.cardGenerator = new CardGenerator();
    }

    public void run() {
        // 게임 시작 및 카드 배분
        String[] playerNames = inputView.readPlayerName();
        List<Player> players = new ArrayList<>();

        for (String playerName : playerNames) {
            CardDeck cardDeck = new CardDeck();
            cardDeck.add(cardGenerator.generate());
            cardDeck.add(cardGenerator.generate());

            Player player = new Player(playerName, cardDeck, cardGenerator);
            players.add(player);
        }

        CardDeck cardDeck = new CardDeck();
        cardDeck.add(cardGenerator.generate());
        cardDeck.add(cardGenerator.generate());
        Dealer dealer = new Dealer(cardDeck, cardGenerator);

        outputView.displayDistributedCardStatus(dealer, players);

        // 추가 카드 뽑기
        for (Player player : players) {
            while (player.canTakeExtraCard()) {
                String answer = inputView.readOneMoreCard(player.getName());
                // 플레이어가 거절할 때까지 계속 기회를 준다
                if (answer.equals("n")) {
                    break;
                }
                player.addCard();
                outputView.displayUpdatedPlayerCardStatus(player);
            }
        }

        // 딜러 카드 뽑기 여부 (뽑은 경우만 출력)
        while (dealer.hasTakenExtraCard()) {
            outputView.displayExtraDealerCardStatus();
        }

        // 딜러와 각 플레이어들의 카드 결과 보여주기
        outputView.displayFinalCardStatus(dealer, players);

        // 딜러와 플레이어들의 승패 결과 보여주기
        GameFinalResult gameFinalResult = new GameFinalResult(new GameRule());
        Map<GameResult, Integer> dealerResult = gameFinalResult.getDealerFinalResult(dealer, players);
        outputView.displayDealerResult(dealerResult);
        for (Player player : players) {
            GameResult playerResult = gameFinalResult.getGameResultFromPlayer(player, dealer);
            outputView.displayPlayerResult(player, playerResult);
        }

    }
}
