package blackjack.controller;

import blackjack.domain.game.Answer;
import blackjack.domain.game.CardDeck;
import blackjack.domain.game.Dealer;
import blackjack.domain.game.Player;
import blackjack.domain.game.Players;
import blackjack.domain.result.Result;
import blackjack.dto.GamerDto;
import blackjack.view.Enter;
import blackjack.view.Enterable;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Blackjack {

    private static final Enterable enterable = new Enter();

    public void run() {
        Dealer dealer = new Dealer();

        OutputView.printPlayerNameInstruction();
        Players players = createPlayers();
        Result result = new Result(players);

        drawCards(dealer, players, result);
        showWinner(dealer, players, result);
    }

    private Players createPlayers() {
        try {
            return new Players(InputView.inputPlayerNames(enterable));
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            return createPlayers();
        }
    }

    // TODO: draw 전략 패턴 적용해보기
    private void drawCards(final Dealer dealer, final Players players, final Result result) {
        CardDeck cardDeck = new CardDeck();
        dealCards(cardDeck, dealer, players);

        if (result.isKeepPlaying(dealer)) {
            drawCardToPlayers(players, cardDeck);
            drawCardToDealer(dealer, cardDeck);
        }
    }

    private void dealCards(final CardDeck cardDeck, final Dealer dealer, final Players players) {
        OutputView.printDealCards(dealer.getName(), players.getNames());
        dealer.dealCards(cardDeck.pickInit());
        for (Player player : players.getPlayers()) {
            player.dealCards(cardDeck.pickInit());
        }
        OutputView.printInitCards(dealer, players);
    }

    private void drawCardToPlayers(final Players players, final CardDeck cardDeck) {
        for (Player player : players.getPlayers()) {
            drawCardToPlayer(player, cardDeck);
        }
        OutputView.printNewLine();
    }

    private void drawCardToPlayer(final Player player, final CardDeck cardDeck) {
        while (player.isDrawable() && isDrawing(player)) {
            player.drawCard(cardDeck.pick());
            // TODO: 도메인 객체에 뷰 함수 전달?
            OutputView.printCards(player.getName(), GamerDto.getCards(player));
        }
    }

    private boolean isDrawing(final Player player) {
        try {
            OutputView.printDrawInstruction(player.getName());
            String input = InputView.inputDrawingAnswer(enterable);
            return Answer.isDraw(input);
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            return isDrawing(player);
        }
    }

    private void drawCardToDealer(final Dealer dealer, final CardDeck cardDeck) {
        while (dealer.isDrawable()) {
            dealer.drawCard(cardDeck.pick());
            OutputView.printDrawDealer(dealer.getName(), Dealer.RECEIVED_MAXIMUM);
        }
        OutputView.printNewLine();
    }

    private void showWinner(final Dealer dealer, final Players players, final Result result) {
        OutputView.printTotalScore(dealer, players);
        result.compete(dealer);
        OutputView.printResult(dealer, result, players);
    }
}
