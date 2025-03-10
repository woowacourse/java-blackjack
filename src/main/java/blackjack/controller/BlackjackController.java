package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardDump;
import blackjack.domain.participant.Dealer;
import blackjack.domain.GameReport;
import blackjack.domain.GameResult;
import blackjack.domain.participant.Player;
import blackjack.dto.DistributedCardDto;
import blackjack.dto.FinalResultDto;
import blackjack.util.RetryUtil;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;
    private final CardDump cardDump;

    public BlackjackController(InputView inputView, OutputView outputView, CardDump cardDump) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.cardDump = cardDump;
    }

    public void run() {
        Dealer dealer = new Dealer(new CardDeck());
        BlackjackGame game = new BlackjackGame(createPlayers(), dealer, cardDump);

        game.distributeInitialCards();
        outputView.displayCardDistribution(
                DistributedCardDto.from(dealer),
                DistributedCardDto.fromPlayers(game.getPlayers())
        );

        for (Player player : game.getPlayers()) {
            processPlayerTurn(player, game);
        }
        processDealerTurn(game);

        outputView.displayFinalCardStatus(
                FinalResultDto.from(dealer),
                FinalResultDto.fromPlayers(game.getPlayers()));

        generateGameResultAndDisplay(dealer, game.getPlayers());
    }

    private List<Player> createPlayers() {
        String[] playerNames = inputView.readPlayerName();

        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(playerName, new CardDeck()));
        }
        return players;
    }

    private void processPlayerTurn(Player player, BlackjackGame game) {
        while (game.canHit(player)) {
            HitOption wantHit = getHitOption(player);
            if (wantHit.isNo()) {
                break;
            }
            game.playerHit(player);
            outputView.displayCardInfo(DistributedCardDto.from(player));
            if (player.isBust()) {
                outputView.displayBustNotice();
                break;
            }
        }
    }

    private void processDealerTurn(BlackjackGame game) {
        int beforeDealerCardSize = game.getDealer().getCardSize();
        game.dealerTurn();
        int afterDealerCardSize = game.getDealer().getCardSize();

        int numberOfHit =  afterDealerCardSize - beforeDealerCardSize;
        outputView.displayDealerTurnResult(numberOfHit);
    }

    private HitOption getHitOption(Player player) {
        return RetryUtil.getReturnWithRetry(() -> {
            String answer = inputView.readOneMoreCard(player.getName());
            return HitOption.from(answer);
        });
    }

    private void generateGameResultAndDisplay(final Dealer dealer, final List<Player> players) {
        GameReport gameReport = new GameReport();
        Map<GameResult, Integer> dealerResult = gameReport.getDealerResult(dealer, players);
        outputView.displayDealerResult(dealerResult);
        for (Player player : players) {
            GameResult playerResult = gameReport.getPlayerResult(player, dealer);
            outputView.displayPlayerResult(player, playerResult);
        }
    }
}
