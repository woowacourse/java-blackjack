package domain.game;

import domain.card.Card;
import domain.card.CardDistributor;
import domain.card.Cards;
import domain.player.Dealer;
import domain.player.Name;
import domain.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {
    // TODO players + dealer 합칠 방법 생각해보기
    private final List<Player> players;
    private final Dealer dealer;
    private final CardDistributor cardDistributor;

    public BlackJackGame(List<Name> names) {
        this.cardDistributor = new CardDistributor();
        this.players = initializePlayers(new ArrayList<>(names));
        this.dealer = new Dealer(drawInitialCards());
    }

    private List<Player> initializePlayers(List<Name> names) {
        return names.stream()
                .map(name -> new Player(name, drawInitialCards()))
                .collect(Collectors.toUnmodifiableList());
    }

    private Cards drawInitialCards() {
        List<Card> cards = new ArrayList<>();
        cards.add(cardDistributor.distribute());
        cards.add(cardDistributor.distribute());
        return new Cards(cards);
    }

    public void drawPlayerCard(Player player) {
        int index = players.indexOf(player);
        Player nowPlayer = players.get(index);
        nowPlayer.drawCard(cardDistributor.distribute());
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
