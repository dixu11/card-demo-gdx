package card.game;

//Texture
//Rectangle
//Sprite

//Actor
//Group

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;

public class PlayerView extends Group {
    private Player player;

    public PlayerView(Player player) {
        this.player = player;
        Texture texture = new Texture(Gdx.files.internal("card.png"));
        for (Card card : player.getCards()) {
            CardView cardView = new CardView(texture, card);
            addActor(cardView);
        }
        setPosition(300,50);
    }
}
