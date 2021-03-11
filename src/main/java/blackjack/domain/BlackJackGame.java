package blackjack.domain;

import blackjack.domain.card.CardDeck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.DealerResult;
import blackjack.domain.user.Participants;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import java.util.List;

public class BlackJackGame {

    private final CardDeck entireCardDeck;
    private final Participants participants;

    public BlackJackGame(String requestPlayers) {
        entireCardDeck = new CardDeck();
        participants = new Participants(entireCardDeck, requestPlayers);
    }

    public void dealerTurn() {
        participants.dealerTurn(entireCardDeck);
    }

    public boolean isAvailableDealerTurn() {
        return this.participants.isAvailableDealerTurn();
    }

    public DealerResult getDealerResult() {
        Dealer dealer = participants.getDealer();
        List<Player> rawPlayers = participants.getPlayers()
            .getRawPlayers();
        return new DealerResult(dealer, rawPlayers);
    }

    public CardDeck getCardDeck() {
        return this.entireCardDeck;
    }

    public Dealer getDealer() {
        return this.participants.getDealer();
    }

    public Players getPlayers() {
        return this.participants.getPlayers();
    }

}
