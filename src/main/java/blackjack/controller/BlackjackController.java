package blackjack.controller;

import blackjack.model.Answer;
import blackjack.model.BlackjackResult;
import blackjack.model.Card;
import blackjack.model.Dealer;
import blackjack.model.Deck;
import blackjack.model.Player;
import blackjack.model.Players;
import blackjack.model.ResultJudgement;
import blackjack.util.RetryUtil;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.CardDto;
import blackjack.view.dto.DealerScoreDto;
import blackjack.view.dto.PlayerDto;
import blackjack.view.dto.PlayerScoreDto;
import blackjack.view.dto.ResultDto;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    private final ResultJudgement resultJudgement;

    public BlackjackController(
            InputView inputView,
            OutputView outputView,
            ResultJudgement resultJudgement
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.resultJudgement = resultJudgement;
    }

    public void run() {
        Players players = retryOnIllegalArgument(this::readPlayers);
        Dealer dealer = new Dealer();
        Deck deck = Deck.unique();

        initialDeal(players, dealer, deck);
        retryOnIllegalArgument(() -> hit(players, dealer, deck));
        printScore(players, dealer);
        printResult(players, dealer);
    }

    private <T> T retryOnIllegalArgument(Supplier<T> retryableAction) {
        return RetryUtil.retryOnInvalidInput(retryableAction, outputView::printErrorMessage);
    }

    private void retryOnIllegalArgument(Runnable retryableAction) {
        RetryUtil.retryOnInvalidInput(retryableAction, outputView::printErrorMessage);
    }

    private Players readPlayers() {
        String rawPlayerNames = inputView.readPlayerNames();

        return Players.from(rawPlayerNames);
    }

    private void initialDeal(Players players, Dealer dealer, Deck deck) {
        deal(players, dealer, deck);
        deal(players, dealer, deck);

        outputView.printInitialDeal(
                cardsToDtos(dealer.getCards()),
                playersToDots(players)
        );
    }

    private void deal(Players players, Dealer dealer, Deck deck) {
        for (Player player : players) {
            player.addCard(deck.draw());
        }
        dealer.addCard(deck.draw());
    }

    private void hit(Players players, Dealer dealer, Deck deck) {
        for (Player player : players) {
            playerHit(player, deck);
        }
        outputView.printEmptyLine();

        dealerHit(dealer, deck);
    }

    private void playerHit(Player player, Deck deck) {
        while (!player.isBust() && inputView.askHit(player.getName()) == Answer.YES) {
            player.addCard(deck.draw());
            outputView.printPlayerCards(player.getName(), cardsToDtos(player.getCards()));
        }
    }

    private void dealerHit(Dealer dealer, Deck deck) {
        while (dealer.shouldDraw()) {
            dealer.addCard(deck.draw());
            outputView.printDealerHit();
        }
    }

    private void printScore(Players players, Dealer dealer) {
        DealerScoreDto dealerDto = DealerScoreDto.from(dealer);
        List<PlayerScoreDto> playerDtos = players.stream()
                .map(PlayerScoreDto::from)
                .toList();

        outputView.printScore(dealerDto, playerDtos);
    }

    private void printResult(Players players, Dealer dealer) {
        List<ResultDto> resultDtos = new ArrayList<>();
        for (Player player : players) {
            BlackjackResult result = resultJudgement.judge(player.getHand(), dealer.getHand());
            resultDtos.add(new ResultDto(player.getName(), result));
        }

        outputView.printResult(resultDtos);
    }

    private List<CardDto> cardsToDtos(List<Card> cards) {
        return cards.stream()
                .map(CardDto::from)
                .toList();
    }

    private List<PlayerDto> playersToDots(Players players) {
        return players.stream()
                .map(PlayerDto::from)
                .toList();
    }
}
