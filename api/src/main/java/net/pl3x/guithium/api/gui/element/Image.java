package net.pl3x.guithium.api.gui.element;

import com.google.common.base.Preconditions;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Objects;
import net.pl3x.guithium.api.Guithium;
import net.pl3x.guithium.api.gui.Vec4;
import net.pl3x.guithium.api.gui.texture.Texture;
import net.pl3x.guithium.api.json.JsonObjectWrapper;
import net.pl3x.guithium.api.key.Key;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an image element.
 */
public class Image extends Rect<Image> {
    /**
     * The default tiled dirt background used on a lot of options type screens.
     */
    public static final Image TILED_DIRT_BACKGROUND = Image.of(Guithium.MOD_ID + ":tiled_dirt")
            .setTexture(Texture.DIRT)
            .setVertexColor(0xFF404040)
            .setTileModifier(32.0F);

    private Texture texture;
    private Vec4 uv;
    private Integer vertex;
    private Float tile;

    /**
     * Create a new image element.
     *
     * @param key Unique identifier
     */
    public Image(@NotNull String key) {
        this(Key.of(key));
    }

    /**
     * Create a new image element.
     *
     * @param key Unique identifier
     */
    public Image(@NotNull Key key) {
        super(key);
    }

    /**
     * Create a new image element.
     *
     * @param key Unique identifier
     * @return New image element
     */
    @NotNull
    public static Image of(@NotNull String key) {
        return of(Key.of(key));
    }

    /**
     * Create a new image element.
     *
     * @param key Unique identifier
     * @return New image element
     */
    @NotNull
    public static Image of(@NotNull Key key) {
        return new Image(key);
    }

    /**
     * Get the image's texture.
     *
     * @return Image texture
     */
    @NotNull
    public Texture getTexture() {
        return this.texture;
    }

    /**
     * Set the image's texture.
     *
     * @param id  Unique identifying key
     * @param url URL or resource location
     * @return This image
     */
    @NotNull
    public Image setTexture(@NotNull String id, @NotNull String url) {
        return setTexture(Texture.of(id, url));
    }

    /**
     * Set the image's texture.
     *
     * @param key Unique identifying key
     * @param url URL or resource location
     * @return This image
     */
    @NotNull
    public Image setTexture(@NotNull Key key, @NotNull String url) {
        return setTexture(Texture.of(key, url));
    }

    /**
     * Set the image's texture.
     *
     * @param texture Image texture
     * @return This image
     */
    @NotNull
    public Image setTexture(@NotNull Texture texture) {
        Preconditions.checkNotNull(texture, "Texture cannot be null");
        this.texture = texture;
        return this;
    }

    /**
     * Get the texture UV.
     * <p>
     * If null, default UV of <code>0,0,1,1</code> will be used.
     *
     * @return Texture UV
     */
    @Nullable
    public Vec4 getUV() {
        return this.uv;
    }

    /**
     * Set the texture UV.
     *
     * @param x X value
     * @param y Y value
     * @param z Z value
     * @param w W value
     * @return This image
     */
    @NotNull
    public Image setUV(float x, float y, float z, float w) {
        return setUV(Vec4.of(x, y, z, w));
    }

    /**
     * Set the texture UV.
     * <p>
     * If null, default UV of <code>0,0,1,1</code> will be used.
     *
     * @param uv Texture UV
     * @return This image
     */
    @NotNull
    public Image setUV(@Nullable Vec4 uv) {
        this.uv = uv;
        return this;
    }

    /**
     * Get the vertex color modifier.
     * <p>
     * This is a color modifier used in mojang's `position_tex_color` fragment
     * shader to control how much of a texture's colors show through.
     * <p>
     * Eg, a modifier of `0xFF00FFFF` will remove all red from the texture.
     * <p>
     * If null, default vertex color of <code>0xFFFFFFFF</code> (opaque white) will be used.
     *
     * @return Vertex color modifier
     */
    @Nullable
    public Integer getVertexColor() {
        return this.vertex;
    }

    /**
     * Set the vertex color modifier.
     * <p>
     * This is a color modifier used in mojang's `position_tex_color` fragment
     * shader to control how much of a texture's colors show through.
     * <p>
     * Eg, a modifier of `0xFF00FFFF` will remove all red from the texture.
     * <p>
     * If null, default vertex color of <code>0xFFFFFFFF</code> (opaque white) will be used.
     *
     * @param color Vertex color modifier
     * @return This image
     */
    @NotNull
    public Image setVertexColor(@Nullable Integer color) {
        this.vertex = color;
        return this;
    }

    /**
     * Get the image's tile modifier.
     * <p>
     * This is used to divide the UV into tiled segments on each axis.
     * <p>
     * Eg, a value of 32 is used on vanilla option screens with the dirt texture
     * in order to get the tiled effect. This does <em>not</em> mean it is tiled 32 times.
     * <p>
     * If null, no tile modifier will be used. If not null and <code>{@link #getUV()}</code> is null,
     * then width and height of the image will replace the UV before applying the tile modifier.
     *
     * @return Tile modifier
     */
    @Nullable
    public Float getTileModifier() {
        return this.tile;
    }

    /**
     * Set the image's tile modifier.
     * <p>
     * This is used to divide the UV into tiled segments on each axis.
     * <p>
     * Eg, a value of 32 is used on vanilla option screens with the dirt texture
     * in order to get the tiled effect. This does <em>not</em> mean it is tiled 32 times.
     * <p>
     * If null, no tile modifier will be used. If not null and <code>{@link #getUV()}</code> is null,
     * then width and height of the image will replace the UV before applying the tile modifier.
     *
     * @param tile Tile modifier
     * @return This image
     */
    @NotNull
    public Image setTileModifier(@Nullable Float tile) {
        this.tile = tile;
        return this;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        Image other = (Image) obj;
        return Objects.equals(getTexture(), other.getTexture())
                && Objects.equals(getUV(), other.getUV())
                && Objects.equals(getVertexColor(), other.getVertexColor())
                && Objects.equals(getTileModifier(), other.getTileModifier());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(),
                getTexture(),
                getUV(),
                getVertexColor(),
                getTileModifier()
        );
    }

    @Override
    @NotNull
    public JsonElement toJson() {
        JsonObjectWrapper json = new JsonObjectWrapper(super.toJson());
        json.addProperty("texture", getTexture());
        json.addProperty("uv", getUV());
        json.addProperty("vertexColor", getVertexColor());
        json.addProperty("tileMod", getTileModifier());
        return json.getJsonObject();
    }

    /**
     * Create a new image from Json.
     *
     * @param json Json representation of an image
     * @return A new image
     */
    @NotNull
    public static Image fromJson(@NotNull JsonObject json) {
        Preconditions.checkArgument(json.has("key"), "Key cannot be null");
        Preconditions.checkArgument(json.has("texture"), "Texture cannot be null");
        Image image = new Image(Key.of(json.get("key").getAsString()));
        Rect.fromJson(image, json);
        image.setTexture(Texture.fromJson(json.get("texture").getAsJsonObject()));
        image.setUV(!json.has("uv") ? null : Vec4.fromJson(json.get("uv").getAsJsonObject()));
        image.setVertexColor(!json.has("vertex") ? null : json.get("vertex").getAsInt());
        image.setTileModifier(!json.has("tile") ? null : json.get("tile").getAsFloat());
        return image;
    }
}
