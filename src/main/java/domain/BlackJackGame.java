package domain;

import domain.cards.Card;
import domain.cards.CardPack;
import domain.cards.Hand;
import domain.gamer.Dealer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {

    private static final int INIT_CARDS_AMOUNT = 2;

    private final Gamers gamers;
    private CardPack cardPack;

    public BlackJackGame(List<String> rawPlayersNames) {
        this.gamers = makeGamers(rawPlayersNames);
        this.cardPack = new CardPack();
    }

    private Gamers makeGamers(List<String> rawPlayersNames) {
        List<Player> players = rawPlayersNames.stream()
                .map(rawPlayersName -> new Player(rawPlayersName, new Hand(new ArrayList<>())))
                .toList();
        Dealer dealer = new Dealer(new Hand(new ArrayList<>()));
        return new Gamers(players, dealer);
    }

    public void setUpGame() {
        gamers.shareInitCards(cardPack, INIT_CARDS_AMOUNT);
    }

    public boolean hitByPlayer(HitOption hitOption, Player player) {
        if (hitOption.isHit()) {
            player.hit(cardPack.pickOneCard());
            return true;
        }
        return false;
    }

    public Card hitByDealer(Dealer dealer) {
        Card pickedCard = cardPack.pickOneCard();
        dealer.hit(pickedCard);
        return pickedCard;
    }

    public Gamers getGamers() {
        return gamers;
    }
}
