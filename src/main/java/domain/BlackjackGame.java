package domain;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.Collections;
import java.util.List;

public class BlackjackGame {

    public static final int INITIAL_CARDS = 2;
    public static final int DEALER_MIN_SCORE = 16;
    public static final int BLACKJACK_SCORE = 21;

    private final CardDeck cardDeck;
    private final Dealer dealer;
    private final Players players;

    private BlackjackGame(CardDeck cardDeck, Dealer dealer, Players players) {
        this.cardDeck = cardDeck;
        this.dealer = dealer;
        this.players = players;
    }

    public static BlackjackGame of(CardDeck cardDeck, Dealer dealer, Players players) {
        return new BlackjackGame(cardDeck, dealer, players);
    }

    public void distributeCards() {
        for (int count = 0; count < INITIAL_CARDS; count++) {
            players.receiveCards(cardDeck);
            dealer.receive(cardDeck.popCard());
        }
    }

    public void passCardToPlayer(String name) {
        players.passCardByName(name, cardDeck.popCard());
    }

    public boolean passCardToDealer() {
        if (dealer.canReceive()) {
            dealer.receive(cardDeck.popCard());
            return true; // TODO: View 때문에 생긴 반환값이 아닌가에 대한 고민
        }
        return false;
    }

    public boolean checkPlayerCanReceive(String name) {
        return players.canPlayerReceive(name);
    }

    // TODO: 모델에서 이름을 빼는 것이 맞는 것일까? Controller에 이미 정보가 있지 않은가? 하지만 모델에서 정보를 빼내는 것이 더 정확하긴 하다. 고민
    public List<String> getPlayersName() {
        return Collections.unmodifiableList(players.getPlayersName());
    }

    public Player getPlayerByName(String name) {
        return players.findByName(name);
    }
}
