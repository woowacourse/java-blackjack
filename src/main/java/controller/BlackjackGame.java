package controller;

import domain.answer.DrawDecision;
import domain.betting.BettingTable;
import domain.betting.BettingMoney;
import domain.betting.Profit;
import domain.betting.dto.GamerBettingProfitDto;
import domain.betting.manager.BettingPolicyManager;
import domain.card.CardDeck;
import domain.card.CardGenerator;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.PlayerName;
import domain.gamer.Players;
import domain.gamer.dto.GamerHandDto;
import domain.gamer.dto.GamerResultDto;
import domain.gamer.dto.GamersNameDto;
import view.ApplicationView;

import java.util.List;

public class BlackjackGame {

    public ApplicationView view;
    public CardDeck cardDeck;
    public BettingPolicyManager policyManager;

    public BlackjackGame(ApplicationView view, CardGenerator cardGenerator, BettingPolicyManager policyManager) {
        this.view = view;
        this.cardDeck = CardDeck.from(cardGenerator);
        this.policyManager = policyManager;
    }

    public void start() {
        Dealer dealer = enterDealer();
        Players players = enterPlayers();

        BettingTable bettingTable = new BettingTable(policyManager);
        askBettingMoney(bettingTable, players);

        dealInitialCard(dealer, players);
        showGamerHands(players, dealer);

        tryHitPlayers(players, dealer);
        tryHitDealer(dealer);

        showGamerHandResult(dealer, players);

        bettingTable.applyBettingRate(dealer, players);
        showGamerProfit(calculateProfit(bettingTable, players), calculateProfit(bettingTable, dealer));
    }

    private Dealer enterDealer() {
        return Dealer.from(cardDeck);
    }

    private Players enterPlayers() {
        return view.enterPlayers();
    }

    private void askBettingMoney(BettingTable bettingTable, Players players) {
        players.getPlayers().forEach(player -> {
            BettingMoney bettingMoney = view.askBettingMoney(player.getName());
            bettingTable.bet(player, bettingMoney);
        });
    }

    private void dealInitialCard(Dealer dealer, Players players) {
        dealer.dealMyself();
        players.dealCardBundle(dealer);
    }

    private void showGamerHands(Players players, Dealer dealer) {
        view.printFirstHandOutResult(GamersNameDto.from(players.getPlayers()));
        view.printParticipantHand(GamerHandDto.onlyFirstCard(dealer));
        view.printAllParticipantsHand(getPlayerHandInformation(players.getPlayers()));
    }

    private List<GamerHandDto> getPlayerHandInformation(List<Player> players) {
        return players.stream()
                .map(GamerHandDto::from)
                .toList();
    }

    private void tryHitPlayers(Players players, Dealer dealer) {
        players.getPlayers()
                .forEach(player -> {
                    drawPlayerCard(player, dealer);
                    view.printParticipantHand(GamerHandDto.from(player));
                });
    }

    private void drawPlayerCard(Player player, Dealer dealer) {
        while (!player.isBusted() && isPlayerWantCard(player)) {
            dealer.hitCardToPlayer(player);
            view.printParticipantHand(GamerHandDto.from(player));
        }
    }

    private boolean isPlayerWantCard(Player player) {
        DrawDecision drawDecision = view.askDrawCard(player.getName());
        return drawDecision.isYes();
    }

    private void tryHitDealer(Dealer dealer) {
        if (dealer.hitIfRequired()) {
            view.printDealerAdditionalDrawCardMessage();
        }
    }

    private void showGamerHandResult(Dealer dealer, Players players) {
        view.printFinalResultMessage(GamerResultDto.from(dealer));
        players.getPlayers().forEach(player -> {
            view.printFinalResultMessage(GamerResultDto.from(player));
        });
    }

    private List<GamerBettingProfitDto> calculateProfit(BettingTable bettingTable, Players players) {
        return players.getPlayers().stream()
                .map(player -> {
                    Profit playerProfit = bettingTable.getPlayerProfit(player);
                    return GamerBettingProfitDto.of(player, playerProfit);
                })
                .toList();
    }

    private GamerBettingProfitDto calculateProfit(BettingTable bettingTable, Dealer dealer) {
        Profit dealerProfit = bettingTable.getDealerProfit();
        return GamerBettingProfitDto.of(dealer, dealerProfit);
    }

    private void showGamerProfit(List<GamerBettingProfitDto> playersProfit, GamerBettingProfitDto dealerProfit) {
        view.printGamerProfit(dealerProfit, playersProfit);
    }

}
