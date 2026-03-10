package controller;

import static config.BlackjackGameConstant.*;

import domain.participant.dto.ParticipantHandMapper;
import domain.result.GameResultAnalyzer;
import domain.result.dto.GameResultAnalysis;
import domain.intention.DrawCardIntetion;
import domain.card.CardDeck;
import domain.card.CardDeckInitializer;
import config.BlackjackGameConfiguration;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.ParticipantName;
import domain.participant.Players;
import domain.participant.dto.ParticipantResult;
import view.ApplicationView;

import java.util.List;

public class BlackjackGame {

    private static final int STARTING_REVEALED_CARD_COUNT = 1;

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
        Dealer dealer = Dealer.of(cardDeck);
        Players players = Players.from(requestPlayerNames());

        handOutInitialCard(dealer, players);

        printInitialParticipantsHand(dealer, players);

        proceedEachPlayersTurn(players, dealer);
        proceedDealersTurn(dealer);

        printAllParticipantsFinalHandResult(dealer, players);
        printFinalWinningStatistic(players, dealer);
    }

    private void printFinalWinningStatistic(Players players, Dealer dealer) {
        GameResultAnalysis analysis = analyzeBlackjackResult(players, dealer);
        view.printFinalResultMessage(analysis);
    }

    private void printAllParticipantsFinalHandResult(Dealer dealer, Players players) {
        view.printFinalResultMessage(ParticipantResult.from(dealer));
        players.toParticipantResult().forEach(view::printFinalResultMessage);
    }

    private void printInitialParticipantsHand(Dealer dealer, Players players) {
        view.printParticipantHand(ParticipantHandMapper.from(dealer, STARTING_REVEALED_CARD_COUNT));
        view.printAllPlayersHand(players.toParticipantHand());
    }

    private void proceedDealersTurn(Dealer dealer) {
        if (dealer.hitIfRequired()) {
            view.printDealerAdditionalDrawCardMessage();
        }
    }

    private void proceedEachPlayersTurn(Players players, Dealer dealer) {
        players.stream().forEach(player -> drawPlayerCard(player, dealer));
    }

    private void drawPlayerCard(Player player, Dealer dealer) {
        if(player.isBusted()) return;

        DrawCardIntetion drawCardIntetion = view.requestDrawCardIntention(player.toDisplayMyName());
        while (!player.isBusted() && drawCardIntetion.isYes()) {
            dealer.handOutCardToPlayer(player, DEFAULT_CARD_DRAW_COUNT);
            view.printParticipantHand(ParticipantHandMapper.from(player));
        }
    }

    private void handOutInitialCard(Dealer dealer, Players players) {
        dealer.drawMySelf(INITIAL_CARD_DRAW_COUNT);
        players.giveInitialCardBundle(dealer);
        view.printInitialHandOutResult(players.displayNames(), INITIAL_CARD_DRAW_COUNT);
    }


    private List<ParticipantName> requestPlayerNames() {
        return view.requestPlayerNames();
    }

    private GameResultAnalysis analyzeBlackjackResult(Players players, Dealer dealer) {
        return GameResultAnalyzer.analyze(players, dealer);
    }

}
