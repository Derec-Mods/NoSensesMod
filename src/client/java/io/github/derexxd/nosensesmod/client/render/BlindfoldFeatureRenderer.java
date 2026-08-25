package io.github.derexxd.nosensesmod.client.render;

import io.github.derexxd.nosensesmod.client.CosmeticFit;
import io.github.derexxd.nosensesmod.client.model.BlindfoldModel;
import io.github.derexxd.nosensesmod.client.state.ClientBlindState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class BlindfoldFeatureRenderer extends FeatureRenderer<PlayerEntityRenderState, PlayerEntityModel> {
    private final BlindfoldModel model;

    public BlindfoldFeatureRenderer(FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel> context, BlindfoldModel model) {
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
        if (state.invisible || !isBlind(state)) {
            return;
        }

        matrices.push();
        this.getContextModel().getHead().rotate(matrices);
        matrices.translate(0.0F, CosmeticFit.blindfoldY(state.name) / 16.0F, 0.0F);
        FeatureRenderer.renderModel(this.model, BlindfoldModel.TEXTURE, matrices, vertexConsumers, light, state, -1);
        matrices.pop();
    }

    private static boolean isBlind(PlayerEntityRenderState state) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null) {
            return false;
        }
        Entity entity = client.world.getEntityById(state.id);
        return entity != null && ClientBlindState.isBlind(entity.getUuid());
    }
}
