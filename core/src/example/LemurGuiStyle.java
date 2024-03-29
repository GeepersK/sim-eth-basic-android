package example;

import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.Insets3f;
import com.simsilica.lemur.component.QuadBackgroundComponent;
import com.simsilica.lemur.component.TbtQuadBackgroundComponent;
import com.simsilica.lemur.style.Attributes;
import com.simsilica.lemur.style.Styles;

/**
 * Created by James on 16/01/2018.
 * https://hub.jmonkeyengine.org/t/many-little-lemur-questions/40244/14
 * https://hub.jmonkeyengine.org/t/running-lemur-on-android-using-groovy/35786/10
 * https://hub.jmonkeyengine.org/t/many-little-lemur-questions/40244/36
 */

public class LemurGuiStyle {

    public LemurGuiStyle(AssetManager assetManager) {

        float guiScale = 3.0f;
        float guiScaleHalf = guiScale * .5f;

        Styles styles = GuiGlobals.getInstance().getStyles();

        Attributes attrs;

        /*
        QuadBackgroundComponent bg = new QuadBackgroundComponent(new ColorRGBA(0.207f, 0.518f, 0.89f, 0.85f));

        TbtQuadBackgroundComponent gradient = TbtQuadBackgroundComponent.create(
                "Textures/Gui/white-rounded.png",
                1, 1, 1, 126, 126,
                1f,false);
        */

        QuadBackgroundComponent bg = new QuadBackgroundComponent(new ColorRGBA(0.207f, 0.207f, 0.207f, 0.85f));

        TbtQuadBackgroundComponent gradient = TbtQuadBackgroundComponent.create(
                "/com/simsilica/lemur/icons/bordered-gradient.png",
                // "Textures/Gui/radial-gradient.png",
                1, 1, 1, 126, 126,
                1f,false);

        TbtQuadBackgroundComponent double_gradient = TbtQuadBackgroundComponent.create(
                "/com/simsilica/lemur/icons/double-gradient-128.png",
                // "Textures/Gui/radial-gradient.png",
                1, 1, 1, 126, 126,
                1f,false);
        double_gradient.setColor(new ColorRGBA(0.5f, 0.75f, 0.85f, 0.5f));

        attrs = styles.getSelector("glass");
        attrs.set("fontSize", 4 * guiScale);

        // label
        attrs = styles.getSelector("label", "glass");
        attrs.set("insets", new Insets3f(2 * guiScaleHalf, 2 * guiScaleHalf, 2 * guiScaleHalf, 2 * guiScaleHalf));
        attrs.set("color", new ColorRGBA(0.5f, 0.75f, 0.75f, 0.85f));

        // title
        attrs = styles.getSelector("title", "glass");
        attrs.set("color", new ColorRGBA(0.8f, 0.9f, 1.0f, 0.85f));
        attrs.set("highlightColor", new ColorRGBA(1.0f, 0.8f, 1.0f, 0.85f));
        attrs.set("shadowColor", new ColorRGBA(0.0f, 0.0f, 0.0f, 0.75f));
        attrs.set("shadowOffset", new Vector3f(2, -2, -1));
        attrs.set("background", double_gradient.clone());
        attrs.set("insets", new Insets3f(2 * guiScale,2 * guiScale,2 * guiScale,2 * guiScale));

        // button
        attrs = styles.getSelector("button", "glass");
        attrs.set("color", new ColorRGBA(0.8f, 0.9f, 1.0f, 0.85f));
        attrs.set("background", gradient.clone());
        ((TbtQuadBackgroundComponent)attrs.get("background")).setColor(new ColorRGBA(0.0f, 0.75f, 0.75f, 0.5f));
        attrs.set("insets", new Insets3f(2 * guiScale,2 * guiScale,2 * guiScale,2 * guiScale));

        // container
        attrs = styles.getSelector("container", "glass");
        attrs.set("background", gradient.clone());
        ((TbtQuadBackgroundComponent)attrs.get("background")).setColor(new ColorRGBA(0.25f, 0.5f, 0.5f, 0.5f));

        // slider
        attrs = styles.getSelector("slider", "glass");
        attrs.set("insets", new Insets3f(2,2,2,2));
        attrs.set("background", bg.clone());
        ((QuadBackgroundComponent)attrs.get("background")).setColor(new ColorRGBA(0.25f, 0.5f, 0.5f, 0.5f));

        attrs = styles.getSelector("slider", "button", "glass");
        attrs.set("background", bg.clone());
        ((QuadBackgroundComponent)attrs.get("background")).setColor(new ColorRGBA(0.5f, 0.75f, 0.75f, 0.5f));
        attrs.set("insets", new Insets3f(0,0,0,0));

        attrs = styles.getSelector("slider", "slider.thumb.button", "glass");
        attrs.set("text", "[]");
        attrs.set("color", new ColorRGBA(0.6f, 0.8f, 0.8f, 0.85f));

        attrs = styles.getSelector("slider", "slider.left.button", "glass");
        attrs.set("text", "-");
        attrs.set("background", bg.clone());
        ((QuadBackgroundComponent)attrs.get("background")).setColor(new ColorRGBA(0.5f, 0.75f, 0.75f, 0.5f));
        ((QuadBackgroundComponent)attrs.get("background")).setMargin(5, 0);
        attrs.set("color", new ColorRGBA(0.6f, 0.8f, 0.8f, 0.85f));

        attrs = styles.getSelector("slider", "slider.right.button", "glass");
        attrs.set("text", "+");
        attrs.set("background", bg.clone());
        ((QuadBackgroundComponent)attrs.get("background")).setColor(new ColorRGBA(0.5f, 0.75f, 0.75f, 0.5f));
        ((QuadBackgroundComponent)attrs.get("background")).setMargin(4, 0);
        attrs.set("color", new ColorRGBA(0.6f, 0.8f, 0.8f, 0.85f));

        // Tabbed Panel
        attrs = styles.getSelector("tabbedPanel", "glass");
        attrs.set("activationColor", new ColorRGBA(0.8f, 0.9f, 1.0f, 0.85f));

        attrs = styles.getSelector("tabbedPanel.container", "glass");
        attrs.set("background", null);

        attrs = styles.getSelector("tab.button", "glass");
        attrs.set("background", gradient.clone());
        ((TbtQuadBackgroundComponent)attrs.get("background")).setColor(new ColorRGBA(0.25f, 0.5f, 0.5f, 0.5f));
        ((TbtQuadBackgroundComponent)attrs.get("background")).setMargin(12, 6);
        attrs.set("color", new ColorRGBA(0.4f, 0.45f, 0.5f, 0.85f));
        attrs.set("insets", new Insets3f(12,6,0,6));

    }

}