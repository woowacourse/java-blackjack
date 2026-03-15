package controller;

import static config.BlackjackGameConstant.DEFAULT_CARD_DRAW_COUNT;
import static config.BlackjackGameConstant.INITIAL_CARD_DRAW_COUNT;

import config.BlackjackGameConfiguration;
import domain.card.CardDeck;
import domain.card.CardDeckInitializer;
import domain.participant.Dealer;
import domain.participant.ParticipantInitialInformation;
import domain.participant.Player;
import domain.participant.Players;
import domain.participant.dto.ParticipantHandDto;
import domain.participant.dto.ParticipantHandDtoMapper;
import domain.result.BettingResult;
import domain.result.GameResultAnalyzer;
import domain.result.dto.BettingProfitDto;
import domain.result.dto.ParticipantGameResultDto;
import java.util.List;
import view.ApplicationView;

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
        List<ParticipantInitialInformation> participantInitialInformation = requestInitialInformation();
        Players players = Players.from(participantInitialInformation);

        handOutInitialCard(dealer, players);

        printInitialParticipantsHand(dealer, players);

        proceedEachPlayersTurn(players);
        proceedDealersTurn(dealer);

        printAllParticipantsFinalHandResult(dealer, players);
        printBettingResult(players, dealer);
    }

    private void printBettingResult(Players players, Dealer dealer) {
        List<BettingResult> playerBettingResults = GameResultAnalyzer.analyzePlayerBettingResults(players, dealer);
        BettingResult dealerBettingResult = GameResultAnalyzer.analyzeDealerBettingResult(playerBettingResults);
        BettingProfitDto dealerBettingResultDto = BettingProfitDto.from(dealerBettingResult);
        List<BettingProfitDto> playerBettingResultDtos = playerBettingResults.stream()
                .map(BettingProfitDto::from)
                .toList();

        view.printBettingResults(dealerBettingResultDto, playerBettingResultDtos);
    }

    private void printAllParticipantsFinalHandResult(Dealer dealer, Players players) {
        view.printParticipantResult(ParticipantGameResultDto.from(dealer));
        players.stream()
                .forEach(player -> view.printParticipantResult(ParticipantGameResultDto.from(player)));
    }

    private void printInitialParticipantsHand(Dealer dealer, Players players) {
        view.printParticipantHand(ParticipantHandDtoMapper.map(dealer, STARTING_REVEALED_CARD_COUNT));
        players.stream()
                .forEach(player -> view.printParticipantHand(ParticipantHandDto.from(player)));
    }

    private void proceedDealersTurn(Dealer dealer) {
        if (dealer.hitIfRequired(cardDeck)) {
            view.printDealerDrawCard();
        }
    }

    private void proceedEachPlayersTurn(Players players) {
        players.stream().forEach(this::drawPlayerCard);
    }

    private void drawPlayerCard(Player player) {
        if (player.isBusted()) {
            return;
        }

        boolean drawCardIntention = view.requestDrawCardDecision(player.toDisplayMyName());
        while (!player.isBusted() && drawCardIntention) {
            player.drawCards(cardDeck, DEFAULT_CARD_DRAW_COUNT);
            view.printParticipantHand(ParticipantHandDtoMapper.map(player));
        }
    }

    private void handOutInitialCard(Dealer dealer, Players players) {
        dealer.drawCards(cardDeck, INITIAL_CARD_DRAW_COUNT);
        players.giveInitialCardBundle(cardDeck);
        view.printInitialDeal(players.displayNames(), INITIAL_CARD_DRAW_COUNT);
    }

    private List<ParticipantInitialInformation> requestInitialInformation() {
        return view.requestInitialInformation();
    }

}
