package controller;

import common.DealerDto;
import common.PlayerDto;
import common.PlayersDto;
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

public class BlackJackGame {
    private final Deck deck = SingleDeck.shuffle();
    private final MatchRule matchRule = new DefaultMatchRule();

    //todo: refac
    public void play() {
        PlayersDto inputPlayersDto = InputView.inputPlayers();
        Dealer dealer = Dealer.start(deck);
        List<String> playerNames = new ArrayList<>();
        List<Money> bettingMoneys = new ArrayList<>();
        for (PlayerDto playerDto : inputPlayersDto.getPlayerDtos()) {
            playerNames.add(playerDto.getName());
            bettingMoneys.add(Money.deserialize(playerDto.getBettingMoney()));
        }

        List<Player> players = dealer.passInitCards(playerNames, bettingMoneys);
        List<PlayerDto> inputPlayerDtos = inputPlayersDto.getPlayerDtos();
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            PlayingCards cards = player.getCards();
            inputPlayerDtos.get(i).setCards(cards.serialize());
        }

        OutputView.printInitGame(dealer.serialize(), inputPlayersDto);

        for (Player player : players) {
            String wantToHit = InputView.inputWantToHit(player.getName());
            while (player.wantToHit(wantToHit)) {
                Card card = dealer.passCard();
                player.hit(card);
                OutputView.printCurrentStateOfPlayer(player.serialize());
                wantToHit = InputView.inputWantToHit(player.getName());
            }
        }

        int countOfHit = dealer.confirmCards();


        List<PlayerDto> playerDtos = new ArrayList<>();
        Money sumOfDealerProfit = Money.of(0);
        for (Player player : players) {
            Result result = player.match(dealer, matchRule);
            Money bettingMoney = player.getBettingMoney();
            Money profitOfDealer = dealer.calculateProfit(result, bettingMoney);
            sumOfDealerProfit = sumOfDealerProfit.add(profitOfDealer);
            PlayerDto playerDtoToShow = player.serialize(result);
            playerDtos.add(playerDtoToShow);
        }

        PlayersDto playersDtoToShow = PlayersDto.of(playerDtos);
        DealerDto dealerDto = dealer.serialize(sumOfDealerProfit);

        OutputView.printDealerHit(dealerDto, countOfHit);
        OutputView.printResult(dealerDto, playersDtoToShow);

    }
}
