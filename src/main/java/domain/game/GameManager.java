package domain.game;

import domain.card.Card;
import domain.card.Deck;
import domain.participant.Participants;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public final class GameManager {

    private final Deck deck;
    private final Participants participants;

    public GameManager(final Deck deck, final Participants participants) {
        this.deck = deck;
        this.participants = participants;
    }

    public static GameManager create(final Deck deck, final Participants participants) {
        return new GameManager(deck, participants);
    }

    public void giveStartCardsForDealer() {
        participants.addCardForDealer(deck.draw());
        participants.addCardForDealer(deck.draw());
    }

    public void giveStartCardsForPlayer(final String targetPlayerName) {
        participants.addCardForPlayer(targetPlayerName, deck.draw());
        participants.addCardForPlayer(targetPlayerName, deck.draw());
    }

    public boolean canDraw(final String targetPlayerName) {
        return participants.canDraw(targetPlayerName);
    }

    public void addCardForPlayer(final String targetPlayerName) {
        participants.addCardForPlayer(targetPlayerName, deck.draw());
    }

    public int playDealerTurn() {
        return participants.playDealerTurn(deck);
    }

    public List<String> playersName() {
        return participants.playersName();
    }

    public List<String> getParticipantsName() {
        return participants.getParticipantsName();
    }

    public String getDealerName() {
        return participants.getDealerName();
    }

    public Card getDealerStartCard() {
        return participants.getDealerStartCard();
    }

    public List<Card> getPlayerCard(final String targetPlayerName) {
        return participants.getPlayerCard(targetPlayerName);
    }

    public List<Card> getDealerCard() {
        return participants.getDealerCard();
    }

    public int getDealerScore() {
        return participants.getDealerScore();
    }

    public int getPlayerScore(final String targetPlayerName) {
        return participants.getPlayerScore(targetPlayerName);
    }

    public Map<String, BigDecimal> getTotalPlayerGameResult() {
        return participants.calculateProfit();
    }
}
