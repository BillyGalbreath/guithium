package net.pl3x.guithium.api.gui.element;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.util.Arrays;
import java.util.Objects;
import net.pl3x.guithium.api.Guithium;
import net.pl3x.guithium.api.gui.Vec2;
import net.pl3x.guithium.api.json.JsonObjectWrapper;
import net.pl3x.guithium.api.key.Key;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a gradient filled rectangle element.
 */
public class Gradient extends Rect<Gradient> {
    /**
     * The default color gradient Minecraft likes to use.
     */
    public static final Gradient GRADIENT = Gradient.of(Guithium.MOD_ID + ":gradient")
            .setColorTop(0xC0101010)
            .setColorBottom(0xD0101010);

    private final int[] color = new int[]{0, 0, 0, 0};

    /**
     * Create a new gradient element.
     *
     * @param key Unique identifier
     */
    public Gradient(@NotNull String key) {
        this(Key.of(key));
    }

    /**
     * Create a new gradient element.
     *
     * @param key Unique identifier
     */
    public Gradient(@NotNull Key key) {
        super(key);
    }

    /**
     * Create a new gradient element.
     *
     * @param key Unique identifier
     * @return New gradient element
     */
    @NotNull
    public static Gradient of(@NotNull String key) {
        return of(Key.of(key));
    }

    /**
     * Create a new gradient element.
     *
     * @param key Unique identifier
     * @return New gradient element
     */
    @NotNull
    public static Gradient of(@NotNull Key key) {
        return new Gradient(key);
    }

    /**
     * Get the colors for all four corners.
     * <p>
     * Array index starts at top left and iterates counterclockwise.
     *
     * @return Array of colors of all four corners
     */
    public int[] getColors() {
        return this.color;
    }

    /**
     * Get the top left color.
     *
     * @return Top left color
     */
    public int getColorTopLeft() {
        return this.color[0];
    }

    /**
     * Set the top left color.
     *
     * @param color Color to set
     * @return This gradient
     */
    @NotNull
    public Gradient setColorTopLeft(int color) {
        this.color[0] = color;
        return this;
    }

    /**
     * Get the bottom left color.
     *
     * @return Bottom left color
     */
    public int getColorBottomLeft() {
        return this.color[1];
    }

    /**
     * Set the bottom left color.
     *
     * @param color Color to set
     * @return This gradient
     */
    @NotNull
    public Gradient setColorBottomLeft(int color) {
        this.color[1] = color;
        return this;
    }

    /**
     * Get the bottom right color.
     *
     * @return Bottom right color
     */
    public int getColorBottomRight() {
        return this.color[2];
    }

    /**
     * Set the bottom right color.
     *
     * @param color Color to set
     * @return This gradient
     */
    @NotNull
    public Gradient setColorBottomRight(int color) {
        this.color[2] = color;
        return this;
    }

    /**
     * Get the top right color.
     *
     * @return Top right color
     */
    public int getColorTopRight() {
        return this.color[3];
    }

    /**
     * Set the top right color.
     *
     * @param color Color to set
     * @return This gradient
     */
    @NotNull
    public Gradient setColorTopRight(int color) {
        this.color[3] = color;
        return this;
    }

    /**
     * Set both top (left and right) colors.
     *
     * @param color Color to set
     * @return This gradient
     */
    @NotNull
    public Gradient setColorTop(int color) {
        setColorTopLeft(color);
        setColorTopRight(color);
        return this;
    }

    /**
     * Set both bottom (left and right) colors.
     *
     * @param color Color to set
     * @return This gradient
     */
    @NotNull
    public Gradient setColorBottom(int color) {
        setColorBottomLeft(color);
        setColorBottomRight(color);
        return this;
    }

    /**
     * Set both left (top and bottom) colors.
     *
     * @param color Color to set
     * @return This gradient
     */
    @NotNull
    public Gradient setColorLeft(int color) {
        setColorTopLeft(color);
        setColorBottomLeft(color);
        return this;
    }

    /**
     * Set both right (top and bottom) colors.
     *
     * @param color Color to set
     * @return This gradient
     */
    @NotNull
    public Gradient setColorRight(int color) {
        setColorTopRight(color);
        setColorBottomRight(color);
        return this;
    }

    /**
     * Set all colors (all four corners) to a single color.
     *
     * @param color Color to set
     * @return This gradient
     */
    @NotNull
    public Gradient setColorAll(int color) {
        setColorTop(color);
        setColorBottom(color);
        return this;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        Gradient other = (Gradient) obj;
        return Arrays.equals(getColors(), other.getColors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(),
                Arrays.hashCode(getColors())
        );
    }

    @Override
    @NotNull
    public JsonElement toJson() {
        JsonObjectWrapper json = new JsonObjectWrapper(super.toJson());
        json.addProperty("color", getColors());
        return json.getJsonObject();
    }

    /**
     * Create a new gradient from Json.
     *
     * @param json Json representation of a gradient
     * @return A new gradient
     */
    @NotNull
    public static Gradient fromJson(@NotNull JsonObject json) {
        if (!json.has("color")) {
            throw new JsonParseException("Missing colors array");
        }
        JsonArray arr = json.get("color").getAsJsonArray();
        if (arr.size() != 4) {
            throw new JsonParseException("Malformed colors array (size!=4)");
        }
        Gradient gradient = new Gradient(Key.of(json.get("key").getAsString()));
        Rect.fromJson(gradient, json);
        gradient.setSize(!json.has("size") ? null : Vec2.fromJson(json.get("size").getAsJsonObject()));
        for (int i = 0; i < 4; i++) {
            gradient.color[i] = arr.get(i).getAsInt();
        }
        return gradient;
    }
}
