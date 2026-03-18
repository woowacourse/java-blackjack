package controller;

import domain.card.Card;
import domain.game.BlackJackGame;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.game.GameResult;
import domain.participant.Player;
import domain.participant.Players;
import domain.state.Outcome;
import dto.domain.PlayerNameAndBettingDto;
import dto.view.DealerStatDto;
import dto.view.ParticipantProfitDto;
import dto.view.ParticipantResultDto;
import dto.view.ParticipantStatsDto;
import dto.view.PlayerCardsDto;
import dto.view.PlayerOutcomeDto;
import dto.view.PlayerProfitDto;
import dto.view.ResultDto;
import dto.view.StartBlackJackDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import util.NameParser;
import view.InputView;
import view.ResultView;

public class BlackJackController {
    private final InputView inputView;
    private final ResultView resultView;

    public BlackJackController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        final List<String> participantNames = NameParser.makeNameList(inputView.getPlayerNames());
        final List<PlayerNameAndBettingDto> playersInfo = inputView.getPlayerBetting(participantNames);
        final BlackJackGame game = BlackJackGame.startGame(playersInfo);
        final Players players = game.players();
        final Dealer dealer = game.dealer();
        final Cards cards = game.cards();

        resultView.printGameStartMessage(toStartBlackJackDto(players, dealer));
        drawPlayersTurn(players, cards);
        drawDealerTurn(dealer, cards);
        printResult(game);
    }

    private void drawPlayersTurn(Players players, Cards cards) {
        players.forEachPlayer(player -> drawPlayerTurn(player, cards));
    }

    private void drawPlayerTurn(Player player, Cards cards) {
        while (player.canHit()) {
            final boolean hit = inputView.askHitOrStand(player);
            if (!hit) {
                break;
            }
            hit(player, cards);
        }
    }

    private void hit(Player player, Cards cards) {
        player.hit(cards);
        resultView.printPlayerCards(player.getName(), resultView.joinCardNames(player.getCardList()));
        printPlayerBustIfNeeded(player);
    }

    private void printPlayerBustIfNeeded(Player player) {
        if (player.checkBust()) {
            resultView.printPlayerBust(player.getName());
        }
    }

    private void drawDealerTurn(Dealer dealer, Cards cards) {
        resultView.printLineBreak();
        while (dealer.shouldDrawCard()) {
            dealer.drawCard(cards);
            resultView.printDealerDrawMessage();
        }
        resultView.printLineBreak();
    }

    private void printResult(BlackJackGame game) {
        final Players players = game.players();
        final Dealer dealer = game.dealer();
        final GameResult gameResult = game.calculateResult();
        printGameResult(players, dealer);
        resultView.printWinner(toParticipantStatsDto(players, gameResult));
        resultView.printFinalProfit(toParticipantProfitDto(players));
    }

    private void printGameResult(Players players, Dealer dealer) {
        resultView.printResult(toResultDto(players, dealer));
        if (dealer.checkBust()) {
            resultView.printDealerBust();
        }
    }

    private StartBlackJackDto toStartBlackJackDto(Players players, Dealer dealer) {
        return new StartBlackJackDto(
                formatCardName(dealer.getCardList().get(0)),
                toPlayerCardsDtos(players)
        );
    }

    private List<PlayerCardsDto> toPlayerCardsDtos(Players players) {
        final List<PlayerCardsDto> playerCardsDtos = new ArrayList<>();
        players.forEachPlayer(player -> playerCardsDtos.add(new PlayerCardsDto(
                player.getName(),
                toCardNames(player.getCardList())
        )));
        return playerCardsDtos;
    }

    private ResultDto toResultDto(Players players, Dealer dealer) {
        final ParticipantResultDto dealerResult = new ParticipantResultDto(
                "딜러",
                resultView.joinCardNames(dealer.getCardList()),
                dealer.getScore()
        );
        final List<ParticipantResultDto> playerResults = new ArrayList<>();
        players.forEachPlayer(player -> playerResults.add(new ParticipantResultDto(
                player.getName(),
                resultView.joinCardNames(player.getCardList()),
                player.getScore()
        )));
        return new ResultDto(dealerResult, playerResults);
    }

    private ParticipantStatsDto toParticipantStatsDto(Players players, GameResult gameResult) {
        return new ParticipantStatsDto(
                toDealerStatDto(gameResult),
                toPlayerOutcomeDtos(players, gameResult)
        );
    }

    private DealerStatDto toDealerStatDto(GameResult gameResult) {
        final int dealerWinCount = gameResult.getDealerCount(Outcome.DEFAULT_WIN)
                + gameResult.getDealerCount(Outcome.BLACKJACK_WIN);
        final int dealerLoseCount = gameResult.getDealerCount(Outcome.LOSE);
        final int dealerDrawCount = gameResult.getDealerCount(Outcome.DRAW);
        return new DealerStatDto(dealerWinCount, dealerLoseCount, dealerDrawCount);
    }

    private List<PlayerOutcomeDto> toPlayerOutcomeDtos(Players players, GameResult gameResult) {
        final List<PlayerOutcomeDto> playerOutcomeDtos = new ArrayList<>();
        players.forEachPlayer(player -> playerOutcomeDtos.add(new PlayerOutcomeDto(
                player.getName(),
                gameResult.getPlayerOutcome(player.getName()).result()
        )));
        return playerOutcomeDtos;
    }

    private ParticipantProfitDto toParticipantProfitDto(Players players) {
        final List<PlayerProfitDto> playerProfitDtos = new ArrayList<>();
        players.forEachPlayer(player -> playerProfitDtos.add(new PlayerProfitDto(
                player.getName(),
                player.getBalance()
        )));
        final int dealerProfit = -playerProfitDtos.stream()
                .mapToInt(PlayerProfitDto::profit)
                .sum();
        return new ParticipantProfitDto(dealerProfit, playerProfitDtos);
    }

    private List<String> toCardNames(List<Card> cards) {
        return cards.stream()
                .map(this::formatCardName)
                .collect(Collectors.toList());
    }

    private String formatCardName(Card card) {
        return card.getRank().symbol() + card.getSuit().suit();
    }
}
