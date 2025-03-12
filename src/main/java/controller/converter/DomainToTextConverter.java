package controller.converter;

import domain.card.TrumpCard;
import domain.participant.Participant;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DomainToTextConverter {

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
        participantCardToText(player));
  }

  public List<String> participantCardToText(final Participant participant) {
    return participant.getCards()
        .stream()
        .map(this::cardToText)
        .toList();
  }

  public List<String> handToText(final List<TrumpCard> hand) {
    return hand.stream()
        .map(this::cardToText)
        .toList();
  }

  public String cardToText(final TrumpCard dealerFirstTrumpCard) {
    final String rankToText = RankToTextConverter.convert(dealerFirstTrumpCard.rank());
    final String suitToText = dealerFirstTrumpCard.suit().getValue();
    return rankToText + suitToText;
  }
}
