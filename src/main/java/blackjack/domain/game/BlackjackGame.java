package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Participant;
import blackjack.domain.user.Participants;
import blackjack.domain.user.Player;
import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {

    private final CardDeck cardDeck;
    private final Participants participants;
    private final BettingTable bettingTable;

    public BlackjackGame(Participants participants, CardDeck cardDeck, BettingTable bettingTable) {
        this.participants = participants;
        this.cardDeck = cardDeck;
        this.bettingTable = bettingTable;
    }

    public BlackjackGame(Participants participants) {
        this(participants, new CardDeck(), new BettingTable());
    }

    public void dealOutCard() {
        dealOutPlayerCard();
        dealOutDealerCard();
    }

    private void dealOutDealerCard() {
        Dealer dealer = participants.getDealer();
        dealer.addCards(pickTwice());
    }

    private void dealOutPlayerCard() {
        for (Player player : participants.getPlayers()) {
            player.addCards(pickTwice());
        }
    }

    private List<Card> pickTwice() {
        List<Card> pick = new ArrayList<>();
        pick.add(cardDeck.pick());
        pick.add(cardDeck.pick());
        return pick;
    }

    public void drawCard(Participant participant) {
        participant.addCard(cardDeck.pick());
    }

    public BlackjackResult getResult() {
        return BlackjackResult.of(participants.getPlayers(), participants.getDealer());
    }

    public Participants getParticipants() {
        return participants;
    }

    public void bet(Player player, BettingMoney money) {
        bettingTable.put(player, money);
    }
}
