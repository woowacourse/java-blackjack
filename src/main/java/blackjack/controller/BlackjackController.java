package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.card.CardHand;
import blackjack.domain.card.CardDump;
import blackjack.domain.participant.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.participant.Player;
import blackjack.dto.CardInfoDto;
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
        Dealer dealer = new Dealer(new CardHand());
        BlackjackGame game = new BlackjackGame(createPlayers(), dealer, cardDump);

        game.distributeInitialCards();
        displayParticipantStartCards(game, dealer);

        for (Player player : game.getPlayers()) {
            processPlayerTurn(player, game);
        }
        processDealerTurn(game);

        displayFinalCardsInfo(dealer, game);
        generateGameResultAndDisplay(game, dealer, game.getPlayers());
    }

    private void displayFinalCardsInfo(Dealer dealer, BlackjackGame game) {
        outputView.displayFinalCardStatus(
                FinalResultDto.from(dealer),
                FinalResultDto.fromPlayers(game.getPlayers()));
    }

    private List<Player> createPlayers() {
        String[] playerNames = inputView.readPlayerName();

        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(playerName, new CardHand()));
        }
        return players;
    }

    private void displayParticipantStartCards(BlackjackGame game, Dealer dealer) {
        List<CardInfoDto> playerStartCardInfos = game.getPlayers().stream()
                .map(player -> CardInfoDto.from(player.getName(), player.getCardDeck()))
                .toList();
        CardInfoDto dealerStartCardInfo = CardInfoDto.from(dealer.getName(), dealer.getCardDeck());
        outputView.displayCardDistribution(
                dealerStartCardInfo,
                playerStartCardInfos
        );
    }

    private void processPlayerTurn(Player player, BlackjackGame game) {
        while (game.canHit(player)) {
            HitOption wantHit = getHitOption(player);
            if (wantHit.isNo()) {
                break;
            }
            game.playerHit(player);
            outputView.displayCardInfo(CardInfoDto.from(player.getName(), player.getCardDeck()));
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

        int numberOfHit = afterDealerCardSize - beforeDealerCardSize;
        outputView.displayDealerTurnResult(numberOfHit);
    }

    private HitOption getHitOption(Player player) {
        return RetryUtil.getReturnWithRetry(() -> {
            String answer = inputView.readOneMoreCard(player.getName());
            return HitOption.from(answer);
        });
    }

    private void generateGameResultAndDisplay(BlackjackGame game, Dealer dealer, List<Player> players) {
        Map<GameResult, Integer> dealerResult = game.getDealerResult(dealer, players);
        outputView.displayDealerResult(dealerResult);
        for (Player player : players) {
            GameResult playerResult = game.getPlayerResult(player, dealer);
            outputView.displayPlayerResult(player, playerResult);
        }
    }
}
