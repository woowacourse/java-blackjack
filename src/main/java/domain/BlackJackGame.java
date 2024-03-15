package domain;

import domain.cards.Card;
import domain.cards.CardPack;
import domain.cards.Hand;
import domain.gamer.Dealer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import domain.result.GameManager;
import java.util.List;

public class BlackJackGame {

    private static final int INIT_CARDS_AMOUNT = 2;

    private final Gamers gamers;
    private final CardPack cardPack;
    private final GameManager gameManager;

    public BlackJackGame(List<String> rawPlayersNames) {
        this.gamers = makeGamers(rawPlayersNames);
        this.cardPack = new CardPack();
        this.gameManager = new GameManager();
    }

    private Gamers makeGamers(List<String> rawPlayersNames) {
        List<Player> players = rawPlayersNames.stream()
                .map(rawPlayersName -> new Player(rawPlayersName, new Hand()))
                .toList();
        Dealer dealer = new Dealer(new Hand());
        return new Gamers(players, dealer);
    }

    public void setUpProfits(Player player, int rawBettingAmount) {
        Bet bet = new Bet(rawBettingAmount);
        gameManager.initializeProfit(player, new Profit(bet));
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
        gameManager.decideResult(gamers);
    }

    public Gamers getGamers() {
        return gamers;
    }

    public GameManager getJudge() {
        return gameManager;
    }
}
