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

public class HeadphoneModel extends EntityModel<PlayerEntityRenderState> {
    public static final EntityModelLayer LAYER = new EntityModelLayer(Identifier.of(Nosensesmod.MOD_ID, "headphones"), "main");
    public static final Identifier TEXTURE = Identifier.of(Nosensesmod.MOD_ID, "textures/entity/headphones.png");

    public HeadphoneModel(ModelPart root) {
        super(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData root = modelData.getRoot();
        ModelPartData head = root.addChild("head", ModelPartBuilder.create(), ModelTransform.NONE);
        head.addChild("headband", ModelPartBuilder.create()
                .uv(18, 5).cuboid(-7.5F, -10.0F, -2.0F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F))
                .uv(18, 13).cuboid(6.5F, -10.0F, -2.0F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-7.5F, -11.0F, -2.0F, 15.0F, 1.0F, 4.0F, new Dilation(0.0F)),
                ModelTransform.NONE);
        head.addChild("earcups", ModelPartBuilder.create()
                .uv(0, 5).cuboid(6.5F, -6.0F, -3.0F, 3.0F, 6.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 17).cuboid(-9.5F, -6.0F, -3.0F, 3.0F, 6.0F, 6.0F, new Dilation(0.0F)),
                ModelTransform.NONE);
        return TexturedModelData.of(modelData, 64, 64);
    }
}
