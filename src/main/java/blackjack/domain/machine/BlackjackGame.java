package blackjack.domain.machine;

import blackjack.domain.card.CardShuffleMachine;
import blackjack.domain.card.Cards;
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

    public void addCard(Player player) {
        player.addCard(cards.assignCard());
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

    private void scorePlayers(Player dealer, Player guest, Results results) {
        int playerPoint = guest.getDeck().sumPoints();
        int dealerPoint = dealer.getDeck().sumPoints();

        Match result = Match.findWinner(playerPoint, dealerPoint);
        Match dealerResult = result.getDealerResult();
        results.addResult(dealer, dealerResult);
        results.addResult(guest, result);
    }

    public List<String> getPlayerNames() {
        return blackjackPlayers.getNames();
    }

    public List<Player> getPlayers() {
        return blackjackPlayers.getPlayers();
    }

    public List<Player> getGuests() {
        return blackjackPlayers.getGuests();
    }

    public Player getDealer() {
        return blackjackPlayers.getDealer();
    }
}
