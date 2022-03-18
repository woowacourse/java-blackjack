package blackJack.domain;

import blackJack.domain.result.BlackJackGameResult;
import blackJack.domain.result.OutCome;
import blackJack.domain.result.YesOrNo;
import java.util.LinkedHashMap;
import java.util.List;

import blackJack.domain.card.Deck;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participant;
import blackJack.domain.participant.Participants;
import blackJack.domain.participant.Player;
import java.util.Map;

public class BlackJackGame {

    private final Deck deck;
    private final Participants participants;

    public BlackJackGame(Deck deck, Participants participants) {
        this.deck = deck;
        this.participants = participants;
    }

    public void defaultDistributeCards() {
        participants.distributeCards(deck);
    }

    public void distributeCard(Participant participant) {
        participant.receiveCard(deck.getCard());
    }

    public boolean isAvailableDistributeCard(Participant participant) {
        return participant.isAvailableHit();
    }

    public boolean isApproveDrawCard(YesOrNo value) {
        return value == YesOrNo.YES;
    }

    public BlackJackGameBoard calculateResult() {
        final BlackJackGameResult blackjackGameResult = new BlackJackGameResult();
        for (Player player : participants.getPlayers()) {
            blackjackGameResult.add(player, getDealer().calculateOutCome(player));
        }

        return blackjackGameResult.calculateEarning();
    }

    public Participants getParticipants() {
        return participants;
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }
}
