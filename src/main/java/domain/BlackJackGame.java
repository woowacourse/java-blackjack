package domain;

import java.util.List;
import java.util.Map;

import domain.card.Deck;
import domain.participant.*;
import domain.result.ResultCalculator;

public class BlackJackGame {

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

    public boolean canPlayerDrawCard(Player player) {
        return player.checkCardsCondition();
    }

    public AdditionalDrawStatus distributePlayerCardOrPass(Player player, String receiveOrNot) {
        AdditionalDrawStatus additionalDrawStatus = AdditionalDrawStatus.PASS;
        if (receiveOrNot.equals("y")) {
            player.takeCard(deck.drawCard());
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

    public int getDealerCardValueSum() {
        return participants.getDealerCardValueSum();
    }

    public int findPlayerCardValueSumByPlayerName(String playerName) {
        Player findPlayer = participants.findPlayerByPlayerName(playerName);
        return findPlayer.getOptimalCardValueSum();
    }

    public Map<String, String> calculateResult() {
        Players players = participants.getPlayers();
        Dealer dealer = participants.getDealer();
        ResultCalculator resultCalculator = new ResultCalculator(dealer, players);
        for (Player player : players.getPlayers()) {
            resultCalculator.calculate(player, dealer);
        }
        return resultCalculator.getFinalFightResults();
    }
}
