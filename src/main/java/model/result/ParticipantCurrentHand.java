package model.result;

import java.util.List;
import model.card.Card;

public record ParticipantCurrentHand(String name, List<Card> deck, Integer score) {}
