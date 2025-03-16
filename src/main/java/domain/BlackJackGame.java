package domain;

import domain.card.Card;
import java.util.Collections;
import java.util.List;

public class BlackJackGame {
    private final Participants participants;
    private final CardDeck cardDeck;

    public BlackJackGame(Participants participants, CardDeck cardDeck) {
        this.participants = participants;
        this.cardDeck = cardDeck;
    }

    public void giveStartingCardsToParticipants() {
        for (Participant participant : participants.getParticipants()) {
            participant.addCard(cardDeck.getAndRemoveFrontCard());
            participant.addCard(cardDeck.getAndRemoveFrontCard());
        }
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants.getParticipants());
    }

    public List<String> getPlayerNames() {
        return participants.getPlayerNames();
    }

    public boolean canPlayerPick(String playerName) {
        return participants.findByName(playerName)
                .canPick();
    }

    public void giveCardToPlayer(String playerName) {
        Participant player = participants.findByName(playerName);
        player.addCard(cardDeck.getAndRemoveFrontCard());
    }

    public List<Card> getPlayerShownCards(String playerName) {
        return participants.findByName(playerName)
                .getShownCard();
    }

    public boolean canDealerPick() {
        return participants.getDealer()
                .canPick();
    }

    public void giveCardToDealer() {
        Participant dealer = participants.getDealer();
        dealer.addCard(cardDeck.getAndRemoveFrontCard());
    }
}
