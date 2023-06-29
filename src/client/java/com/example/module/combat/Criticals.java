package com.example.module.combat;

import com.example.module.Mod;
import com.example.module.settings.ModeSetting;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

public class Criticals extends Mod {

    private ModeSetting mode = new ModeSetting("Mode", "Packet", "Packet", "MiniJump", "Jump");

    public Criticals() {
        super("Criticals", "Automatically crits when attacking", Category.COMBAT);
        addSetting(mode);
    }



    @Override
    public void onLeftClick(){

        if(mc.player.isInLava() || mc.player.isInsideWaterOrBubbleColumn() || mc.player.isTouchingWater() || mc.player.isFallFlying() || mc.player.hasVehicle() || mc.player.isSneaking() || mc.player.isInSneakingPose() || mc.player.isInSwimmingPose() || mc.player.isInSneakingPose() || mc.player.isInSwimmingPose() || mc.player.isInSneakingPose() || mc.player.isInSwimmingPose() || mc.player.isInSneakingPose() || mc.player.isInSwimmingPose() || mc.player.isInSneakingPose() || mc.player.isInSwimmingPose() || mc.player.isInSneakingPose() || mc.player.isInSwimmingPose() || mc.player.isInSneakingPose() || mc.player.isInSwimmingPose() || mc.player.isInSneakingPose() || mc.player.isInSwimmingPose()){

            return;

        }

        //check if the players crosshair is facing an entity
        if(mc.crosshairTarget == null
                || mc.crosshairTarget.getType() != HitResult.Type.ENTITY
                || !(((EntityHitResult)mc.crosshairTarget)
                .getEntity() instanceof LivingEntity))
            return;


        switch (mode.getMode()){
            case "Packet":
                performPacketJump();
            case "MiniJump":
                performMiniJump();
            case "Jump":
                performJump();
        }





    }


    private void performPacketJump()
    {
        assert mc.player != null;
        double posX = mc.player.getX();
        double posY = mc.player.getY();
        double posZ = mc.player.getZ();

        sendPos(posX, posY + 0.0625D, posZ, true);
        sendPos(posX, posY, posZ, false);
        sendPos(posX, posY + 1.1E-5D, posZ, false);
        sendPos(posX, posY, posZ, false);
    }

    private void sendPos(double x, double y, double z, boolean onGround)
    {
        assert mc.player != null;
        mc.player.networkHandler.sendPacket(
                new PlayerMoveC2SPacket.PositionAndOnGround(x, y, z, onGround));
    }

    private void performMiniJump()
    {
        assert mc.player != null;
        mc.player.addVelocity(0, 0.1, 0);
        mc.player.fallDistance = 0.1F;
        mc.player.setOnGround(false);
    }

    private void performJump()
    {
        assert mc.player != null;
        mc.player.jump();
    }
    
    
}

