package controller.dto;

import domain.TrumpCard;
import java.util.List;

public record CardScoreDto(List<TrumpCard> cards, int totalScore) {

}
