package domain.user;

import domain.UserInterface;
import domain.card.Card;
import domain.card.PlayingCards;
import domain.result.MatchRule;
import domain.result.Result;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private static final int DEAFAULT_DEALER_PROFIT = 0;

    private final List<Player> players;
    private UserInterface userInterface;

    private Players(List<Player> players, UserInterface userInterface) {
        this.players = players;
        this.userInterface = userInterface;
    }

    public static Players join(UserInterface userInterface) {

        List<Player> players = new ArrayList<>();
        List<String> playerNames = userInterface.inputPlayerNames();
        for (String playerName : playerNames) {
            int bettingMoney = userInterface.inputBettingMoney(playerName);
            Player player = Player.join(playerName, bettingMoney);
            players.add(player);
        }

        return new Players(players, userInterface);
    }

    private static Players update(List<Player> players, UserInterface userInterface) {
        return new Players(players, userInterface);

    }

    public Players receiveInitCards(Dealer dealer) {
        List<Player> players = new ArrayList<>();
        for (Player player : this.players) {
            PlayingCards initCards = dealer.passInitCards();
            players.add(player.receiveInitCards(initCards));
        }
        return Players.update(players, userInterface);
    }

    public Players confirmCards(Dealer dealer) {
        for (Player player : players) {
            String wantToHit = userInterface.inputWantToHit(player.getName());
            while (player.wantToHit(wantToHit)) {
                Card card = dealer.passCard();
                player.hit(card);
                userInterface.showCurrentStateOfPlayer(player);
                wantToHit = userInterface.inputWantToHit(player.getName());
            }
        }

        return update(players, userInterface);
    }

    public Profit match(Dealer dealer, MatchRule matchRule) {
        Profit dealerProfit = new Profit(DEAFAULT_DEALER_PROFIT);
        for (Player player : players) {
            Result result = player.match(dealer, matchRule);
            player.calculateProfit(result);
            dealerProfit = calculateDealerProfit(dealer, dealerProfit, player, result);
        }

        return dealerProfit;
    }

    public List<Player> getPlayers() {
        return players;
    }

    private Profit calculateDealerProfit(Dealer dealer, Profit sumOfDealerProfit, Player player, Result result) {
        Money bettingMoney = player.getBettingMoney();
        Profit profitOfDealer = dealer.calculateProfit(result, bettingMoney);
        sumOfDealerProfit = sumOfDealerProfit.add(profitOfDealer);
        return sumOfDealerProfit;
    }
}
