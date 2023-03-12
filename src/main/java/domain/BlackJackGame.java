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

    public boolean isDealerBlackjack() {
        Dealer dealer = participants.getDealer();
        return dealer.isBlackjack();
    }

    public List<String> getPlayerNames() {
        return participants.getPlayerNames();
    }

    public Player findPlayerByPlayerName(String playerName) {
        return participants.findPlayerByPlayerName(playerName);
    }

    public ParticipantResultDto generateParticipantResultByParticipantName(String participantName) {
        List<String> findCardNames = participants.findCardNamesByParticipantName(participantName);
        return new ParticipantResultDto(participantName, findCardNames);
    }

    public ParticipantFinalResultDto generateParticipantFinalResultByParticipantName(String participantName) {
        ParticipantResultDto participantResultDto = generateParticipantResultByParticipantName(participantName);

        Participant findParticipant = participants.findParticipantByParticipantName(participantName);
        int totalValueSum = findParticipant.calculateOptimalCardValueSum();

        return new ParticipantFinalResultDto(participantResultDto, totalValueSum);
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

    public List<Player> getRawPlayers() {
        return participants.getRawPlayers();
    }
}
