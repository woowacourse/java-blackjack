package domain;

import domain.betting.BetMoney;
import domain.betting.BetMonies;
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

    public List<Card> getPlayerFirstShownCards(String playerName) {
        return participants.getPlayerFirstShownCard(playerName);
    }

    public List<Card> getDealerFirstShownCards() {
        return participants.getDealerFirstShownCard();
    }

    public boolean hasPlayerReceivedCard(String playerName) {
        return participants.hasPlayerReceivedExtraCard(playerName);
    }

    public boolean canDealerPick() {
        return participants.getDealer()
                .canPick();
    }

    public void giveCardToDealer() {
        Participant dealer = participants.getDealer();
        dealer.addCard(cardDeck.getAndRemoveFrontCard());
    }

    public Revenues calculateRevenue(BetMonies betMonies) {
        ParticipantsResult participantsResult = BlackJackResultCalculator.calculate(participants);
        List<Revenue> totalRevenues = new ArrayList<>();
        for (PlayerWinningStatus playerWinningStatus : participantsResult.playerResults()
                .getPlayerResult()) {
            BetMoney betMoney = betMonies.findByPlayerName(playerWinningStatus.playerName());
            double rate = BlackjackPlayerRevenueRate.getRate(playerWinningStatus.status());
            int resultMoney = (int) (betMoney.getMoney() * rate);
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

    public List<Card> getPlayerCards(String playerName) {
        return participants.getPlayerCards(playerName);
    }
}
