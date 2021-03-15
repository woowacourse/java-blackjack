package blackjack.domain;

import blackjack.domain.card.CardDeck;
import blackjack.domain.money.Money;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.GameResult;
import blackjack.domain.user.Participants;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import java.util.List;

public class BlackJackGame {

    private final CardDeck entireCardDeck;
    private final Participants participants;

    public BlackJackGame(List<String> requestPlayers, List<Money> betCapital) {
        entireCardDeck = new CardDeck();
        participants = new Participants(entireCardDeck, requestPlayers, betCapital);
    }

    public void dealerTurn() {
        participants.dealerTurn(entireCardDeck);
    }

    public boolean isAvailableDealerTurn() {
        return this.participants.isAvailableDealerTurn();
    }

    public void drawOnePlayer(int playerIndex) {
        participants.drawOnePlayer(entireCardDeck, playerIndex);
    }

    public GameResult getDealerResult() {
        Dealer dealer = participants.getDealer();
        List<Player> rawPlayers = participants.getPlayers()
            .getRawPlayers();
        return new GameResult(dealer, rawPlayers);
    }

    public int playersSize() {
        return this.getPlayers().size();
    }

    public Dealer getDealer() {
        return this.participants.getDealer();
    }

    public Players getPlayers() {
        return this.participants.getPlayers();
    }

    public Player getPlayer(int playerIndex) {
        return getPlayers().getPlayer(playerIndex);
    }
}
