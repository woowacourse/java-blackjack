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
        List<Player> players = setupPlayers();

        // 딜러를 생성한다.
        Dealer dealer = Dealer.create();

        // 카드 덱을 초기화 한다.
        CardDeck cardDeck = CardDeck.of(pickStrategy);

        // 딜러, 플레이어 카드 초기 2장 나눠줌
        distributeInitCards(dealer, cardDeck, players);

        // 각 플레이어 히트/스탠드 진행
        askAllPlayersHitOrStand(players, cardDeck);

        // 딜러 카드 16초과할 때까지 추가
        addDealerCard(dealer, cardDeck);

        // 딜러 카드 및 결과 공개
        openDealerHands(dealer);

        // 플레이어 카드 및 결과 공개
        openPlayersHands(players);

        // 결과 출력
        printResult(players, dealer);
    }

    private List<Player> setupPlayers() {
        outputView.printPlayerNamesInputPrompt();
        List<String> names = inputView.inputPlayerNames();

        return names.stream()
                .map(Player::of).toList();
    }

    // 참가자 카드 분배
    private void distributeInitCards(Dealer dealer, CardDeck cardDeck, List<Player> players) {
        dealer.pickInitCards(cardDeck);
        players.forEach(player -> player.pickInitCards(cardDeck));

        outputView.printCardDistributionCompleted(players);

        // 딜러 1장 공개, 플레이어 2장 공개
        outputView.printParticipantCards(dealer.getName(), dealer.getOpenedCards());
        players.forEach(player -> outputView.printParticipantCards(player.getName(), player.getOpenedCards()));
    }

    private void askAllPlayersHitOrStand(List<Player> players, CardDeck cardDeck) {
        for (Player player : players) {
            askHitOrStand(cardDeck, player);
        }
    }

    private void askHitOrStand(CardDeck cardDeck, Player player) {
        outputView.printMoreCardInputPrompt(player.getName());
        boolean isContinued = inputView.inputMoreCard();

        while (isContinued) {
            player.pickAdditionalCard(cardDeck);
            outputView.printParticipantCards(player.getName(), player.getAllCard());
            outputView.printMoreCardInputPrompt(player.getName());
            isContinued = inputView.inputMoreCard();
        }
    }

    private void addDealerCard(Dealer dealer, CardDeck cardDeck) {
        while (dealer.canPick()) {
            dealer.pickAdditionalCard(cardDeck);
            outputView.printDealerPicksCard();
        }
        outputView.printDealerDoesNotPickCard();
    }

    private void openPlayersHands(List<Player> players) {
        for (Player player : players) {
            outputView.printParticipantCardsWithScore(
                    player.getName(),
                    player.getAllCard(),
                    player.getCurrentTotalScore()
            );
        }
    }

    private void openDealerHands(Dealer dealer) {
        outputView.printParticipantCardsWithScore(
                dealer.getName(),
                dealer.getAllCard(),
                dealer.getCurrentTotalScore()
        );
    }

    private void printResult(List<Player> players, Dealer dealer) {
        // 결과 계산
        TotalResult totalResult = TotalResult.of(players, dealer);

        // 최종 승패 출력
        outputView.printResult(totalResult);
    }
}
