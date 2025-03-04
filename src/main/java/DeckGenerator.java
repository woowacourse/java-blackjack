import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class DeckGenerator {

    public List<Card> generate() {
        return Arrays.stream(CardType.values()).flatMap(
                cardType -> IntStream.rangeClosed(1, 11).filter(cardType::isValidate)
                        .mapToObj(value -> new Card(cardType, value))).toList();
    }

    private void generateByAce(List<Card> cards){

    }

    private void generateByFace
}
