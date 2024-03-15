package domain;

import domain.cards.Card;
import domain.cards.CardPack;
import domain.cards.Hand;
import domain.gamer.Dealer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import java.util.List;

public class BlackJackGame {

    private static final int INIT_CARDS_AMOUNT = 2;

    private final Gamers gamers;
    private final CardPack cardPack;

    public BlackJackGame(List<String> rawPlayersNames) {
        this.gamers = makeGamers(rawPlayersNames);
        this.cardPack = new CardPack();
    }

    private Gamers makeGamers(List<String> rawPlayersNames) {
        List<Player> players = rawPlayersNames.stream()
                .map(rawPlayersName -> new Player(rawPlayersName, new Hand()))
                .toList();
        Dealer dealer = new Dealer(new Hand());
        return new Gamers(players, dealer);
    }

    public void setUpGame() {
        gamers.shareInitCards(cardPack, INIT_CARDS_AMOUNT);
    }

    public HitOption hitByPlayer(HitOption hitOption, Player player) {
        if (hitOption.isHit()) {
            player.hit(cardPack.pickOneCard());
            return HitOption.HIT;
        }
        return HitOption.NOT_HIT;
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
