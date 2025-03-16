package controller.converter;

import domain.card.TrumpCard;
import domain.participant.Participant;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class DomainToTextConverter {

  private static DomainToTextConverter converter = null;

  private DomainToTextConverter() {
  }

  public static DomainToTextConverter getInstance() {
    if (converter == null) {
      converter = new DomainToTextConverter();
    }
    return converter;
  }

  public List<String> playersToNames(final List<Participant> players) {
    return players.stream()
        .map(Participant::getName)
        .collect(Collectors.toList());
  }

  public List<Map.Entry<String, List<String>>> playersToEntries(
      final List<Participant> players) {
    return players.stream()
        .map(this::playerToEntry)
        .collect(Collectors.toList());
  }

  public Map.Entry<String, List<String>> playerToEntry(final Participant player) {
    return new AbstractMap.SimpleEntry<>(player.getName(),
        participantCardToText(player.getCards()));
  }

  public List<String> participantCardToText(final List<TrumpCard> cars) {
    return cars.stream()
        .map(this::cardToText)
        .toList();
  }

  public List<String> handToText(final List<TrumpCard> hand) {
    return hand.stream()
        .map(this::cardToText)
        .toList();
  }

  public String cardToText(final TrumpCard dealerFirstTrumpCard) {
    final String rankToText = RankToTextConverter.convert(dealerFirstTrumpCard.getRank());
    final String suitToText = dealerFirstTrumpCard.getSuit().getValue();
    return rankToText + suitToText;
  }
}
