// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class headphone extends EntityModel<Entity> {
	private final ModelPart headband;
	private final ModelPart earcups;
	public headphone(ModelPart root) {
		this.headband = root.getChild("headband");
		this.earcups = this.headband.getChild("earcups");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData headband = modelPartData.addChild("headband", ModelPartBuilder.create().uv(18, 5).cuboid(-4.5F, -2.0F, -2.0F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F))
		.uv(18, 13).cuboid(3.5F, -2.0F, -2.0F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F))
		.uv(3, 0).cuboid(-4.5F, -3.0F, -2.0F, 9.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -8.0F, 0.0F));

		ModelPartData earcups = headband.addChild("earcups", ModelPartBuilder.create().uv(0, 5).cuboid(3.5F, 2.0F, -3.0F, 3.0F, 6.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 17).cuboid(-6.5F, 2.0F, -3.0F, 3.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		headband.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}