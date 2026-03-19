package controller;

import domain.BettingTable;
import domain.BlackjackGame;
import domain.participant.Dealer;
import domain.participant.player.Player;
import domain.vo.Money;
import dto.DealerInfoDto;
import dto.DealerResultDto;
import dto.PlayerInfoDto;
import dto.PlayerResultDto;
import view.InputView;
import view.ResultView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameController {
    private final BlackjackGame blackjackGame = new BlackjackGame();
    private final BettingTable bettingTable = new BettingTable();

    public void run() {
        gameSetup();
        askBettingMoney();

        participantsHit();
        finalizeGameResult();
    }

    private void gameSetup() {
        List<String> names = InputView.askName();

        blackjackGame.registerPlayers(names);
        blackjackGame.dealParticipantsCardsOut();

        ResultView.printStartPlayersCards(getDealerInfo(), getPlayerInfos());
    }

    private void askBettingMoney() {
        for (Player player : blackjackGame.getPlayers()) {
            bettingTable.placeBet(player, new Money(InputView.askBettingMoney(player.getName().getValueOf())));
        }
    }

    private void participantsHit() {
        for (Player player : blackjackGame.getPlayers()) {
            playerHit(player);
        }

        if (blackjackGame.isDealerHit()) {
            ResultView.printDealerOneMoreCard();
        }
    }

    private void playerHit(Player player) {
        while (canHitAndDraw(player)) {
            ResultView.printPlayerCards(getPlayerInfo(player));
        }
    }

    private boolean canHitAndDraw(Player player) {
        if (player.isBust()) {
            return false;
        }

        if (InputView.askHit(player.getName().getValueOf())) {
            blackjackGame.playerHit(player);
            return true;
        }

        ResultView.printPlayerCards(getPlayerInfo(player));
        return false;
    }

    private void finalizeGameResult() {
        blackjackGame.decideDealerResult();

        DealerResultDto dealerResultDto = getDealerResult();
        List<PlayerResultDto> playerResultDtos = getPlayerResultInfos();

        ResultView.printCardsAndScoreResult(dealerResultDto, playerResultDtos);
        ResultView.printRankResult(dealerResultDto, playerResultDtos);

        ResultView.printDealerProfit(dealerResultDto);
        playerResultDtos.stream()
                .forEach(playerResultDto -> ResultView.printPlayerProfit(playerResultDto));
    }

    private DealerInfoDto getDealerInfo() {
        return new DealerInfoDto(blackjackGame.getDealer().getCards());
    }

    private PlayerInfoDto getPlayerInfo(Player player) {
        return new PlayerInfoDto(player.getName().getValueOf(), player.getCards());
    }

    private List<PlayerInfoDto> getPlayerInfos() {
        return blackjackGame.getPlayers().stream()
                .map(player -> new PlayerInfoDto(
                        player.getName().getValueOf(),
                        player.getCards()
                ))
                .collect(Collectors.toList());
    }

    public DealerResultDto getDealerResult() {
        Dealer dealer = blackjackGame.getDealer();
        Money dealerProfit = bettingTable.getDealerProfit(blackjackGame.getPlayers(), dealer);

        return new DealerResultDto(dealer.getCards(), dealer.getScore(), dealer.getRecord(), dealerProfit);
    }

    public List<PlayerResultDto> getPlayerResultInfos() {
        List<PlayerResultDto> resultDtos = new ArrayList<>();
        Dealer dealer = blackjackGame.getDealer();

        for (Player player : blackjackGame.getPlayers()) {
            resultDtos.add(
                    new PlayerResultDto(player.getName().getValueOf(),
                            player.getCards(),
                            player.getScore(),
                            player.decideWinStatus(dealer),
                            bettingTable.calculateProfit(player, dealer)));
        }

        return resultDtos;
    }
}
