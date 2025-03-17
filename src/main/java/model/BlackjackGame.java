package model;

import view.Intent;
import model.card.Card;
import model.card.CardDeck;
import model.participant.*;
import view.InputView;
import view.OutputView;

import java.util.*;
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

        for (Player player : players.getPlayers()) {
            receiveAdditionalCard(player);
        }
        receiveAdditionalCard(dealer);

        OutputView.printAllParticipantScore(dealer, players);
        printBatingResult();
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
            distributeCardByParticipant(participant, INITIAL_DEAL_COUNT);
        }
    }

    public void distributeCardByParticipant(Participant participant, int amount) {
        List<Card> pickCards = deck.pickCard(amount);
        participant.addCards(pickCards);
    }

    private void receiveAdditionalCard(Player player) {
        while (player.satisfiedCondition()) {
            distributeCardByParticipant(player, 1);
            OutputView.printCurrentHands(player);
        }
    }

    private void receiveAdditionalCard(Dealer dealer) {
        while (dealer.satisfiedCondition()) {
            distributeCardByParticipant(dealer, 1);
            OutputView.printStandingDealer(dealer);
        }
    }

    private static boolean agreeIntent(String nickname) {
        return Intent.from(InputView.readIntent(nickname)).equals(Intent.Y);
    }

    public void printBatingResult() {
        HashMap<Player, Integer> playersBatingResult = players.getMatchResult(dealer);
        OutputView.printBatingResult(playersBatingResult);
    }
}
