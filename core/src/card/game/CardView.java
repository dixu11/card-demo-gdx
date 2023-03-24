package card.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.time.LocalTime;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class CardView extends Actor {
    public static final int WIDTH = 100;
    public static final int HEIGHT = 150;

    private Texture texture;
    private Card card;
    private boolean animated = false;

    public CardView(Texture texture, Card card) {
        this.texture = texture;
        this.card = card;
        setBounds(0, 0, WIDTH, HEIGHT);

        addListener(new InputListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                addAction(scaleTo(1.2f,1.2f, 0.2f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                addAction(scaleTo(1f,1f, 0.2f));
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(),
                getRotation(), 0, 0, texture.getWidth(), texture.getHeight(),
                false, false);

        batch.setColor(Color.WHITE);
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (animated) {
            return;
        }
        animated = true;
       queueActions();
    }

    public void queueActions() {
        addAction(sequence( getMoveAction(), run(new Runnable() {
            @Override
            public void run() {
                queueActions();
            }
        })));
    }


    public Action getMoveAction() {
        return sequence(moveTo(getX(), getY() + 400, 5), moveTo(getX(), getY(), 2));
    }

}
