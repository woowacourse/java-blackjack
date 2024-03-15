package domain;

import domain.cards.Card;
import domain.cards.CardPack;
import domain.cards.Hand;
import domain.gamer.Dealer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import domain.result.Judge;
import java.util.List;

public class BlackJackGame {

    private static final int INIT_CARDS_AMOUNT = 2;

    private final Gamers gamers;
    private final CardPack cardPack;
    private final Judge judge;

    public BlackJackGame(List<String> rawPlayersNames) {
        this.gamers = makeGamers(rawPlayersNames);
        this.cardPack = new CardPack();
        this.judge = new Judge();
    }

    private Gamers makeGamers(List<String> rawPlayersNames) {
        List<Player> players = rawPlayersNames.stream()
                .map(rawPlayersName -> new Player(rawPlayersName, new Hand()))
                .toList();
        Dealer dealer = new Dealer(new Hand());
        return new Gamers(players, dealer);
    }

    public void setUpProfits(Player player, int bettingAmount) {
        judge.initializeProfit(player, new Profit(bettingAmount));
    }

    public void setUpGame() {
        gamers.shareInitCards(cardPack, INIT_CARDS_AMOUNT);
    }

    public void hitByPlayer(Player player) {
        player.hit(cardPack.pickOneCard());
    }

    public Card hitByDealer(Dealer dealer) {
        Card pickedCard = cardPack.pickOneCard();
        dealer.hit(pickedCard);
        return pickedCard;
    }

    public void makeResult() {
        judge.decideResult(gamers);
    }

    public Gamers getGamers() {
        return gamers;
    }

    public Judge getJudge() {
        return judge;
    }
}
