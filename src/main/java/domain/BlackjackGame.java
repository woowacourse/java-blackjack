package domain;

import domain.analyzer.ResultAnalyzer;
import domain.analyzer.dto.ResultAnalysisDto;
import domain.answer.DrawCardIntetion;
import domain.card.CardDeck;
import domain.card.CardGenerator;
import domain.config.BlackjackGameConfiguration;
import domain.dealer.Dealer;
import domain.player.Player;
import domain.player.PlayerName;
import domain.player.Players;
import domain.player.dto.PlayerHandDto;
import domain.player.dto.PlayerResultDto;
import domain.view.ApplicationView;

import java.util.List;

public class BlackjackGame {

    private static final int INITIAL_CARD_DRAW_COUNT = 2;
    private static final int DEFAULT_CARD_DRAW_COUNT = 1;

    private final ApplicationView view;
    private final CardDeck cardDeck;

    private BlackjackGame(ApplicationView view, CardGenerator cardGenerator) {
        this.view = view;
        this.cardDeck = CardDeck.from(cardGenerator);
    }

    public static BlackjackGame from(BlackjackGameConfiguration configuration) {
        return new BlackjackGame(configuration.view(), configuration.gameCardGenerator());
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
        ResultAnalysisDto analysis = analyzeBlackjackResult(players, dealer);
        view.printFinalResultMessage(analysis);
    }

    private void printAllParticipantsFinalHandResult(Dealer dealer, Players players) {
        view.printFinalResultMessage(PlayerResultDto.from(dealer));
        players.stream().forEach(player -> {
            view.printFinalResultMessage(PlayerResultDto.from(player));
        });
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

    private void drawPlayerCard(Player p, Dealer dealer) {
        while (!p.isBusted()) {
            DrawCardIntetion drawCardIntetion = view.requestDrawCardIntention(p.toDisplayMyName());
            if (drawCardIntetion.isNo()) {
                return;
            }

            dealer.handOutCardToPlayer(p, DEFAULT_CARD_DRAW_COUNT);
            view.printParticipantHand(PlayerHandDto.of(p));
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

    private ResultAnalysisDto analyzeBlackjackResult(Players players, Dealer dealer) {
        ResultAnalyzer analyzer = getAnalyzer();
        return analyzer.analyze(players, dealer);
    }

    private ResultAnalyzer getAnalyzer() {
        return ResultAnalyzer.getInstance();
    }

}
