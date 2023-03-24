package card.game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> cards;

    public Player() {
        cards = new ArrayList<>();
        cards.add(new Card());
        cards.add(new Card());
        cards.add(new Card());
    }

    public List<Card> getCards() {
        return cards;
    }
}
