package controller;

import domain.analyzer.ResultAnalyzer;
import domain.analyzer.dto.ResultAnalysisDto;
import domain.answer.DrawDecision;
import domain.card.CardDeck;
import domain.card.CardGenerator;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.PlayerName;
import domain.gamer.Players;
import domain.gamer.dto.PlayerHandDto;
import domain.gamer.dto.PlayerResultDto;
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

        dealInitialCard(dealer, players);
        showGamerHands(players, dealer);
        
        tryHitPlayers(players, dealer);
        tryHitDealer(dealer);

        showGamerHandResult(dealer, players);
        showGameResultAnalysis(players, dealer);
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

    private void dealInitialCard(Dealer dealer, Players players) {
        dealer.dealMyself();
        players.dealCardBundle(dealer);
    }

    private void showGamerHands(Players players, Dealer dealer) {
        view.printFirstHandOutResult(players.displayNames());
        view.printParticipantHand(PlayerHandDto.of(dealer));
        view.printAllParticipantsHand(getPlayerHandInformation(players));
    }

    private List<PlayerHandDto> getPlayerHandInformation(Players players) {
        return players.stream()
                .map(PlayerHandDto::of)
                .toList();
    }

    private void tryHitPlayers(Players players, Dealer dealer) {
        players.stream()
                .forEach(player -> drawPlayerCard(player, dealer));
    }

    private void tryHitDealer(Dealer dealer) {
        if (dealer.hitIfRequired()) {
            view.printDealerAdditionalDrawCardMessage();
        }
    }

    private void drawPlayerCard(Player player, Dealer dealer) {
        while(!player.isBusted() && isPlayerWantCard(player)) {
            dealer.hitCardToPlayer(player);
            view.printParticipantHand(PlayerHandDto.of(player));
        }
    }

    private boolean isPlayerWantCard(Player player) {
        DrawDecision drawDecision = view.askDrawCard(player.toDisplayMyName());
        return drawDecision.isYes();
    }

    private void showGamerHandResult(Dealer dealer, Players players) {
        view.printFinalResultMessage(PlayerResultDto.from(dealer));
        players.stream().forEach(player -> {
            view.printFinalResultMessage(PlayerResultDto.from(player));
        });
    }

    private void showGameResultAnalysis(Players players, Dealer dealer) {
        ResultAnalysisDto analysis = analyzeBlackjackResult(players, dealer);
        view.printFinalResultMessage(analysis);
    }

    private ResultAnalysisDto analyzeBlackjackResult(Players players, Dealer dealer) {
        return ResultAnalyzer.analyze(players, dealer);
    }

}
