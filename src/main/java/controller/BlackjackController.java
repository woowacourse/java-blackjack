package controller;

import controller.converter.DomainToTextConverter;
import controller.converter.PlayerResultText;
import domain.BlackjackGame;
import domain.RoundHistory;
import domain.card.TrumpCard;
import domain.participant.Participant;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackjackController {

  private static final DomainToTextConverter converter = DomainToTextConverter.getInstance();

  private final InputView inputView;
  private final OutputView outputView;

  public BlackjackController(final InputView inputView, final OutputView outputView) {
    this.inputView = inputView;
    this.outputView = outputView;
  }

  public void run() {
    final var names = inputView.readPlayerNames();
    final BlackjackGame blackjack = BlackjackGame.from(names);

    startGame(blackjack);
  }

  private void startGame(final BlackjackGame blackjack) {
    initialDael(blackjack);
    deal(blackjack);
    processOpenHandResult(blackjack);
    round(blackjack);
  }

  private void initialDael(final BlackjackGame blackjack) {
    blackjack.initialDeal();
    outputInitialDeal(blackjack);
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
    final TrumpCard firstCard = blackjack.openDealerFirstCard();
    outputView.printDealerHitResult(converter.cardToText(firstCard));
  }


  private void deal(final BlackjackGame blackjack) {
    for (Participant player : blackjack.getPlayers()) {
      processPlayerHits(player, blackjack);
    }
  }

  public void processPlayerHits(final Participant player, final BlackjackGame blackjack) {
    final var name = player.getName();
    while (player.isHit() && inputView.readPlayerAnswer(name)) {
      blackjack.hitByParticipant(player);
      final List<TrumpCard> hand = blackjack.getCards(player);
      outputView.printPlayerHand(player.getName(), converter.handToText(hand));
    }
  }


  private void processOpenHandResult(final BlackjackGame blackjack) {
    final List<Participant> participants = blackjack.getParticipants();
    for (Participant participant : participants) {
      outputPlayerHandResult(participant);
    }
  }

  private void outputPlayerHandResult(final Participant participants) {
    final List<String> convertedCards = converter.participantCardToText(participants);
    final var score = participants.calculateScore();
    outputView.printPlayerRoundResult(participants.getName(), convertedCards, score);
  }

  private void round(final BlackjackGame blackjack) {
    outputView.printRoundResultIntroduce();
    final RoundHistory roundHistory = blackjack.writeRoundHistory();
    final var dealerRoundHistory = roundHistory.getDealerResult();
    outputView.printRoundResultOnDealer(dealerRoundHistory);

    roundHistory.getHistory()
        .forEach((name, isWin) -> {
          final var result = PlayerResultText.convertBooleanToText(isWin);
          outputView.printRoundResultOnPlayers(name, result);
        });
  }
}
