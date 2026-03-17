package dto;

import java.util.List;

public record ParticipantCurrentHandResponse(String name, List<String> cards, Integer score) {}
