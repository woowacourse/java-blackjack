package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.Dealer;
import blackjack.domain.entry.Participant;
import blackjack.domain.entry.Player;
import blackjack.domain.entry.Participants;
import blackjack.domain.vo.BettingMoney;
import blackjack.dto.request.PlayerRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackTable {
    private final Deck deck;
    private final Participants participants;

    public BlackjackTable(List<PlayerRequest> players) {
        this.deck = Deck.of(Card.createDeck());
        this.participants = new Participants(createDealer(), toPlayers(players));
    }

    public void hit(Participant participant) {
        participant.addCard(deck.draw());
    }

    public boolean needMoreCardByDealer() {
        return participants.isHitDealer();
    }

    public void hitDealer() {
        participants.hitDealer(deck.draw());
    }

    public boolean canHit(Participant participant) {
        return participant.canHit();
    }

    public List<Participant> getParticipants() {
        return participants.getParticipants();
    }

    public Map<Player, Double> countGameResult() {
        return participants.getGameResults();
    }

    private Dealer createDealer() {
        return new Dealer(HoldCards.initTwoCards(deck.draw(), deck.draw()));
    }

    private List<Player> toPlayers(List<PlayerRequest> playersRequests) {
        return playersRequests.stream()
            .map(this::toPlayer)
            .collect(Collectors.toList());
    }

    private Player toPlayer(PlayerRequest playerRequest) {
        return new Player(
            playerRequest.getName(),
            new BettingMoney(playerRequest.getBettingMoney()),
            HoldCards.initTwoCards(deck.draw(), deck.draw())
        );
    }
}
