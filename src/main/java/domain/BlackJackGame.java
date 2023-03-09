package domain;

import java.util.List;
import java.util.Map;

import domain.card.Deck;
import domain.participant.*;
import domain.result.ResultCalculator;

public class BlackJackGame {

    private static final String ADDITIONAL_DRAW_CARD_OK_SIGN = "y";

    private final Deck deck;
    private final Participants participants;

    public BlackJackGame(List<String> playerNames) {
        this.deck = InitGameSetter.generateDeck();
        Players players = InitGameSetter.generatePlayers(deck, playerNames);
        Dealer dealer = InitGameSetter.generateDealer(deck);
        this.participants = new Participants(players, dealer);
    }

    public List<String> getPlayerNames() {
        return participants.getPlayerNames();
    }

    public List<String> findCardNamesByParticipantName(String participantName) {
        return participants.findCardNamesByParticipantName(participantName);
    }

    public Players getPlayers() {
        return participants.getPlayers();
    }

    public boolean canPlayerDrawCard(String playerName) {
        Player findPlayer = participants.findPlayerByPlayerName(playerName);
        return findPlayer.checkCardsCondition();
    }

    public AdditionalDrawStatus distributePlayerCardOrPass(String playerName, String receiveOrNot) {
        AdditionalDrawStatus additionalDrawStatus = AdditionalDrawStatus.PASS;
        Player findPlayer = participants.findPlayerByPlayerName(playerName);
        if (receiveOrNot.equals(ADDITIONAL_DRAW_CARD_OK_SIGN)) {
            findPlayer.takeCard(deck.drawCard());
            additionalDrawStatus = AdditionalDrawStatus.DRAW;
        }
        return additionalDrawStatus;
    }

    public void distributeDealerCard() {
        Dealer dealer = participants.getDealer();
        dealer.takeCard(deck.drawCard());
    }

    public boolean canDealerDrawCard() {
        return participants.canDealerDrawCard();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public int getDealerCardValueSum() {
        return participants.getDealerCardValueSum();
    }

    public int findPlayerCardValueSumByPlayerName(String playerName) {
        Player findPlayer = participants.findPlayerByPlayerName(playerName);
        return findPlayer.getOptimalCardValueSum();
    }

    public List<Player> getRawPlayers() {
        return participants.getRawPlayers();
    }
}
