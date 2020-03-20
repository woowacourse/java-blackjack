package domain.user;

import common.DealerDto;
import common.PlayerDto;
import common.PlayersDto;
import domain.UserInterface;
import domain.card.Card;
//todo: fix
import domain.card.PlayingCards;
import domain.result.MatchRule;
import domain.result.Result;
import domain.result.Results;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private static final int DEAFAULT_DEALER_PROFIT = 0;
    private final List<Player> players;
    private UserInterface userInterface;

    private Players(List<Player> players) {
        this.players = players;
    }

    private Players(List<Player> players, UserInterface userInterface) {
        this(players);
        this.userInterface = userInterface;
    }


    public static Players join(PlayersDto playersDto, UserInterface userInterface) {
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

    public Players confirmCards(Dealer dealer) {
        for (Player player : players) {
            String wantToHit = userInterface.inputWantToHit(player.getName());
            while (player.wantToHit(wantToHit)) {
                Card card = dealer.passCard();
                player.hit(card);
                //todo: important fix
                OutputView.printCurrentStateOfPlayer(player.serialize(PlayerDto.init()));
                wantToHit = userInterface.inputWantToHit(player.getName());
            }
        }

        return update(players, userInterface);
    }

    public Results match(Dealer dealer, MatchRule matchRule) {
        Money dealerProfit = Money.of(DEAFAULT_DEALER_PROFIT);
        List<Result> results = new ArrayList<>();
        for (Player player : players) {
            Result result = match(dealer, matchRule, results, player);
            dealerProfit = calculateDealerProfit(dealer, dealerProfit, player, result);
        }

        return Results.of(results, dealerProfit);
    }

    private Result match(Dealer dealer, MatchRule matchRule, List<Result> results, Player player) {
        Result result = player.match(dealer, matchRule);
        results.add(result);
        return result;
    }

    private Money calculateDealerProfit(Dealer dealer, Money sumOfDealerProfit, Player player, Result result) {
        Money bettingMoney = player.getBettingMoney();
        Money profitOfDealer = dealer.calculateProfit(result, bettingMoney);
        sumOfDealerProfit = sumOfDealerProfit.add(profitOfDealer);
        return sumOfDealerProfit;
    }

    public PlayersDto serialize() {
        List<PlayerDto> playerDtos = new ArrayList<>();
        for (Player player : players) {
            playerDtos.add(player.serialize(PlayerDto.init()));
        }
        return PlayersDto.of(playerDtos);
    }

    public Players receiveInitCards(Dealer dealer) {
        List<Player> players = new ArrayList<>();
        for (Player player : this.players) {
            PlayingCards initCards = dealer.passInitCards();
            players.add(player.receiveInitCards(initCards));
        }
        return Players.update(players, userInterface);
    }
}
