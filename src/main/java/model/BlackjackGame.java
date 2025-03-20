package model;

import model.bating.Money;
import model.participant.*;
import view.InputView;
import view.Intent;
import view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class BlackjackGame {

    private final Players players;
    private final Dealer dealer;

    private BlackjackGame(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public static BlackjackGame create() {
        Players players = createPlayers();
        Dealer dealer = Dealer.create();
        return new BlackjackGame(players, dealer);
    }

    public void start() {
        distributeStartingHand();
        OutputView.printDivisionStart(dealer, players);

        distributeAdditionalCard();

        OutputView.printAllParticipantScore(dealer, players);
        printBatingResult();
    }

    private void distributeAdditionalCard() {
        for (Player player : players.getAllPlayer()) {
            receiveAdditionalCard(player, OutputView::printCurrentHands);
        }
        receiveAdditionalCard(dealer, OutputView::printStandingDealer);
    }

    private static Players createPlayers() {
        Map<Player, Money> players = new HashMap<>();
        for (Nickname nickname : readNicknames()) {
            Money money = readBatingMoneyBy(nickname);
            Player player = new Player(nickname, () -> agreeIntent(nickname.getValue()));
            players.put(player, money);
        }
        return new Players(players);
    }

    private static List<Nickname> readNicknames() {
        return InputView.readPlayerNames().stream()
                .map(Nickname::new)
                .collect(Collectors.toList());
    }

    private static Money readBatingMoneyBy(Nickname nickname) {
        return Money.from(InputView.readMoney(nickname));
    }

    public void distributeStartingHand() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.getPlayers().keySet());
        for (Participant participant : participants) {
            dealer.distributeStartingHand(participant);
        }
    }

    private void receiveAdditionalCard(Participant participant, Consumer<Participant> printState) {
        while (participant.canHit()) {
            dealer.distributeAdditionalCard(participant);
            printState.accept(participant);
        }
    }

    private static boolean agreeIntent(String nickname) {
        return Intent.from(InputView.readIntent(nickname)).equals(Intent.Y);
    }

    public void printBatingResult() {
        Map<Player, Integer> playersBatingResult = players.calculateEarnings(dealer);
        OutputView.printBatingResult(playersBatingResult);
    }
}
