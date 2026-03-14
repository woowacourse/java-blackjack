package dto.result;

import java.util.List;

public record ParticipantCurrentHand(String name, List<String> deck, Integer score) {}
