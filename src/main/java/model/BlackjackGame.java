package model;

import model.card.Card;
import model.card.CardDeck;
import model.participant.*;
import view.InputView;
import view.Intent;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class BlackjackGame {

    private static final int INITIAL_DEAL_COUNT = 2;

    private final Players players;
    private final Dealer dealer;
    private final CardDeck deck;

    private BlackjackGame(Players players, Dealer dealer, CardDeck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public static BlackjackGame create() {
        Players players = createPlayers();
        Dealer dealer = Dealer.create();
        CardDeck deck = new CardDeck();
        return new BlackjackGame(players, dealer, deck);
    }

    public void start() {
        distributeStartingHand();
        OutputView.printDivisionStart(dealer, players);

        distributeAdditionalCard();

        OutputView.printAllParticipantScore(dealer, players);
        printBatingResult();
    }

    private void distributeAdditionalCard() {
        for (Player player : players.getPlayers()) {
            receiveAdditionalCard(player, OutputView::printCurrentHands);
        }
        receiveAdditionalCard(dealer, OutputView::printStandingDealer);
    }

    private static Players createPlayers() {
        List<Player> players = new ArrayList<>();
        for (Nickname nickname : readNicknames()) {
            Money money = readBatingMoneyBy(nickname);
            Player player = new Player(nickname, money, () -> agreeIntent(nickname.getValue()));
            players.add(player);
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
        participants.addAll(players.getPlayers());
        for (Participant participant : participants) {
            distributeCard(participant, INITIAL_DEAL_COUNT);
        }
    }

    public void distributeCard(Participant participant, int amount) {
        List<Card> pickCards = deck.pickCard(amount);
        participant.addCards(pickCards);
    }

    private void receiveAdditionalCard(Participant participant, Consumer<Participant> printState) {
        while (participant.canHit()) {
            distributeCard(participant, 1);
            printState.accept(participant);
        }
    }

    private static boolean agreeIntent(String nickname) {
        return Intent.from(InputView.readIntent(nickname)).equals(Intent.Y);
    }

    public void printBatingResult() {
        Map<Player, Integer> playersBatingResult = players.getMatchResult(dealer);
        OutputView.printBatingResult(playersBatingResult);
    }
}
