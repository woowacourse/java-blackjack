package domain;

import except.BlackJackException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BlackjackGame {

    private static final int MIN_PEOPLE_WITHOUT_DEALER = 1;
    private static final int MAX_PEOPLE_WITHOUT_DEALER = 7;
    private static final String DUPLIACTE_NAME = "중복된 이름이 존재합니다.";
    private static final String INVALID_BLACKJACK_PLAYER_SIZE = "블랙잭은 1-7명만 이용하실 수 있습니다";

    private final BlackjackParticipants<Player> blackjackParticipants;
    private final BlackjackDeck deck;

    public BlackjackGame(List<String> names, BlackjackDeck deck, Dealer dealer) {
        validateDuplicateName(names);
        int playerSize = names.size();
        if (playerSize < MIN_PEOPLE_WITHOUT_DEALER || playerSize > MAX_PEOPLE_WITHOUT_DEALER) {
            throw new BlackJackException(INVALID_BLACKJACK_PLAYER_SIZE);
        }
        List<Player> players = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        this.deck = deck;
        this.blackjackParticipants = new BlackjackParticipants<>(players, dealer);
        initiateGame();
    }

    private void validateDuplicateName(List<String> names) {
        Set<String> duplicateNames = new HashSet<>(names);
        if (duplicateNames.size() != names.size()) {
            throw new BlackJackException(DUPLIACTE_NAME);
        }
    }

    private void initiateGame() {
        List<String> playerNames = blackjackParticipants.getPlayerNames();
        for (String name : playerNames) {
            drawCard(name);
            drawCard(name);
        }
        drawDealerCard();
        drawDealerCard();
    }

    public List<BlackjackResult> currentPlayerBlackjackResult() {
        List<BlackjackResult> blackjackResults = new ArrayList<>();
        for (String name : blackjackParticipants.getPlayerNames()) {
            List<TrumpCard> trumpCards = blackjackParticipants.playerCards(name);
            int sum = blackjackParticipants.calculateCardSum(name);
            blackjackResults.add(new BlackjackResult(name, trumpCards, sum));
        }
        return Collections.unmodifiableList(blackjackResults);
    }

    public BlackjackResult currentDealerBlackjackResult() {
        List<TrumpCard> trumpCards = blackjackParticipants.dealerCards();
        int sum = blackjackParticipants.calculateDealerSum();
        String name = blackjackParticipants.dealerName();
        return new BlackjackResult(name, trumpCards, sum);
    }

    public List<TrumpCard> playerCards(String name) {
        return Collections.unmodifiableList(blackjackParticipants.playerCards(name));
    }

    public TrumpCard dealerCardFirst() {
        return blackjackParticipants.firstDealerCards();
    }

    public String dealerName() {
        return blackjackParticipants.dealerName();
    }

    public List<String> playerNames() {
        return blackjackParticipants.getPlayerNames();
    }

    public void drawCard(String name) {
        blackjackParticipants.addCard(name, deck.drawCard());
    }

    private void drawDealerCard() {
        blackjackParticipants.addDealerCard(deck.drawCard());
    }

    public boolean isBust(String name) {
        return blackjackParticipants.isBust(name);
    }

    public void dealerHit() {
        if (blackjackParticipants.dealerDrawable()) {
            blackjackParticipants.addDealerCard(deck.drawCard());
        }
    }

    public boolean dealerDrawable() {
        return blackjackParticipants.dealerDrawable();
    }
}
