package blackjack.controller;

import blackjack.model.CardDeck;
import blackjack.model.Dealer;
import blackjack.model.PickStrategy;
import blackjack.model.Player;
import blackjack.model.TotalResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final PickStrategy pickStrategy;

    public BlackjackController(InputView inputView, OutputView outputView, PickStrategy pickStrategy) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.pickStrategy = pickStrategy;
    }

    public void run() {
        //플레이어들을 생성한다.
        outputView.printPlayerNamesInputPrompt();
        List<String> names = inputView.inputPlayerNames();

        List<Player> players = names.stream()
                .map(Player::of).toList();

        // 딜러, 플레이어 카드 초기 2장 나눠줌
        CardDeck cardDeck = CardDeck.of(pickStrategy);

        Dealer dealer = Dealer.create();
        dealer.pickCard(cardDeck);
        dealer.pickCard(cardDeck);

        players.forEach(player -> player.pickACard(cardDeck));
        players.forEach(player -> player.pickACard(cardDeck));

        outputView.printCardDistributionCompleted(names);

        // 딜러 1장 공개, 플레이어 2장 공개
        outputView.printDealerCards(dealer.getCards(1));
        players.forEach(player -> outputView.printPlayersCards(player.getName(), player.getCards(2)));

        // 각 플레이어 히트/스탠드 진행
        for (Player player : players) {
            outputView.printMoreCardInputPrompt(player.getName());
            boolean isContinued = inputView.inputMoreCard();

            while(isContinued) {
                player.pickACard(cardDeck);
                outputView.printPlayersCards(player.getName(), player.getAllCard());
                outputView.printMoreCardInputPrompt(player.getName());
                isContinued = inputView.inputMoreCard();
            }
        }

        // 딜러 카드 16초과할 때까지 추가
        while (dealer.canPick()) {
            dealer.pickCard(cardDeck);
            outputView.printDealerPicksCard();
        }
        outputView.printDealerDoesNotPickCard();

        // 딜러 카드 및 결과 공개
        outputView.printDealerCardsWithScore(
                dealer.getAllCard(),
                dealer.getCurrentTotalScore()
        );

        // 플레이어 카드 및 결과 공개
        for (Player player : players) {
            outputView.printPlayersCardsWithScore(
                    player.getName(),
                    player.getAllCard(),
                    player.getCurrentTotalScore()
            );
        }

        // 결과 계산
        TotalResult totalResult = TotalResult.of(players, dealer);

        // 최종 승패 출력
        outputView.printResult(totalResult);
    }
}
