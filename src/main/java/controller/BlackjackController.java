package controller;

import model.blackjackgame.BlackjackGame;
import model.blackjackgame.HitAnswer;
import model.card.Card;
import model.card.CardDispenser;
import model.card.Cards;
import model.dealer.Dealer;
import model.player.Player;
import model.player.Players;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final CardDispenser cardDispenser = CardDispenser.getCardDispenser();

    public void run() {
        BlackjackGame blackjackGame = start();
        setting(blackjackGame);
        executeGame(blackjackGame);
    }

    private BlackjackGame start() {
        Players players = Players.from(InputView.askPlayerNames());
        Dealer dealer = new Dealer();
        return new BlackjackGame(dealer, players);
    }

    private void setting(BlackjackGame blackjackGame) {
        int cardCount = blackjackGame.countSettingCard();
        Cards cardsForSetting = cardDispenser.dispenseCards(cardCount);

        blackjackGame.distributeCardsForSetting(cardsForSetting);
        OutputView.printCardsAfterSetting(blackjackGame);
    }

    private void executeGame(BlackjackGame blackjackGame) {
        Players players = blackjackGame.getPlayers();
        for (Player player : players.getPlayers()) {
            continueHit(blackjackGame, player);
        }
        Card card = cardDispenser.dispenseCard();
        if (blackjackGame.hitForDealer(card)) {
            OutputView.printAfterDealerHit();
        }
    }

    private void continueHit(BlackjackGame blackjackGame, Player player) {
        while (player.isPossibleAddCard()) {
            HitAnswer hitAnswer = HitAnswer.of(InputView.askHitAnswer(player));
            if (hitAnswer.isStay()) {
                break;
            }
            player = hit(blackjackGame, player);
            OutputView.printPlayerCard(player);
        }
    }

    private Player hit(BlackjackGame blackjackGame, Player player) {
        Card card = cardDispenser.dispenseCard();
        return blackjackGame.hitForPlayer(player, card);
    }
}
