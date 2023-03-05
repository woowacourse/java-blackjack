package blackjack.response;

public interface CardConvertStrategy {

    String convertSymbol(String symbol);

    String convertShape(String shape);
}
