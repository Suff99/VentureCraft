package mc.craig.software.craftplus.client.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.client.models.GliderModel;
import mc.craig.software.craftplus.client.models.Models;
import mc.craig.software.craftplus.client.models.XWingModel;
import mc.craig.software.craftplus.common.items.ParagliderItem;
import mc.craig.software.craftplus.util.GliderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class PlayerGliderLayer<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {

    public static GliderModel gliderModel;
    public static XWingModel<Entity> xWingModel;
    private static final ResourceLocation COPPER_EMBED = new ResourceLocation(VentureCraft.MODID, "textures/entity/glider/copper_overlay.png");
    private static final ResourceLocation COPPER_EMBED_CHARGED = new ResourceLocation(VentureCraft.MODID, "textures/entity/glider/copper_overlay_charged.png");
    private static final ResourceLocation XWING_TEXTURE = new ResourceLocation(VentureCraft.MODID, "textures/entity/glider/xwing.png");


    public PlayerGliderLayer(RenderLayerParent<T, M> renderLayerParent) {
        super(renderLayerParent);
        gliderModel = new GliderModel(Minecraft.getInstance().getEntityModels().bakeLayer(Models.GLIDER));
        xWingModel = new XWingModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(Models.X_WING));
    }

    public static ResourceLocation getGliderTexture(ItemStack stack) {
        if (stack.getDisplayName().getString().contains("xwing")) return XWING_TEXTURE;
        ResourceLocation itemLoc = Registry.ITEM.getKey(stack.getItem());
        return new ResourceLocation(itemLoc.getNamespace(), "textures/entity/glider/" + itemLoc.getPath() + ".png");
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int p_117351_, T living, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {
        if (living.isInvisibleTo(Minecraft.getInstance().player)) return;

        ItemStack stack = living.getItemBySlot(EquipmentSlot.CHEST);

        // Render above players when gliding
        if (GliderUtil.isGlidingWithActiveGlider(living)) {
            poseStack.pushPose();


            if (stack.getDisplayName().getString().contains("xwing")) {
                // Translate and render base glider
                poseStack.translate(0, -1.9, -0.5);
                xWingModel.setupAnim(living, 0, 0, living.tickCount, 0, 0);
                xWingModel.renderToBuffer(poseStack, bufferSource.getBuffer(RenderType.entityCutoutNoCull(getGliderTexture(stack))), p_117351_, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
            } else {

                // Translate and render base glider
                poseStack.translate(0, -1.9, 0);
                gliderModel.setupAnim(living, 0, 0, living.tickCount, 0, 0);
                gliderModel.renderToBuffer(poseStack, bufferSource.getBuffer(RenderType.entityCutoutNoCull(getGliderTexture(stack))), p_117351_, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);

                // Has Coppered Embedded
                if (ParagliderItem.hasCopperMod(stack)) {
                    gliderModel.setupAnim(living, 0, 0, living.tickCount, 0, 0);
                    gliderModel.renderToBuffer(poseStack, bufferSource.getBuffer(RenderType.eyes(ParagliderItem.hasBeenStruck(stack) ? COPPER_EMBED_CHARGED : COPPER_EMBED)), p_117351_, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
                }
            }
            poseStack.popPose();
        }


        
 /*      // Render on players back
        if (GliderUtil.hasParagliderEquipped(living)) {
            ItemStack stack = living.getItemBySlot(EquipmentSlot.CHEST);
            poseStack.pushPose();
            poseStack.mulPose(Vector3f.XP.rotationDegrees(-90));
            poseStack.translate(0, -0.8, 0.5);
            poseStack.scale(0.4F, 0.4F,0.4F);
            //gliderModel.handles.visible = false;
            gliderModel.renderToBuffer(poseStack, p_117350_.getBuffer(RenderType.entityCutoutNoCull(getGliderTexture(stack))), p_117351_, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
            poseStack.popPose();
        }*/
    }

}
