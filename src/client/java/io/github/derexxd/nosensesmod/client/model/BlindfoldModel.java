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

public class BlindfoldModel extends EntityModel<PlayerEntityRenderState> {
    public static final EntityModelLayer LAYER = new EntityModelLayer(Identifier.of(Nosensesmod.MOD_ID, "blindfold"), "main");
    public static final Identifier TEXTURE = Identifier.of(Nosensesmod.MOD_ID, "textures/entity/blindfold.png");

    public BlindfoldModel(ModelPart root) {
        super(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData root = modelData.getRoot();
        ModelPartData outerBand = root.addChild("OuterBand", ModelPartBuilder.create(), ModelTransform.pivot(5.0F, 24.0F, 5.0F));

        ModelPartData outerBand2 = outerBand.addChild("OuterBand2", ModelPartBuilder.create()
                .uv(2, 2).cuboid(-1.0F, -31.0F, -10.0F, 1.0F, 5.0F, 10.0F, new Dilation(0.0F))
                .uv(3, 3).cuboid(-10.0F, -31.0F, -10.0F, 1.0F, 5.0F, 9.0F, new Dilation(0.0F))
                .uv(1, 0).cuboid(-10.0F, -31.0F, -1.0F, 9.0F, 5.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(-9.0F, -31.0F, -10.0F, 8.0F, 5.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        outerBand2.addChild("InnerBand", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        outerBand2.addChild("Hang", ModelPartBuilder.create()
                .uv(19, 18).cuboid(-1.0F, -26.0F, -12.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(16, 7).cuboid(-2.0F, -26.0F, -12.0F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(-1.0F, 0.0F, 2.0F));

        return TexturedModelData.of(modelData, 32, 32);
    }
}
