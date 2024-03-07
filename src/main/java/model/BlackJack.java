package model;

import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import model.card.Cards;
import model.player.Dealer;
import model.player.Participant;
import model.player.Player;
import model.player.Participants;

public class BlackJack {

    private final Participants participants;
    private final Dealer dealer;

    public BlackJack(Participants participants, Dealer dealer) {
        this.participants = participants;
        this.dealer = dealer;
    }

    public void offerCardToPlayers(int cardCount) {
        participants.offerCardToPlayers(cardCount);
        offerCardToDealer(cardCount);
    }

    public void offerCardToPlayer(String name, int cardCount) {
        participants.offerCardToPlayer(name, cardCount);
    }

    public void offerCardToDealer(int cardCount) {
        dealer.addCards(Cards.selectRandomCards(cardCount));
    }

    public Map<Participant, GameResult> findResult() {
        List<Participant> sumPlayers = participants.getParticipants();
        return sumPlayers.stream()
                .collect(toMap(
                        participant -> participant,
                        participant -> findGameResult(participant, dealer)
                ));
    }

    private GameResult findGameResult(Player participant, Player dealer) {
        if (participant.isOverMaximumSum() && dealer.isOverMaximumSum()) {
            return GameResult.DRAW;
        }
        if (participant.isOverMaximumSum()) {
            return GameResult.LOSE;
        }
        if (dealer.isOverMaximumSum()) {
            return GameResult.WIN;
        }
        return findResultByMinimumDifference(participant.findPlayerDifference(), dealer.findPlayerDifference());
    }

    private GameResult findResultByMinimumDifference(int participantDifference, int dealerDifference) {
        if (participantDifference > dealerDifference) {
            return GameResult.LOSE;
        }
        if (participantDifference < dealerDifference) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    public List<Participant> getParticipants() {
        return participants.getParticipants();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void giveCard() {
        offerCardToPlayers(1);
    }

    public boolean isDealerUnderThreshold() {
        Dealer dealer = getDealer();
        return dealer.receiveCard();
    }
}
