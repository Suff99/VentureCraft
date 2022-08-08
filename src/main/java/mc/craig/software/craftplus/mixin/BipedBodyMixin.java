package mc.craig.software.craftplus.mixin;

import mc.craig.software.craftplus.client.Animations;
import mc.craig.software.craftplus.common.capability.ModCapability;
import mc.craig.software.craftplus.util.AnimationUtil;
import mc.craig.software.craftplus.util.GliderUtil;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidModel.class)
public class BipedBodyMixin {

    @Inject(at = @At("HEAD"), cancellable = true, method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V")
    private void setupAnimHead(LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo callbackInfo) {
        HumanoidModel<?> bipedModel = (HumanoidModel<?>) (Object) this;

       bipedModel.head.getAllParts().forEach(ModelPart::resetPose);
       bipedModel.body.getAllParts().forEach(ModelPart::resetPose);
       bipedModel.leftArm.getAllParts().forEach(ModelPart::resetPose);
       bipedModel.rightArm.getAllParts().forEach(ModelPart::resetPose);
       bipedModel.leftLeg.getAllParts().forEach(ModelPart::resetPose);
       bipedModel.rightLeg.getAllParts().forEach(ModelPart::resetPose);

        ModCapability.get(livingEntity).ifPresent(iCap -> {
            // Gliding Animation
            if (GliderUtil.isGlidingWithActiveGlider(livingEntity)) {
                AnimationUtil.animate(bipedModel, iCap.getAnimation(ModCapability.AnimationStates.GLIDING), Animations.GLIDING, ageInTicks, 1);
                fixLayers(bipedModel);
                callbackInfo.cancel();
            }

            // Falling Animation
            if (livingEntity instanceof Player player) {
                ModCapability.get(player).ifPresent(iCap1 -> {
                    if (iCap1.isFalling()) {
                        AnimationUtil.animate(bipedModel, iCap.getAnimation(ModCapability.AnimationStates.FALLING), Animations.FALLING, ageInTicks, 1);
                        fixLayers(bipedModel);
                        callbackInfo.cancel();
                    }
                });
            }
        });
    }

    private void fixLayers(HumanoidModel<?> bipedModel) {
        if (bipedModel instanceof PlayerModel<?> playerModel) {
            playerModel.jacket.copyFrom(bipedModel.body);
            playerModel.leftPants.copyFrom(bipedModel.leftLeg);
            playerModel.rightPants.copyFrom(bipedModel.rightLeg);
            playerModel.leftSleeve.copyFrom(bipedModel.leftArm);
            playerModel.rightSleeve.copyFrom(bipedModel.rightArm);
            playerModel.hat.copyFrom(bipedModel.head);
        }
    }

/*    @Inject(at = @At("TAIL"), method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V")
    private void setupAnimTail(LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo callbackInfo) {
        HumanoidModel<LivingEntity> bipedModel = (HumanoidModel) (Object) this;
        AnimationHandler.setupAnim(livingEntity, bipedModel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        //AnimationUtil.animate(bipedModel, CommonEvents.glideAnimation, Animations.GLIDING, ageInTicks, 1);
        bipedModel.hat.copyFrom(bipedModel.head);
    }*/


}