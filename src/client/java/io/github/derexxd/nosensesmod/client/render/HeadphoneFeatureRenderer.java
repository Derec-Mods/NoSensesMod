package io.github.derexxd.nosensesmod.client.render;

import io.github.derexxd.nosensesmod.client.model.HeadphoneModel;
import io.github.derexxd.nosensesmod.client.state.ClientDeafState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class HeadphoneFeatureRenderer extends FeatureRenderer<PlayerEntityRenderState, PlayerEntityModel> {
    private final HeadphoneModel model;

    public HeadphoneFeatureRenderer(FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel> context, HeadphoneModel model) {
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
        if (state.invisible || !isDeaf(state)) {
            return;
        }

        matrices.push();
        ModelPart head = this.getContextModel().getHead();
        matrices.translate(head.pivotX / 16.0F, head.pivotY / 16.0F, head.pivotZ / 16.0F);
        head.rotate(matrices);
        FeatureRenderer.renderModel(this.model, HeadphoneModel.TEXTURE, matrices, vertexConsumers, light, state, -1);
        matrices.pop();
    }

    private static boolean isDeaf(PlayerEntityRenderState state) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null) {
            return false;
        }
        Entity entity = client.world.getEntityById(state.id);
        return entity != null && ClientDeafState.isDeaf(entity.getUuid());
    }
}
