package domain;

import domain.card.CardDeck;
import domain.money.Bets;
import domain.money.Money;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import domain.result.WinningResult;
import java.util.List;
import java.util.Map;

public final class BlackJackGame {

    private final Participants participants;
    private final CardDeck cardDeck;
    private final Bets bets;

    public BlackJackGame(final Participants participants, final CardDeck cardDeck) {
        this.participants = participants;
        this.cardDeck = cardDeck;
        this.bets = new Bets();
    }

    public void distributeInitialCards() {
        participants.distributeInitialCards(cardDeck);
    }

    public void giveCardTo(Participant participant) {
        participant.receiveCard(cardDeck.getCard());
    }

    public WinningResult makeResult() {
        return new WinningResult(participants);
    }

    public void addBet(final Player player, final Money money) {
        this.bets.addBet(player, money);
    }

    public Map<Player, Money> playersProfit() {
        return bets.calculatePlayersProfit(makeResult());
    }

    public Money dealerProfit() {
        return bets.calculateDealerProfit(makeResult());
    }

    public Participants getParticipants() {
        return participants;
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }
}
