import java.util.ArrayList;
import java.util.List;

public class TotalDeckGenerator {
    public List<Card> generate(){
        List<Card> cards = new ArrayList<>();
        for(int i=0;i<52;i++){
            cards.add(new Card());
        }
        return cards;
    }
}
