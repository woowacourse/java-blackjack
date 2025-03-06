package domain;

import domain.card.Card;
import domain.card.CardGenerator;
import domain.card.CardGroup;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;
import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private final Dealer dealer;
    private final List<Player> players;
    private final CardGenerator cardGenerator;

    public GameManager(final Dealer dealer, final List<Player> players, final CardGenerator cardGenerator) {
        this.dealer = dealer;
        this.players = players;
        this.cardGenerator = cardGenerator;
    }

    public void receiveCardToDealer(){
        while(dealer.isLessThen(16)){
            dealer.receiveCard(cardGenerator.generate());
        }
    }

    public static GameManager create(final CardGenerator cardGenerator, final List<String> playerNames) {
        final Dealer dealer = new Dealer(new CardGroup(generateCards(cardGenerator)));
        final List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            final Player player = new Player(playerName, new CardGroup(generateCards(cardGenerator)));
            players.add(player);
        }
        return new GameManager(dealer, players, cardGenerator);
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
        player.receiveCard(cardGenerator.generate());
    }
}
