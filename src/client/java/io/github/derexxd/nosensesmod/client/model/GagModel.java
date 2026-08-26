package io.github.derexxd.nosensesmod.client.model;

import io.github.derexxd.nosensesmod.Nosensesmod;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public final class GagModel {
    public static final Identifier TEXTURE = Identifier.of(Nosensesmod.MOD_ID, "textures/entity/gagtape.png");

    private static final float X0 = -4.0F;
    private static final float Y0 = -2.0F;
    private static final float X1 = 4.0F;
    private static final float Y1 = 0.0F;
    private static final float Z = -4.08F;

    private static final float U0 = 0.28125F / 16.0F;
    private static final float V0 = 0.10938F / 16.0F;
    private static final float U1 = 8.28125F / 16.0F;
    private static final float V1 = 3.10938F / 16.0F;

    private GagModel() {
    }

    public static void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        MatrixStack.Entry entry = matrices.peek();
        VertexConsumer vertex = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(TEXTURE));
        vertex(vertex, entry, X1, Y0, Z, U0, V1, light);
        vertex(vertex, entry, X0, Y0, Z, U1, V1, light);
        vertex(vertex, entry, X0, Y1, Z, U1, V0, light);
        vertex(vertex, entry, X1, Y1, Z, U0, V0, light);
    }

    private static void vertex(
            VertexConsumer buffer,
            MatrixStack.Entry entry,
            float x,
            float y,
            float z,
            float u,
            float v,
            int light
    ) {
        buffer.vertex(entry, x, y, z)
                .color(255, 255, 255, 255)
                .texture(u, v)
                .overlay(OverlayTexture.DEFAULT_UV)
                .light(light)
                .normal(entry, 0.0F, 0.0F, -1.0F);
    }
}
