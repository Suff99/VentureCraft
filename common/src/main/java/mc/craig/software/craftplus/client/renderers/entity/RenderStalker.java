package mc.craig.software.craftplus.client.renderers.entity;

import mc.craig.software.craftplus.VentureCraft;
import mc.craig.software.craftplus.client.models.Models;
import mc.craig.software.craftplus.client.models.PlayerModelChanges;
import mc.craig.software.craftplus.common.entities.Stalker;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.resources.ResourceLocation;

public class RenderStalker extends LivingEntityRenderer<Stalker, PlayerModelChanges> {

    public RenderStalker(EntityRendererProvider.Context context) {
        super(context, new PlayerModelChanges(context.bakeLayer(Models.PLAYER)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(Stalker stalker) {
        return new ResourceLocation(VentureCraft.MODID, "textures/entity/stalker/stalker.png");
    }

    @Override
    protected boolean shouldShowName(Stalker stalker) {
        return false;
    }
}
