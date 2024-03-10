package controller;

import model.blackjackgame.BlackjackGame;
import model.blackjackgame.GameResult;
import model.blackjackgame.HitAnswer;
import model.card.Card;
import model.card.RandomCardPicker;
import model.card.Cards;
import model.dealer.Dealer;
import model.player.Player;
import model.player.Players;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final RandomCardPicker randomCardPicker = RandomCardPicker.getRandomCardPicker();

    public void run() {
        Players players = InputView.preparePlayers();
        Dealer dealer = new Dealer();
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players);

        initGame(blackjackGame);
        executeGame(blackjackGame);
        finishGameWithResult(blackjackGame);
    }

    private void initGame(BlackjackGame blackjackGame) {
        int cardCount = blackjackGame.determineInitCardCount();
        Cards cards = randomCardPicker.pickCards(cardCount);

        blackjackGame.initCards(cards);
        OutputView.printInitCards(blackjackGame);
    }

    private void executeGame(BlackjackGame blackjackGame) {
        Players players = blackjackGame.getPlayers();
        for (Player player : players.getElements()) {
            continueHit(blackjackGame, player);
        }
        Card card = randomCardPicker.pickCard();
        if (blackjackGame.hitForDealer(card)) {
            OutputView.printAfterDealerHit();
        }
    }

    private void continueHit(BlackjackGame blackjackGame, Player player) {
        HitAnswer hitAnswer = new HitAnswer(true);
        if (player.isPossibleAddCard()) {
            hitAnswer = InputView.prepareHitAnswer(player);
        }
        while (player.isPossibleAddCard() && hitAnswer.isHit()) {
            player = hit(blackjackGame, player);
            OutputView.printPlayerCard(player);
            hitAnswer = InputView.prepareHitAnswer(player);
        }
    }

    private Player hit(BlackjackGame blackjackGame, Player player) {
        Card card = randomCardPicker.pickCard();
        return blackjackGame.hitForPlayer(player, card);
    }

    private void finishGameWithResult(BlackjackGame blackjackGame) {
        Dealer dealer = blackjackGame.getDealer();
        Players players = blackjackGame.getPlayers();
        GameResult gameResult = GameResult.of(dealer, players);
        OutputView.printFinalScore(dealer, players, gameResult);
        OutputView.printGameResult(gameResult);
    }
}
