package domain.blackjackgame;

import domain.participant.BlackjackBet;
import domain.participant.BlackjackHands;
import domain.participant.BlackjackParticipant;
import domain.participant.BlackjackParticipantsManager;
import domain.participant.Dealer;
import domain.participant.Player;
import exception.BlackJackException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGame {

    private static final int MIN_PEOPLE_WITHOUT_DEALER = 1;
    private static final int MAX_PEOPLE_WITHOUT_DEALER = 7;
    private static final String INVALID_BLACKJACK_PLAYER_SIZE = "블랙잭은 1-7명만 이용하실 수 있습니다";

    private final BlackjackParticipantsManager blackjackParticipantsManager;
    private final BlackjackDeck deck;

    public static BlackjackGame nonBettingBlackjackGame(BlackjackDeck deck, List<String> names) {
        return new BlackjackGame(names, deck);
    }

    public BlackjackGame(List<String> names, BlackjackDeck deck) {
        validatePlayerSize(names.size());
        this.deck = deck;
        List<BlackjackParticipant> players = names.stream()
                .map(name -> new Player(name, drawFirstCards(deck)))
                .collect(Collectors.toList());
        BlackjackParticipant dealer = new Dealer(drawFirstCards(deck));
        this.blackjackParticipantsManager = new BlackjackParticipantsManager(players, dealer);
    }

    public BlackjackGame(List<String> names, BlackjackDeck deck, List<Integer> bets) {
        validatePlayerSize(names.size());
        this.deck = deck;
        List<BlackjackParticipant> players = names.stream()
                .map(name -> new Player(name, drawFirstCards(deck)))
                .collect(Collectors.toList());
        BlackjackParticipant dealer = new Dealer(drawFirstCards(deck));
        this.blackjackParticipantsManager = new BlackjackParticipantsManager(players, dealer);
    }

    private static List<TrumpCard> drawFirstCards(BlackjackDeck deck) {
        return List.of(deck.drawCard(), deck.drawCard());
    }

    private static void validatePlayerSize(int playerCount) {
        if (playerCount < MIN_PEOPLE_WITHOUT_DEALER || playerCount > MAX_PEOPLE_WITHOUT_DEALER) {
            throw new BlackJackException(INVALID_BLACKJACK_PLAYER_SIZE);
        }
    }

    public List<BlackjackResult> currentPlayerBlackjackResult() {
        List<BlackjackResult> blackjackResults = new ArrayList<>();
        for (String name : blackjackParticipantsManager.getPlayerNames()) {
            List<TrumpCard> holdingCards = blackjackParticipantsManager.playerCards(name);
            int sum = blackjackParticipantsManager.calculateCardSum(name);
            blackjackResults.add(new BlackjackResult(name, holdingCards, sum));
        }
        return Collections.unmodifiableList(blackjackResults);
    }

    public BlackjackResult currentDealerBlackjackResult() {
        List<TrumpCard> trumpCards = blackjackParticipantsManager.dealerCards();
        int sum = blackjackParticipantsManager.calculateDealerSum();
        String name = blackjackParticipantsManager.dealerName();
        return new BlackjackResult(name, trumpCards, sum);
    }

    public List<TrumpCard> playerCards(String name) {
        return blackjackParticipantsManager.playerCards(name);
    }

    public TrumpCard dealerCardFirst() {
        return blackjackParticipantsManager.firstDealerCards();
    }

    public String dealerName() {
        return blackjackParticipantsManager.dealerName();
    }

    public List<String> playerNames() {
        return blackjackParticipantsManager.getPlayerNames();
    }

    public void drawCard(String name) {
        blackjackParticipantsManager.addCard(name, deck.drawCard());
    }

    public boolean isBust(String name) {
        return blackjackParticipantsManager.isBust(name);
    }

    public void dealerHit() {
        if (blackjackParticipantsManager.dealerDrawable()) {
            blackjackParticipantsManager.addDealerCard(deck.drawCard());
        }
    }

    public boolean dealerDrawable() {
        return blackjackParticipantsManager.dealerDrawable();
    }

    public BlackjackHands dealerHands() {
        return blackjackParticipantsManager.dealerHands();
    }

    public BlackjackHands playerHands(String name) {
        return blackjackParticipantsManager.playerHands(name);
    }

    public BlackjackBet playerBet(String name) {
        return blackjackParticipantsManager.playerBet(name);
    }
}

