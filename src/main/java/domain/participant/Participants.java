package domain.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;
import util.Constants;

public class Participants {

    private final Players players;
    private final Dealer dealer;

    public Participants(final Players players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public List<String> findCardNamesByParticipantName(String participantName) {
        List<String> cardNames = new ArrayList<>();
        if (participantName.equals(Constants.DEALER_NAME)) {
            cardNames = generateParticipantHandCardsName(dealer);
        }
        if (!participantName.equals(Constants.DEALER_NAME)) {
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

    public Participant findParticipantByParticipantName(String participantName) {
        if (participantName.equals(Constants.DEALER_NAME)) {
            return dealer;
        }
        return players.findPlayerByPlayerName(participantName);
    }

    public boolean canDealerDrawCard() {
        return dealer.canHit();
    }

    public List<String> getPlayerNames() {
        return players.getPlayerNames();
    }

    public List<Player> getRawPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }
}
