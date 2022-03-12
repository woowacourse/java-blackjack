package blackjack.domain.machine;

import blackjack.domain.card.CardShuffleMachine;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.List;
import java.util.NoSuchElementException;

public class BlackjackGame {

    private static final String CANNOT_FIND_DEALER_MESSAGE = "딜러를 찾을 수 없습니다.";
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

    public Results calculateResult(List<Player> players) {
        Results results = new Results();
        Player dealer = players.stream()
                .filter(Player::isDealer)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(CANNOT_FIND_DEALER_MESSAGE));
        for (Player player : players) {
            scoreResultIfGuest(dealer, player, results);
        }
        return results;
    }

    private void scoreResultIfGuest(Player dealer, Player player, Results results) {
        if (player.isDealer()) {
            return;
        }
        scorePlayers(dealer, player, results);
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
