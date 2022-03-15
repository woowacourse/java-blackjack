package blackjack.domain.machine;

import blackjack.domain.card.CardShuffleMachine;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Guest;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.List;

public class BlackjackGame {

    private final Cards cards;
    private final Players blackjackPlayers;

    public BlackjackGame(List<String> playerNames) {
        this.cards = new Cards(new CardShuffleMachine());
        this.blackjackPlayers = new Players(playerNames);
        blackjackPlayers.startWithTwoCards(cards);
    }


    public boolean hasNextGuest() {
        return blackjackPlayers.hasNextGuest();
    }

    public void turnGuest() {
        blackjackPlayers.turnNextGuest();
    }

    public GameResponse addCardToPlayer() {
        Player player = blackjackPlayers.getTurnPlayer();
        player.addCard(cards.assignCard());
        return new GameResponse(player.getName(), player.getDeck());
    }

    public boolean checkOverLimit() {
        Player player = blackjackPlayers.getTurnPlayer();
        return player.isOverLimit(Guest.LIMIT_POINT);
    }

    public boolean canGetMoreCardToDealer() {
        Player dealer = blackjackPlayers.getDealer();
        return !dealer.isOverLimit(Dealer.LIMIT_POINT);
    }

    public void addCardToDealer() {
        Player dealer = blackjackPlayers.getDealer();
        dealer.addCard(cards.assignCard());
    }

    public Results calculateResult() {
        Results results = new Results();
        Player dealer = blackjackPlayers.getDealer();
        List<Player> guests = blackjackPlayers.getGuests();
        for (Player player : guests) {
            scorePlayers(dealer, player, results);
        }
        return results;
    }

    public List<String> getPlayerNames() {
        return blackjackPlayers.getNames();
    }

    public List<Player> getPlayers() {
        return blackjackPlayers.getPlayers();
    }

    public Player getTurnPlayer() {
        return blackjackPlayers.getTurnPlayer();
    }

    private void scorePlayers(Player dealer, Player guest, Results results) {
        int playerPoint = guest.getDeck().sumPoints();
        int dealerPoint = dealer.getDeck().sumPoints();

        Match result = Match.findWinner(playerPoint, dealerPoint);
        Match dealerResult = result.getDealerResult();
        results.addResult(dealer, dealerResult);
        results.addResult(guest, result);
    }
}
