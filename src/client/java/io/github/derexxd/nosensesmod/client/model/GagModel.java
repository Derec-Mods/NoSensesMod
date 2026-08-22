package io.github.derexxd.nosensesmod.client.model;

import io.github.derexxd.nosensesmod.Nosensesmod;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.util.Identifier;

public class GagModel extends EntityModel<PlayerEntityRenderState> {
    public static final EntityModelLayer LAYER = new EntityModelLayer(Identifier.of(Nosensesmod.MOD_ID, "gag"), "main");
    public static final Identifier TEXTURE = Identifier.of(Nosensesmod.MOD_ID, "textures/entity/gag.png");

    public GagModel(ModelPart root) {
        super(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData root = modelData.getRoot();
        ModelPartData waist = root.addChild("Waist", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 12.0F, 0.0F));
        waist.addChild("Head", ModelPartBuilder.create()
                .uv(7, 32).cuboid(-3.0F, -3.0F, -5.0F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, -12.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }
}
