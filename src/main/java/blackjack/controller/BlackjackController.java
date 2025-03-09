package blackjack.controller;

import blackjack.model.Dealer;
import blackjack.model.MatchResult;
import blackjack.model.Participant;
import blackjack.model.card.Deck;
import blackjack.model.Name;
import blackjack.model.Player;
import blackjack.model.card.RandomCardShuffler;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<Player> players = getPlayers();
        Dealer dealer = new Dealer(Deck.createStandardDeck(new RandomCardShuffler()));
        validateGame(players, dealer);
        initializeHand(dealer, players);
        outputView.printInitialCards(dealer.getVisibleCard(), players);
        askHitForAllPlayer(dealer, players);
        askHitForDealer(dealer);
        displayResult(dealer, players);
    }

    private List<Player> getPlayers() {
        List<String> playerNames = inputView.readPlayerNames();
        return playerNames.stream()
                .map(name -> new Player(new Name(name)))
                .toList();
    }

    private void validateGame(List<Player> players, Dealer dealer) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("게임 참가자가 없습니다.");
        }
        validateParticipants(players, dealer);
    }

    private void validateParticipants(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            validateParticipant(player);
        }
        validateParticipant(dealer);
    }

    private void validateParticipant(Participant participant) {
        if (!participant.getHand().isEmpty()) {
            throw new IllegalArgumentException("게임 참가자의 패가 이미 존재하여 게임을 시작할 수 없습니다.");
        }
    }

    private void initializeHand(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            dealer.dealCard(player);
            dealer.dealCard(player);
        }
        dealer.dealCard(dealer);
        dealer.dealCard(dealer);
    }

    private void askHitForAllPlayer(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            askHit(dealer, player);
        }
    }

    private void askHit(Dealer dealer, Player player) {
        while (player.canHit() && inputView.readHitOrNot(player.getName())) {
            dealer.dealCard(player);
            outputView.printPlayerHand(player);
        }
    }

    private void askHitForDealer(Dealer dealer) {
        if (dealer.canHit()) {
            dealer.dealCard(dealer);
            outputView.printDealerHit(true);
        }
    }

    private Map<Player, MatchResult> judgeMatchResults(Dealer dealer, List<Player> players) {
        Map<Player, MatchResult> results = new LinkedHashMap<>();
        for (Player player : players) {
            results.put(player, MatchResult.judge(dealer, player));
        }
        return results;
    }

    private void displayResult(Dealer dealer, List<Player> players) {
        outputView.printDealerHandAndTotal(dealer.getHand(), dealer.getTotal());
        outputView.printPlayerHandAndTotal(players);
        outputView.printMatchResult(judgeMatchResults(dealer, players));
    }
}
