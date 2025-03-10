package blackjack.controller;

import blackjack.model.participant.Dealer;
import blackjack.model.MatchResult;
import blackjack.model.participant.Participant;
import blackjack.model.card.Deck;
import blackjack.model.participant.Name;
import blackjack.model.participant.Player;
import blackjack.model.card.RandomCardShuffler;
import blackjack.model.participant.ParticipantAction;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.HashSet;
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
        Dealer dealer = getDealer();
        List<Player> players = getPlayers();
        validateGame(players, dealer);
        initializeHand(dealer, players);
        outputView.printInitialCards(dealer.getVisibleCard(), players);
        askHitForAllPlayer(dealer, players);
        askHitForDealer(dealer);
        displayResult(dealer, players);
    }

    private Dealer getDealer() {
        return new Dealer(Deck.createStandardDeck(new RandomCardShuffler()));
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
        boolean isDuplicatePlayerName = new HashSet<>(players).size() != players.size();
        if (isDuplicatePlayerName) {
            throw new IllegalArgumentException("게임 참가자의 이름이 중복되어 게임을 시작할 수 없습니다.");
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
        ParticipantAction playerAction = inputView.readHitOrNot(player.getName());
        while (player.canHit() && playerAction.isHit()) {
            dealer.dealCard(player);
            outputView.printPlayerHand(player);
        }
    }

    private void askHitForDealer(Dealer dealer) {
        ParticipantAction dealerAction = dealer.decideHit();
        if (dealer.canHit() && dealerAction.isHit()) {
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
