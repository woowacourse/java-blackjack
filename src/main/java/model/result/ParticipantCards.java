package model.result;

import java.util.List;
import model.participant.Dealer;
import model.participant.Players;

public class ParticipantCards {

    private final ParticipantCard dealerCard;
    private final List<ParticipantCard> playerCards;

    private ParticipantCards(ParticipantCard dealerCard, List<ParticipantCard> playerCards) {
        this.dealerCard = dealerCard;
        this.playerCards = playerCards;
    }

    public static ParticipantCards createWithInitialCards(Dealer dealer, Players players) {
        ParticipantCard dealerCard = ParticipantCard.createWithFirstCard(dealer);
        List<ParticipantCard> playerCards = players.getPlayers()
            .stream()
            .map(ParticipantCard::createWithAllCard)
            .toList();
        return new ParticipantCards(dealerCard, playerCards);
    }

    public String dealerName() {
        return dealerCard.getName();
    }

    public List<String> playerNames() {
        return playerCards.stream()
            .map(ParticipantCard::getName)
            .toList();
    }

    public ParticipantCard getDealerCard() {
        return dealerCard;
    }

    public List<ParticipantCard> getPlayerCards() {
        return playerCards;
    }
}
