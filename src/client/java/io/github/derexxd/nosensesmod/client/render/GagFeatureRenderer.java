package io.github.derexxd.nosensesmod.client.render;

import io.github.derexxd.nosensesmod.client.model.GagModel;
import io.github.derexxd.nosensesmod.client.state.ClientMuteState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class GagFeatureRenderer extends FeatureRenderer<PlayerEntityRenderState, PlayerEntityModel> {
    private final GagModel model;

    public GagFeatureRenderer(FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel> context, GagModel model) {
        super(context);
        this.model = model;
    }

    @Override
    public void render(
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            PlayerEntityRenderState state,
            float limbAngle,
            float limbDistance
    ) {
        if (state.invisible || !isMuted(state)) {
            return;
        }

        matrices.push();
        this.getContextModel().getHead().rotate(matrices);
        FeatureRenderer.renderModel(this.model, GagModel.TEXTURE, matrices, vertexConsumers, light, state, -1);
        matrices.pop();
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
