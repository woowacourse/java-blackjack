package controller;

import domain.analyzer.ResultAnalyzer;
import domain.analyzer.dto.ResultAnalysisDto;
import domain.answer.DrawDecision;
import domain.betting.BettingTable;
import domain.betting.Money;
import domain.betting.dto.GamerBettingProfitDto;
import domain.card.CardDeck;
import domain.card.CardGenerator;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.PlayerName;
import domain.gamer.Players;
import domain.gamer.dto.GamerHandDto;
import domain.gamer.dto.GamerResultDto;
import domain.gamer.dto.GamersNameDto;
import domain.view.ApplicationView;

import java.util.List;

public class BlackjackGame {

    public ApplicationView view;
    public CardDeck cardDeck;

    public BlackjackGame(ApplicationView view, CardGenerator cardGenerator) {
        this.view = view;
        this.cardDeck = CardDeck.from(cardGenerator);
    }

    public void start() {
        Dealer dealer = enterDealer();
        Players players = enterPlayers();

        BettingTable bettingTable = setBettingTable();
        askBettingMoney(bettingTable, players);

        dealInitialCard(dealer, players);
        showGamerHands(players, dealer);

        tryHitPlayers(players, dealer);
        tryHitDealer(dealer);

        showGamerHandResult(dealer, players);
//        showGameResultAnalysis(players, dealer);
    }

    private Dealer enterDealer() {
        return Dealer.from(cardDeck);
    }

    private Players enterPlayers() {
        return Players.from(requestPlayerNames()
                .stream()
                .map(Player::from)
                .toList()
        );
    }

    private List<PlayerName> requestPlayerNames() {
        return view.requestPlayerNames();
    }

    private BettingTable setBettingTable() {
        return new BettingTable();
    }

    private void askBettingMoney(BettingTable bettingTable, Players players) {
        players.getPlayers().forEach(player -> {
            Money bettingMoney = view.askBettingMoney(player.getMyName());
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
                .forEach(player -> drawPlayerCard(player, dealer));
        // 출력해야하넹...
    }

    private void tryHitDealer(Dealer dealer) {
        if (dealer.hitIfRequired()) {
            view.printDealerAdditionalDrawCardMessage();
        }
    }

    private void drawPlayerCard(Player player, Dealer dealer) {
        while (!player.isBusted() && isPlayerWantCard(player)) {
            dealer.hitCardToPlayer(player);
            view.printParticipantHand(GamerHandDto.from(player));
        }
    }

    private boolean isPlayerWantCard(Player player) {
        DrawDecision drawDecision = view.askDrawCard(player.getMyName());
        return drawDecision.isYes();
    }

    private void showGamerHandResult(Dealer dealer, Players players) {
        view.printFinalResultMessage(GamerResultDto.from(dealer));
        players.getPlayers().forEach(player -> {
            view.printFinalResultMessage(GamerResultDto.from(player));
        });
    }

    private void showGameResultAnalysis(Players players, Dealer dealer) {
        ResultAnalysisDto analysis = analyzeBlackjackResult(players, dealer);
        view.printFinalResultMessage(analysis);
    }

    private ResultAnalysisDto analyzeBlackjackResult(Players players, Dealer dealer) {
        return ResultAnalyzer.analyze(players.getPlayers(), dealer);
    }

}
