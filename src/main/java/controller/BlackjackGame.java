package controller;

import common.PlayerDto;
import common.PlayersDto;
import domain.UserInterface;
import domain.blackjack.BlackjackService;
import domain.card.Deck;
import domain.result.MatchRule;
import domain.user.*;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BlackjackGame {
    private Deck deck;
    private MatchRule matchRule;
    private UserInterface userInterface;

    public BlackjackGame(Deck deck, MatchRule matchRule, UserInterface userInterface) {
        this.deck = deck;
        this.matchRule = matchRule;
        this.userInterface = userInterface;
    }

    public void play() {
        Dealer dealer = Dealer.shuffle(deck);
        Players players = Players.join(userInterface);
        BlackjackService blackjackService = BlackjackService.of(dealer, matchRule);
        players = distributeInitCards(dealer, players, blackjackService);
        players = confirmCards(dealer, players, blackjackService);
        Profit dealerProfit = blackjackService.match(players);
        OutputView.printResult(dealer.serialize(dealerProfit), serialziePlayers(players));
    }

    private Players distributeInitCards(Dealer dealer, Players players, BlackjackService blackjackService) {
        players = blackjackService.distributeInitCards(players);
        OutputView.printInitGame(dealer.serialize(), serialziePlayers(players));
        return players;
    }

    private Players confirmCards(Dealer dealer, Players players, BlackjackService blackjackService) {
        players = blackjackService.confirmCardsOfPlayers(players);
        int countOfHit = blackjackService.confirmCardsOfDealer();
        OutputView.printDealerHit(dealer.serialize(), countOfHit);
        return players;
    }

    private PlayersDto serialziePlayers(Players players) {
        List<PlayerDto> playerDtos = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            PlayerDto playerDto = PlayerDto.of(player.getName(),
                    player.getBettingBoney(),
                    player.getCards().serialize(),
                    player.getScore());
            Profit profit = player.getProfit();
            if (Objects.nonNull(profit)) {
                playerDto = PlayerDto.of(playerDto.getName(),
                        playerDto.getBettingMoney(),
                        playerDto.getCards(),
                        playerDto.getScore(),
                        profit.getValue());
            }
            playerDtos.add(playerDto);
        }
        return PlayersDto.of(playerDtos);
    }
}
