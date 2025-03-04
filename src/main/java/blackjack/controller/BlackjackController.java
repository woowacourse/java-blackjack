package blackjack.controller;

import blackjack.domain.CardDeck;
import blackjack.domain.CardGenerator;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.view.InputVIew;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

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

        //
    }
}
