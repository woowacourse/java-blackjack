package domain;

import static java.util.stream.Collectors.toList;

import domain.card.Card;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;

public class BlackJackGame {

    private static final int NUMBER_OF_SPLIT_CARDS = 2;

    private final Players players;
    private final Dealer dealer;
    private final CardDeck cardDeck;

    public BlackJackGame(Players players, Dealer dealer, CardDeck cardDeck) {
        this.players = players;
        this.dealer = dealer;
        this.cardDeck = cardDeck;
    }

    public void splitCards() {
        splitEachParticipant(dealer);
        players.stream()
                .forEach(this::splitEachParticipant);
    }

    private void splitEachParticipant(final Participant participant) {
        for (int i = 0; i < NUMBER_OF_SPLIT_CARDS; i++) {
            participant.drawCard(cardDeck.draw());
        }
    }

    public List<Card> getOpenCardsByName(String name) {
        Player player = players.findPlayerByName(name);
        return player.openDrawnCards();
    }

    public List<Card> getDealerOpenCard() {
        return dealer.openDrawnCards();
    }

    public void drawPlayerCardByName(final String playerName) {
        Player player = players.findPlayerByName(playerName);
        player.drawCard(cardDeck.draw());
    }

    public boolean canPlayerDrawMore(final String playerName) {
        Player player = players.findPlayerByName(playerName);
        return player.isDrawable();
    }

    public void drawDealerCard() {
        dealer.drawCard(cardDeck.draw());
    }

    public boolean canDealerDrawMore() {
        return dealer.isDrawable();
    }

    public int calculateBettingResult(String name) {
        Player player = players.findPlayerByName(name);
        return player.calculatePrize(dealer);
    }

    public List<String> getPlayersName() {
        return players.stream()
                .map(Participant::getName)
                .collect(toList());
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public int getDealerDrawLimitScore() {
        return dealer.getDrawLimitScore();
    }

    public List<Card> getDrawnCardByNames(String name) {
        Player player = players.findPlayerByName(name);
        return player.getDrawnCards();
    }

    public List<Card> getDealerCards() {
        return dealer.getDrawnCards();
    }

    public int getDealerScore() {
        return dealer.calculateScore();
    }

    public int getScoreByName(String name) {
        Player player = players.findPlayerByName(name);
        return player.calculateScore();
    }
}
