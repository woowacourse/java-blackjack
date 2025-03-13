package controller;

import controller.converter.DomainToTextConverter;
import domain.Bet;
import domain.BlackjackManager;
import domain.RoundHistory;
import domain.card.TrumpCard;
import domain.participant.Participant;
import domain.participant.Role;
import java.util.List;
import java.util.Map;
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
    final Map<String, Bet> participants = inputView.readPlayerNames();
    final BlackjackManager blackjack = BlackjackManager.from(participants);

    startGame(blackjack);
  }

  private void startGame(final BlackjackManager blackjack) {
    outputInitialDeal(blackjack);
    deal(blackjack);
    openHandResult(blackjack);
    round(blackjack);
  }

  private void outputInitialDeal(final BlackjackManager blackjack) {
    final var players = blackjack.getPlayers();
    final var playerNames = converter.playersToNames(players);

    outputView.printDealIntroduce(playerNames);
    outputDealerInitialDealResult(blackjack);

    final var convertedPlayers = converter.playersToEntries(players);
    outputView.printPlayersHand(convertedPlayers);
  }

  private void outputDealerInitialDealResult(final BlackjackManager blackjack) {
    final TrumpCard firstCard = blackjack.openDealerFirstCard();
    outputView.printDealerHitResult(converter.cardToText(firstCard));
  }


  private void deal(final BlackjackManager blackjack) {
    for (final Participant<? extends Role> player : blackjack.getPlayers()) {
      processPlayerHits(player, blackjack);
    }
    processDealerHits(blackjack);
  }


  public void processPlayerHits(Participant<? extends Role> player,
      final BlackjackManager blackjack) {
    final var name = player.getName();
    while (player.isHit() && inputView.readPlayerAnswer(name)) {
      player = blackjack.hitByParticipant(player);
      final List<TrumpCard> hand = player.getCards();
      outputView.printPlayerHand(player.getName(), converter.handToText(hand));
    }
  }

  private void processDealerHits(final BlackjackManager blackjack) {
    var dealer = blackjack.getDealer();
    while (dealer.isHit()) {
      outputView.printDealerHit();
      dealer = blackjack.hitByParticipant(dealer);
    }
  }

  private void openHandResult(final BlackjackManager blackjack) {
    final var participants = blackjack.getParticipant();
    for (final var participant : participants) {
      outputPlayerHandResult(participant);
    }
  }

  private void outputPlayerHandResult(final Participant<? extends Role> participant) {
    final List<String> convertedCards = converter.participantCardToText(participant.getCards());
    final var score = participant.calculateScore();
    outputView.printPlayerRoundResult(participant.getName(), convertedCards, score.value());
  }

  private void round(final BlackjackManager blackjack) {
    outputView.printRoundResultIntroduce();
    final RoundHistory roundHistory = blackjack.writeRoundHistory();
    final var dealerRoundHistory = roundHistory.getDealerResult();
    outputView.printRoundResultOnDealer(dealerRoundHistory);
    final var history = roundHistory.getHistory();
    history.forEach(outputView::printRoundResultOnPlayers);
  }
}
