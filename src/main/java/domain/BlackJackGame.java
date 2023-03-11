package domain;

import java.util.List;

import domain.betting.BettingMoney;
import domain.card.Deck;
import domain.participant.*;

public class BlackJackGame {

    private static final String ADDITIONAL_DRAW_CARD_OK_SIGN = "y";

    private final Deck deck;
    private final Participants participants;

    public BlackJackGame(List<String> playerNames) {
        this.deck = new Deck();
        Players players = InitGameSetter.generatePlayers(deck, playerNames);
        Dealer dealer = InitGameSetter.generateDealer(deck);
        this.participants = new Participants(players, dealer);
    }

    public List<String> getPlayerNames() {
        return participants.getPlayerNames();
    }

    public Player findPlayerByPlayerName(String playerName) {
        return participants.findPlayerByPlayerName(playerName);
    }

    public List<String> findCardNamesByParticipantName(String participantName) {
        return participants.findCardNamesByParticipantName(participantName);
    }

    public boolean canPlayerDrawCard(String playerName) {
        Player findPlayer = participants.findPlayerByPlayerName(playerName);
        return findPlayer.canHit();
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
        Hand hand = findPlayer.getHand();
        return hand.calculateOptimalCardValueSum();
    }

    public List<Player> getRawPlayers() {
        return participants.getRawPlayers();
    }
}
