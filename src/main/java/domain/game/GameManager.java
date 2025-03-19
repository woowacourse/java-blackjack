package domain.game;

import domain.card.Card;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Player;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameManager {

    private final Dealer dealer;
    private final List<Player> players;
    private final CardDeck cardDeck;

    public GameManager(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
        this.cardDeck = new CardDeck();
    }

    public void distributeSetUpCards() {
        dealer.setUpCardDeck(cardDeck.poll(), cardDeck.poll());
        players.forEach(player -> player.setUpCardDeck(cardDeck.poll(), cardDeck.poll()));
    }

    public EarningResult evaluateEarning(PlayerBets playerBets) {

        Map<Player, Double> earningResult = players.stream()
                .collect(Collectors.toMap(player -> player
                        , player -> Winning.determine(player.getHands(), dealer.getHands())
                                .getEarningRate() * playerBets.getBetAmountByPlayer(player)
                        , (player1, player2) -> player1, LinkedHashMap::new));

        return new EarningResult(earningResult);
    }

    public boolean canDealerTakeMoreCard() {
        return dealer.canTakeMoreCard();
    }

    public boolean canPlayerTakeMoreCard(String name) {
        Player player = findPlayerByName(name);
        if(player != null) {
            return player.canTakeMoreCard();
        }
        return false;
    }

    public void distributeExtraCardToDealer() {
        dealer.takeMoreCard(cardDeck.poll());
    }

    public void distributeExtraCardToPlayer(String name) {
        Player player = findPlayerByName(name);
        if(player != null) {
            player.takeMoreCard(cardDeck.poll());
        }
    }

    public List<Card> getPlayerCards(String name) {
        Player player = findPlayerByName(name);
        if(player != null) {
            return player.getHands().getCards();
        }
        return new ArrayList<>();
    }


    private Player findPlayerByName(String name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }
}
