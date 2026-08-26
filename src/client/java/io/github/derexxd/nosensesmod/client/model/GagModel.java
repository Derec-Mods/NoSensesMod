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

    private static final float X0 = -4.25F;
    private static final float Y0 = -1.8F;
    private static final float Z0 = -7.25F;
    private static final float X1 = 4.25F;
    private static final float Y1 = 0.2F;
    private static final float Z1 = -5.25F;

    private static final float U0 = 0.28125F / 16.0F;
    private static final float V0 = 0.10938F / 16.0F;
    private static final float U1 = 8.28125F / 16.0F;
    private static final float V1 = 3.10938F / 16.0F;
    private static final float U_WEST1 = 1.28125F / 16.0F;
    private static final float U_EAST0 = 7.28125F / 16.0F;
    private static final float V_UP1 = 0.60938F / 16.0F;
    private static final float V_DOWN0 = 2.60938F / 16.0F;

    private GagModel() {
    }

    public static void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        MatrixStack.Entry entry = matrices.peek();
        VertexConsumer vertex = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(TEXTURE));
        north(vertex, entry, light);
        south(vertex, entry, light);
        west(vertex, entry, light);
        east(vertex, entry, light);
        up(vertex, entry, light);
        down(vertex, entry, light);
    }

    private static void north(VertexConsumer vertex, MatrixStack.Entry entry, int light) {
        quad(vertex, entry, light, 0.0F, 0.0F, -1.0F,
                X1, Y0, Z0, U0, V1,
                X0, Y0, Z0, U1, V1,
                X0, Y1, Z0, U1, V0,
                X1, Y1, Z0, U0, V0);
    }

    private static void south(VertexConsumer vertex, MatrixStack.Entry entry, int light) {
        quad(vertex, entry, light, 0.0F, 0.0F, 1.0F,
                X0, Y0, Z1, U0, V1,
                X1, Y0, Z1, U1, V1,
                X1, Y1, Z1, U1, V0,
                X0, Y1, Z1, U0, V0);
    }

    private static void west(VertexConsumer vertex, MatrixStack.Entry entry, int light) {
        quad(vertex, entry, light, -1.0F, 0.0F, 0.0F,
                X0, Y0, Z0, U0, V1,
                X0, Y0, Z1, U_WEST1, V1,
                X0, Y1, Z1, U_WEST1, V0,
                X0, Y1, Z0, U0, V0);
    }

    private static void east(VertexConsumer vertex, MatrixStack.Entry entry, int light) {
        quad(vertex, entry, light, 1.0F, 0.0F, 0.0F,
                X1, Y0, Z1, U_EAST0, V1,
                X1, Y0, Z0, U1, V1,
                X1, Y1, Z0, U1, V0,
                X1, Y1, Z1, U_EAST0, V0);
    }

    private static void up(VertexConsumer vertex, MatrixStack.Entry entry, int light) {
        quad(vertex, entry, light, 0.0F, 1.0F, 0.0F,
                X0, Y1, Z1, U0, V_UP1,
                X1, Y1, Z1, U1, V_UP1,
                X1, Y1, Z0, U1, V0,
                X0, Y1, Z0, U0, V0);
    }

    private static void down(VertexConsumer vertex, MatrixStack.Entry entry, int light) {
        quad(vertex, entry, light, 0.0F, -1.0F, 0.0F,
                X0, Y0, Z0, U0, V1,
                X1, Y0, Z0, U1, V1,
                X1, Y0, Z1, U1, V_DOWN0,
                X0, Y0, Z1, U0, V_DOWN0);
    }

    private static void quad(
            VertexConsumer vertex,
            MatrixStack.Entry entry,
            int light,
            float nx,
            float ny,
            float nz,
            float x1, float y1, float z1, float u1, float v1,
            float x2, float y2, float z2, float u2, float v2,
            float x3, float y3, float z3, float u3, float v3,
            float x4, float y4, float z4, float u4, float v4
    ) {
        vertex(vertex, entry, x1, y1, z1, u1, v1, nx, ny, nz, light);
        vertex(vertex, entry, x2, y2, z2, u2, v2, nx, ny, nz, light);
        vertex(vertex, entry, x3, y3, z3, u3, v3, nx, ny, nz, light);
        vertex(vertex, entry, x4, y4, z4, u4, v4, nx, ny, nz, light);
    }

    private static void vertex(
            VertexConsumer buffer,
            MatrixStack.Entry entry,
            float x,
            float y,
            float z,
            float u,
            float v,
            float nx,
            float ny,
            float nz,
            int light
    ) {
        buffer.vertex(entry, x, y, z)
                .color(255, 255, 255, 255)
                .texture(u, v)
                .overlay(OverlayTexture.DEFAULT_UV)
                .light(light)
                .normal(entry, nx, ny, nz);
    }
}
