package domain;

import except.BlackJackException;
import java.util.Collections;
import java.util.List;

public class BlackjackGame {

    private final int MIN_PEOPLE_WITHOUT_DEALER = 1;
    private final int MAX_PEOPLE_WITHOUT_DEALER = 7;
    private final String INVALID_BLACKJACK_PLAYER_SIZE = "블랙잭은 1-7명만 이용하실 수 있습니다.";

    private final BlackjackParticipants blackjackParticipants;
    private final BlackjackDeck deck;

    public BlackjackGame(List<String> names, BlackjackDeck deck, Dealer dealer) {
        int playerSize = names.size();
        if (playerSize < MIN_PEOPLE_WITHOUT_DEALER || playerSize > MAX_PEOPLE_WITHOUT_DEALER) {
            throw new BlackJackException(INVALID_BLACKJACK_PLAYER_SIZE);
        }
        List<Player> players = names.stream()
                .map(Player::new)
                .toList();
        this.deck = deck;
        this.blackjackParticipants = new BlackjackParticipants(players, dealer);
        initiateGame();
    }

    private void initiateGame() {
        for (String name : blackjackParticipants.getPlayerNames()) {
            drawCard(name);
            drawCard(name);
        }
        drawDealerCard();
        drawDealerCard();
    }

    public List<TrumpCard> playerCards(String name) {
        return blackjackParticipants.playerCards(name);
    }

    public TrumpCard dealerCardFirst() {
        return dealerCards().getFirst();
    }

    public List<TrumpCard> dealerCards() {
        return Collections.unmodifiableList(blackjackParticipants.dealerCards());
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

    public List<BlackjackResult> currentPlayerBlackjackResult() {
        return blackjackParticipants.calculatePlayerResults();
    }

    public BlackjackResult currentDealerBlackjackResult() {
        return blackjackParticipants.calculateDealerResult();
    }
}
