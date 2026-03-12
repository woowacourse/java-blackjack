package controller;

import static config.BlackjackGameConstant.*;

import domain.participant.ParticipantInitialInformation;
import domain.participant.dto.ParticipantHandDtoMapper;
import domain.result.GameResultAnalyzer;
import domain.result.dto.GameResultDto;
import domain.card.CardDeck;
import domain.card.CardDeckInitializer;
import config.BlackjackGameConfiguration;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.dto.ParticipantGameResultDto;
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
        Dealer dealer = Dealer.from();
        List<ParticipantInitialInformation> participantInitialInformations = requestInitialInformations();
        Players players = Players.from(participantInitialInformations);

        handOutInitialCard(dealer, players);

        printInitialParticipantsHand(dealer, players);

        proceedEachPlayersTurn(players);
        proceedDealersTurn(dealer);

        printAllParticipantsFinalHandResult(dealer, players);
        printFinalWinningStatistic(players, dealer);
    }

    private void printFinalWinningStatistic(Players players, Dealer dealer) {
        GameResultDto analysis = analyzeBlackjackResult(players, dealer);
        view.printFinalResultMessage(analysis);
    }

    private void printAllParticipantsFinalHandResult(Dealer dealer, Players players) {
        view.printFinalResultMessage(ParticipantGameResultDto.from(dealer));
        players.toParticipantGameResultDtos().forEach(view::printFinalResultMessage);
    }

    private void printInitialParticipantsHand(Dealer dealer, Players players) {
        view.printParticipantHand(ParticipantHandDtoMapper.map(dealer, STARTING_REVEALED_CARD_COUNT));
        view.printAllPlayersHand(players.toParticipantHandDtos());
    }

    private void proceedDealersTurn(Dealer dealer) {
        if (dealer.hitIfRequired(cardDeck)) {
            view.printDealerAdditionalDrawCardMessage();
        }
    }

    private void proceedEachPlayersTurn(Players players) {
        players.stream().forEach(this::drawPlayerCard);
    }

    private void drawPlayerCard(Player player) {
        if (player.isBusted()) {
            return;
        }

        boolean drawCardIntention = view.requestDrawCardIntention(player.toDisplayMyName());
        while (!player.isBusted() && drawCardIntention) {
            player.drawCards(cardDeck, DEFAULT_CARD_DRAW_COUNT);
            view.printParticipantHand(ParticipantHandDtoMapper.map(player));
        }
    }

    private void handOutInitialCard(Dealer dealer, Players players) {
        dealer.drawCards(cardDeck, INITIAL_CARD_DRAW_COUNT);
        players.giveInitialCardBundle(cardDeck);
        view.printInitialHandOutResult(players.displayNames(), INITIAL_CARD_DRAW_COUNT);
    }

    private List<ParticipantInitialInformation> requestInitialInformations() {
        return view.requestInitialInformations();
    }

    private GameResultDto analyzeBlackjackResult(Players players, Dealer dealer) {
        return GameResultAnalyzer.analyze(players, dealer);
    }

}
