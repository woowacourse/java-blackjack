package controller;

import static config.BlackjackGameConstant.*;

import domain.result.GameResultAnalyzer;
import domain.result.dto.GameResultAnalysisDto;
import domain.intention.DrawCardIntetion;
import domain.card.CardDeck;
import domain.card.CardDeckInitializer;
import config.BlackjackGameConfiguration;
import domain.dealer.Dealer;
import domain.player.Player;
import domain.player.PlayerName;
import domain.player.Players;
import domain.player.dto.PlayerHandDto;
import domain.player.dto.PlayerResultDto;
import view.ApplicationView;

import java.util.List;

public class BlackjackGame {

    private final ApplicationView view;
    private final CardDeck cardDeck;

    private BlackjackGame(ApplicationView view, CardDeckInitializer cardDeckInitializer) {
        this.view = view;
        this.cardDeck = CardDeck.from(cardDeckInitializer);
    }

    public static BlackjackGame from(BlackjackGameConfiguration configuration) {
        return new BlackjackGame(configuration.view(), configuration.gameCardDeckInitializer());
    }

    public void start() {
        Dealer dealer = enterDealer();
        Players players = enterPlayers();

        handOutInitialCard(dealer, players);

        printAllParticipantsHand(dealer, players);

        proceedEachPlayersTurn(players, dealer);
        proceedDealersTurn(dealer);

        printAllParticipantsFinalHandResult(dealer, players);
        printFinalWinningStatistic(players, dealer);
    }

    private void printFinalWinningStatistic(Players players, Dealer dealer) {
        GameResultAnalysisDto analysis = analyzeBlackjackResult(players, dealer);
        view.printFinalResultMessage(analysis);
    }

    private void printAllParticipantsFinalHandResult(Dealer dealer, Players players) {
        view.printFinalResultMessage(PlayerResultDto.from(dealer));
        players.toPlayerResultDtos().forEach(view::printFinalResultMessage);
    }

    private void printAllParticipantsHand(Dealer dealer, Players players) {
        view.printParticipantHand(PlayerHandDto.of(dealer));
        view.printAllPlayersHand(players.getPlayerHandDtos());
    }

    private void proceedDealersTurn(Dealer dealer) {
        if (dealer.hitIfRequired()) {
            view.printDealerAdditionalDrawCardMessage();
        }
    }

    private void proceedEachPlayersTurn(Players players, Dealer dealer) {
        players.stream().forEach(player -> {
            drawPlayerCard(player, dealer);
        });
    }

    private void drawPlayerCard(Player player, Dealer dealer) {
        if(player.isBusted()) return;

        DrawCardIntetion drawCardIntetion = view.requestDrawCardIntention(player.toDisplayMyName());
        while (!player.isBusted() && drawCardIntetion.isYes()) {
            dealer.handOutCardToPlayer(player, DEFAULT_CARD_DRAW_COUNT);
            view.printParticipantHand(PlayerHandDto.of(player));
        }
    }

    private void handOutInitialCard(Dealer dealer, Players players) {
        dealer.drawMySelf(INITIAL_CARD_DRAW_COUNT);
        players.giveInitialCardBundle(dealer);
        view.printInitialHandOutResult(players.displayNames(), INITIAL_CARD_DRAW_COUNT);
    }

    private Dealer enterDealer() {
        return Dealer.of(cardDeck);
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

    private GameResultAnalysisDto analyzeBlackjackResult(Players players, Dealer dealer) {
        return GameResultAnalyzer.analyze(players, dealer);
    }

}
