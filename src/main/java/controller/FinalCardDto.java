package controller;

import java.util.List;

import domain.Card;

public record FinalCardDto (String name, List<Card> cards, int total){
}
