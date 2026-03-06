package domain.dto;

import java.util.List;

import domain.Card;


public record CardContentDto(String name, List<Card> cards) {

}
