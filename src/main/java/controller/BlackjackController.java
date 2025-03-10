package controller;

import controller.converter.DomainToTextConverter;
import domain.BlackjackGame;
import domain.card.Hand;
import domain.card.TrumpCard;
import domain.participant.Participant;
import java.util.List;
import java.util.Map.Entry;
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
    final BlackjackGame blackjack = BlackjackGame.generate();
    final var names = inputView.readPlayerNames();
    blackjack.addParticipants(names);

    startGame(blackjack);
  }

  private void startGame(final BlackjackGame blackjack) {
    initialDael(blackjack);
    deal(blackjack);
    openHandResult(blackjack);
    round(blackjack);
    result(blackjack);
  }

  private void initialDael(final BlackjackGame blackjack) {
    final var players = blackjack.getPlayers();
    var playerNames = converter.playersToNames(players);
    outputView.printDealIntroduce(playerNames);

    blackjack.initialDeal();
    outputDealerInitialDealResult(blackjack);

    List<Entry<String, List<String>>> convertedPlayers = converter.playersToEntries(players);
    outputView.printPlayersHand(convertedPlayers);
  }

  private void outputDealerInitialDealResult(final BlackjackGame blackjack) {
    final var dealer = blackjack.getDealer();
    final Hand hand = dealer.getHand();
    final TrumpCard firstCard = hand.getCards().getFirst();
    outputView.printDealerHitResult(converter.cardToText(firstCard));
  }

  private void deal(final BlackjackGame blackjack) {
    playerPickCard(blackjack);
    dealerPickCard(blackjack);
  }

  private void playerPickCard(final BlackjackGame blackjack) {
    final var players = blackjack.getPlayers();
    players.forEach(player -> ifCanPickCard(blackjack, player));
  }

  private void ifCanPickCard(final BlackjackGame blackjack, Participant player) {
    final var name = player.getName();
    while (player.isHit() && inputView.readPlayerAnswer(name)) {
      final var card = blackjack.deal();
      player.hit(card);
      outputView.printPlayerHand(name, converter.participantCardToText(player));
    }
  }

  private void dealerPickCard(final BlackjackGame blackjack) {
    final var dealer = blackjack.getDealer();

    while (dealer.isHit()) {
      final var card = blackjack.deal();
      dealer.hit(card);
      outputView.printDealerHit();
    }
  }

  private void openHandResult(final BlackjackGame blackjack) {
    final var participants = blackjack.getParticipants();
    participants.forEach(this::outputPlayerHandResult);
  }

  private void outputPlayerHandResult(final Participant participants) {
    final List<String> convertedCards = converter.participantCardToText(participants);
    final int score = participants.calculateScore();
    outputView.printPlayerRoundResult(participants.getName(), convertedCards, score);
  }

  private void round(final BlackjackGame blackjack) {
  }

  private void result(BlackjackGame blackjack) {
  }
}
