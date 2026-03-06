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

        // 딜러를 생성한다.
        Dealer dealer = Dealer.create();

        // 카드 덱을 초기화 한다.
        CardDeck cardDeck = CardDeck.of(pickStrategy);

        // 딜러, 플레이어 카드 초기 2장 나눠줌
        dealer.pickInitCards(cardDeck);
        players.forEach(player -> player.pickInitCards(cardDeck));

        outputView.printCardDistributionCompleted(names);

        // 딜러 1장 공개, 플레이어 2장 공개
        outputView.printParticipantCards(dealer.getName(), dealer.getOpenedCards());
        players.forEach(player -> outputView.printParticipantCards(player.getName(), player.getOpenedCards()));


        // 각 플레이어 히트/스탠드 진행
        for (Player player : players) {
            outputView.printMoreCardInputPrompt(player.getName());
            boolean isContinued = inputView.inputMoreCard();

            while (isContinued) {
                player.pickAdditionalCard(cardDeck);
                outputView.printParticipantCards(player.getName(), player.getAllCard());
                outputView.printMoreCardInputPrompt(player.getName());
                isContinued = inputView.inputMoreCard();
            }
        }

        // 딜러 카드 16초과할 때까지 추가
        while (dealer.canPick()) {
            dealer.pickAdditionalCard(cardDeck);
            outputView.printDealerPicksCard();
        }
        outputView.printDealerDoesNotPickCard();

        // 딜러 카드 및 결과 공개
        outputView.printParticipantCardsWithScore(
                dealer.getName(),
                dealer.getAllCard(),
                dealer.getCurrentTotalScore()
        );

        // 플레이어 카드 및 결과 공개
        for (Player player : players) {
            outputView.printParticipantCardsWithScore(
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
