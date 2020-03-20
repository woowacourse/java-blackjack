package controller;

import common.DealerDto;
import common.PlayerDto;
import common.PlayersDto;
import domain.UserInterface;
import domain.card.Card;
import domain.card.Deck;
import domain.card.PlayingCards;
import domain.result.DefaultMatchRule;
import domain.result.MatchRule;
import domain.result.Result;
import domain.user.Dealer;
import domain.user.Money;
import domain.user.Player;
import infra.repository.SingleDeck;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {
    private Deck deck;
    private MatchRule matchRule;
    private UserInterface userInterface;

    //todo: refac
    public void play() {
        PlayersDto inputPlayersDto = userInterface.inputPlayers();

        //setUp
        Dealer dealer = Dealer.shuffle(deck);
        //join
        List<Player> players = new ArrayList<>();
        for (PlayerDto playerDto : inputPlayersDto.getPlayerDtos()) {
            Player player = Player.join(playerDto);
            players.add(player);
        }
        List<PlayerDto> inputPlayerDtos = inputPlayersDto.getPlayerDtos();
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            PlayingCards cards = player.getCards();
            inputPlayerDtos.get(i).setCards(cards.serialize());
        }
        OutputView.printInitGame(dealer.serialize(), inputPlayersDto);

        int countOfHit = dealer.confirmCards();



        //match
        List<PlayerDto> playerDtos = new ArrayList<>();
        Money sumOfDealerProfit = Money.of(0);
        for (Player player : players) {
            Result result = player.match(dealer, matchRule);
            //todo: refac
            Money bettingMoney = Money.of(10);
//            Money bettingMoney = player.getBettingMoney();
            Money profitOfDealer = dealer.calculateProfit(result, bettingMoney);
            sumOfDealerProfit = sumOfDealerProfit.add(profitOfDealer);
            PlayerDto playerDtoToShow = player.serialize(result);
            playerDtos.add(playerDtoToShow);
        }

        //summarize
        PlayersDto playersDtoToShow = PlayersDto.of(playerDtos);
        DealerDto dealerDto = dealer.serialize(sumOfDealerProfit);

        OutputView.printDealerHit(dealerDto, countOfHit);
        OutputView.printResult(dealerDto, playersDtoToShow);

    }
}
