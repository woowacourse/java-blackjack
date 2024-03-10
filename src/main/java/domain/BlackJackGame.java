package domain;

import domain.cards.Card;
import domain.cards.CardPack;
import domain.gamer.Dealer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import java.util.List;

public class BlackJackGame {

    private static final int INIT_CARDS_AMOUNT = 2;

    private Gamers gamers;
    private CardPack cardPack;

    public BlackJackGame() {
    }

    public void prepareGamers(List<String> rawPlayersNames) {
        this.gamers = new Gamers(rawPlayersNames);
    }

    public void prepareCardPack() {
        this.cardPack = new CardPack();
    }

    public void setUpGame() {
        gamers.shareInitCards(cardPack, INIT_CARDS_AMOUNT);
    }

    public boolean hitByPlayer(String rawHitOption, Player player) {
        HitOption hitOption = new HitOption(rawHitOption);
        if (hitOption.doHit()) {
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

    public Judge makeFinalResult() {
        Judge judge = new Judge();
        judge.decideResult(gamers);
        return judge;
    }

    public Gamers getGamers() {
        return gamers;
    }
}
