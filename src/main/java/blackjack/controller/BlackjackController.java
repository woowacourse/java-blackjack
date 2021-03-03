package blackjack.controller;

import static java.util.stream.Collectors.toList;

import blackjack.domain.Game;
import blackjack.domain.GameResult;
import blackjack.domain.Status;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamers;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BlackjackController {

  private final Cards cards;

  public BlackjackController(Cards cards) {
    this.cards = cards;
  }

  public void run() {
    Dealer dealer = new Dealer();
    Gamers gamers = new Gamers(InputView.getGamerNamesFromUser());
    Game game = new Game(cards, dealer, gamers);

    OutputView.printDrawResult(game.getGamers());
    OutputView.printDealerAndPlayersDeckState(dealer, gamers.getGamers());

    for (Player gamer : gamers.getGamers()) {
      drawCardWhileNo(gamer);
    }

    if(dealer.isDrawable()) {
      OutputView.dealerDrawsCard();
    }

    List<Player> allPlayers = new ArrayList<>(Collections.singletonList(dealer));
    allPlayers.addAll(gamers.getGamers());

    OutputView.printCurrentDeckAndScore(allPlayers.stream().map(PlayerResultDto::new).collect(toList()));

    GameResult result = game.getResult();
    OutputView.printResult(result);
  }

  private void drawCardWhileNo(Player gamer) {
    boolean yesOrNo = InputView.getYesOrNo(gamer.getName());
    if (!yesOrNo) {
      return;
    }

    gamer.addCardToDeck(cards.next());
    Status status = gamer.getStatus();

    OutputView.printPlayersDeckState(gamer);
    if (status == Status.HIT) {
      drawCardWhileNo(gamer);
    }
  }

}
