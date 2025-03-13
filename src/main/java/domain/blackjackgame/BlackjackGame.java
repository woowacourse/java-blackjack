package domain.blackjackgame;

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
    private static final String INVALID_PLAYER_BET = "유저의 이름과 베팅정보가 유효하지 않습니다.";

    private final BlackjackParticipantsManager blackjackParticipantsManager;
    private final BlackjackDeck deck;

    public static BlackjackGame nonBettingBlackjackGame(BlackjackDeck deck, Dealer dealer, List<String> names) {
        return new BlackjackGame(deck, dealer, names);
    }

    private BlackjackGame(BlackjackDeck deck, Dealer dealer, List<String> names) {
        validatePlayerSize(names.size());
        List<BlackjackParticipant> players = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        this.deck = deck;
        this.blackjackParticipantsManager = new BlackjackParticipantsManager(players, dealer);
        initiateGame();
    }

    private static void validatePlayerSize(int playerCount) {
        if (playerCount < MIN_PEOPLE_WITHOUT_DEALER || playerCount > MAX_PEOPLE_WITHOUT_DEALER) {
            throw new BlackJackException(INVALID_BLACKJACK_PLAYER_SIZE);
        }
    }

    private void initiateGame() {
        List<String> playerNames = blackjackParticipantsManager.getPlayerNames();
        for (String name : playerNames) {
            initiateDrawCardToPlayer(name);
        }
        initiateDealerDrawCard();
    }

    private void initiateDealerDrawCard() {
        List<TrumpCard> firstDrawCards = List.of(deck.drawCard(), deck.drawCard());
        blackjackParticipantsManager.addInitiateCardsToDealer(firstDrawCards);
    }

    private void initiateDrawCardToPlayer(String name) {
        List<TrumpCard> firstDrawCards = List.of(deck.drawCard(), deck.drawCard());
        blackjackParticipantsManager.addInitiateCardsToPlayer(name, firstDrawCards);
    }

    public List<BlackjackResult> currentPlayerBlackjackResult() {
        List<BlackjackResult> blackjackResults = new ArrayList<>();
        for (String name : blackjackParticipantsManager.getPlayerNames()) {
            List<TrumpCard> trumpCards = blackjackParticipantsManager.playerCards(name);
            int sum = blackjackParticipantsManager.calculateCardSum(name);
            blackjackResults.add(new BlackjackResult(name, trumpCards, sum));
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
        return Collections.unmodifiableList(blackjackParticipantsManager.playerCards(name));
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

    public List<TrumpCard> dealerCards() {
        return blackjackParticipantsManager.dealerCards();
    }

    public List<BlackjackParticipant> players() {
        return blackjackParticipantsManager.players();
    }
}

