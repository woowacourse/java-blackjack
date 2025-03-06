package domain;

import domain.card.Card;
import domain.card.CardGenerator;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;

import java.util.List;

public class GameManager {

    private final Dealer dealer;
    private final List<Player> players;

    public GameManager(final Dealer dealer, final List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void receiveCardToDealer(){
        while(dealer.isLessThen(16)){
            dealer.receiveCard();
        }
    }

    public static GameManager create(Dealer dealer, List<Player> players) {
        return new GameManager(dealer, players);
    }

    private static List<Card> generateCards(final CardGenerator cardGenerator) {
        return List.of(cardGenerator.generate(), cardGenerator.generate());
    }


    public boolean isAbleToHit(final Gamer gamer) {
        return !gamer.isBust();
    }

    public List<Player> getPlayers() {
        return players.stream().map(Player::new).toList();
    }

    public void giveCardToPlayer(final Player player) {
        player.receiveCard();
    }

    public GameResult calculateResult(final int index) {
        return null;
    }
}
