package blackjack.controller;

import blackjack.dto.PlayerResultDto;
import blackjack.dto.PlayerResultsDto;
import blackjack.model.cardDeck.CardDeck;
import blackjack.model.cardDeck.RandomPickStrategy;
import blackjack.model.money.Money;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import blackjack.model.result.PlayerResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<Player> players = setupPlayers();
        Dealer dealer = Dealer.create();
        CardDeck cardDeck = CardDeck.of(new RandomPickStrategy());

        setupPlayersBettingMoney(players);

        distributeInitCards(dealer, cardDeck, players);

        askPlayersHitOrStand(players, cardDeck);
        dealerHits(dealer, cardDeck);

        openDealerHands(dealer);
        openPlayersHands(players);

        printResult(players, dealer);
    }

    private void setupPlayersBettingMoney(List<Player> players) {
        players.forEach(this::setupBettingMoney);
    }

    private void setupBettingMoney(Player player) {
        outputView.printBettingAmountInputPlayer(player.getName());
        double amount = inputView.inputBettingAmount();
        player.bet(amount);
    }

    private List<Player> setupPlayers() {
        outputView.printPlayerNamesInputPrompt();
        List<String> names = inputView.inputPlayerNames();

        return names.stream()
                .map(Player::of)
                .toList();
    }

    private void distributeInitCards(Dealer dealer, CardDeck cardDeck, List<Player> players) {
        dealer.pickInitCards(cardDeck);
        players.forEach(player -> player.pickInitCards(cardDeck));

        outputView.printCardDistributionCompleted(players);

        outputView.printParticipantHands(dealer.getName(), dealer.getInitCards());
        players.forEach(player -> outputView.printParticipantHands(player.getName(), player.getInitCards()));
    }

    private void askPlayersHitOrStand(List<Player> players, CardDeck cardDeck) {
        players.forEach(player -> askHitOrStand(cardDeck, player));
    }

    private void askHitOrStand(CardDeck cardDeck, Player player) {
        outputView.printMoreCardInputPrompt(player.getName());
        boolean isContinued = inputView.inputMoreCard();

        while (isContinued) {
            player.pickAdditionalCard(cardDeck);
            outputView.printParticipantHands(player.getName(), player.getAllCards());
            outputView.printMoreCardInputPrompt(player.getName());
            isContinued = inputView.inputMoreCard();
        }
    }

    private void dealerHits(Dealer dealer, CardDeck cardDeck) {
        while (dealer.canPick()) {
            dealer.pickAdditionalCard(cardDeck);
            outputView.printDealerPicksCard();
        }
        outputView.printDealerDoesNotPickCard();
    }

    private void openPlayersHands(List<Player> players) {
        players.forEach(player -> outputView.printParticipantCardsWithScore(
                player.getName(),
                player.getAllCards(),
                player.getFinalScore()
        ));
    }

    private void openDealerHands(Dealer dealer) {
        outputView.printParticipantCardsWithScore(
                dealer.getName(),
                dealer.getAllCards(),
                dealer.getFinalScore()
        );
    }

    private void printResult(List<Player> players, Dealer dealer) {
        List<PlayerResultDto> results = new ArrayList<>();

        for (Player player : players) {
            PlayerResult result = dealer.judgePlayerResult(player);
            Money profit = player.calculateProfit(result);
            results.add(new PlayerResultDto(player.getName(), profit));
        }

        outputView.printResult(new PlayerResultsDto(results));
    }
}
