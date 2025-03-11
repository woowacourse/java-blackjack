package controller;

import controller.converter.DomainToTextConverter;
import controller.converter.PlayerResultText;
import domain.BlackjackGame;
import domain.RoundHistory;
import domain.card.Hand;
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
  }

  private void initialDael(final BlackjackGame blackjack) {
    blackjack.initialDeal();
    outputInitialDeal(blackjack);
  }

  private void outputInitialDeal(BlackjackGame blackjack) {
    final var players = blackjack.getPlayers();
    final var playerNames = converter.playersToNames(players);
    outputView.printDealIntroduce(playerNames);
    outputDealerInitialDealResult(blackjack);
    final var convertedPlayers = converter.playersToEntries(players);
    outputView.printPlayersHand(convertedPlayers);
  }

  private void outputDealerInitialDealResult(final BlackjackGame blackjack) {
    final var dealer = blackjack.getDealer();
    final Hand hand = dealer.getHand();
    final TrumpCard firstCard = hand.getCards().getFirst();
    outputView.printDealerHitResult(converter.cardToText(firstCard));
  }

  private void deal(final BlackjackGame blackjack) {
    hitByPlayer(blackjack);
    hitByDealer(blackjack);
  }

  private void hitByPlayer(final BlackjackGame blackjack) {
    final var players = blackjack.getPlayers();
    players.forEach(player -> processPlayerHits(blackjack, player));
  }

  private void processPlayerHits(final BlackjackGame blackjack, final Participant player) {
    final var name = player.getName();
    while (player.isHit() && inputView.readPlayerAnswer(name)) {
      hitByParticipant(blackjack, player);
      outputPlayerHand(player);
    }
  }

  private void hitByParticipant(final BlackjackGame blackjack, final Participant participant) {
    final var card = blackjack.getCardForDeal();
    participant.hit(card);
  }

  private void outputPlayerHand(final Participant player) {
    final var name = player.getName();
    outputView.printPlayerHand(name, converter.participantCardToText(player));
  }

  private void hitByDealer(final BlackjackGame blackjack) {
    final var dealer = blackjack.getDealer();

    while (dealer.isHit()) {
      hitByParticipant(blackjack, dealer);
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
