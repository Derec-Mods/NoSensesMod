// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class blindfold_Converted<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "blindfold_converted"), "main");
	private final ModelPart OuterBand;
	private final ModelPart OuterBand2;
	private final ModelPart InnerBand;
	private final ModelPart Hang;

	public blindfold_Converted(ModelPart root) {
		this.OuterBand = root.getChild("OuterBand");
		this.OuterBand2 = this.OuterBand.getChild("OuterBand2");
		this.InnerBand = this.OuterBand2.getChild("InnerBand");
		this.Hang = this.OuterBand2.getChild("Hang");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition OuterBand = partdefinition.addOrReplaceChild("OuterBand", CubeListBuilder.create(), PartPose.offset(5.0F, 24.0F, 5.0F));

		PartDefinition OuterBand2 = OuterBand.addOrReplaceChild("OuterBand2", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, -31.0F, -11.0F, 1.0F, 5.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(1, 5).addBox(-12.0F, -31.0F, -11.0F, 1.0F, 5.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-11.0F, -31.0F, 1.0F, 12.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 1).addBox(-11.0F, -31.0F, -12.0F, 12.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition InnerBand = OuterBand2.addOrReplaceChild("InnerBand", CubeListBuilder.create().texOffs(3, 5).addBox(-1.0F, -31.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(5, 7).addBox(-10.0F, -31.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 4).addBox(-10.0F, -31.0F, -11.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(15, 18).addBox(-1.0F, -31.0F, -11.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(2, 13).addBox(0.0F, -31.0F, -11.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(13, 2).addBox(0.0F, -31.0F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(15, 1).addBox(-11.0F, -31.0F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(15, 5).addBox(-11.0F, -31.0F, -11.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Hang = OuterBand2.addOrReplaceChild("Hang", CubeListBuilder.create().texOffs(19, 18).addBox(-1.0F, -26.0F, -12.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 7).addBox(-2.0F, -26.0F, -12.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		OuterBand.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}