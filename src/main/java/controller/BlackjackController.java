package controller;

import controller.converter.DomainToTextConverter;
import domain.BlackjackGame;
import domain.card.TrumpCard;
import domain.participant.Participant;
import domain.participant.Role;
import java.util.List;
import view.InputView;
import view.OutputView;

public final class BlackjackController {

  private static final DomainToTextConverter converter = DomainToTextConverter.getInstance();

  private final InputView inputView;
  private final OutputView outputView;

  public BlackjackController(final InputView inputView, final OutputView outputView) {
    this.inputView = inputView;
    this.outputView = outputView;
  }

  public void run() {
    final var participants = inputView.readPlayerNames();
    final var blackjack = BlackjackGame.from(participants);

    startGame(blackjack);
  }

  private void startGame(final BlackjackGame blackjack) {
    outputInitialDeal(blackjack);
    deal(blackjack);
    openHandResult(blackjack);
    round(blackjack);
  }

  private void outputInitialDeal(final BlackjackGame blackjack) {
    final var players = blackjack.getPlayers();
    final var playerNames = converter.playersToNames(players);

    outputView.printDealIntroduce(playerNames);
    outputDealerInitialDealResult(blackjack);

    final var convertedPlayers = converter.playersToEntries(players);
    outputView.printPlayersHand(convertedPlayers);
  }

  private void outputDealerInitialDealResult(final BlackjackGame blackjack) {
    final var firstCard = blackjack.openDealerFirstCard();
    outputView.printDealerHitResult(converter.cardToText(firstCard));
  }


  private void deal(final BlackjackGame blackjack) {
    for (final var player : blackjack.getPlayers()) {
      processPlayerHits(player, blackjack);
    }
    processDealerHits(blackjack);
  }


  private void processPlayerHits(
      Participant<? extends Role> player,
      final BlackjackGame blackjack
  ) {
    final var name = player.getName();
    while (player.isHit() && inputView.readPlayerAnswer(name)) {
      player = blackjack.hitByParticipant(player);
      final List<TrumpCard> hand = player.getCards();
      outputView.printPlayerHand(player.getName(), converter.handToText(hand));
    }
  }

  private void processDealerHits(final BlackjackGame blackjack) {
    var dealer = blackjack.getDealer();
    while (dealer.isHit()) {
      outputView.printDealerHit();
      dealer = blackjack.hitByParticipant(dealer);
    }
  }

  private void openHandResult(final BlackjackGame blackjack) {
    final var participants = blackjack.getParticipant();
    for (final var participant : participants) {
      outputParticipantHandResult(participant);
    }
  }

  private void outputParticipantHandResult(final Participant<? extends Role> participant) {
    final var convertedCards = converter.participantCardToText(participant.getCards());
    final var score = participant.calculateScore();
    outputView.printParticipantRoundResult(participant.getName(), convertedCards, score.value());
  }

  private void round(final BlackjackGame blackjack) {
    outputView.printRoundResultIntroduce();
    final var roundHistory = blackjack.writeRoundHistory();
    final var allocated = roundHistory.allocate();
    final var dealer = blackjack.getDealer();
    final var allocatedDealer = dealer.getBet().seekAllocationTotalDifference(allocated);
    outputView.printRoundResultOnDealer(allocatedDealer);
    allocated.forEach(outputView::printRoundResultOnPlayers);
  }
}
