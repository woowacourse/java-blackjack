package controller;

import controller.converter.DomainToTextConverter;
import domain.BlackjackGame;
import domain.card.TrumpCard;
import domain.participant.Participant;
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
    final var participantsNames = inputView.readPlayerNames();
    final var blackjack = BlackjackGame.from(participantsNames);

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
    outputDealerInitialDeal(blackjack);

    final var convertedPlayers = converter.playersToEntries(players);
    outputView.printPlayersHand(convertedPlayers);
  }

  private void outputDealerInitialDeal(final BlackjackGame blackjack) {
    final var firstCard = blackjack.openDealerFirstCard();
    final var card = converter.cardToText(firstCard);
    outputView.printDealerHitResult(card);
  }


  private void deal(final BlackjackGame blackjack) {
    for (final var player : blackjack.getPlayers()) {
      processPlayerHits(player, blackjack);
    }
    processDealerHits(blackjack);
  }


  private void processPlayerHits(
      Participant player,
      final BlackjackGame blackjack
  ) {
    while (player.isHit() && inputView.readPlayerAnswer(player.getName())) {
      player = blackjack.hitByParticipant(player);
      final List<TrumpCard> hand = player.getCards();
      final var cards = converter.handToText(hand);
      outputView.printPlayerHand(player.getName(), cards);
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

  private void outputParticipantHandResult(final Participant participant) {
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
