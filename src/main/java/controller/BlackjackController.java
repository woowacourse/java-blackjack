package controller;

import domain.Dealer;
import domain.Deck;
import domain.Hand;
import domain.Participant;
import domain.Player;
import domain.TrumpCard;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class BlackjackController {

  private final InputView inputView;
  private final OutputView outputView;

  public BlackjackController(final InputView inputView, final OutputView outputView) {
    this.inputView = inputView;
    this.outputView = outputView;
  }

  public void run() {
    final List<Player> players = inputPlayers();
    final Dealer dealer = new Dealer();
    final Deck deck = Deck.generateShuffledDeck();
    startGame(players, dealer, deck);
  }

  private void startGame(final List<Player> players, final Dealer dealer, final Deck deck) {
    handOutCards(players, dealer, deck);
    outputPickCard(players, dealer, deck);
    outputHandResult(dealer, players);
    duel(dealer, players);
    outputDuelResult(players, dealer);
  }

  private void handOutCards(final List<Player> players, final Dealer dealer, final Deck deck) {
    players.forEach(player -> player.initialDeal(deck));
    dealer.initialDeal(deck);
    outputHandOut(players, dealer);
  }

  private void outputHandOut(final List<Player> players, final Dealer dealer) {
    outputView.printDealIntroduce(convertPlayersToNames(players));

    final Participant dealerParticipant = dealer.getParticipant();
    outputView.printDealerHitResult(convertedDealerCardText(dealerParticipant));

    outputView.printPlayersHand(convertPlayersToEntries(players));
  }

  private void outputPickCard(final List<Player> players, final Dealer dealer, Deck deck) {
    playerPickCard(players, deck);
    dealerPickCard(dealer, deck);
  }

  private void playerPickCard(final List<Player> players, final Deck deck) {
    players.forEach(player -> ifCanPickCard(deck, player));
  }

  private void ifCanPickCard(Deck deck, Player player) {
    final String name = player.getName();
    while (player.isHit() && inputView.readPlayerAnswer(name)) {
      player.hit(deck);
      outputView.printPlayerHand(name, convertParticipantCardText(player.getParticipant()));
    }
  }

  private void dealerPickCard(final Dealer dealer, final Deck deck) {
    while (dealer.isHit()) {
      dealer.hit(deck);
      outputView.printDealerHit();
    }
  }

  private void outputHandResult(final Dealer dealer, final List<Player> players) {
    outputDealerHandResult(dealer);
    outputPlayersHandResult(players);
  }

  private void outputDealerHandResult(final Dealer dealer) {
    final Participant participant = dealer.getParticipant();
    final List<String> convertedCards = convertParticipantCardText(participant);
    final int score = participant.calculateScore();
    outputView.printDealerRoundResult(convertedCards, score);
  }

  private void outputPlayersHandResult(final List<Player> players) {
    players.forEach(this::outputPlayerHandResult);
  }

  private void outputPlayerHandResult(final Player player) {
    final Participant participant = player.getParticipant();
    final List<String> convertedCards = convertParticipantCardText(participant);
    final int score = participant.calculateScore();
    outputView.printPlayerRoundResult(player.getName(), convertedCards, score);
  }

  private void duel(final Dealer dealer, final List<Player> players) {
    players.forEach(dealer::round);
  }

  private void outputDuelResult(final List<Player> players, final Dealer dealer) {
    outputView.printBlackjackRoundResultIntroduce();
    outputView.printBlackjackResultOnDealer(dealer.getWinCount(), dealer.getLoseCount());
    players.forEach(this::outputPlayersDuelResult);
  }

  private void outputPlayersDuelResult(final Player player) {
    final String name = player.getName();
    outputView.printBlackjackResultOnPlayer(name, player.isWinDuel());
  }

  private List<Player> inputPlayers() {
    final List<String> playerNames = inputView.readPlayerNames();
    return playerNames.stream()
        .map(Player::new)
        .collect(Collectors.toList());
  }

  private static List<String> convertPlayersToNames(List<Player> players) {
    return players.stream()
        .map(Player::getName)
        .collect(Collectors.toList());
  }

  private List<Map.Entry<String, List<String>>> convertPlayersToEntries(
      final List<Player> players) {
    return players.stream()
        .map(this::convertPlayerToEntry)
        .collect(Collectors.toList());
  }

  private Map.Entry<String, List<String>> convertPlayerToEntry(final Player player) {
    return new AbstractMap.SimpleEntry<>(player.getName(),
        convertParticipantCardText(player.getParticipant()));
  }

  private String convertedDealerCardText(final Participant dealerParticipant) {
    final Hand dealerHand = dealerParticipant.getCards();
    final TrumpCard dealerFirstCard = dealerHand.getCards().getFirst();
    return convertedCardText(dealerFirstCard);
  }

  private String convertedCardText(final TrumpCard dealerFirstTrumpCard) {
    final String rankToText = RankToTextConverter.convert(dealerFirstTrumpCard.rank());
    final String suitToText = dealerFirstTrumpCard.suit().getValue();
    return rankToText + suitToText;
  }

  private List<String> convertParticipantCardText(final Participant dealerParticipant) {
    return dealerParticipant.getCards().getCards()
        .stream()
        .map(this::convertedCardText)
        .toList();
  }

}
