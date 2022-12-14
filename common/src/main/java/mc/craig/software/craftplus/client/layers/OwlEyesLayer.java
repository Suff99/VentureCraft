package mc.craig.software.craftplus.client.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.client.models.OwlModel;
import mc.craig.software.craftplus.common.entities.Owl;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class OwlEyesLayer extends EyesLayer<Owl, OwlModel<Owl>> {

    public OwlEyesLayer(RenderLayerParent renderLayerParent) {
        super(renderLayerParent);
    }

    private final RenderType renderType = RenderType.entityTranslucent(new ResourceLocation(VentureCraft.MODID, "textures/entity/owl/owl_eyes.png"));


    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int lightning, Owl owl, float p_116987_, float p_116988_, float p_116989_, float p_116990_, float p_116991_, float p_116992_) {
        poseStack.pushPose();
        int packedOverlayCoords = OverlayTexture.NO_OVERLAY;
        int packedLightmapCoords = 0x00F000F0;
        VertexConsumer vertexconsumer = multiBufferSource.getBuffer(this.renderType());
        this.getParentModel().renderToBuffer(poseStack, vertexconsumer, packedLightmapCoords, packedOverlayCoords, 1.0F, 1.0F, 1.0F, 0.5F);
        poseStack.popPose();
    }

    @Override
    public RenderType renderType() {
        return renderType;
    }
}