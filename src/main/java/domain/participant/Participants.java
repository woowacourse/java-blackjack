package domain.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;

public class Participants {

    private static final String DEALER_NAME = "딜러";
    private static final String NOT_MATCH_DEALER_NAME = "[ERROR] 딜러 이름이 일치하지 않습니다.";

    private final Players players;
    private final Dealer dealer;

    public Participants(final Players players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public List<String> findCardNamesByParticipantName(String participantName) {
        List<String> cardNames = new ArrayList<>();
        if (participantName.equals(DEALER_NAME)) {
            cardNames = generateParticipantHandCardsName(dealer);
        }
        if (!participantName.equals(DEALER_NAME)) {
            Player findPlayer = players.findPlayerByPlayerName(participantName);
            cardNames = generateParticipantHandCardsName(findPlayer);
        }
        return cardNames;
    }

    private List<String> generateParticipantHandCardsName(Participant participant) {
        List<Card> handCards = participant.getHandCards();
        return handCards.stream()
                .map(card -> convertCardNameForPrint(card.getAlias(), card.getShape()))
                .collect(Collectors.toUnmodifiableList());
    }

    private String convertCardNameForPrint(String alias, String shape) {
        return alias + shape;
    }

    public Player findPlayerByPlayerName(String playerName) {
        return players.findPlayerByPlayerName(playerName);
    }

    public boolean canDealerDrawCard() {
        return dealer.checkCardsCondition();
    }

    public Dealer findDealerByDealerName(String dealerName) {
        if (dealerName.equals(DEALER_NAME)) {
            return dealer;
        }
        throw new IllegalArgumentException(NOT_MATCH_DEALER_NAME);
    }

    public List<String> getPlayerNames() {
        return players.getPlayerNames();
    }

    public int getDealerCardValueSum() {
        return dealer.getCardValueSum();
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
