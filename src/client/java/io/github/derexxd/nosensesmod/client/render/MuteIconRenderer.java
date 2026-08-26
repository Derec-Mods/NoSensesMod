package io.github.derexxd.nosensesmod.client.render;

import io.github.derexxd.nosensesmod.Nosensesmod;
import io.github.derexxd.nosensesmod.client.state.ClientMuteState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.joml.Quaternionf;

public final class MuteIconRenderer {
    public static final Identifier TEXTURE = Identifier.of(Nosensesmod.MOD_ID, "textures/gui/muted.png");
    private static final float SIZE = 0.28F;
    private static final float ABOVE_NAMETAG = 0.72F;

    private MuteIconRenderer() {
    }

    public static void render(EntityRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (!(state instanceof PlayerEntityRenderState playerState) || playerState.invisible || !isMuted(playerState)) {
            return;
        }

        Vec3d labelPos = playerState.nameLabelPos;
        double y = (labelPos != null ? labelPos.y : playerState.height + 0.5) + ABOVE_NAMETAG;
        double x = labelPos != null ? labelPos.x : 0.0;
        double z = labelPos != null ? labelPos.z : 0.0;

        matrices.push();
        matrices.translate(x, y, z);
        Quaternionf rotation = MinecraftClient.getInstance().getEntityRenderDispatcher().getRotation();
        matrices.multiply(rotation);
        MatrixStack.Entry entry = matrices.peek();
        VertexConsumer vertex = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(TEXTURE));
        vertex(vertex, entry, -SIZE, -SIZE, 0.0F, 1.0F, light);
        vertex(vertex, entry, -SIZE, SIZE, 0.0F, 0.0F, light);
        vertex(vertex, entry, SIZE, SIZE, 1.0F, 0.0F, light);
        vertex(vertex, entry, SIZE, -SIZE, 1.0F, 1.0F, light);
        matrices.pop();
    }

    private static void vertex(VertexConsumer buffer, MatrixStack.Entry entry, float x, float y, float u, float v, int light) {
        buffer.vertex(entry, x, y, 0.0F)
                .color(255, 255, 255, 255)
                .texture(u, v)
                .overlay(OverlayTexture.DEFAULT_UV)
                .light(light)
                .normal(entry, 0.0F, 1.0F, 0.0F);
    }

    private static boolean isMuted(PlayerEntityRenderState state) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null) {
            return false;
        }
        Entity entity = client.world.getEntityById(state.id);
        return entity != null && ClientMuteState.isMuted(entity.getUuid());
    }
}
