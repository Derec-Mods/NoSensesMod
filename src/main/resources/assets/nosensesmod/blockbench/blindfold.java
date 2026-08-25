// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class blindfold extends EntityModel<Entity> {
	private final ModelPart OuterBand;
	private final ModelPart OuterBand2;
	private final ModelPart InnerBand;
	private final ModelPart Hang;
	public blindfold(ModelPart root) {
		this.OuterBand = root.getChild("OuterBand");
		this.OuterBand2 = this.OuterBand.getChild("OuterBand2");
		this.InnerBand = this.OuterBand2.getChild("InnerBand");
		this.Hang = this.OuterBand2.getChild("Hang");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData OuterBand = modelPartData.addChild("OuterBand", ModelPartBuilder.create(), ModelTransform.pivot(5.0F, 24.0F, 5.0F));

		ModelPartData OuterBand2 = OuterBand.addChild("OuterBand2", ModelPartBuilder.create().uv(2, 2).cuboid(-1.0F, -31.0F, -10.0F, 1.0F, 5.0F, 10.0F, new Dilation(0.0F))
		.uv(3, 3).cuboid(-10.0F, -31.0F, -10.0F, 1.0F, 5.0F, 9.0F, new Dilation(0.0F))
		.uv(1, 0).cuboid(-10.0F, -31.0F, -1.0F, 9.0F, 5.0F, 1.0F, new Dilation(0.0F))
		.uv(2, 1).cuboid(-9.0F, -31.0F, -10.0F, 8.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData InnerBand = OuterBand2.addChild("InnerBand", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Hang = OuterBand2.addChild("Hang", ModelPartBuilder.create().uv(19, 18).cuboid(-1.0F, -26.0F, -12.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
		.uv(16, 7).cuboid(-2.0F, -26.0F, -12.0F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 0.0F, 2.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		OuterBand.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}