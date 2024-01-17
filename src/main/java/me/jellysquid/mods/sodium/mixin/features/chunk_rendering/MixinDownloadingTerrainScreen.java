package me.jellysquid.mods.sodium.mixin.features.chunk_rendering;

import net.minecraft.client.gui.screens.ReceivingLevelScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ReceivingLevelScreen.class)
public class MixinDownloadingTerrainScreen {
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getBlockPos()Lnet/minecraft/core/BlockPos;"), require = 0)
    private BlockPos redirect$getPlayerBlockPosition(LocalPlayer instance) {
        // Ensure the "eye" position (which the chunk rendering code is actually concerned about) is used instead of
        // the "feet" position. This solves a problem where the loading screen can become stuck waiting for the chunk
        // at the player's feet to load, when it is determined to not be visible due to the true location of the
        // player's eyes.
        return new BlockPos(instance.getX(), instance.getEyeY(), instance.getZ());
    }
}