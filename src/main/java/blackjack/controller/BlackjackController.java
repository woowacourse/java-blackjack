package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.answer.Answer;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.result.BettingResult;
import blackjack.domain.result.GameResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    private BlackjackGame blackjackGame;

    public void play() {
        blackjackGame = new BlackjackGame(Deck.create(), new Dealer(), participatePlayers());
        inputBettingAmount();
        initStartingCards();

        hitOrStand();

        GameResult gameResult = new GameResult(blackjackGame.judgeResult());
        BettingResult bettingResult = gameResult.calculateRevenue();
        OutputView.printTotalResult(blackjackGame.getDealer(), bettingResult);
    }

    private Players participatePlayers() {
        try {
            return new Players(InputView.inputPlayers());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return participatePlayers();
        }
    }

    private void inputBettingAmount() {
        blackjackGame.getPlayers()
            .forEach(this::betAmount);
    }

    private void betAmount(Player player) {
        try {
            player.betAmount(InputView.inputBettingAmount(player));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            betAmount(player);
        }
    }

    private void initStartingCards() {
        blackjackGame.initStartingCards();
        OutputView.printInitCard(blackjackGame.getDealer(), blackjackGame.getPlayers());
    }

    private void hitOrStand() {
        hitOrStandPlayers();
        hitOrStandDealer();
        OutputView.printDrawResult(blackjackGame.getDealer(), blackjackGame.getPlayers());
    }

    private void hitOrStandPlayers() {
        blackjackGame.getPlayers()
            .forEach(this::hitOrStandPlayer);
    }

    private void hitOrStandPlayer(Player player) {
        while (player.canDraw() && isHit(player)) {
            blackjackGame.drawCard(player);
            OutputView.printCard(player);
        }
    }

    private boolean isHit(Player player) {
        try {
            Answer answer = Answer.of(InputView.inputHitOrStand(player));
            return answer.isHit();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return isHit(player);
        }
    }

    private void hitOrStandDealer() {
        while (blackjackGame.getDealer().canDraw()) {
            blackjackGame.drawCard(blackjackGame.getDealer());
            OutputView.printDealerDrawCardMessage();
        }
    }
}
