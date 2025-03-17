package domain;

import domain.betting.BatMoney;
import domain.betting.BatMonies;
import domain.betting.Revenue;
import domain.betting.Revenues;
import domain.card.Card;
import domain.card.CardDeck;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.result.PlayerWinningStatus;
import java.util.ArrayList;
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

    public boolean hasPlayerReceivedCard(String playerName) {
        return participants.findByName(playerName).getCards().size() > 2;
    }

    public boolean canDealerPick() {
        return participants.getDealer()
                .canPick();
    }

    public void giveCardToDealer() {
        Participant dealer = participants.getDealer();
        dealer.addCard(cardDeck.getAndRemoveFrontCard());
    }

    public Revenues calculateRevenue(BatMonies batMonies) {
        ParticipantsResult participantsResult = BlackJackResultCalculator.calculate(participants);
        List<Revenue> totalRevenues = new ArrayList<>();
        for (PlayerWinningStatus playerWinningStatus : participantsResult.playerResults().getPlayerResult()) {
            BatMoney batMoney = batMonies.findByPlayerName(playerWinningStatus.playerName());
            double rate = BlackjackPlayerRevenueRate.getRate(playerWinningStatus.status());
            int resultMoney = (int) (batMoney.getMoney() * rate);
            totalRevenues.add(new Revenue(playerWinningStatus.playerName(), resultMoney));
        }
        return new Revenues(totalRevenues);
    }

    public Participant getDealer() {
        return participants.getDealer();
    }

    public Participant findByName(String playerName) {
        return participants.findByName(playerName);
    }
}
