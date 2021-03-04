package blackjack.controller;

import blackjack.domain.Game;
import blackjack.domain.user.PlayerDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void run() {
        Game game = new Game(InputView.receivePlayerNames());

        initCards(game);
        drawCardsV2(game);
        printResults(game);
    }

    private void initCards(Game game) {
        game.initialCards();
        OutputView.printInitialCards(game.getDealer(), game.getPlayers());
    }

    // 기존 구현 방식
    // Deck이 Controller에 있었음
//    private void drawCards(Deck deck, Game game) {
//        game.getPlayers().forEach(player -> getAdditionalCard((Player) player, deck));
//        OutputView.printDealerDraw(game.askDrawToDealer(deck));
//    }
//
//    private void getAdditionalCard(Player player, Deck deck) {
//        while (player.isHit()) {
//            player.askDraw(InputView.askIfMoreCard(player), deck);
//            OutputView.printPlayerCard(player);
//        }
//    }

    // 로직 개선 방안 1
    private void drawCardsV2(Game game) {
        while (game.isAnyPlayerHit()) {
            PlayerDto hitPlayerDto = game.getAnyHitPlayerDto();
            game.drawCard(hitPlayerDto, InputView.askIfMoreCardV2(hitPlayerDto));
            OutputView.printPlayerCardV2(hitPlayerDto);
        }
        OutputView.printDealerDraw(game.askDrawToDealer());
    }

    private void printResults(Game game) {
        OutputView.printUserResult(game.getResultDTOs());
        OutputView.printWinningResult(game.getWinningResultDTOs());
    }
}

