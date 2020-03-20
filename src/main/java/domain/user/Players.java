package domain.user;

import common.PlayerDto;
import common.PlayersDto;
import domain.UserInterface;
import domain.card.Card;
import domain.card.PlayingCards;
import domain.result.MatchRule;
import domain.result.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Players {
    private static final int DEAFAULT_DEALER_PROFIT = 0;

    private final List<Player> players;
    private UserInterface userInterface;

    private Players(List<Player> players, UserInterface userInterface) {
        this.players = players;
        this.userInterface = userInterface;
    }

    public static Players join(UserInterface userInterface) {
        PlayersDto playersDto = userInterface.inputPlayers();
        List<Player> players = new ArrayList<>();
        for (PlayerDto playerDto : playersDto.getPlayerDtos()) {
            Player player = Player.join(playerDto);
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

    public PlayersDto serialize() {
        List<PlayerDto> playerDtos = new ArrayList<>();
        for (Player player : players) {
            PlayerDto playerDto = player.serialize(PlayerDto.init());
            Profit profit = player.getProfit();
            if (Objects.nonNull(profit)) {
                playerDto.setProfit(profit.getValue());
            }
            playerDtos.add(playerDto);
        }
        return PlayersDto.of(playerDtos);
    }

    private Profit calculateDealerProfit(Dealer dealer, Profit sumOfDealerProfit, Player player, Result result) {
        Money bettingMoney = player.getBettingMoney();
        Profit profitOfDealer = dealer.calculateProfit(result, bettingMoney);
        sumOfDealerProfit = sumOfDealerProfit.add(profitOfDealer);
        return sumOfDealerProfit;
    }
}
