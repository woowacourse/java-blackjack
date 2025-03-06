package blackjack.controller;

import blackjack.domain.CardDeck;
import blackjack.domain.CardDump;
import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.GameRule;
import blackjack.domain.Player;
import blackjack.dto.DistributedCardDto;
import blackjack.dto.FinalResultDto;
import blackjack.view.InputVIew;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackController {
    private final InputVIew inputView;
    private final OutputView outputView;
    private final CardDump cardDump;
    private final GameRule gameRule;

    public BlackjackController() {
        this.inputView = new InputVIew();
        this.outputView = new OutputView();
        this.cardDump = new CardDump();
        this.gameRule = new GameRule();
    }

    public void run() {
        List<Player> players = distributeCardToPlayers();
        Dealer dealer = distrubuteCardToDealer();
        outputView.displayDistributedCardStatus(DistributedCardDto.from(dealer), players.stream().map(
                DistributedCardDto::from).toList());

        hitExtraCardForPlayers(players);
        hitExtraCardForDealer(dealer);
        outputView.displayFinalCardStatus(FinalResultDto.from(dealer), players.stream().map(
                FinalResultDto::from).toList());

        getGameResultAndDisplay(dealer, players);
    }

    private List<Player> distributeCardToPlayers() {
        String[] playerNames = inputView.readPlayerName();
        List<Player> players = new ArrayList<>();

        for (String playerName : playerNames) {
            CardDeck cardDeck = new CardDeck();
            cardDeck.add(cardDump.drawCard());
            cardDeck.add(cardDump.drawCard());

            Player player = new Player(playerName, cardDeck, cardDump);
            players.add(player);
        }
        return players;
    }

    private Dealer distrubuteCardToDealer() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.add(cardDump.drawCard());
        cardDeck.add(cardDump.drawCard());
        return new Dealer(cardDeck, cardDump);
    }

    private void hitExtraCardForPlayers(List<Player> players) {
        for (Player player : players) {
            checkPlayerHit(player);
        }
    }

    private void checkPlayerHit(Player player) {
        while (player.canTakeExtraCard()) {
            String answer = inputView.readOneMoreCard(player.getName());
            HitOption option = HitOption.from(answer);
            if (option.isNo()) {
                break;
            }
            player.addCard();
            outputView.displayCardInfo(DistributedCardDto.from(player));
        }
    }

    private void hitExtraCardForDealer(Dealer dealer) {
        while (dealer.hasTakenExtraCard()) {
            outputView.displayExtraDealerCardStatus();
        }
    }

    private void getGameResultAndDisplay(Dealer dealer, List<Player> players) {
        Map<GameResult, Integer> dealerResult = getDealerFinalResult(dealer, players);
        outputView.displayDealerResult(dealerResult);
        for (Player player : players) {
            GameResult playerResult = getGameResultFromPlayer(player, dealer);
            outputView.displayPlayerResult(player, playerResult);
        }
    }

    private Map<GameResult, Integer> getDealerFinalResult(Dealer dealer, List<Player> players) {
        Map<GameResult, Integer> gameFinalResult = new HashMap<>();
        for (Player player : players) {
            GameResult result = gameRule.evaluateDealerWin(player, dealer);
            gameFinalResult.put(result, gameFinalResult.getOrDefault(result, 0) + 1);
        }
        return gameFinalResult;
    }

    private GameResult getGameResultFromPlayer(Player player, Dealer dealer) {
        return gameRule.evaluatePlayerWin(player, dealer);
    }
}
