package morningsage.particletitlescreen.config;

public final class ModConfig {
    @ConfigField(
        category = "general",
        comment = "The background color... Pretty self explanatory."
    )
    public static String backgroundColor = "#2e3440";

    @ConfigField(
        category = "general",
        comment = "Particle color"
    )
    public static String particleColor = "#ffffff";

    @ConfigField(
        category = "general",
        comment = "Enables particles to have a random radius (specified by particleMinRadius and particleMaxRadius).  When true, this overrides particleRadius."
    )
    public static boolean randomParticleRadius = true;

    @ConfigField(
        category = "general",
        comment = "Particle radius (from middle to edge).  Ignored if randomParticleRadius is true."
    )
    public static float particleRadius = 5.0F;

    @ConfigField(
        category = "general",
        comment = "Minimum particle radius (from middle to edge).  Ignored if randomParticleRadius is false."
    )
    public static float particleMinRadius = 0.1F;
    @ConfigField(
        category = "general",
        comment = "Maximum particle radius (from middle to edge).  Ignored if randomParticleRadius is false."
    )
    public static float particleMaxRadius = 5.0F;

    @ConfigField(
        category = "general",
        comment = "Enables particles to have a random opacity.  When true, this overrides particleOpacity."
    )
    public static boolean randomParticleOpacity = true;

    @ConfigField(
        category = "general",
        comment = "Particle opacity.  Ignored if randomParticleOpacity is true."
    )
    public static float particleOpacity = 1.0F;

    @ConfigField(
        category = "general",
        comment = "Minimum particle opacity.  Ignored if randomParticleOpacity is false."
    )
    public static float particleMinOpacity = 0.5F;
    @ConfigField(
        category = "general",
        comment = "Maximum particle radius.  Ignored if randomParticleOpacity is false."
    )
    public static float particleMaxOpacity = 1.0F;

    @ConfigField(
        category = "general",
        comment = "Particle movement."
    )
    public static boolean particleMovement = true;
    @ConfigField(
        category = "general",
        comment = "Particle movement speed.  Ignored if particleMovement is false."
    )
    public static float particleMovementSpeed = 5.0F;
    @ConfigField(
        category = "general",
        comment = "Indicates whether or not lines should be drawn between particles as they near one another."
    )
    public static boolean particleInteractions = true;
    @ConfigField(
        category = "general",
        comment = "The color of the lines when particles interact.  Ignored if particleInteractions is false."
    )
    public static String particleInteractionColor = "#ffffff";
    @ConfigField(
        category = "general",
        comment = "The distance at which particles begin to interact.  Ignored if particleInteractions is false."
    )
    public static double particleInteractionDistance = 100.0D;
    @ConfigField(
        category = "general",
        comment = "The max opacity for the interactions as they occur.  Ignored if particleInteractions is false."
    )
    public static float particleInteractionOpacity = 0.4F;
    @ConfigField(
        category = "general",
        comment = "Indicates whether particles should bound off the side of the window.  Ignored if particleMovement is false."
    )
    public static boolean particleBounce = false;
    @ConfigField(
        category = "general",
        comment = "Indicates whether particles should be repelled by the mouse cursor."
    )
    public static boolean particleRepelledByMouse = true;
    @ConfigField(
        category = "general",
        comment = "Indicates how far particles should be repelled by the mouse cursor.  Ignored if particleRepelledByMouse is false."
    )
    public static float particleDistanceRepelledByMouse = 100.0F;
}
