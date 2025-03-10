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

    private final BlackjackParticipantsManager<Player, Dealer> blackjackParticipantsManager;
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
        this.blackjackParticipantsManager = new BlackjackParticipantsManager<>(players, dealer);
        initiateGame();
    }

    private void validateDuplicateName(List<String> names) {
        Set<String> duplicateNames = new HashSet<>(names);
        if (duplicateNames.size() != names.size()) {
            throw new BlackJackException(DUPLIACTE_NAME);
        }
    }

    private void initiateGame() {
        List<String> playerNames = blackjackParticipantsManager.getPlayerNames();
        for (String name : playerNames) {
            drawCard(name);
            drawCard(name);
        }
        drawDealerCard();
        drawDealerCard();
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

    private void drawDealerCard() {
        blackjackParticipantsManager.addDealerCard(deck.drawCard());
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
}
