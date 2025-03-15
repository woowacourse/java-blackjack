package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Payout;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayerName;
import blackjack.domain.participant.Players;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackjackGame {

    private final CardDeck cardDeck;
    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(final CardDeck cardDeck, Dealer dealer, final Players players) {
        this.cardDeck = cardDeck;
        this.dealer = dealer;
        this.players = players;
    }

    public static BlackjackGame createByPlayerNames(final List<String> names) {
        CardDeck cardDeck = CardDeck.createCardDeck();
        List<Player> players = new ArrayList<>();

        for (String name : names) {
            PlayerName playerName = new PlayerName(name);
            Player player = new Player(playerName);
            players.add(player);
        }
        return new BlackjackGame(cardDeck, new Dealer(), new Players(players));
    }

    public void initCardsToParticipants() {
        dealer.addInitialCards(cardDeck);
        players.addTwoCards(cardDeck);
    }

    public void addExtraCard(final Participant participant) {
        Card card = cardDeck.pickRandomCard();
        participant.addCards(card);
    }

    public boolean addExtraCardToDealer() {
        if (dealer.isPossibleToAdd()) {
            addExtraCard(dealer);
            return true;
        }
        return false;
    }

    public Payout calculateDealerPayout() {
        return players.calculateTotalPayout(dealer).negate();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players.getPlayers());
    }
}
