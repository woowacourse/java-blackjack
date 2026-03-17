package service;

import domain.BettingTable;
import domain.BlackjackGame;
import domain.participant.Dealer;
import domain.participant.player.Player;
import domain.vo.Money;
import domain.vo.Name;
import dto.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackService {
    private final BlackjackGame blackjackGame = new BlackjackGame();
    private final BettingTable bettingTable = new BettingTable();

    public void registerPlayers(List<String> playerNames) {
        blackjackGame.registerPlayers(playerNames);
    }

    public void placeBet(Name name, Money money) {
        bettingTable.placeBet(name, money);
    }

    public void dealCardsOut() {
        blackjackGame.dealDealerCardsOut();
        blackjackGame.dealPlayerCardsOut();
    }

    public void playerHit(Name name) {
        blackjackGame.playerHit(name);
    }

    public boolean isBust(Name name) {
        return blackjackGame.isBust(name);
    }

    public boolean isDealerHit() {
        return blackjackGame.isDealerHit();
    }

    public void decideDealerResult(List<Name> names) {
        names.stream()
                .map(name -> blackjackGame.decidePlayerResult(name))
                .forEach(winstatus -> blackjackGame.decideDealerResult(winstatus));
    }

    public DealerInfoDto getDealerInfo() {
        return new DealerInfoDto(blackjackGame.getDealer().getCards());
    }

    public PlayerInfoDto getPlayerInfo(Name name) {
        Player player = blackjackGame.findByName(name);
        return new PlayerInfoDto(player.getName().getValueOf(), player.getCards());
    }

    public List<PlayerInfoDto> getPlayerInfos() {
        return blackjackGame.getPlayers().stream()
                .map(player -> new PlayerInfoDto(
                        player.getName().getValueOf(),
                        player.getCards()
                ))
                .collect(Collectors.toList());
    }

    public DealerResultDto getDealerResult() {
        Dealer dealer = blackjackGame.getDealer();
        List<ResultForBettingDto> results = new ArrayList<>();

        for (Player player : blackjackGame.getPlayers()) {
            results.add(new ResultForBettingDto(player.getName(), player.decideWinStatus(dealer), player.isBlackjack()));
        }

        return new DealerResultDto(dealer.getCards(), dealer.getScore(), dealer.getRecord(), bettingTable.getDealerProfit(results));
    }

    public List<PlayerResultDto> getPlayerResultInfos() {
        List<PlayerResultDto> resultDtos = new ArrayList<>();

        for (Player player : blackjackGame.getPlayers()) {
            ResultForBettingDto dto = new ResultForBettingDto(player.getName(),
                    player.decideWinStatus(blackjackGame.getDealer()),
                    player.isBlackjack());

            resultDtos.add(
                    new PlayerResultDto(player.getName().getValueOf(),
                            player.getCards(),
                            player.getScore(),
                            player.decideWinStatus(blackjackGame.getDealer()),
                            bettingTable.calculateProfit(dto)));
        }

        return resultDtos;
    }
}
